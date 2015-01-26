package com.example.book;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;

import android.widget.TextView;


public class CoursePageActivity extends Activity {
	private Course course = new Course("","","","","","","","","", null, null, null, 0, 0, 0, 0);
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 Intent i = getIntent();
		 course.setTitle(i.getStringExtra("courseName"));
		 course.setCourseNum(i.getStringExtra("courseNumber"));
		 course.setTime(i.getStringExtra("courseTime"));
		 course.setLocation(i.getStringExtra("courseLocation"));
		 course.setDistReqs(i.getStringExtra("courseDistReq"));
		 course.setCourseNum(i.getStringExtra("courseNumber"));
		 course.setDescription(i.getStringExtra("courseDescription"));
		 course.setProfRating(i.getDoubleExtra("profRating", 0));
		 course.setClassRating(i.getDoubleExtra("classRating",0));
		 course.setWorkRating(i.getDoubleExtra("workRating",0));
		 
		 setContentView(R.layout.course_page_layout);
		
		 TextView courseNameView  = (TextView) findViewById(R.id.courseName);
		 
		 TextView courseTimeView  = (TextView) findViewById(R.id.time);
		 courseTimeView.setText(course.getTime());
		 
		 TextView courseLocationView  = (TextView) findViewById(R.id.location);
		 courseLocationView.setText(course.getLocation());
		 
		 TextView courseDistView  = (TextView) findViewById(R.id.distReqs);
		 courseDistView.setText(course.getDistReqs());
		 
		 TextView courseNumView  = (TextView) findViewById(R.id.courseNumber);
		 courseNumView.setText(course.getCourseNum());
		 
		 TextView courseDescView  = (TextView) findViewById(R.id.description);
		 courseDescView.setText(course.getDescription()); 
		 

		 TextView profRating = (TextView) findViewById(R.id.Rating1);
		 profRating.setText(Double.toString(course.getProfRating()));
		 
		 TextView classRating = (TextView) findViewById(R.id.Rating2);
		 classRating.setText(Double.toString(course.getClassRating()));
		 
		 TextView workRating = (TextView) findViewById(R.id.Rating3);
		 workRating.setText(Double.toString(course.getWorkRating()));
		 
	}
	
}
