package com.demo.handler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import com.demo.bo.Course;

public class CourseHandler extends DefaultHandler {
	
	
	private boolean courseid = false;
	private boolean courseno = false;
	private boolean name = false;
	private boolean term = false;
	private boolean startdate = false;
	private boolean enddate = false;
	private boolean location = false;
	private boolean time = false;
	private boolean remarks = false;
	private boolean status = false;
	
	private int courseidVal = 0;
	
	private Course courseObj = null;
	
	private List courseList = null;
	
	public List getList()
	{
		return courseList;
	}
	
	@Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException 
    {

		if("course".equalsIgnoreCase(qName))
		{
			courseObj = new Course();
			
			if(courseList == null)
			{
				courseList = new ArrayList();
		
			}
		}
		else if("courseid".equalsIgnoreCase(qName))
		{
			courseid = true;
		}
		else if("courseno".equalsIgnoreCase(qName))
		{
			courseno = true;
		}
		else if("name".equalsIgnoreCase(qName))
		{
			name = true;
		}
		else if("term".equalsIgnoreCase(qName))
		{
			term = true;
		}
		else if ("startdate".equalsIgnoreCase(qName)) 
        {
			startdate = true;
        }
		else if("enddate".equalsIgnoreCase(qName))
		{		
			enddate = true;
		}
		else if("location".equalsIgnoreCase(qName))
		{
			location = true;
		}
		else if("time".equalsIgnoreCase(qName))
		{
			time = true;
		}
		else if("remarks".equalsIgnoreCase(qName))
		{
			remarks = true;
		}
		else if("status".equalsIgnoreCase(qName))
		{
			status = true;
		}

    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException 
    {
		
    	if (qName.equalsIgnoreCase("course")) 
        {
    		courseObj.setCourseid(courseidVal);    		
    		courseList.add(courseObj);
    		courseObj = null;
        }
    }
    

    @Override
    public void characters(char ch[], int start, int length) throws SAXException 
    {   
    	if(courseid)
    	{    		
    		courseidVal = Integer.parseInt(new String(ch, start, length).trim());
    		courseid = false;
    	}
    	if(courseno)
    	{
    		courseObj.setCourseno(new String(ch, start, length).trim());	
    		courseno = false;
    	}
    	else if(name)
    	{
    		courseObj.setName(new String(ch, start, length).trim());	
    		name = false;
    	}
    	else if(term)
    	{
    		courseObj.setTerm(new String(ch, start, length).trim());	
    		term = false;
    	}
    	else if(startdate)
    	{
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    		try
    		{
    			courseObj.setStartdate(sdf.parse(new String(ch, start, length).trim()));
    		}
    		catch(Exception e)
    		{
    			courseObj.setStartdate(null);
    		}
    		
    		startdate = false;
    	}
    	else if(enddate)
    	{    			
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    		try
    		{
    			courseObj.setEnddate(sdf.parse(new String(ch, start, length).trim()));
    		}
    		catch(Exception e)
    		{
    			courseObj.setEnddate(null);
    		}
    		enddate = false;
    	}
    	else if(location)
    	{
    		courseObj.setLocation(new String(ch, start, length).trim());	
    		location = false;
    	}    	
    	else if(time)
    	{
    		courseObj.setTime(new String(ch, start, length).trim());	
    		time = false;
    	}  
    	else if(remarks)
    	{
    		courseObj.setRemarks(new String(ch, start, length).trim());	
    		remarks = false;
    	}  
    	else if(status)
    	{
    		courseObj.setStatus(new String(ch, start, length).trim());	
    		status = false;
    	}  
    }
	

}
