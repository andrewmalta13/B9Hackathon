package com.example.book;

import java.util.ArrayList;


import org.json.JSONArray;
import org.json.JSONObject;



import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CoursesFragment extends ListFragment{
	ArrayList<Course> courses = new ArrayList<Course>();
	CoursesAdapter adapter;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		 View rootView = inflater.inflate(R.layout.courses_list_fragment, container, false);
		return rootView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);
	   
	    if(courses.isEmpty()){
            JsonFetch parser = new JsonFetch(this, "http://ninebookjson.appspot.com/201501.json");
            parser.execute();        
	    }
	    
	    
	    /* offline testing courses to use. Anyone feel free to make these more complete. Yes YOU!
	    Course c1 = new Course("This is test course!","","","","","","","","","",false,false,20001,0.0,0.0,0.0);
	    Course c2 = new Course("This is another test course!","","","","","","","","","",false,false,20001,0.0,0.0,0.0);
	    Course c3 = new Course("Gotta have at least 3!","","","","","","","","","",false,false,20001,0.0,0.0,0.0);
	    
	    courses.add(c1);
	    courses.add(c2);
	    courses.add(c3);
	    */
	    
	    adapter = new CoursesAdapter(getActivity(), courses);
	    setListAdapter(adapter);   
	}
	
	public void generateCourses(String json){
		ArrayList<Course> courseList = new ArrayList<Course>();
		try{
		    JSONObject jObject = new JSONObject(json);
            JSONArray coursesArray = jObject.getJSONArray("courses");
        
            for(int i = 0; i < coursesArray.length(); i++){
                JSONObject course = (JSONObject) coursesArray.get(i);
                
                String title = course.getString("title");
                String professor = course.getString("professor");
                String time = course.getString("time");
                String location = course.getString("location");
                String distReqAreas = course.getString("distReqAreas");
                String term = course.getString("term");
                String description = course.getString("description");
                String instructorPermissionRequired = course.getString("instructorPermissionRequired");
                String finalDescription = course.getString("finalDescription");
                String courseNum = course.getString("courseNum");
                
                
                Boolean departmentPermissionRequired = course.getBoolean("departmentPermissionRequired");
                Boolean readingPeriod = course.getBoolean("readingPeriod");
                
                int OCInumber = course.getInt("OCInumber");
                
                courseList.add(new Course(title, professor, time, location, distReqAreas, term, description,
                		instructorPermissionRequired, finalDescription, courseNum,
                		departmentPermissionRequired, readingPeriod, OCInumber, 0.0, 0.0, 0.0));
            }
            	
                courses = courseList;
        	    adapter.updateCourseList(courseList);
        	       
        }
		
		catch(Exception e){
			Log.e("json parsing", "error parsing json" + e.toString());
		}
	}
	
}
