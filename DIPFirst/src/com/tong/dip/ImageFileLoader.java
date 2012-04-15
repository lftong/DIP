package com.tong.dip;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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
	
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
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
		
	    System.out.println("minX:"+minX);
	    System.out.println("minY:"+minY);
	    System.out.println("width:"+width);
	    System.out.println("height:"+height);
	    System.out.println(fileName);
	    
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
	
}
