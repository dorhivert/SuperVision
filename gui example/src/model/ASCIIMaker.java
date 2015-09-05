package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

// source at: http://www.katharinasabel.de/java-ascii-art-generator/
public class ASCIIMaker {


	private BufferedImage image;
	private double pixelValue;
	private OutputStream printer;
	
	int imageSize;

	public FileInputStream filenameToInputStream(String fileName)
	{
		try {
			FileInputStream  inputStream = new FileInputStream(fileName);
			if(inputStream!=null)
				return inputStream;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	

	/**
	 * converts an image to ASCII characters 
	 * @param im - an input stream for the image source
	 * @param out - an output stream for the ascii result
	 */
	public void convertToAscii(InputStream im,OutputStream out) {
		printer=out;
		try {
			image = ImageIO.read(im);            
		} catch (IOException e) {
		}

		imageSize = image.getHeight() * image.getWidth();

		for (int i = 0; i < image.getHeight(); i++) {


			for (int j = 0; j < image.getWidth(); j++) {
				Color pixelColor = new Color(image.getRGB(j, i));
				pixelValue = (((pixelColor.getRed() * 0.30)
						+ (pixelColor.getBlue() * 0.59) + (pixelColor
								.getGreen() * 0.11)));
				print(strChar(pixelValue));
			}
			try {                
				printer.write((int)'\n');
				printer.flush();
			}
			catch (Exception ex) 
			{

			}
		}

	}

	/** The specified symbols that will be used for the Art */
	public String strChar(double d) {
		String string = " ";
		if (d >= 240) {
			string = " ";
		} else if (d >= 210) {
			string = ".";
		} else if (d >= 190) {
			string = "*";
		} else if (d >= 170) {
			string = "+";
		} else if (d >= 120) {
			string = "^";
		} else if (d >= 110) {
			string = "&";
		} else if (d >= 80) {
			string = "8";
		} else if (d >= 60) {
			string = "#";
		} else {
			string = "@";
		}
		return string;
	}

	public void print(String str) {
		try {
			printer.write(str.getBytes());
			printer.flush();

			//printPercent();

		} catch (Exception ex) {
			System.out.println("FATAL ERROR!");
			return;
		}
	}

}