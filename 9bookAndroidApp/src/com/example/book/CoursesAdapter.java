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
		
		TextView courseNameText = (TextView) view.findViewById(R.id.courseName);
		courseNameText.setText(course.getTitle());
		
		return null;
	}
	
	public void updateCourseList(ArrayList<Course> updatedCourses){
		this.clear();
	    for(Course c : updatedCourses){
	    	this.add(c);
	    }
	    this.notifyDataSetChanged();
	}
}
