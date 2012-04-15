package com.tong.dip;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Histogram {
	private ImageFileLoader imageFileLoader = null;

	public void setImageFileLoader(ImageFileLoader imageFileLoader) {
		this.imageFileLoader = imageFileLoader;
	}

	public void draw(){
		int[] histo = imageFileLoader.getHisto();
		DefaultCategoryDataset dataset=new DefaultCategoryDataset();
		for(int i=0;i<256;i++){
			dataset.addValue(histo[i], "",i+"");
		}
		JFreeChart chart = ChartFactory.createBarChart("Dip", "Pix", "#", dataset, PlotOrientation.VERTICAL, false, true,false);
		String graphPath;
	    FileOutputStream fos;
	    graphPath = "D:/histo_"+imageFileLoader.getFileName();

	    try {
	    	fos = new FileOutputStream(graphPath);
	         //用ChartUtilities工具输出  
		    ChartUtilities.writeChartAsJPEG(fos, chart, 800, 400);
	        fos.close();  
	    } catch (FileNotFoundException e) {
	    	e.printStackTrace();
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	}
	
	
	public void histogramEqualization()
	{
		int[] histo = imageFileLoader.getHisto();
		int width = imageFileLoader.getWidth();
		int height = imageFileLoader.getHeight();
		double p[] =new double[256];
		double c[]=new double[256];
		int n= width*height;
		int[][] newPix=new int[width][height];
		int[][] pix = imageFileLoader.getPixels2D();
		for(int i=0;i<256;i++){
			p[i]=(double)histo[i]/n;
		}
			
		for(int i=0;i<256;i++){
			for(int j=0;j<i;j++){
				c[i]+=p[j];
			}
		}

        for(int i=0;i<width;i++){
        	for(int j=0;j<height;j++){
        		newPix[i][j]=(int) (255*c[pix[i][j]]);
        	}
        }


    	try {
			imageFileLoader.reconstructor(newPix, width, height, imageFileLoader.getFileName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
