package com.demo.handler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import com.demo.bo.Event;

import oracle.net.aso.s;

public class EventHandler extends DefaultHandler {
	
	private boolean eventid = false;
	private boolean courseid = false;
	private boolean eventtype = false;
	private boolean name = false;
	private boolean description = false;
	private boolean startdate = false;
	private boolean enddate = false;
	private boolean createdate = false;
	private boolean remarks = false;
	
	private int eventidVal = 0;
	
	private Event eventObj = null;
	
	private List eventList = null;
	
	public List getList()
	{
		return eventList;
	}
	
	@Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException 
    {

		if("event".equalsIgnoreCase(qName))
		{
			eventObj = new Event();
			
			if(eventList == null)
			{
				eventList = new ArrayList();
		
			}
		}
		else if("eventid".equalsIgnoreCase(qName))
		{
			eventid = true;
		}
		else if("courseid".equalsIgnoreCase(qName))
		{
			courseid = true;
		}
		else if("eventtype".equalsIgnoreCase(qName))
		{
			eventtype = true;
		}
		else if("name".equalsIgnoreCase(qName))
		{
			name = true;
		}
		else if ("description".equalsIgnoreCase(qName)) 
        {
			description = true;
        }
		else if("startdate".equalsIgnoreCase(qName))
		{		
			startdate = true;
		}
		else if("enddate".equalsIgnoreCase(qName))
		{
			enddate = true;
		}
		else if("createdate".equalsIgnoreCase(qName))
		{
			createdate = true;
		}
		else if("remarks".equalsIgnoreCase(qName))
		{
			remarks = true;
		}

    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException 
    {
		
    	if (qName.equalsIgnoreCase("event")) 
        {    		
    		eventObj.setEventid(eventidVal);    		
    		eventList.add(eventObj);
    		eventObj = null;
        }
    }
    

    @Override
    public void characters(char ch[], int start, int length) throws SAXException 
    {   
    	if(eventid)
    	{    		
    		eventidVal = Integer.parseInt(new String(ch, start, length).trim());
    		eventid = false;
    	}
    	if(courseid)
    	{
    		eventObj.setCourseid(Integer.parseInt(new String(ch, start, length).trim()));	
    		courseid = false;
    	}
    	else if(eventtype)
    	{
    		eventObj.setEventtype(new String(ch, start, length).trim());	
    		eventtype = false;
    	}
    	else if(name)
    	{
    		eventObj.setName(new String(ch, start, length).trim());	
    		name = false;
    	}
    	else if(description)
    	{
    		eventObj.setDescription(new String(ch, start, length).trim());	
    		description = false;
    	}
    	else if(startdate)
    	{    		
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    		try
    		{
    			eventObj.setStartdate(sdf.parse(new String(ch, start, length).trim()));
    		}
    		catch(Exception e)
    		{
    			eventObj.setStartdate(null);
    		}
    		
    		startdate = false;
    	}
    	else if(enddate)
    	{	
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    		try
    		{
    			eventObj.setEnddate(sdf.parse(new String(ch, start, length).trim()));
    		}
    		catch(Exception e)
    		{
    			eventObj.setEnddate(null);
    		}
    		enddate = false;
    	}
    	else if(createdate)
    	{
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    		try
    		{
    			eventObj.setCreatedate(sdf.parse(new String(ch, start, length).trim()));
    		}
    		catch(Exception e)
    		{
    			eventObj.setCreatedate(null);
    		}
    		createdate = false;
    	}
    	else if(remarks)
    	{
    		eventObj.setRemarks(new String(ch, start, length).trim());	
    		remarks = false;
    	}  
    }
	


}
