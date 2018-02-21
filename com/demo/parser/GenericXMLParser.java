package com.demo.parser;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.demo.handler.GenericHandler;

public class GenericXMLParser {
	
    public static String ParserGenericXML(String xmlFile) 
    {
    	SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
    	
    	try 
    	{
	        SAXParser saxParser = saxParserFactory.newSAXParser();
	        GenericHandler handler = new GenericHandler();
	        saxParser.parse(new File(xmlFile), handler);
	        String strEntityType = handler.getStrEntityName();	        
	   	        
	        return strEntityType;
    	} 
    	catch (ParserConfigurationException | SAXException | IOException e)
    	{
    		//e.printStackTrace();
    		System.out.println("Error occured while parsing :"+e.getMessage());
    		return "";
    	}
    	catch (Exception e)
    	{
    		//e.printStackTrace();
    		System.out.println("Error occured while parsing :"+e.getMessage());
    		return "";
    	}
    }
}
