package com.example.book;

public class Course {
	
    private String title;
    private String professor;
    private String time;
    private String location;
    private String distReqAreas;
    private String term;
    private String description;
    private String instructorPermissionRequired; // could be a variety of things so dont use boolean.
    private String finalDescription;
    private String courseNum;
    
    private Boolean departmentPermissionRequired;
    private Boolean readingPeriod;
    
    private int OCInumber;
    
    private double classRating;
    private double professorRating;
    private double workRating;
    
    
    public Course(String titl, String prof, String t, String loc, String distReq, String ter, String descr,
    		      String instPerm, String fDesc, String cn, Boolean deptPerm, Boolean rp, int OCI,
    		      double clsRt, double profRt, double workRt){
    	title = titl;
    	professor = prof;
    	time = t;
    	location = loc;
    	distReqAreas = distReq;
    	term = ter;
    	description = descr;
    	instructorPermissionRequired = instPerm;
    	finalDescription = fDesc;
    	departmentPermissionRequired = deptPerm;
    	readingPeriod = rp;
    	classRating = clsRt;
    	OCInumber = OCI;
    	professorRating = profRt;
    	workRating = workRt;
    	courseNum = cn;
    }
    
    //get methods for Course Object
    public String getTitle(){
    	return title;
    }
    public String getProfessor(){
    	return professor;
    }
    public String getTime(){
    	return time;
    }
    public String getLocation(){
    	return location;
    }
    public String getDistReqs(){
    	return distReqAreas;
    }
    public String getTerm(){
    	return term;
    }
    public String getDescription(){
    	return description;
    }
    public String getInstPermReq(){
    	return instructorPermissionRequired;
    }
    public String getFinalDescription(){
    	return finalDescription;
    }
    public String getCourseNum(){
    	return courseNum;
    }
    public Boolean getDeptPermReq(){
    	return departmentPermissionRequired;
    }
    public Boolean getReadingPeriod(){
    	return readingPeriod;
    }
    public double getClassRating(){
    	return classRating;
    }
    public double getProfRating(){
    	return professorRating;
    }
    public double getWorkRating(){
    	return workRating;
    }
    public int getOCINumber(){
    	return OCInumber;
    }
    
    
    //setter methods for Course Object
    public void setTitle(String s){
    	title = s;
    }
    public void setProfessor(String s){
    	professor = s;
    }
    public void setTime(String s){
    	time = s;
    }
    public void setLocation(String s){
    	location = s;
    }
    public void setDistReqs(String s){
    	distReqAreas = s;
    }
    public void setTerm(String s){
    	term = s;
    }
    public void setDescription(String s){
    	description = s;
    }
    public void setInstPermReq(String s){
    	instructorPermissionRequired = s;
    }
    public void setFinalDescription(String s){
    	finalDescription = s;
    }
    public void setCourseNum(String i){
    	courseNum = i;
    }
    public void setDeptPermReq(Boolean b){
    	departmentPermissionRequired = b;
    }
    public void setReadingPeriod(Boolean b){
    	readingPeriod = b;
    }
    public void setClassRating(double d){
    	classRating = d;
    }
    public void setProfRating(double d){
    	professorRating = d;
    }
    public void setWorkRating(double d){
    	workRating = d;
    }

    
   
}

