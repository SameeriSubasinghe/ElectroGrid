package model;

import java.sql.*; 
public class ODPayment { 
	
 //DB Connection with electrogrid DB
 	
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
	 {e.printStackTrace();} 
	 return con; 
	 } 

	
	
 //Inserting an Overdue Payment
	
 public String insertODPayment(String overdueCode, String totDueAmount, Integer NoDueMonths, String months, String accNo) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the electrogrid database for inserting."; } 
	 // create a prepared statement
	 String query = " insert into odPayments(`ODPaymentID`, `ODCode`, `dueAmount`, `dueMonthsNo`, `dueMonths`, `accountNo`)"
	 + " values (?, ?, ?, ?, ?, ?)"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	 
	 // binding values
	 preparedStmt.setInt(1, 0); 
	 preparedStmt.setString(2, overdueCode); 
	 preparedStmt.setDouble(3, Double.parseDouble(totDueAmount)); 
	 preparedStmt.setInt(4, NoDueMonths); 
	 preparedStmt.setString(5, months);
	 preparedStmt.setString(6, accNo);
	 // execute the statement

	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Inserted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while inserting the Overdue Payement."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
 
		}