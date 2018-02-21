package com.demo.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SequenceGenerator {
	
	public static int getNextvalue(String sequenceName) throws Exception
	{
		int iSeqVal = 0;
		
		Connection conn = null;
		
		try
		{
			conn = DBManagerFactory.getDBConnection();
			String sql = "select "+sequenceName+".NEXTVAL from dual";
			PreparedStatement pst = conn.prepareStatement(sql);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next())
			{
				iSeqVal = rs.getInt(1);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		finally
		{
			if(conn!=null)
				conn.close();
		}
		
		
		return iSeqVal;
	}

}
