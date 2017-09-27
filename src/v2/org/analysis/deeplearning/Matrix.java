package v2.org.analysis.deeplearning;

import java.awt.Color;

public abstract class Matrix {
	public abstract void exportImage(String fileName, int size);
	public abstract void exportImage(String fileName);
	public abstract void insertValue(MatrixState s1, MatrixState s2, Color val); 
}
