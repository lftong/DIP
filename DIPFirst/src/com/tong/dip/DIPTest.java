package com.tong.dip;

import java.io.IOException;

public class DIPTest {

	public static void main(String[] args) throws IOException {
		ImageFileLoader imageFileLoader = new ImageFileLoader();
		imageFileLoader.setFilePath("D:\\Hand.jpg");
		imageFileLoader.load();
		imageFileLoader.computeHisto();
		
		Histogram histogram = new Histogram();
		histogram.setImageFileLoader(imageFileLoader);
		histogram.draw();
		
//		NoiseFilter noiseFilter = new NoiseFilter();
//		noiseFilter.setImageFileLoader(imageFileLoader);
//		noiseFilter.filter();
	}

}
