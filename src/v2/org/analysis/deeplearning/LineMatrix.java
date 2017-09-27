package v2.org.analysis.deeplearning;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class LineMatrix extends Matrix {
	private List<Color> colorList;
	private int numName;

	public LineMatrix() {
		colorList = new ArrayList<>();
		numName = 0;
	}

	@Override
	public void exportImage(String fileName, int size) {
		System.out.println("Size of Line:" + colorList.size() + " , max size for image:" + size);
		while(true) {
			if (colorList.size() > size*size) {
				outputImageAndRemove(fileName, size);
			} else {
				outputImage(fileName, size);
				return;
			}
		}
	}
	
	private void outputImageAndRemove(String fileName, int size) {
		try {
			BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_BYTE_INDEXED);
			int xS = 0, yS = 0;
			while (!colorList.isEmpty()) {
				Color c = colorList.remove(0);
				if (c == null) {
					continue;
				}
				image.setRGB(yS, xS, c.getRGB());
				yS++;

				if (yS >= size) {
					xS++;
					yS = 0;
					if (xS >= size) {
						File output = new File(fileName + "_" + numName + ".png");
						ImageIO.write(image, "png", output);
						numName++;
						return;
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	private void outputImage(String fileName, int size) {
		int xS = 0, yS = 0;
		try {
			BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_BYTE_INDEXED);
			for (Color c : colorList) {
				if (c == null) {
					continue;
				}

				image.setRGB(yS, xS, c.getRGB());
				yS++;
				
				if (yS >= size) {
					xS++;
					yS = 0;					
				}
			}
			File output = new File(fileName + "_" + numName + ".png");
			ImageIO.write(image, "png", output);
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	@Override
	public void exportImage(String fileName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertValue(MatrixState s1, MatrixState s2, Color val) {
		// TODO Auto-generated method stub
		colorList.add(val);
//		System.out.println(", Color(" + val.getRed() + ", " + val.getGreen() + ", " + val.getBlue() + ")");
	}

}
