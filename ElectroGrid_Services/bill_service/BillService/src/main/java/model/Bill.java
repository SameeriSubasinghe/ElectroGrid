package model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Bill {
	
	//A common method to connect to the DB
		private Connection connect()
		 {
		 Connection con = null;
		 try
		 {
		 Class.forName("com.mysql.jdbc.Driver");

		 //Provide the correct details: DBServer/DBName, username, password
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid", "root", "");
		 }
		 catch (Exception e)
		 {
			 e.printStackTrace();
		 }
		 return con;
		} 
		
		
		public String insertBill(String electricityAccountNo, String accountHolderName, String accountHolderAddress, String billMonth, String units)
		{ 
			String output = ""; 
			
			//bill calculation
			//Conversion of the String variable into double
			double unit = Double.parseDouble(units);
			double unitPrice = 50.00;
			//calculation
			double tot = unit*unitPrice;
			String paymentAmount = Double.toString(tot);
		
			try
			{ 
				Connection con = connect(); 
				if (con == null) 
				{ 
					return "Error while connecting to the database for inserting"; 
				} 
				
				// create a prepared statement
				 String query = " insert into bill(`billID`,`electricityAccountNo`,`accountHolderName`,`accountHolderAddress`,`billMonth`, `units`,`paymentAmount`)"
				 + " values (?, ?, ?, ?, ?, ?, ?)"; 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
		
				 // binding values
				 preparedStmt.setInt(1, 0); 
				 preparedStmt.setString(2, electricityAccountNo); 
				 preparedStmt.setString(3, accountHolderName); 
				 preparedStmt.setString(4, accountHolderAddress);
				 preparedStmt.setString(5, billMonth);
				 preparedStmt.setDouble(6, Double.parseDouble(units));
				 preparedStmt.setDouble(7, Double.parseDouble(paymentAmount)); 
				 
		
				 //execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 output = "Inserted successfully"; 
			 } 
			catch (Exception e) 
			 { 
			 output = "Error while inserting"; 
			 System.err.println(e.getMessage()); 
			 } 
		return output; 
		}
		
}
