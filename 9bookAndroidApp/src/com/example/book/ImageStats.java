package com.example.book;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.webkit.CookieManager;


public class ImageStats extends AsyncTask<Void, Void, Void> {
	private int semesterNum;
	private List<Course> courses;
	private Fragment parent;
	
	public ImageStats(int semesterNum, List<Course> courses, Fragment f){
	    this.semesterNum=semesterNum;
		this.courses = courses;
		parent = f;
	}
	
    public static double[] getStats(int ociNum, int semesterNum, String title)
    {
    	try {
    		
    	    double rec1 = getStats(URLgenerator.generateEvalUrl1(ociNum, semesterNum));
    	    Log.d("eval1: " + title + " " + ociNum, "" + rec1);
    	    double rec2 = getStats(URLgenerator.generateEvalUrl2(ociNum, semesterNum));
    	    Log.d("eval2: " + title + " " + ociNum, "" + rec2);
    	
    	    double[] r = {rec1, rec2};
    	    return r;
    	} catch (Exception e){
            return new double[] {0.0, 0.0};
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
	protected Void doInBackground(Void... params) {
		
		List<Course> courseList = courses;
         
		
		for(Course c : courseList){
			 double[] ratingsArray = getStats(c.getOCINumber(), semesterNum, c.getCourseNum());
			 c.setWorkRating(ratingsArray[0]);
			 c.setClassRating(ratingsArray[1]);

		}
		return null;
	}
	
	
}
