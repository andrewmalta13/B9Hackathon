package com.example.book;

import android.graphics.Bitmap;
import android.graphics.Color;


public class ImageStats {

	public static double getStats(String url)
	{
		Bitmap image=ImageDecoder.getBitmapFromURL(url);
		
		int[] xlocs={90,150,210,270,330};
		int yloc=248;

		int total=0;
		int num=0;
		for(int i=1; i<=5;i++) {
			int	bg=0;
			int barheight=-1;
			while (bg <= 10) {
				barheight+=1;
				int b=Color.blue(image.getPixel(xlocs[i-1], yloc-barheight));
				int g=Color.green(image.getPixel(xlocs[i-1], yloc-barheight));
				bg=b+g;
			}
			total+=barheight*i;
			num+=barheight;
		}
		return (total/(float)num);
	}
	public static void main(String[] args)
	{
		System.out.println(getStats("http://i60.tinypic.com/qwxgdk.jpg"));
	}
}
