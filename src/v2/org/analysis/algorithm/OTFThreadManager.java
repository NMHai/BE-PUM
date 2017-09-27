/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.algorithm
 * File name: OTFThreadManager.java
 * Created date: Dec 3, 2015
 * Description:
 */
package v2.org.analysis.algorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jakstab.Program;

import v2.org.analysis.algorithm.OTFModelGeneration.OTFThread;
import v2.org.analysis.interactive.AnalysisModel;
import v2.org.analysis.interactive.IInteractive;
import v2.org.analysis.log.BPLogger;
//import v2.org.analysis.algorithm.OTFModelGeneration.OTFThread;
import v2.org.analysis.path.BPPath;
import v2.org.analysis.path.BPState;

/**
 * @author Yen Nguyen
 *
 */
public class OTFThreadManager {

	private boolean isMultiThread = false, isBusy = false;
	private static final boolean isChecksum = true;
	private static final int DEFAULT_NUMBER_CORE = 2;
//	private static final int NUMBER_OF_CORE = (IS_MULTI_THREAD) ? (Runtime.getRuntime().availableProcessors()) : 1;
	private int numberCore = 1, numConflict = 0;
	private long totalWaitingTime = 0;
	
	public int getNumberCore() {
		return numberCore;
	}

	public void setNumberCore(int numberCore) {
		this.numberCore = numberCore;
	}

	public boolean isMultiThread() {
		return isMultiThread;
	}

	public void setMultiThread(boolean isThread) {
		this.isMultiThread = isThread;
		int t = (Runtime.getRuntime().availableProcessors() >= 2) ? Runtime.getRuntime().availableProcessors(): DEFAULT_NUMBER_CORE;			
		numberCore = (isMultiThread) ? t : 1;
//		numberCore = 2;
	}

	public static int DEFAULT_MAX_SIZE_THREAD_BUFFER = 150;

	/**
	 * Singleton instance of {@link OTFThreadManager} class
	 */
	private static volatile OTFThreadManager mInstance = null;
	
	private OTFModelGeneration mOtfModelGeneration = null;
	private Set<String> globalStateBuffer = null;
	private int maxBufferSize;

	/**
	 * The constructor of {@link OTFThreadManager} class. This method just be
	 * called if only the caller is getIntance.
	 * 
	 * @throws Exception
	 *             will be thrown if the caller is not getInstance
	 * 
	 */
	public OTFThreadManager() throws Exception {
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		maxBufferSize = DEFAULT_MAX_SIZE_THREAD_BUFFER;
		numberCore = (isMultiThread) ? 5 : 1;
//		numberCore = 2;
		boolean isRightAccess = false;
		if (stackTraceElements.length >= 3) {
			String className = OTFThreadManager.class.getName();
			if (stackTraceElements[1].getClassName().equals(className)
					&& stackTraceElements[1].getMethodName().equals("<init>")
					&& stackTraceElements[2].getClassName().equals(className)
					&& stackTraceElements[2].getMethodName().equals("getInstance")) {
				isRightAccess = true;
			}
		}

		/**
		 * Check if user violates singleton design pattern
		 */
		if (!isRightAccess) {
			throw new Exception(
					"Violate Singleton Design Pattern! Please call getInstance static method of this class to get the instance!");
		}

		if (isChecksum) {
			globalStateBuffer = new HashSet<String>();
		}
	}

