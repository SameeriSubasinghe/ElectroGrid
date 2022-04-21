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
		
		
		public String insertBill(String billCode, String electricityAccountNo, String billMonth, String units)
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
				 String query = " insert into bill( `billID`, `billCode`, `electricityAccountNo`,`billMonth`, `units`,`paymentAmount`)"
				 + " values (?, ?, ?, ?, ?, ?)"; 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
		
				 // binding values
				 preparedStmt.setInt(1, 0); 
				 preparedStmt.setString(2, billCode);
				 preparedStmt.setString(3, electricityAccountNo); 
				 preparedStmt.setString(4, billMonth);
				 preparedStmt.setDouble(5, Double.parseDouble(units));
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
				 
				 output = "<table border='1'><tr><th>Bill Code</th> " + "<th>Electricity Account Number</th>"  + "<th>Bill Month</th>" +  "<th>Units</th>" + "<th>Payment Amount</th>" +"<th>Update</th><th>Remove</th></tr>";
			
				 String query = "select * from bill";
				 Statement stmt = con.createStatement();
				 ResultSet rs = stmt.executeQuery(query);
				 
				 // iterate through the rows in the result set
				 while (rs.next())
				 {
					 String billID = Integer.toString(rs.getInt("billID"));
					 String billCode = rs.getString("billCode");
					 String electricityAccountNo = rs.getString("electricityAccountNo");
					 String billMonth = rs.getString("billMonth");
					 String units = Double.toString(rs.getDouble("units"));
					 String paymentAmount = Double.toString(rs.getDouble("paymentAmount"));
					 
				 // Add into the html table
					 output += "<tr><td>" + billCode + "</td>";
					 output += "<td>" + electricityAccountNo + "</td>";
					 output += "<td>" + billMonth + "</td>";
					 output += "<td>" + units + "</td>";
					 output += "<td>" + paymentAmount + "</td>";
					 
				 // buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
				 + "<td><form method='post' action='bills.jsp'>" + "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
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
		
		//Update bill method
		public String updateBill(String billID, String billCode, String electricityAccountNo, String billMonth, String units) {
			
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
				   return "Error while connecting to the database for updating."; 
			   }
			   
			 // create a prepared statement
			 String query = "UPDATE bill SET billCode=?, electricityAccountNo=?, billMonth=?, units=?, paymentAmount=? WHERE billID=?";
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 
				 preparedStmt.setString(1, billCode);
				 preparedStmt.setString(2, electricityAccountNo);
				 preparedStmt.setString(3, billMonth);
				 preparedStmt.setDouble(4, Double.parseDouble(units));
				 preparedStmt.setDouble(5, Double.parseDouble(paymentAmount));
				 preparedStmt.setInt(6, Integer.parseInt(billID));
				 
			    // execute the statement
			 
				 preparedStmt.execute();
			     con.close();
			     output = "Updated successfully";
			 }
			 
			 catch (Exception e)
			 {
				 output = "Error while updating the item.";
				 System.err.println(e.getMessage());
			 }
			 return output;
		}
		
       public String deleteBill(String billID) {
			
			String output = "";
			 try
			 {
			   Connection con = connect();
			   if (con == null)
			   {
				   return "Error while connecting to the database for deleting."; 
			   }
			   
			 // create a prepared statement
			 String query = "delete from bill where billID=?";
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(billID));
			 
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 output = "Deleted successfully";
			 }
			 catch (Exception e)
			 {
				 output = "Error while deleting the bill.";
				 System.err.println(e.getMessage());
			 }
			 return output;
		}
}
