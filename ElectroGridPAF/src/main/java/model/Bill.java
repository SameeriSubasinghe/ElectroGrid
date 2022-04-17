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
		
		
		public String insertBill(String electricityAccountNo, String accountHolderName, String accountHolderAddress, String billMonth, String paymentAmount)
		{ 
			String output = ""; 
		
			try
			{ 
				Connection con = connect(); 
				if (con == null) 
				{ 
					return "Error while connecting to the database for inserting"; 
				} 
				
				// create a prepared statement
				 String query = " insert into bill(`billID`,`electricityAccountNo`,`accountHolderName`,`accountHolderAddress`,`billMonth`, `paymentAmount`)"
				 + " values (?, ?, ?, ?, ?, ?)"; 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
		
				 // binding values
				 preparedStmt.setInt(1, 0); 
				 preparedStmt.setString(2, electricityAccountNo); 
				 preparedStmt.setString(3, accountHolderName); 
				 preparedStmt.setString(4, accountHolderAddress);
				 preparedStmt.setString(5, billMonth);
				 preparedStmt.setDouble(6, Double.parseDouble(paymentAmount)); 
				 
		
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
		
		public String readBills()
		   {
			 String output = "";
			 
			 try
		     {
				 Connection con = connect();
				 if (con == null)
				 {
					 return "Error while connecting to the database for reading."; 
				 }
				 
				 // Prepare the html table to be displayed
				 
				 output = "<table border='1'><tr><th>Electricity Account Number</th> " + "<th>Account Holder Name</th>" + "<th>Account Holder Address</th>" + "<th>Bill Month</th>" + "<th>Payment Amount</th>" +"<th>Update</th><th>Remove</th></tr>";
			
				 String query = "select * from bill";
				 Statement stmt = con.createStatement();
				 ResultSet rs = stmt.executeQuery(query);
				 
				 // iterate through the rows in the result set
				 while (rs.next())
				 {
					 String billID = Integer.toString(rs.getInt("billID"));
					 String electricityAccountNo = rs.getString("electricityAccountNo");
					 String accountHolderName = rs.getString("accountHolderName");
					 String accountHolderAddress = rs.getString("accountHolderAddress");
					 String billMonth = rs.getString("billMonth");
					 String paymentAmount = Double.toString(rs.getDouble("paymentAmount"));
					 
				 // Add into the html table
					 output += "<tr><td>" + electricityAccountNo + "</td>";
					 output += "<td>" + accountHolderName + "</td>";
					 output += "<td>" + accountHolderAddress + "</td>";
					 output += "<td>" + billMonth + "</td>";
					 output += "<td>" + paymentAmount + "</td>";
					 
				 // buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
				 + "<td><form method='post' action='items.jsp'>" + "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
				 + "<input name='itemID' type='hidden' value='" + billID + "'>" + "</form></td></tr>";
				 }
				 
				 con.close();
				 // Complete the html table
				 output += "</table>";
				 }
				 catch (Exception e)
				 {
					 output = "Error while reading the items.";
					 System.err.println(e.getMessage());
				 }
				 return output;
				 } 

}
