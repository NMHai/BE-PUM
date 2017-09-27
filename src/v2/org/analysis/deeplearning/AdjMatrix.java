package v2.org.analysis.deeplearning;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

public class AdjMatrix extends Matrix{
	public static int NULL_VALUE = -1; 
	private List<MatrixState> vertices; 
	private List<HashMap<Integer, Color>> edges;
	private int num; 
	
	public AdjMatrix() {
		vertices = new ArrayList<>();
		edges = new ArrayList<>();
		num = 0;
	}
	
	public void insertVertex(MatrixState s) {
		for (MatrixState temp: vertices) {
			if (temp.equal(s)) {
				return;
			}
		}
		
		vertices.add(s);
		edges.add(new HashMap<Integer, Color>());
		num ++;
	}
	
	@Override
	public void insertValue(MatrixState s1, MatrixState s2, Color val) {
		int id1 = -1, id2 = -1;
		int tempId = 0;
		for (MatrixState temp: vertices) {
			if (s1.equal(temp)) {
				id1 = tempId; 
			}
			
			if (s2.equal(temp)) {
				id1 = tempId; 
			}			 
			
			if (id1 != -1 && id2 != -1) {
				break;
			}
			tempId ++; 
		}
		
		if (id1 == -1) {
			vertices.add(s1);
			edges.add(new HashMap<Integer, Color>());
			id1 = num; 
			num ++;
		}
		
		if (id2 == -1) {
			vertices.add(s2);
			edges.add(new HashMap<Integer, Color>());
			id2 = num; 
			num ++;
		}
		
		this.insertValue(id1, id2, val);
	}
	
	private void insertValue(int id1, int id2, Color val) {
		// TODO Auto-generated method stub
		HashMap<Integer, Color> temp = edges.get(id1);
		
		if (temp != null) {
			temp.put(id2, val);
		} else {
			while(true) {
				edges.add(new HashMap<Integer, Color>());
				temp = edges.get(id1);
				
				if (temp != null) {
					temp.put(id2, val);
					return;
				}
			}
		}
		// Remove later, it is for correction
//		System.out.println("(ID1 = " + id1 + ", ID2 = " + id2 + ", Color(" + val.getRed() + ", " + val.getGreen() + ", " + val.getBlue() + ")");
		/////////////////////////////////////
	}

	public MatrixState getState(int index) {
		return vertices.get(index);
	}
	
	public MatrixState getState(String hash) {
		for (MatrixState temp: vertices) {
			if (hash.equals(temp.getHash())) {
				return temp;
			}
		}
		
		return null;
	}
	
	public Color getValue(int x, int y) {
		Map<Integer, Color> temp = edges.get(x);
		if (temp == null) {
			return null; 
		}
		
		if (temp.containsKey(y)) {
			return temp.get(y);
		}
		
		return null; 
	}

	public int getLength() {
		return num;
	}

	private Map getEdge(int i) {
		// TODO Auto-generated method stub
		return edges.get(i);
	}

	@Override
	public void exportImage(String fileName, int size) {
		// TODO Auto-generated method stub
		int numName = 0;
		System.out.println("Size of Matrix:" + getLength() + "x" + getLength() + " , max size for image:" + size);
		try {
			BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_BYTE_INDEXED);
			int xS = 0, yS = 0;
			for (int i = 0; i < getLength(); i++) {
				Map <Integer, Integer> t = getEdge(i);
				if (t == null) {
					continue;
				}
				for (int j = 0; j < getLength(); j++) {					
					if (t.containsKey(j)) {
						Color c = getValue(i, j);
						// c = new Color(0, 0, 0);
						
						// Remove later, it is for presentation
//						if (c.getRed() == 0 && c.getGreen() == 0 && c.getBlue() == 0) {
//							c = new Color(255, 255, 255);
//						}
						///////////////////////////////////////
						
						image.setRGB(yS, xS, c.getRGB());
						yS++;
						if (yS >= size) {
							xS++;
							yS = 0;
							if (xS >= size) {
								File output = new File(fileName + "_" + numName + ".png");
								ImageIO.write(image, "png", output);
								numName ++;
								xS = 0;
								yS = 0;
							}
						}
					}
				}
			}

			File output = new File(fileName + "_" + numName + ".png");
			ImageIO.write(image, "png", output);
//			numName ++;
		}

		catch (Exception e) {
			System.out.println(e.toString());
		}

	}
	
	@Override
	public void exportImage(String fileName) {
		// TODO Auto-generated method stub
		System.out.println("Size of Image:" + getLength() + "x" + getLength());
		try {
			BufferedImage image = new BufferedImage(getLength(), getLength(),
					BufferedImage.TYPE_BYTE_INDEXED);
			for (int i = 0; i < getLength(); i++) {
				for (int j = 0; j < getLength(); j++) {
					Color c = getValue(i, j);
					if (c == null) {
						c = new Color(0, 0, 0);
					}

					image.setRGB(j, i, c.getRGB());
				}
			}
			File output = new File(fileName + ".jpg");
			ImageIO.write(image, "jpg", output);
		}

		catch (Exception e) {
			System.out.println(e.toString());
		}

	}
}
