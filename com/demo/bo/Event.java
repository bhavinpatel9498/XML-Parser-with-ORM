package com.demo.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Event {
	
	private int eventid;
	private int courseid;
	private String eventtype;
	private String name;
	private String description;
	private Date startdate;
	private Date enddate;
	private Date createdate;
	private String remarks;
	
	public Event()
	{
		eventid = 0;
		courseid = 0;
		eventtype = null;
		name = null;
		description = null;
		startdate = null;
		enddate = null;
		createdate = null;
		remarks = null;		
	}
	
	public List getBOValues()
	{
		List attribList = new ArrayList();
		attribList.add(eventid);
		attribList.add(courseid);
		attribList.add(eventtype);
		attribList.add(name);
		attribList.add(description);
		attribList.add(startdate);
		attribList.add(enddate);
		attribList.add(createdate);
		attribList.add(remarks);
		
		return attribList;		
	}
	
	public String getDBTableName()
	{
		String strTableName = "events";
		return strTableName;
	}
	
	public String getKeySqeuenceName()
	{
		String strSqeuenceName = "eventid";
		return strSqeuenceName;
	}
	
	public List getDBFieldList()
	{
		List attribList = new ArrayList();
		attribList.add("eventid");
		attribList.add("courseid");
		attribList.add("eventtype");
		attribList.add("name");
		attribList.add("description");
		attribList.add("startdate");
		attribList.add("enddate");
		attribList.add("createdate");
		attribList.add("remarks");
				
		return attribList;		
	}
	
	public List getColumnType()
	{
		List attribList = new ArrayList();
		attribList.add("integer");
		attribList.add("integer");
		attribList.add("string");
		attribList.add("string");
		attribList.add("string");
		attribList.add("timestamp");
		attribList.add("timestamp");
		attribList.add("timestamp");
		attribList.add("string");
		
		return attribList;		
	}

	public int getEventid() {
		return eventid;
	}

	public void setEventid(int eventid) {
		this.eventid = eventid;
	}

	public int getCourseid() {
		return courseid;
	}

	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}

	public String getEventtype() {
		return eventtype;
	}

	public void setEventtype(String eventtype) {
		this.eventtype = eventtype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	

}
