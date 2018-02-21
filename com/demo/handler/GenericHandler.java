package com.demo.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class GenericHandler extends DefaultHandler 
{
   private boolean entityTypeTag = false;
   private boolean headTag = false;
   private boolean stopExecution = false;
   private String strEntityName = "";
   	
   
	public String getStrEntityName() {
		return strEntityName;
	}

	public void setStrEntityName(String strEntityName) {
		this.strEntityName = strEntityName;
	}

	@Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException 
    {
		//stop execution if header details have been extracted
		if(stopExecution)
		{
			return;
		}
	
		if("head".equalsIgnoreCase(qName))
		{
			headTag = true;
		}
		else if ("entitytype".equalsIgnoreCase(qName)) 
        {
			entityTypeTag = true;
        }
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException 
    {
		//stop execution if header details have been extracted
		if(stopExecution)
		{
			return;
		}
		
    	if (qName.equalsIgnoreCase("head")) 
        {
        	stopExecution = true;
        }
    }
    

    @Override
    public void characters(char ch[], int start, int length) throws SAXException 
    {
		//stop execution if header details have been extracted
		if(stopExecution)
		{
			return;
		}

    	if(headTag)
    	{
    		if(entityTypeTag)
    		{
    			strEntityName = new String(ch, start, length).trim();
    			entityTypeTag = false;
    		}
    	}
    }

    
}
