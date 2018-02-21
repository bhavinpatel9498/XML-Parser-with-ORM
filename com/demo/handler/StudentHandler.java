package com.demo.handler;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import com.demo.bo.Student;


public class StudentHandler extends DefaultHandler  {
	
	
	private boolean studentid = false;
	private boolean cwid = false;
	private boolean courseid = false;
	private boolean firstname = false;
	private boolean lastname = false;
	private boolean emailid = false;
	private boolean remarks = false;
	
	private int studentidVal = 0;
	
	private Student studentObj = null;
	
	private List studentList = null;
	
	public List getList()
	{
		return studentList;
	}
	
	@Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException 
    {

		if("student".equalsIgnoreCase(qName))
		{
			studentObj = new Student();
			
			if(studentList == null)
			{
				studentList = new ArrayList();
		
			}
		}
		else if("studentid".equalsIgnoreCase(qName))
		{
			studentid = true;
		}
		else if("cwid".equalsIgnoreCase(qName))
		{
			cwid = true;
		}
		else if("courseid".equalsIgnoreCase(qName))
		{
			courseid = true;
		}
		else if ("firstname".equalsIgnoreCase(qName)) 
        {
			firstname = true;
        }
		else if("lastname".equalsIgnoreCase(qName))
		{		
			lastname = true;
		}
		else if("emailid".equalsIgnoreCase(qName))
		{
			emailid = true;
		}
		else if("remarks".equalsIgnoreCase(qName))
		{
			remarks = true;
		}
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException 
    {
		
    	if (qName.equalsIgnoreCase("student")) 
        {
    		studentObj.setStudentid(studentidVal);    		
    		studentList.add(studentObj);
    		studentObj = null;
        }
    }
    

    @Override
    public void characters(char ch[], int start, int length) throws SAXException 
    {   
    	if(studentid)
    	{    		
    		studentidVal = Integer.parseInt(new String(ch, start, length).trim());
    		studentid = false;
    	}
    	if(cwid)
    	{
    		studentObj.setCwid(new String(ch, start, length).trim());	
    		cwid = false;
    	}
    	else if(courseid)
    	{
    		studentObj.setCourseid( Integer.parseInt(new String(ch, start, length).trim()) );	
    		courseid = false;
    	}
    	else if(firstname)
    	{
    		studentObj.setFirstname(new String(ch, start, length).trim());	
    		firstname = false;
    	}
    	else if(lastname)
    	{
    		studentObj.setLastname(new String(ch, start, length).trim());	
    		lastname = false;
    	}
    	else if(emailid)
    	{
    		studentObj.setEmailid(new String(ch, start, length).trim());	
    		emailid = false;
    	}
    	else if(remarks)
    	{
    		studentObj.setRemarks(new String(ch, start, length).trim());	
    		remarks = false;
    	}    	
    }

}
