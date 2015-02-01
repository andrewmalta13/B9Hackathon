package com.example.book;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.widget.SearchView;
import android.widget.Toast;


public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;
    public Boolean userAuthenticated = false;
    public int semesterCode = 201301;
    private SearchView searchView;
   
    public ArrayList<Course> courses =  new ArrayList<Course>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        WebkitCookieManagerProxy cookieStore = new WebkitCookieManagerProxy(null, java.net.CookiePolicy.ACCEPT_ALL);
        android.webkit.CookieSyncManager.createInstance(this);
    	android.webkit.CookieManager.getInstance().setAcceptCookie(true);
    	java.net.CookieHandler.setDefault(cookieStore);
       
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
    	boolean needCourseReload = false;
    	switch (position) {
        case 0:
            mTitle = "Spring 2015";
            if(semesterCode != 201501){
            	needCourseReload = true;
            	semesterCode = 201501;
            }
            break;
        case 1:
            mTitle = "Fall 2014";
            if(semesterCode != 201403){
            	needCourseReload = true;
            	semesterCode = 201403;
            }
            break;
        case 2:
            mTitle = "Spring 2013";
            if(semesterCode != 201301){
            	needCourseReload = true;
            	semesterCode = 201301;
            }
            break;  
    	}
    	
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, new CoursesFragment(semesterCode, "", needCourseReload))
                .commit();
    }


    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            
            this.searchView = (SearchView) menu.findItem(R.id.search).getActionView();
            SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() 
            {
            	 @Override
            	    public boolean onQueryTextSubmit(String query) 
            	    {
            			handleQuery(query); 
            	        return true;
            	    }
            	   @Override
            	   public boolean onQueryTextChange(String newText) 
            	   {
            	       return false;
            	   }
            };
            searchView.setOnQueryTextListener(queryTextListener);
        }
   
		return true;
      
    }
    
    public void handleQuery(String newText) {
    	this.getFragmentManager().beginTransaction()
        .replace(R.id.container, new CoursesFragment(semesterCode, newText, false))
        .commit();
    	searchView.clearFocus();
	}

	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        else if(id == R.id.update_course_list){
        	createCredentials();
        	return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void createCredentials() {
    	//load the cas login page to generate the cookies needed to fetch course evals.
    	FragmentManager fragmentManager = getFragmentManager();
    	
  	   
    	ImprovedWebViewFragment casLoginView = new 
    			ImprovedWebViewFragment("https://students.yale.edu/evalsearch", this);
    	fragmentManager.beginTransaction()
        .replace(R.id.container, casLoginView)
        .commit();
	}
    
 
}
