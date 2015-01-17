package com.example.book;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class CoursePageFragment extends Fragment {
	private Course course;
	public CoursePageFragment(Course c){
		course = c;
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		
		 View rootView = inflater.inflate(R.layout.course_page_layout, container, false);
		 
		 TextView courseNameView  = (TextView)rootView.findViewById(R.id.courseName);
		 courseNameView.setText(course.getTitle());
		 
		 TextView courseTimeView  = (TextView)rootView.findViewById(R.id.time);
		 courseTimeView.setText(course.getTime());
		 
		 TextView courseLocationView  = (TextView)rootView.findViewById(R.id.location);
		 courseLocationView.setText(course.getLocation());
		 
		 TextView courseDistView  = (TextView)rootView.findViewById(R.id.distReqs);
		 courseDistView.setText(course.getDistReqs());
		 
		 TextView courseNumView  = (TextView)rootView.findViewById(R.id.courseNumber);
		 courseNumView.setText(course.getCourseNum());
		 
		 TextView courseDescView  = (TextView)rootView.findViewById(R.id.description);
		 courseDescView.setText(course.getDescription());
		 
		 
		 return rootView;
	}
	
	
}
