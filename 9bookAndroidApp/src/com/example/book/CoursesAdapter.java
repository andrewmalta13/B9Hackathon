package com.example.book;

import java.util.ArrayList;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CoursesAdapter extends ArrayAdapter<Course>{
	public CoursesAdapter(Context context, ArrayList<Course> courses) {
		super(context, R.layout.courses_row_layout, courses);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
		
		View view = inflater.inflate(R.layout.courses_row_layout, parent, false);
		
		Course course = (Course) getItem(position);
		
		TextView courseNumberText = (TextView) view.findViewById(R.id.courseNumber_row);
		courseNumberText.setText(course.getCourseNum());
		
		TextView courseNameText = (TextView) view.findViewById(R.id.courseName_row);
		courseNameText.setText(course.getTitle());
		
		TextView professorNameText = (TextView) view.findViewById(R.id.professorName_row);
		professorNameText.setText(course.getProfessor());
		
		TextView distReqAreasText = (TextView) view.findViewById(R.id.distReqAreas_row);
		distReqAreasText.setText(course.getDistReqs());
		
		TextView locationText = (TextView) view.findViewById(R.id.location_row);
		locationText.setText(course.getLocation());
		
		TextView timeText = (TextView) view.findViewById(R.id.time_row);
		timeText.setText(course.getTime());
		
		return view;
	}
	
	public void updateCourseList(ArrayList<Course> updatedCourses){
		this.clear();
	    for(Course c : updatedCourses){
	    	this.add(c);
	    }
	    this.notifyDataSetChanged();
	}
    
}
