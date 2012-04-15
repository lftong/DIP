package com.tong.dip;


public class NoiseFilter {
	ImageFileLoader imageFileLoader = null;
	
	public void setImageFileLoader(ImageFileLoader imageFileLoader) {
		this.imageFileLoader = imageFileLoader;
	}


	public void	filter()
	{
		int[][] mode=new int[3][3];
		int midValue=0;
		int [][] originalPixels=null;
		String filename;
		int width;
		int height;
		originalPixels=imageFileLoader.getPixels2D();

		width=imageFileLoader.getWidth();
		height=imageFileLoader.getHeight();
		filename="new"+imageFileLoader.getFileName();
		int [][] newPixels=originalPixels;
		for(int i=1;i<width-1;i++)
		{
			for(int j=1;j<height-1;j++)
			{
				mode[0][0]=originalPixels[i-1][j-1];
				mode[0][1]=originalPixels[i-1][j];
				mode[0][2]=originalPixels[i-1][j+1];
				mode[1][0]=originalPixels[i][j-1];
				mode[1][1]=originalPixels[i][j];
				mode[1][2]=originalPixels[i][j+1];
				mode[2][0]=originalPixels[i+1][j-1];
				mode[2][1]=originalPixels[i+1][j];
				mode[2][2]=originalPixels[i+1][j+1];
				midValue=midValue(mode);
				newPixels[i][j]=midValue;
			}
			
			
		}
		
		try {
			imageFileLoader.reconstructor(newPixels, width, height, filename);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public int midValue(int[][] mode)
	{
		int[] midVal=new int[3];
		int[] maxVal=new int[3];
		int[] minVal=new int[3];
		int[] secondSort=new int[3];
		int mid=0;
		for(int i=0;i<3;i++){
			int[] inorder=sort(mode[i]);
			maxVal[i]=inorder[0];
			midVal[i]=inorder[1];
			minVal[i]=inorder[2];
		}
		//三个最大值中的最小值
		for(int i=0;i<3;i++){
			int[] Value=sort(maxVal);
			secondSort[0]=Value[2];
		}
		//三个最小值中的最大值
		for(int i=0;i<3;i++){
			int[] Value=sort(minVal);
			secondSort[1]=Value[0];
		}
		//三个中间值中的中间值
		for(int i=0;i<3;i++){
			int[] Value=sort(midVal);
			secondSort[2]=Value[1];
		}
		//中间值
		for(int i=0;i<3;i++){
			int[] Value=sort(secondSort);
	        mid=Value[1];
		}
		return mid;
	}
	
	private int[] sort(int[] mode) {
		int t;
		if(mode[0]<mode[1]){
			t=mode[0];
			mode[0]=mode[1];
			mode[1]=t;
		}
		if(mode[1]<mode[2]){
			t=mode[1];
			mode[1]=mode[2];
			mode[2]=t;
		}
		if(mode[0]<mode[2]){
			t=mode[0];
			mode[0]=mode[2];
			mode[2]=t;
		}
	   return mode;
	}
}
