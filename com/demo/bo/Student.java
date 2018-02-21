package com.demo.bo;

import java.util.ArrayList;
import java.util.List;

public class Student 
{
	private int studentid;
	private String cwid;
	private int courseid;
	private String firstname;
	private String lastname;
	private String emailid;
	private String remarks;
	
	public List getBOValues()
	{
		List attribList = new ArrayList();
		attribList.add(studentid);
		attribList.add(cwid);
		attribList.add(courseid);
		attribList.add(firstname);
		attribList.add(lastname);
		attribList.add(emailid);
		attribList.add(remarks);
		
		return attribList;		
	}
	
	public String getDBTableName()
	{
		String strTableName = "students";
		return strTableName;
	}
	
	public String getKeySqeuenceName()
	{
		String strSqeuenceName = "studentid";
		return strSqeuenceName;
	}
	
	public List getDBFieldList()
	{
		List attribList = new ArrayList();
		attribList.add("studentid");
		attribList.add("cwid");
		attribList.add("courseid");
		attribList.add("firstname");
		attribList.add("lastname");
		attribList.add("emailid");
		attribList.add("remarks");
		
		return attribList;		
	}
	
	public List getColumnType()
	{
		List attribList = new ArrayList();
		attribList.add("integer");
		attribList.add("string");
		attribList.add("integer");
		attribList.add("string");
		attribList.add("string");
		attribList.add("string");
		attribList.add("string");
		
		return attribList;		
	}
	
	public String getCwid() {
		return cwid;
	}

	public void setCwid(String cwid) {
		this.cwid = cwid;
	}

	public int getCourseid() {
		return courseid;
	}

	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getStudentid() 
	{
		return studentid;
	}
	
	public void setStudentid(int studentid) 
	{
		this.studentid = studentid;
	}
	
	public String getFirstname() 
	{
		return firstname;
	}
	
	public void setFirstname(String firstname)
	{
		this.firstname = firstname;
	}
	
	public String getLastname() 
	{
		return lastname;
	}
	
	public void setLastname(String lastname) 
	{
		this.lastname = lastname;
	}
	
	public String getEmailid() 
	{
		return emailid;
	}
	
	public void setEmailid(String emailid) 
	{
		this.emailid = emailid;
	}
	



}
