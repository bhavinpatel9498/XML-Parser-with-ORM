package com.demo.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DataSave 
{
	public static boolean checkIfNull(Object obj)
	{
		boolean flag = false;
		try
		{
			if(obj == null)
			{
				flag = true;
			}		
		}
		catch(Exception e)
		{
			System.out.println("Exception during null check");
			flag = false;
		}
		
		return flag;		
	}
	
	public static String saveBO(List boFieldList, List columnType, List valueList, String strTableName, String strSeqName, Connection conn) throws Exception
	{
		String strQuery = "";
		String strResponse = "";
		PreparedStatement ps=null;		
		
		Statement stmt = conn.createStatement();
		stmt.executeUpdate("SET CONSTRAINTS ALL IMMEDIATE");
		
		try
		{
			if((int)valueList.get(0) == 0 )
			{	
				strQuery = "INSERT INTO "+strTableName;
				
				int boFieldListSize = boFieldList.size();
				
				for(int i=0;i<boFieldListSize;i++)
				{				
					if(i==0)
					{
						strQuery = strQuery +"(" +(String)boFieldList.get(i);
					}
					else if(i+1 == boFieldListSize)
					{
						strQuery = strQuery +", " +(String)boFieldList.get(i)+" )";
					}
					else
					{
						strQuery = strQuery +", " +(String)boFieldList.get(i);
					}
				}
				
				strQuery = strQuery + " VALUES (";
				
				for(int i=0;i<boFieldListSize;i++)
				{				
					if(i==0)
					{
						strQuery = strQuery +" ?";
					}
					else if(i+1 == boFieldListSize)
					{
						strQuery = strQuery +", ? )";
					}
					else
					{
						strQuery = strQuery +", ?";
					}
				}
							
				ps=conn.prepareStatement(strQuery);
				
				ps.setInt(1, SequenceGenerator.getNextvalue(strSeqName));
				
				for(int i=2;i<=boFieldListSize;i++)
				{			
					
					if("string".equalsIgnoreCase((String)columnType.get(i-1)) )
					{	
						if(checkIfNull((Object)valueList.get(i-1)) || "null".equalsIgnoreCase((String)valueList.get(i-1)) )
						{
							ps.setString(i, null);
						}
						else
						{
							ps.setString(i, (String)valueList.get(i-1) );
						}
					}
					else if("integer".equalsIgnoreCase((String)columnType.get(i-1)) )
					{
						if(checkIfNull((Object)valueList.get(i-1)) )
						{
							ps.setInt(i, 0);
						}
						else
						{
							ps.setInt(i, (int)valueList.get(i-1) );
						}
						
						ps.setInt(i, (int)valueList.get(i-1) );
					}
					else if("date".equalsIgnoreCase((String)columnType.get(i-1)) )
					{	
						if(checkIfNull((Object)valueList.get(i-1)) )
						{
							 ps.setDate(i, null);	
						}
						else
						{
							java.util.Date utilDate = (java.util.Date)valueList.get(i-1);
							java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
							ps.setDate(i, sqlDate);	
						}
					   			
					}	
					else if("timestamp".equalsIgnoreCase((String)columnType.get(i-1)) )
					{
						if(checkIfNull((Object)valueList.get(i-1)) )
						{
							ps.setTimestamp(i, null);	
						}
						else
						{
							java.util.Date utilDate = (java.util.Date)valueList.get(i-1);
							java.sql.Timestamp sqTimeStamp = new java.sql.Timestamp(utilDate.getTime());
					    	ps.setTimestamp(i, sqTimeStamp);
						}
					}
					
				}
							
				ps.execute();
				//int i = ps.executeUpdate();
				
				strResponse = "Insert success";
				
			}
			else
			{
				strQuery = "UPDATE "+strTableName+" SET ";
				
				int boFieldListSize = boFieldList.size();
				
				for(int i=1;i<boFieldListSize;i++)
				{				
					if(i==1)
					{
						strQuery = strQuery +(String)boFieldList.get(i) + " = ?";
					}
					else
					{
						strQuery = strQuery +", " +(String)boFieldList.get(i) + " = ?";
					}
				}
				
				strQuery = strQuery + " WHERE "+boFieldList.get(0) +" = ? ";
					
				ps=conn.prepareStatement(strQuery);
				
				for(int i=1;i<boFieldListSize;i++)
				{						
					if("string".equalsIgnoreCase((String)columnType.get(i)) || "null".equalsIgnoreCase((String)valueList.get(i-1)))
					{
						if(checkIfNull((Object)valueList.get(i)))
						{
							ps.setString(i, null);
						}
						else
						{						
							ps.setString(i, (String)valueList.get(i) );
						}
					}
					else if("integer".equalsIgnoreCase((String)columnType.get(i)) )
					{
						if(checkIfNull((Object)valueList.get(i)))
						{
							ps.setInt(i, 0 );
						}
						else
						{						
							ps.setInt(i, (int)valueList.get(i) );
						}
					}
					else if("date".equalsIgnoreCase((String)columnType.get(i)) )
					{
						if(checkIfNull((Object)valueList.get(i)))
						{
							ps.setDate(i, null);
						}
						else
						{
							java.util.Date utilDate = (java.util.Date)valueList.get(i);
							java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
					        ps.setDate(i, sqlDate);
						}
					}		
					else if("timestamp".equalsIgnoreCase((String)columnType.get(i)) )
					{
						if(checkIfNull((Object)valueList.get(i)))
						{
							ps.setTimestamp(i, null);
						}
						else
						{
							java.util.Date utilDate = (java.util.Date)valueList.get(i);
							java.sql.Timestamp sqTimeStamp = new java.sql.Timestamp(utilDate.getTime());
							ps.setTimestamp(i, sqTimeStamp);
						}
					}	
				}
				
				ps.setInt(boFieldListSize, (int)valueList.get(0));
				
				int i = ps.executeUpdate();
							
				strResponse = "Successfully updated records "+i;				
			}
			
		}
		catch(Exception e)
		{
			strResponse = "Error occured >>>"+e.getMessage();			
		}

		return strResponse;
		
	}

}
