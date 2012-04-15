package com.tong.dip;

import java.io.IOException;

public class DIPTest {

	public static void main(String[] args) throws IOException {
		ImageFileLoader imageFileLoader = new ImageFileLoader();
		imageFileLoader.setFilePath("E:\\workspace\\DIPproject\\hand.jpg");
		imageFileLoader.load();
	}

}
