package com.example.book;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;



public class ImageStats extends AsyncTask<Void, Void, Void> {
	private int semesterNum;
	private List<Course> courses;
	private int startIndex;
	private int numThreads;  //used in modulus calculation of which evals to update
	
	
	public ImageStats(int semesterNum, List<Course> courses, int startIndex, int threadNum){
	    this.semesterNum=semesterNum;
		this.courses = courses;
		this.startIndex =  startIndex;
		this.numThreads = threadNum; 
	}
	
    public  double[] getStats(int ociNum, int semesterNum, String title)
    {
    	try {
    	    double rec1 = getStats(URLgenerator.generateEvalUrl1(ociNum, semesterNum));
    	    double rec2 = getStats(URLgenerator.generateEvalUrl2(ociNum, semesterNum));
    	    
    	    if (Double.isNaN(rec1)) rec1 = 0.0;
    	    if (Double.isNaN(rec2)) rec2 = 0.0;
    	    
    	    
    	    Log.d("debug: ", "" + startIndex + " " + "" + rec1 + " " + rec2);
    	
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
		return ((total/(float)num) * 100) / 100;
	}
	
	public static Bitmap getBitmapFromURL(String src) {
	    try {
	        URL url = new URL(src);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setDoInput(true);
	        connection.connect();
	        InputStream input = connection.getInputStream();
	        Bitmap myBitmap = BitmapFactory.decodeStream(input);
	        connection.disconnect();
	        return myBitmap;
	    } catch (IOException e) {
	        return null;
	    }
	}

	@Override
	protected Void doInBackground(Void... params) {
		int size = courses.size();
		for(int i = startIndex; i < size; i += numThreads){
			 Course c = courses.get(i);
			 double[] ratingsArray = getStats(c.getOCINumber(), semesterNum, c.getCourseNum());
			
			 c.setWorkRating(ratingsArray[0]);
			 c.setClassRating(ratingsArray[1]); 
		}
		return null;
	}
}