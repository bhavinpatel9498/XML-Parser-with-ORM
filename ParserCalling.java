import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.demo.bo.Student;
import com.demo.common.DBManagerFactory;
import com.demo.common.DataSave;
import com.demo.common.XMLSyntaxValidator;
import com.demo.handler.GenericHandler;
import com.demo.parser.GenericXMLParser;

public class ParserCalling 
{
	
	   public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException 
	   {
		   //String strXMlPath = "D:/IITData/Sem1/ITMD-515 Advanced Software Programming/Project/XML Parser/CourseUpload.xml";
		   //String strXSDPath = "D:/IITData/Sem1/ITMD-515 Advanced Software Programming/Project/XML Parser/CourseUpload.xsd";
		   
		   if(args == null || args.length != 2)
		   {
			   System.out.println("Please provide XML and XSD files");
			   return;
		   }
		   
		   String strXMlPath = args[0];
		   
		   if(strXMlPath == null || "".equalsIgnoreCase(strXMlPath) || "null".equalsIgnoreCase(strXMlPath) || !(strXMlPath.toLowerCase()).contains("xml".toLowerCase()) )
		   {
			   System.out.println("Please provide XML file");
			   return;
		   }
		   
		   String strXSDPath = args[1];
		   
		   if(strXSDPath == null || "".equalsIgnoreCase(strXSDPath) || "null".equalsIgnoreCase(strXSDPath) || !(strXSDPath.toLowerCase()).contains("xsd".toLowerCase()) )
		   {
			   System.out.println("Please provide XSD file");
			   return;
		   }   
		   
		   
		   boolean isValid = XMLSyntaxValidator.validateXMLSchema(strXSDPath,strXMlPath);
	       
		   if(isValid)
		   {
	           System.out.println("XML file is valid against XSD...Processing XML");
	          	          	           
	           String strType =  GenericXMLParser.ParserGenericXML(strXMlPath);
	           
	   			Class cls = Class.forName("com.demo.handler."+strType+"Handler");
	   			Object obj = cls.newInstance();
	   			   			
	   	    	SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
	   	    	Connection conn = null;
	   	    	boolean commitFlag = true;
	   	    	
	   	    	try 
	   	    	{
	   		        SAXParser saxParser = saxParserFactory.newSAXParser();
	   		       // GenericHandler handler = new GenericHandler();
	   		        saxParser.parse(new File(strXMlPath), (DefaultHandler)obj);
	   	
	   		        Method methodObj = (obj.getClass()).getMethod("getList");
	   		        
	   		        List objList = (List) methodObj.invoke(obj, null);
	   		        
	   		        if(objList != null)
	   		        {
	   		        	int objListSize = objList.size();
	   		        	
	   		        	conn = DBManagerFactory.getDBConnection();
	   		        	
	   		        	if(conn == null)
	   		        	{
	   		        		throw new Exception("Connection not available");
	   		        	}
	   		        		   		        		   		        	
	   		        	for(int i=0;i<objListSize;i++)
	   		        	{
	   		        		obj = objList.get(i);
	   		        		
	   		        		Method methodObj1 = (obj.getClass()).getMethod("getDBFieldList");
	   		        		List dbFieldList = (List)methodObj1.invoke(obj, null);
	   		        		
	   		        		Method methodObj2 = (obj.getClass()).getMethod("getColumnType");
	   		        		List columnTypeList = (List)methodObj2.invoke(obj, null);
	   		        		
	   		        		Method methodObj3 = (obj.getClass()).getMethod("getBOValues");	   
	   		        		List boValuesList = (List)methodObj3.invoke(obj, null);
	   		        		
	   		        		Method methodObj4 = (obj.getClass()).getMethod("getDBTableName");
	   		        		String strTableName = (String)methodObj4.invoke(obj, null);
	   		        		
	   		        		Method methodObj5 = (obj.getClass()).getMethod("getKeySqeuenceName");
	   		        		String strSeqName = (String) methodObj5.invoke(obj, null);
	   		        		
	   		        		String strResponse = DataSave.saveBO(dbFieldList, columnTypeList, boValuesList, strTableName, strSeqName, conn);
	   		        		
	   		        		int rec = i + (int) 1;
	   		        		
	   		        		System.out.println("Record no "+rec+" status: "+strResponse);
	   		        		
	   		        		if(commitFlag && strResponse != null && (strResponse.toLowerCase()).contains("error".toLowerCase()))
	   		        		{
	   		        			commitFlag = false;
	   		        		}
	   		        		
	   		        	}
	   		        	
	   		        	if(commitFlag)
	   		        	{
	   		        		conn.commit();
	   		        	}
	   		        	else
	   		        	{
	   		        		throw new Exception("One or more records cannot be inserted/updated");
	   		        	}
	   		        	
	   		        	System.out.println("\n\n ** Transaction commit **");
	   		        	
	   		        }
	   		      
	   		        
	   	    	} 
	   	    	catch (ParserConfigurationException | SAXException | IOException e)
	   	    	{
	   	    		//e.printStackTrace();
	   	    		System.out.println("\n\nError occured in XML parsing:"+e.getMessage());
	   	    	}
	   	    	catch (Exception e)
	   	    	{
	   	    		//e.printStackTrace();
	   	    		
	   	    		System.out.println("\n\nError occured :"+e.getMessage());
	   	    		System.out.println("\n\n ** Transaction rollback **");
	   	    		
	   	    		try {
						conn.rollback();
					} catch (SQLException e1) {
						//e1.printStackTrace();
					}
	   	    	
	   	    	}
	   	    	finally
	   	    	{
	   	    		if(conn!=null)
						try {
							conn.close();
						} catch (SQLException e) {
							//e.printStackTrace();
						}
	   	    	}
	           
	       }
		   else 
		   {
			   System.out.println("XML file is NOT valid against XSD...Cannot proceed further");
	       }
	      
	   }

}
