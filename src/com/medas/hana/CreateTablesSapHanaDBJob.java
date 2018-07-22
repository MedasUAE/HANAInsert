package com.medas.hana;


import java.sql.DriverManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.medas.util.DBUtil;



public class CreateTablesSapHanaDBJob {
	

	public static void main(String[] argv) throws Exception {

			try {

				createTable();

			} catch (SQLException e) {

				System.out.println(e.getMessage());

			}

		}

		private static void createTable() throws Exception {

			Connection dbConnection = null;
			Statement statement = null;
			
			 int successCount=0;
		       int failedCount=0;
		       int totalCount=0;
		       String errorText="";

		     
           // String basePath="D:/alam/HMC-NEW/SQL/create/";
		      String basePath="D:/alam/HMC-NEW/SQL/create/";
//            
           String tableNames =DBUtil.readFileAsString(basePath+"tableNames.txt");
//            String tables[]=tableNames.split(",");
            
			//String tableNames = "EQP_RESULTS,IP_ADMISSION,MULTIPLEMAPPING,TEST_RESULTS";
			 String tables[]=tableNames.split(",");
			 
			
           for(String table:tables) {
        	   
        	   String createTableSQL =null;
         
			try {
				createTableSQL =DBUtil.readFileAsString(basePath+table+".sql");
				dbConnection = DBUtil.getConnection();
				statement = dbConnection.createStatement();
                // execute the SQL stetement
				statement.execute(createTableSQL);

				System.out.println("Table  "+table+"  is created!");
				successCount++;

			} catch (Exception e) {

				System.out.println(e.getMessage()+createTableSQL);
				failedCount++;
				errorText+=e.getMessage()+createTableSQL+";";
          	
          	  e.printStackTrace();
          	continue;
		
			} 
           
           }
           
           System.out.println(basePath+":SuccessCount:"+successCount);
		      errorText+=basePath+":  SuccessCount  :"+successCount;
		      System.out.println(basePath+":FailedCount:"+failedCount);
		      errorText+=basePath+":  FailedCount  :"+failedCount;
		      totalCount=successCount+failedCount;
		      System.out.println(basePath+"TotalCount:"+totalCount);
		      errorText+=basePath+":   TotalCount  :"+totalCount;
		      DBUtil.writeLogToFile(basePath+".Errorlog.txt ",errorText);
		      
				if (statement != null) {
					statement.close();
				}

				if (dbConnection != null) {
					dbConnection.close();
				}

		}
	}

