package com.medas.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	public static Connection getConnection() {
		Connection connection = null;
		try {
		   Class.forName("com.sap.db.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:sap://192.168.0.151:39015/?autocommit=false&currentschema=ECLINIC_HMC_NEW&user=SYSTEM&password=Medteam2013");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		 catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	/**
	 * Save the given text to the given filename.
	 * @param canonicalFilename Like /Users/al/foo/bar.txt
	 * @param text All the text you want to save to the file as one String.
	 * @throws IOException
	 */
	public static void writeLogToFile(String canonicalFilename, String text) 
	throws IOException
	{
		creatFile(canonicalFilename);
	  File file = new File (canonicalFilename);
	  BufferedWriter out = new BufferedWriter(new FileWriter(file)); 
	  out.write(text);
	  out.close();
	}
	
	public static void creatFile(String path) {
		 try {
		     File file = new File(path);
		     /*If file gets created then the createNewFile() 
		      * method would return true or if the file is 
		      * already present it would return false
		      */
	             boolean fvar = file.createNewFile();
		     if (fvar){
		          System.out.println("File has been created successfully");
		     }
		     else{
		          System.out.println("File already present at the specified location");
		     }
	    	} catch (IOException e) {
	    		System.out.println("Exception Occurred:");
		        e.printStackTrace();
		  }
	   }
	

	public static String readFileAsString(String fileName)throws Exception
	  {
	    String data = "";
	    data = new String(Files.readAllBytes(Paths.get(fileName)));
	    return data;
	  }
	

}
