package com.example.book;

import java.util.ArrayList;

import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ImprovedWebViewFragment extends Fragment {
 
    private WebView mWebView;
    private boolean mIsWebViewAvailable;
    private String mUrl = null;
    public Boolean finished;
    public Context activity;
    /**
     * Creates a new fragment which loads the supplied url as soon as it can
     * @param url the url to load once initialised
     */
    public ImprovedWebViewFragment(String url, Context c) {
        super();
        mUrl = url;
        activity = c;
    }
    
 
    /**
     * Called to instantiate the view. Creates and returns the WebView.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        if (mWebView != null) {
            mWebView.destroy();
        }
        mWebView = new WebView(getActivity());
        
        mWebView.setWebViewClient(new WebViewClient() {
 
          
          public void onPageFinished(WebView view, String url1) {
              if(url1.contains("students.yale.edu/evalsearch")){
            	 ((MainActivity) activity).userAuthenticated = true;
                 
            	 onFinishCreateCourseFragment(); 
              }
          }
        });
        mWebView.loadUrl(mUrl);   //load the course evaluation search url to generate correct cookies.
        
        mIsWebViewAvailable = true;
        return mWebView;
    }
   
    /**
     * Convenience method for loading a url. Will fail if {@link View} is not initialised (but won't throw an {@link Exception})
     * @param url
     */
    public void loadUrl(String url) {
        if (mIsWebViewAvailable){
        	getWebView().getSettings().setJavaScriptEnabled(true);
        	getWebView().loadUrl(mUrl = url);
        	
        }
        else Log.w("ImprovedWebViewFragment", "WebView cannot be found. Check the view and fragment have been loaded.");
    }
 
    /**
     * Called when the fragment is visible to the user and actively running. Resumes the WebView.
     */
    @Override
    public void onPause() {
        super.onPause();
        mWebView.onPause();
    }
 
    /**
     * Called when the fragment is no longer resumed. Pauses the WebView.
     */
    @Override
    public void onResume() {
        mWebView.onResume();
        super.onResume();
    }
    
    public void onFinishCreateCourseFragment(){
     
    	
    	// Threads for fetching the course evals. TODO try to omptimize the time this process takes.
    	
    	EvalExecutor e = new EvalExecutor();
    	
    	for(int i = 0; i <= 99; i++){
    		new ImageStats(((MainActivity)this.getActivity()).semesterCode, ((MainActivity)this.getActivity()).
    				courses, i, 100).executeOnExecutor(e, null);
    	}
    	
 
    	this.getFragmentManager().beginTransaction()
        .replace(R.id.container, new CoursesFragment(((MainActivity)this.getActivity()).semesterCode, "", false))
        .commit();
    }
 
    /**
     * Called when the WebView has been detached from the fragment.
     * The WebView is no longer available after this time.
     */
    @Override
    public void onDestroyView() {		
 			     
        mIsWebViewAvailable = false;
        super.onDestroyView();
    }
 
    /**
     * Called when the fragment is no longer in use. Destroys the internal state of the WebView.
     */
    @Override
    public void onDestroy() {
        if (mWebView != null) {
        	
        	CookieManager cookieManager = CookieManager.getInstance();		
        	cookieManager.setAcceptCookie(true);		
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();
    }
 
    /**
     * Gets the WebView.
     */
    public WebView getWebView() {
        return mIsWebViewAvailable ? mWebView : null;
    }    
}
