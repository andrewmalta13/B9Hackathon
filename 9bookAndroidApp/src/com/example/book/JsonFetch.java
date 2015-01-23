package com.example.book;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Fragment;
import android.os.AsyncTask;
import android.util.Log;

public class JsonFetch extends AsyncTask<Void, Void, String>{
	private String url = "";
    private Fragment parentFragment;

    
    public JsonFetch(Fragment a,  String jsonurl){
    	parentFragment = a;
    	url = jsonurl;
    }
	
	@Override
	protected String doInBackground(Void... params) {
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);
		String data = "";
		try {
			HttpResponse response = client.execute(get);
			int status = response.getStatusLine().getStatusCode();
			
			if(status == 200){
				HttpEntity entity = response.getEntity();
				data = EntityUtils.toString(entity);
			}
	   
		} catch(Exception e){
			Log.e("json fetch", "encountered an error when fetching the json.");
		}
		return data;
	}
	
	@Override
    protected void onPostExecute(String data) {
		((CoursesFragment)parentFragment).generateCourses(data);
		if(((MainActivity) parentFragment.getActivity()).userAuthenticated){
			((CoursesFragment)parentFragment).generateRatings();
		}
	}
		
}
