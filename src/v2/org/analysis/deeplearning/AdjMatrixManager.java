package v2.org.analysis.deeplearning;

import java.awt.Color;
import java.util.List;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Flag;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.memory.MemoryV2;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.BooleanValue;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class AdjMatrixManager {
	private static volatile AdjMatrixManager mInstance = null;
	public static int MAX_SIZE = 500;
	private boolean exportImg = false;
	private Matrix matrix = null;

	public void setExportImg(boolean t) {
		exportImg = t;
	}
	
	public boolean isExportImg() {
		return exportImg;
	}

	/**
	 * Get singleton instance of this class
	 * 
	 * @return {@link AdjMatrixManager} instance
	 */

	public static synchronized AdjMatrixManager getInstance() {
		if (mInstance == null) {
			try {
				mInstance = new AdjMatrixManager();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mInstance;
	}

	private AdjMatrixManager() {
		matrix = new LineMatrix();
	}

	public void exportImage(String fileName) {
		// TODO Auto-generated method stub
		if (!exportImg) {
			return;
		}
		
		matrix.exportImage(fileName);

	}

	public void exportImage(String fileName, int size) {
		// TODO Auto-generated method stub
		if (!exportImg) {
			return;
		}
		
		matrix.exportImage(fileName, size);

	}

	public void updateMatrix(DLState oldState, BPState curState) {
		// TODO Auto-generated method stub
		if (!exportImg) {
			return;
		}
		MatrixState s1 = new MatrixState(oldState.getLocation(), oldState.getInst(), oldState.getReg().toHashString());
		MatrixState s2 = new MatrixState(curState.getLocation(), curState.getInstruction(), curState.getEnvironement()
				.getRegister().toHashString());
		Color val = compareEnvironment(oldState, curState.getEnvironement());
		// Remove later, it is for correction
//		System.out.print("Addr=" + s2.getAddr() + ",\t");
		/////////////////////////////////////
		matrix.insertValue(s1, s2, val);
	}

	private Color compareEnvironment(DLState env1, Environment env2) {
		// TODO Auto-generated method stub
		int red = subtractRegister(env1.getReg(), env2.getRegister());
		int green = subtractFlag(env1.getFlag(), env2.getFlag());
		long ret = env2.getMemory().getDiff();
		int blue = (int) Math.abs((ret) % 255);
		return new Color(red, green, blue);
	}

	private int subtractMemory(MemoryV2 m1, MemoryV2 m2) {
		// TODO Auto-generated method stub
		long ret = 0;
		List<Long> temp = m2.getChangedList();
		// int num = 1;
		for (int i = 0; i < temp.size(); i++) {
			long addr = temp.get(i);

			Value t2 = m2.getByteMemoryValue(addr);
			Value t1 = m1.getByteMemoryValue(addr);
			if (t1 == null || t2 == null) {
				return 255;
			}

			Value t = t2.subFunction(t1);
			if (t != null && t instanceof LongValue) {
				// num ++;
				ret += Math.abs(((LongValue) t).getValue());
			} else {
				return 255;
			}
		}

		// return (int) Math.abs((ret/num) % 255);
		return (int) Math.abs((ret) % 255);
	}

	private int subtractFlag(Flag flag1, Flag flag2) {
		// TODO Auto-generated method stub
		int ret = 0;
		Value temp;
		temp = flag2.getAFlag().subFunction(flag1.getAFlag());
		if (!(temp instanceof BooleanValue)) {
			return 255;
		}
		ret += (((BooleanValue) temp).getValue() ? 1 : 0);

		temp = flag2.getCFlag().subFunction(flag1.getCFlag());
		if (!(temp instanceof BooleanValue)) {
			return 255;
		}
		ret += (((BooleanValue) temp).getValue() ? 1 : 0);

		temp = flag2.getPFlag().subFunction(flag1.getPFlag());
		if (!(temp instanceof BooleanValue)) {
			return 255;
		}
		ret += (((BooleanValue) temp).getValue() ? 1 : 0);

		temp = flag2.getZFlag().subFunction(flag1.getZFlag());
		if (!(temp instanceof BooleanValue)) {
			return 255;
		}
		ret += (((BooleanValue) temp).getValue() ? 1 : 0);

		temp = flag2.getSFlag().subFunction(flag1.getSFlag());
		if (!(temp instanceof BooleanValue)) {
			return 255;
		}
		ret += (((BooleanValue) temp).getValue() ? 1 : 0);

		temp = flag2.getTFlag().subFunction(flag1.getTFlag());
		if (!(temp instanceof BooleanValue)) {
			return 255;
		}
		ret += (((BooleanValue) temp).getValue() ? 1 : 0);

		temp = flag2.getIFlag().subFunction(flag1.getIFlag());
		if (!(temp instanceof BooleanValue)) {
			return 255;
		}
		ret += (((BooleanValue) temp).getValue() ? 1 : 0);

		temp = flag2.getDFlag().subFunction(flag1.getDFlag());
		if (!(temp instanceof BooleanValue)) {
			return 255;
		}
		ret += (((BooleanValue) temp).getValue() ? 1 : 0);

		temp = flag2.getOFlag().subFunction(flag1.getOFlag());
		if (!(temp instanceof BooleanValue)) {
			return 255;
		}
		ret += (((BooleanValue) temp).getValue() ? 1 : 0);

		return (ret / 2) % 255;
	}

	private int subtractRegister(Register reg1, Register reg2) {
		// TODO Auto-generated method stub
		long ret = 0;
		Value temp;
		temp = reg2.getRegisterValue("eax").subFunction(reg1.getRegisterValue("eax"));
		if (!(temp instanceof LongValue)) {
			return 255;
		}
		ret += Math.abs(((LongValue) temp).getValue());

		temp = reg2.getRegisterValue("ebx").subFunction(reg1.getRegisterValue("ebx"));
		if (!(temp instanceof LongValue)) {
			return 255;
		}
		ret += Math.abs(((LongValue) temp).getValue());

		temp = reg2.getRegisterValue("ecx").subFunction(reg1.getRegisterValue("ecx"));
		if (!(temp instanceof LongValue)) {
			return 255;
		}
		ret += Math.abs(((LongValue) temp).getValue());

		temp = reg2.getRegisterValue("edx").subFunction(reg1.getRegisterValue("edx"));
		if (!(temp instanceof LongValue)) {
			return 255;
		}
		ret += Math.abs(((LongValue) temp).getValue());

		temp = reg2.getRegisterValue("esi").subFunction(reg1.getRegisterValue("esi"));
		if (!(temp instanceof LongValue)) {
			return 255;
		}
		ret += Math.abs(((LongValue) temp).getValue());

		temp = reg2.getRegisterValue("edi").subFunction(reg1.getRegisterValue("edi"));
		if (!(temp instanceof LongValue)) {
			return 255;
		}
		ret += Math.abs(((LongValue) temp).getValue());

		temp = reg2.getRegisterValue("esp").subFunction(reg1.getRegisterValue("esp"));
		if (!(temp instanceof LongValue)) {
			return 255;
		}
		ret += Math.abs(((LongValue) temp).getValue());

		temp = reg2.getRegisterValue("ebp").subFunction(reg1.getRegisterValue("ebp"));
		if (!(temp instanceof LongValue)) {
			return 255;
		}

		ret += Math.abs(((LongValue) temp).getValue());

		return Math.abs((int) (ret / 8) % 255);
	}
}
