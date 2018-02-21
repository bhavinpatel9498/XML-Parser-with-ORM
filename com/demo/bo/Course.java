package com.demo.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Course {
	
	private int courseid;
	private String courseno;
	private String name;
	private String term;
	private Date startdate;
	private Date enddate;
	private String location;
	private String time;
	private String remarks;
	private String status;
	
	public Course()
	{
		courseid = 0;
		courseno = null;
		name = null;
		term = null;
		startdate = null;
		enddate = null;
		location = null;
		time = null;
		remarks = null;
		status = null;
	}

	public List getBOValues()
	{
		List attribList = new ArrayList();
		attribList.add(courseid);
		attribList.add(courseno);
		attribList.add(name);
		attribList.add(term);
		attribList.add(startdate);
		attribList.add(enddate);
		attribList.add(location);
		attribList.add(time);
		attribList.add(remarks);
		attribList.add(status);
		
		return attribList;		
	}
	
	public String getDBTableName()
	{
		String strTableName = "courses";
		return strTableName;
	}
	
	public String getKeySqeuenceName()
	{
		String strSqeuenceName = "courseid";
		return strSqeuenceName;
	}
	
	public List getDBFieldList()
	{
		List attribList = new ArrayList();
		attribList.add("courseid");
		attribList.add("courseno");
		attribList.add("name");
		attribList.add("term");
		attribList.add("startdate");
		attribList.add("enddate");
		attribList.add("location");
		attribList.add("time");
		attribList.add("remarks");
		attribList.add("status");
		
		return attribList;		
	}
	
	public List getColumnType()
	{
		List attribList = new ArrayList();
		attribList.add("integer");
		attribList.add("string");
		attribList.add("string");
		attribList.add("string");
		attribList.add("date");
		attribList.add("date");
		attribList.add("string");
		attribList.add("string");
		attribList.add("string");
		attribList.add("string");
		
		return attribList;		
	}
	
	public int getCourseid() {
		return courseid;
	}
	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}
	public String getCourseno() {
		return courseno;
	}
	public void setCourseno(String courseno) {
		this.courseno = courseno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public Date getStartdate() {
		return startdate;
	}
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}


}
