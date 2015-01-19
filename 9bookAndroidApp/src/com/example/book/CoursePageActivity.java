package com.example.book;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class CoursePageActivity extends Activity {
	private Course course = new Course("","","","","","","","","", null, null, null, 0, 0, 0, 0);
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 Intent i = getIntent();
		 course.setCourseNum(i.getStringExtra("courseName"));
		 course.setTime(i.getStringExtra("courseTime"));
		 course.setLocation(i.getStringExtra("courseLocation"));
		 course.setDistReqs(i.getStringExtra("courseDistReq"));
		 course.setCourseNum(i.getStringExtra("courseNumber"));
		 course.setDescription(i.getStringExtra("courseDescription"));
		 
		 
		 setContentView(R.layout.courses_row_layout);
		
		 TextView courseNameView  = (TextView) findViewById(R.id.courseName);
		 courseNameView.setText(course.getTitle());
		 
		 TextView courseTimeView  = (TextView) findViewById(R.id.time);
		 courseTimeView.setText(course.getTime());
		 
		 TextView courseLocationView  = (TextView) findViewById(R.id.location); // changed this from location in Andrew's code. Was throwing an error
		 courseLocationView.setText(course.getLocation());
		 
		 TextView courseDistView  = (TextView) findViewById(R.id.distReqs);
		 courseDistView.setText(course.getDistReqs());
		 
		 TextView courseNumView  = (TextView) findViewById(R.id.courseNumber);
		 courseNumView.setText(course.getCourseNum());
		 
		 TextView courseDescView  = (TextView) findViewById(R.id.description);
		 courseDescView.setText(course.getDescription()); 
		 
	}
	
}
