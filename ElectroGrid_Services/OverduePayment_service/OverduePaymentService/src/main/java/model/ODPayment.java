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
	
 public String insertODPayment(String overdueCode, String totDueAmount, Integer noDueMonths, String months, String accNo, Boolean suspend) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the electrogrid database for inserting."; }
	 
	 
	 // create a prepared statement
	 String query = " insert into odPayments(`ODPaymentID`, `ODCode`, `dueAmount`, `dueMonthsNo`, `dueMonths`, `accountNo`, `IsSuspend`)"
	 + " values (?, ?, ?, ?, ?, ?, ? )"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	 
	 // binding values
	 preparedStmt.setInt(1, 0); 
	 preparedStmt.setString(2, overdueCode); 
	 preparedStmt.setDouble(3, Double.parseDouble(totDueAmount)); 
	 preparedStmt.setInt(4, noDueMonths); 
	 preparedStmt.setString(5, months);
	 preparedStmt.setString(6, accNo);
	 preparedStmt.setBoolean(7, suspend);
	 
	 
	 // execute the statement

	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Inserted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while inserting the Overdue Payment."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
 


//Reading Overdue Payments from the DB

	public String readODPayment() 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for reading.";
	 } 
	 
	 // html table with OD payment details to be displayed
	 output = "<table border='1'><tr><th>Overdue Payment Code</th><th>Total due Amount</th>" +
	 "<th>No of Due months</th>" + 
	 "<th>Due months</th>" +
	 "<th>Account No</th>" +
	 "<th>Suspend or Not</th>" +
	 "<th>Update</th><th>Remove</th></tr>"; 
	 
	 String query = "select * from odPayments"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query);
	 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String ODPaymentID = Integer.toString(rs.getInt("ODPaymentID")); 
	 String ODCode = rs.getString("ODCode"); 
	 String dueAmount = Double.toString(rs.getDouble("dueAmount")); 
	 String dueMonthsNo = Integer.toString(rs.getInt("dueMonthsNo")); 
	 String dueMonths = rs.getString("dueMonths");
	 String accountNo = rs.getString("accountNo");
	 String IsSuspend = Boolean.toString(rs.getBoolean("IsSuspend"));
	 
	 // Add into the html table
	 output += "<tr><td>" + ODCode + "</td>"; 
	 output += "<td>" + dueAmount + "</td>"; 
	 output += "<td>" + dueMonthsNo + "</td>"; 
	 output += "<td>" + dueMonths + "</td>"; 
	 output += "<td>" + accountNo + "</td>"; 
	 output += "<td>" + IsSuspend + "</td>"; 
	 
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
	 + "<td><form method='post' action='items.jsp'>"+"<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	 + "<input name='ODPaymentID' type='hidden' value='" + ODPaymentID 
	 + "'>" + "</form></td></tr>"; 
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the Overdue Payments."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	
	
	
	//Update Overdue Payments from the DB
	
	public String updateODPayment(String ID, String overdueCode, String totDueAmount, String noDueMonths, String months, String accNo, String suspend)
	{ 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for updating.";
		 } 
		 
		 
		 // create a prepared statement to update
		 String query = "UPDATE odpayments SET ODCode=?,dueAmount=?,dueMonthsNo=?,dueMonths=?,accountNo=?,IsSuspend=? WHERE ODPaymentID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		 // binding values
		 preparedStmt.setString(1, overdueCode);
		 preparedStmt.setDouble(2, Double.parseDouble(totDueAmount));
		 preparedStmt.setInt(3, Integer.parseInt(noDueMonths));
		 preparedStmt.setString(4, months); 
		 preparedStmt.setString(5, accNo); 
		 preparedStmt.setBoolean(6, Boolean.parseBoolean(suspend));
		 preparedStmt.setInt(7, Integer.parseInt(ID));
		 
		 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Updated successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while updating the Overdue Payment."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
	
	

	
	
	
	
	
  //Deleting Overdue Payments 
	
  public String deleteODPayment(String ODPaymentID) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for deleting."; } 
		 // create a prepared statement
		 String query = "delete from odpayments where ODPaymentID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(ODPaymentID)); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Deleted successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while deleting the overdue payment."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
  
		}