	/**
	 * Get singleton instance of this class
	 * 
	 * @return {@link OTFThreadManager} instance
	 */
	public static synchronized OTFThreadManager getInstance() {
		if (mInstance == null) {
			try {
				mInstance = new OTFThreadManager();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mInstance;
	}
	
	public synchronized void setOTFModelGeneration(OTFModelGeneration pOtfModelGeneration) {
		mOtfModelGeneration = pOtfModelGeneration;
	}
	
	public OTFModelGeneration getOtfModelGeneration() {
		return mOtfModelGeneration;
	}

	private int mNumberOfCurrentThreads = 0;

	/**
	 * Inform the manager that you want to start a new OTFThread. This method
	 * will return a number of current threads (after having started) if this
	 * number is smaller than a number of cores of processor; otherwise it will
	 * throw an exception.
	 * 
	 * @return Number of current threads after having started
	 * 
	 * @throws Exception
	 *             If number of current thread is larger than number of cores.
	 */
	private synchronized int startNewThread() throws Exception {
		synchronized (this) {
			if (this.isCanStart()) {
				this.mNumberOfCurrentThreads++;
			} else {
				throw new Exception("Reached to a maximum of cores!");
			}
		}
		return this.mNumberOfCurrentThreads;
	}

	/**
	 * Inform the manager that you want to finish an OTFThread. This method will
	 * return a number of current threads (after having finished). If this
	 * number is equal to zero, it will let the main thread (running algorithm)
	 * resume.
	 * 
	 * @return Number of current threads after having finished
	 * 
	 * @throws Exception
	 *             If number of current thread is smaller than zero.
	 */
	private synchronized int finishThread() throws Exception {
		synchronized (this) {
			if (this.mNumberOfCurrentThreads > 0) {
				this.mNumberOfCurrentThreads--;

				// Notify waiting main thread to resume
				if (this.mNumberOfCurrentThreads == 0) {
					synchronized (this) {
						this.notifyAll();
						BPLogger.debugLogger.info("************************ END ***************************");
					}
				}
			} else {
				throw new Exception("There is NO thread to finish!");
			}
		}
		return this.mNumberOfCurrentThreads;
	}

	public synchronized boolean isCanStart() {
		// System.out.println(String.format("%d-%d",
		// this.mNumberOfCurrentThreads, this.mNumberOfCore));
		if (this.mNumberOfCurrentThreads < OTFThreadManager.getInstance().getNumberCore()) {
			return true;
		} else {
			return false;
		}
	}

	public synchronized void check(OTFModelGeneration otfModelGeneration, List<BPPath> pathList) throws Exception {
		if (otfModelGeneration == null || pathList == null) {
			BPLogger.debugLogger.error(String.format("[OTFThreadManager] NULL INPUT - otf:%s,pathList:%s",
					otfModelGeneration, pathList));
		}
		if (pathList != null && pathList.size() > 0) {
			BPLogger.debugLogger.info(String.format("[OTFThreadManager] check pathListSize:%d", pathList.size()));

			if (this.isCanStart()) {
				BPPath path = pathList.remove(pathList.size() - 1);

				OTFThread thread = otfModelGeneration.new OTFThread(path);
				otfModelGeneration._curThread = thread;
				IInteractive analysis = AnalysisModel.getInstance().createAnalysis(Program.getProgram().getHashFile());
				AnalysisModel.getInstance().setAnalysis(analysis);
				thread.setInteractive(analysis);
				thread.start();
				BPLogger.debugLogger.info(String
						.format("[OTFThreadManager] START location:%s,numOfCurrentThreads:%d/%d", path
								.getCurrentState().getLocation().toString(), this.mNumberOfCurrentThreads,
								OTFThreadManager.getInstance().getNumberCore()));
			}
		}
	}

	/**
	 * Add all checksum string elements in processed state buffer set of child
	 * thread to this manager.
	 * 
	 * @param pProcessedSet
	 *            The temporary set holding a part processed states of child
	 *            thread.
	 */
	protected synchronized void addProcessedStates(Collection<? extends String> pProcessedSet) {
		globalStateBuffer.addAll(pProcessedSet);
		pProcessedSet.clear();
	}

	protected synchronized boolean isProcessed(String pChecksum) {
		if (globalStateBuffer != null) {
			if (globalStateBuffer.contains(pChecksum)) {
				return true;
			}
		}
		return false;
	}

	public int getSizeThreadBuffer() {
		return maxBufferSize;
	}

	public void setSizeThreadBuffer(int maxSize) {
		maxBufferSize = maxSize;
	}

	public boolean isBusy() {
		return isBusy;
	}
	
	public synchronized void setBusy(boolean isBusy) {
		this.isBusy = isBusy;
	}

	public int getNumConflict() {
		return numConflict;
	}

	public synchronized void addNumConflict(int i) {
		this.numConflict += i;
	}

	public long getTotalWaitingTime() {
		return totalWaitingTime;
	}

	public synchronized void addTotalWaitingTime(long totalWaitingTime) {
		this.totalWaitingTime += totalWaitingTime;
	}

	/**
	 * 
	 * @author Yen Nguyen
	 *
	 */
	static public abstract class OTFThreadBase extends Thread {
		protected String id = null;
		private OTFThreadManager mOTFThreadManager = OTFThreadManager.getInstance();
		private List<String> localStateBuffer = null;

		/**
		 * Check if the state input was processed by another thread.
		 * 
		 * @param pBPState
		 *            Processed stated of current thread
		 * 
		 * @return {@code TRUE} if processed, {@code FALSE} otherwise.
		 */
		public boolean isStopCurrentPath(BPState pBPState) {
			if (mOTFThreadManager.mNumberOfCurrentThreads > 1 && 
					isChecksum && pBPState != null && pBPState.getLocation() != null) {
				if (localStateBuffer == null) {
					localStateBuffer = new ArrayList<String>(mOTFThreadManager.getSizeThreadBuffer());
				}

				String location = pBPState.getLocation().toString();
				String checksum = pBPState.getEnvironement().getHashString();
				String combinedChecksum = String.format("%s_%s", location, checksum);
				localStateBuffer.add(combinedChecksum);
				
				if (localStateBuffer.size() < mOTFThreadManager.getSizeThreadBuffer()) {
					return false;
				}
//				System.out.println("Checking " + this);
				boolean isProcessed = mOTFThreadManager.isProcessed(combinedChecksum);
				
				if (isProcessed || localStateBuffer.size() == mOTFThreadManager.getSizeThreadBuffer()) {
					// Detect if there is conflict and count the waiting time
					if (mOTFThreadManager.isBusy()) {
						mOTFThreadManager.addNumConflict(1);
					}
					long startTime = System.currentTimeMillis();
					while (mOTFThreadManager.isBusy()) {
						;
					}
					
					mOTFThreadManager.setBusy(true);
					mOTFThreadManager.addTotalWaitingTime(System.currentTimeMillis() - startTime);
					mOTFThreadManager.addProcessedStates(localStateBuffer);
					mOTFThreadManager.setBusy(false);
				}
				
				if (isProcessed) {
					System.out.println("_____________________________________________________________________________");
					System.out.println(String.format("_____________________ INTERRUPT OTF THREAD %s AT %s _______________________", id, location));
					System.out.println("_____________________________________________________________________________");
				}

				return isProcessed;
			}
			return false;
		}

		public abstract void execute();

		private void beforeExecute() {
			try {
				mOTFThreadManager.startNewThread();				
				id = "" + System.currentTimeMillis();
				System.out.println("_____________________________________________________________________________");
				System.out.println(String.format("_____________________ START NEW OTF THREAD %s _______________________", id));
				System.out.println("_____________________________________________________________________________");
			} catch (Exception e) {
				System.out.println();
				System.out.println("				**** Reached to a maximum of cores! ****");
				System.out.println();
				e.printStackTrace();
			}
//			id = "" + System.currentTimeMillis();
//			System.out.println("_____________________________________________________________________________");
//			System.out.println(String.format("_____________________ START NEW OTF THREAD %s AT Fs_______________________", id));
//			System.out.println("_____________________________________________________________________________");
		}

		private void afterExecute() {
			try {
				mOTFThreadManager.finishThread();
				
				if (localStateBuffer != null && localStateBuffer.size() > 0) {
					mOTFThreadManager.addProcessedStates(localStateBuffer);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void afterLoop(OTFModelGeneration otfModelGeneration, List<BPPath> pathList) {
			try {
				mOTFThreadManager.check(otfModelGeneration, pathList);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			this.beforeExecute();
			this.execute();
			this.afterExecute();
		}
	}

}