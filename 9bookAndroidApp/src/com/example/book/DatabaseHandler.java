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
    
    private SQLiteDatabase mDb;
 
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
                "TEXT" + KEY_courseNum + "TEXT" + KEY_departmentPermissionRequired + "INTEGER"
                + KEY_readingPeriod + "INTEGER" + KEY_OCInumber + "INTEGER" + KEY_classRating + "FLOAT"
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
    
    public Course getCourse (SQLiteDatabase db, int OCInumber) {
    	String[] tableColumns = new String[] {
    		    "KEY_OCInumber",
    		    "(SELECT 1 FROM TABLE_COURSES)"
    		};
    		String whereClause = "KEY_OCInumber = ?";
    		String[] whereArgs = new String[] {
    		    "" + OCInumber 
    		};
    		Cursor c = db.query("table1", tableColumns, whereClause, whereArgs,
    		        null, null, null);
    		
    		
    		
    		return null; //figure out how to access a course object from a Cursor. 

    }
    
    public ArrayList<Course> getAllCourses(SQLiteDatabase db) {
 
    	Cursor  cursor = db.rawQuery("select * from TABLE_COURSES",null);
    	ArrayList<Course> courses = new ArrayList<Course>();
    	if (cursor .moveToFirst()) {

            while (cursor.isAfterLast() == false) {
            	
            	Course course = new Course(cursor.getString(cursor.getColumnIndex(KEY_title)),
            			                   cursor.getString(cursor.getColumnIndex(KEY_professor)),
            			                   cursor.getString(cursor.getColumnIndex(KEY_time)),
            			                   cursor.getString(cursor.getColumnIndex(KEY_location)),
            			                   cursor.getString(cursor.getColumnIndex(KEY_distReqAreas)),
            			                   cursor.getString(cursor.getColumnIndex(KEY_term)),
            			                   cursor.getString(cursor.getColumnIndex(KEY_description)),
            			                   cursor.getString(cursor.getColumnIndex(KEY_instructorPermissionRequired)),
            			                   cursor.getString(cursor.getColumnIndex(KEY_finalDescription)),
            			                   cursor.getString(cursor.getColumnIndex(KEY_courseNum)),
            			                   (cursor.getInt(cursor.getColumnIndex(KEY_departmentPermissionRequired)) > 0),
            			                   (cursor.getInt(cursor.getColumnIndex(KEY_readingPeriod)) > 0),
            			                   cursor.getInt(cursor.getColumnIndex(KEY_OCInumber)),
            			                   cursor.getFloat(cursor.getColumnIndex(KEY_classRating)),
            			                   cursor.getFloat(cursor.getColumnIndex(KEY_professorRating)),
            			                   cursor.getFloat(cursor.getColumnIndex(KEY_workRating)));
                courses.add(course);
                cursor.moveToNext();
            }
        }
    	
    	return courses;
    }
    
    public int getCourseCount() {
    	
    }
    
    public int updateCourse(Course course) {
    	
    }
    
    public void deleteCourse (Course course) {
    	
    }

}
