/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.msvcrt.functions
 * File name: fopen.java
 * Created date: Sep 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import java.io.File;
import java.io.IOException;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTDLL;
import v2.org.analysis.statistics.FileProcess;
import v2.org.analysis.system.Storage;
import v2.org.analysis.value.LongValue;

/**
 * Open a file.
 * int _open( const char *filename, int oflag [, int pmode] );
 * 
 * @author HaiNM
 *
 */
public class _open extends MSVCRTAPI {

	public _open() {
		super();
		NUM_OF_PARMS = 2;
		IS_POP_STACK_VALUE = false;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		String filename = memory.getText(this, t1);
		int oflag = (int) t2;
		
		String filename1 = Storage.getMappingPath(filename);
		// Check file exit. 
		File f1 = new File(filename1);
		if (!f1.isFile()) {
			System.out.println(f1.getAbsolutePath() + " does not exists.");
			File f2 = new File(filename);
			if (f2.isFile()) {
				// Copy File 
				try {
					System.out.println("Copy from " + f2.getAbsolutePath() + " to " + f1.getAbsolutePath());
					FileProcess.copyFileUsingStream(f2, f1);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				System.out.println(f2.getAbsolutePath() + " does not exists.");
			}
		} else {
			System.out.println(f1.getAbsolutePath() + " exists.");
		}
		
		System.out.println(String.format("Path: %s, Oflag: %d", filename1, oflag));

		int ret = MSVCRTDLL.INSTANCE._open(filename1, oflag);		
		register.mov("eax", new LongValue(ret));		
	}
	
	
}
