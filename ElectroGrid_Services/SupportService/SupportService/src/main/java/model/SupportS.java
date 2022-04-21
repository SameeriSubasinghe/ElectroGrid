  package model;
  
  import java.sql.*;
  
  public class SupportS {

	//A method to connect to the DB
	private Connection connect()
	{
		Connection con = null;
	try
	{
		Class.forName("com.mysql.jdbc.Driver");	
	//Provide the correct details: DBServer/DBName, username, password
		con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:4306/electrogrid", "root", "");
	}
	catch (Exception e)
		{e.printStackTrace();}
	return con;
	}

	public String insertComplaint(String accountNum, String name, String address, String phoneNum , String email, String complaint)
	{
		String output = "";
	try
	{
		Connection con = connect();
	if (con == null)
	{	return "Error while connecting to the database for inserting."; }

	// create a prepared statement
	String query = " insert into complaints(`complaintID`,`accountNum`,`complaintName`,`complaintAdd`,`complaintPhone`,`complaintEmail`,`complaintMessage`)"
	+ " values (?, ?, ?, ?, ?,?,?)";
	PreparedStatement preparedStmt = con.prepareStatement(query);
	
	// binding values
		preparedStmt.setInt(1, 0);
		preparedStmt.setString(2, accountNum);
		preparedStmt.setString(3, name);
		preparedStmt.setString(4, address);
		preparedStmt.setString(5, phoneNum);
		preparedStmt.setString(6, email);
		preparedStmt.setString(7, complaint);

	// execute the statement
	preparedStmt.execute();
	con.close();
	output = "Inserted successfully";
	}
	catch (Exception e)
	{
		output = "Error while inserting the Complaint.";
		System.err.println(e.getMessage());
	}
	return output;
	}
		public String readComplaints()
	{
	String output = "";
	try
	{
	Connection con = connect();
	if (con == null)
	{return "Error while connecting to the database for reading."; }
	
	// Prepare the html table to be displayed
	output = "<table border='1'><tr><th>Complaint ID</th>"+
			"<th>Account Number</th>" +
			"<th> Name</th>" +
			"<th>Address</th>" +
			"<th>Phone Number</th>" +
			"<th>Email</th>" +
			"<th>Complaint</th>" +
			"<th>Update</th><th>Remove</th></tr>";
	
	String query = "select * from complaints";
	Statement stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery(query);
	
	// iterate through the rows in the result set
	while (rs.next())
	{
	String complaintID= Integer.toString(rs.getInt("complaintID"));
	String accountNum = rs.getString("accountNum");
	String complaintName = rs.getString("complaintName");
	String complaintAdd = rs.getString("complaintAdd");
	String complaintPhone = rs.getString("complaintPhone");
	String complaintEmail = rs.getString("complaintEmail");
	String complaintMessage = rs.getString("complaintMessage");

	// Add into the html table
	output += "<tr><td>" + complaintID + "</td>";
	output += "<td>" + accountNum + "</td>";
	output += "<td>" + complaintName + "</td>";
	output += "<td>" +complaintAdd + "</td>";
	output += "<td>" + complaintPhone+ "</td>";
	output += "<td>" + complaintEmail+ "</td>";
	output += "<td>" +  complaintMessage+ "</td>";

	// buttons
	output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
	+ "<td><form method='post' action='complaints.jsp'>"+"<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	+ "<input name='complaintID' type='hidden' value='" + complaintID
	+ "'>" + "</form></td></tr>";
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
	
		//accountNum, String name, String address, String phoneNum , String email, String complaint
		
		public String updateComplaints(String ID, String accountNum, String name, String address,  String phoneNum,String email, String complaint)
		{
		String output = "";
		try
		{
		Connection con = connect();
		if (con == null)
		{return "Error while connecting to the database for updating."; }
		// create a prepared statement
		String query = "UPDATE complaints SET accountNum=?,complaintName=?,complaintAdd=? ,complaintPhone=?,complaintEmail=?,complaintMessage=?WHERE complaintID=?";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		
		// binding values
		preparedStmt.setString(1, accountNum);
		preparedStmt.setString(2, name);
		preparedStmt.setString(3,address);
		preparedStmt.setString(4, phoneNum);
		preparedStmt.setString(5, email);
		preparedStmt.setString(6,complaint);
		preparedStmt.setInt(7, Integer.parseInt(ID));
		
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
	
	
	
	// Delete Complaints
	public String deleteComplaint(String complaintID)
	{
	String output = "";
	try
	{
	Connection con = connect();
	if (con == null)
	{return "Error while connecting to the database for deleting."; }
	// create a prepared statement
	String query = "delete from complaints where complaintID=?";
	PreparedStatement preparedStmt = con.prepareStatement(query);
	// binding values
	preparedStmt.setInt(1, Integer.parseInt(complaintID));
	// execute the statement
	preparedStmt.execute();
	con.close();
	output = "Deleted successfully";
	}
	catch (Exception e)
	{
	output = "Error while deleting the item.";
	System.err.println(e.getMessage());
	}
	return output;
	}
}