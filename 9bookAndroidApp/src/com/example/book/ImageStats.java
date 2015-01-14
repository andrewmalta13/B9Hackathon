package com.example.book;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;


public class ImageStats extends AsyncTask<Void, Void, Double> {
	private int ociNum;
	private int semesterNum;
	
	public ImageStats(int ociNum, int semesterNum){
		this.ociNum=ociNum;
		this.semesterNum=semesterNum;
	}
	
    public static double[] getStats(int ociNum, int semesterNum)
    {
    	try {
    	URL url = new URL(URLgenerator.generateRecUrl(ociNum, semesterNum));
    	HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    	connection.setDoInput(true);
    	connection.connect();

    	double rec1 = getStats(URLgenerator.generateEvalUrl1(ociNum, semesterNum));
    	double rec2 = getStats(URLgenerator.generateEvalUrl1(ociNum, semesterNum));
    	
    	double[] r = {rec1, rec2};
    	return r;
    	} catch (Exception e){
    		return null;
    	}
    }
	public static double getStats(String url)
	{
		
		Bitmap image= getBitmapFromURL(url);
		
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
	
	public static Bitmap getBitmapFromURL(String src) {
	    try {
	        URL url = new URL(src);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setDoInput(true);
	        connection.connect();
	        InputStream input = connection.getInputStream();
	        Bitmap myBitmap = BitmapFactory.decodeStream(input);
	        return myBitmap;
	    } catch (IOException e) {
	        return null;
	    }
	}

	@Override
	protected Double doInBackground(Void... params) {
		return getStats(ociNum, semesterNum)[0];
	}
	
	 @Override
     protected void onPostExecute(Double data) {
         Log.d("result", "" + data);
     }
	
}