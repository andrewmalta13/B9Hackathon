package com.example.book;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class CoursesFragment extends ListFragment{
	private CoursesAdapter adapter;
	private int semesterCode;
	private String filter;
	
	public CoursesFragment(int semCode, String filter){
		super();
		semesterCode = semCode; 
		this.filter = filter;
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		 View rootView = inflater.inflate(R.layout.courses_list_fragment, container, false);
		 return rootView;
	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);
	    
	    if(((MainActivity)this.getActivity()).courses.isEmpty()){
            JsonFetch parser = new JsonFetch(this, "http://ninebookjson.appspot.com/"+ semesterCode + ".json");
            parser.execute();        
	    }
	    
	    adapter = new CoursesAdapter(this.getActivity(), copyCourseArray());
	    Log.d("num courses before :", "" + ((MainActivity)this.getActivity()).courses.size());
	    filterCourses(this.filter);
	    Log.d("num courses after:", "" + ((MainActivity)this.getActivity()).courses.size());
	    
	    setListAdapter(adapter);   
	}
	
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Course courseSelected =(Course) l.getAdapter().getItem(position);
		Intent courseIntent = new Intent(this.getActivity(), CoursePageActivity.class);
	    courseIntent.putExtra("courseName", courseSelected.getTitle());
		courseIntent.putExtra("courseTime", courseSelected.getTime());
		courseIntent.putExtra("courseLocation", courseSelected.getLocation());
		courseIntent.putExtra("courseDistReqst", courseSelected.getDistReqs());
		courseIntent.putExtra("courseNumber", courseSelected.getCourseNum());
		courseIntent.putExtra("courseDescription", courseSelected.getDescription());

		courseIntent.putExtra("profRating",courseSelected.getProfRating());
		courseIntent.putExtra("classRating",courseSelected.getClassRating());
		courseIntent.putExtra("workRating", courseSelected.getWorkRating());
		 
		
		this.getActivity().startActivity(courseIntent);
		
		/*
		CoursePageFragment page = new CoursePageFragment(courseSelected);	
		FragmentManager fm = getActivity().getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		ft.replace(R.id.container, page);
		
		ft.commit(); */
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
                
                double workRating = 0.0;
                double classRating = 0.0;
                double professorRating = 0.0;
                
                
                courseList.add(new Course(title, professor, time, location, distReqAreas, term, description,
                		instructorPermissionRequired, finalDescription, courseNum,
                		departmentPermissionRequired, readingPeriod, OCInumber, classRating, professorRating, workRating));
            }
            	  
            ((MainActivity)this.getActivity()).courses = courseList;
            adapter.updateCourseList(courseList);
        }
		
		catch(Exception e){
			Log.e("json parsing", "error parsing json" + e.toString());
		}
	}
	
	
	public void filterCourses(String filter){
		ArrayList<Course> courses = ((MainActivity)this.getActivity()).courses;
		Log.d("num courses filter before:", "" + ((MainActivity)this.getActivity()).courses.size());
		ArrayList<Course> filtered = new ArrayList<Course>();
		if(filter == "")filtered = courses;		// no filter is applied.
		else{
			for(Course c : courses){
				if(c.getTitle().contains(filter) || c.getCourseNum().contains(filter)) filtered.add(c);		
			}
		
		}
		adapter.updateCourseList(filtered);
		Log.d("num courses filter after:", "" + ((MainActivity)this.getActivity()).courses.size());
		
	}
	
    public ArrayList<Course> copyCourseArray(){
    	ArrayList<Course> copy = new ArrayList<Course>();
    	for(Course c : ((MainActivity)this.getActivity()).courses){
    		copy.add(c);
    	}
    	return copy;
    }
	
}
