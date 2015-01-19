package com.example.book;

import java.util.ArrayList;
import java.util.List;
 
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

	
	// All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "courseManager";
 
    // Courses table name
    private static final String TABLE_COURSES = "courses";
 
    // Courses Table Columns names
    private static final String KEY_title = "title";
    private static final String KEY_professor = "professor";
    private static final String KEY_time = "time";
    private static final String KEY_location = "location";
    private static final String KEY_distReqAreas = "distReqAreas";
    private static final String KEY_term = "term";
    private static final String KEY_description = "description";
    private static final String KEY_instructorPermissionRequired = "instructorPermissionRequired";
    private static final String KEY_finalDescription = "finalDescription";
    private static final String KEY_courseNum = "courseNum";
    private static final String KEY_departmentPermissionRequired = "departmentPermissionRequired ";
    private static final String KEY_readingPeriod = "readingPeriod";
    private static final String KEY_OCInumber = "OCInumber";
    private static final String KEY_classRating = "classRating";
    private static final String KEY_professorRating = "professorRating";
    private static final String KEY_workRating ="workRating";
 
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables. Maybe make OCI Number INTEGER PRIMARY KEY. don't know if images work. blob type?
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_COURSES_TABLE = "CREATE TABLE " + TABLE_COURSES + "("
                + KEY_title + " TEXT," + KEY_professor + " TEXT,"
                + KEY_time + " TEXT" + KEY_location + "TEXT" + KEY_location
                + "TEXT" + KEY_distReqAreas + "TEXT" + KEY_description + "TEXT" +
                KEY_instructorPermissionRequired + "TEXT" + KEY_finalDescription + 
                "TEXT" + KEY_courseNum + "TEXT" + KEY_departmentPermissionRequired + "BOOLEAN"
                + KEY_readingPeriod + "BOOLEAN" + KEY_OCInumber + "INTEGER" + KEY_classRating + "FLOAT"
                 + KEY_professorRating + "FLOAT" + KEY_workRating + "FLOAT" + ")";
        db.execSQL(CREATE_COURSES_TABLE);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSES);
 
        // Create tables again
        onCreate(db);
    }
    
    public void addCourse (Course course) {
    	  	
    }
    
    public Course getCourse (int OCInumber) {
    	
    }
    
    public List<Course> getAllCourses() {
    	
    }
    
    public int getCourseCount() {
    	
    }
    
    public int updateCourse(Course course) {
    	
    }
    
    public void deleteCourse (Course course) {
    	
    }
    
    
}
