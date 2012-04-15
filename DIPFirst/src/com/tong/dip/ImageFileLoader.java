package com.tong.dip;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

/**
 * @author tong
 * class ImageFileLoader
 * load the image file
 */
public class ImageFileLoader {
	private String filePath;
	private String fileName;
	private int minX;
	private int minY;
	private int width;
	private int height;
	private int[][] pixels2D;
	private int[] histo = new int[256];
	
	public int[] getHisto() {
		return histo;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int[][] getPixels2D() {
		return pixels2D;
	}

	public String getFileName() {
		return fileName;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}


	public void load() throws IOException{
		BufferedImage bufferedImage=null;
		int r, g, b;	
        File file=new File(filePath);
        Color color;
        
        fileName=file.getName();
		bufferedImage=ImageIO.read(file);
		minX=bufferedImage.getMinX();
		minY=bufferedImage.getMinY();
		width=bufferedImage.getWidth();
		height=bufferedImage.getHeight();
		pixels2D= new int[width][height];
	    
		for(int i=minX;i<width;i++){
			for(int j=minY;j<height;j++){
				int pixel=bufferedImage.getRGB(i, j);
				color=new Color(pixel);
				r=color.getBlue();
				g=color.getGreen();
				b=color.getRed();
				pixels2D[i][j]=(r+g+b)/3;
			}
		}
	}
	
	public void computeHisto() {
		for(int i=minX;i<width;i++){
			for(int j=minY;j<height;j++){
				histo[pixels2D[i][j]]++;
			}	
		}
//		for(int i=0; i<256; i++)
//			System.out.println(histo[i]);
	}
	
	public void reconstructor(int gray[][],int width,int height,String filename ) throws Exception
	{
		BufferedImage biout=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = (Graphics2D)biout.getGraphics();
		for(int i=0;i<width;i++)
        {
        	for(int j=0;j<height;j++)
        	{
        		Color c = new Color(gray[i][j],gray[i][j],gray[i][j],255);
       	        g2.setColor(c);    	     
       	        g2.drawLine(i, j, i + 1, j + 1);
        	}
        }
		String outputFile="D:/"+filename;
        FileOutputStream outfile = new FileOutputStream(outputFile);
        ImageOutputStream g=ImageIO.createImageOutputStream(outfile);
        ImageIO.write(biout, "JPG",g);
	}
}
