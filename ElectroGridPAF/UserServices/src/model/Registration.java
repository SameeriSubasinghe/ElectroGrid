package model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Registration {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/epower?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertRegister(String regName, String regAddress, String regEmail, String regDate, String regPNo) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into registerapi(`regID`,`regName`,`regAddress`,`regEmail`,`regDate`,`regPNo`)"
					+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, regName);
			preparedStmt.setString(3, regAddress);
			preparedStmt.setString(4, regEmail);
			preparedStmt.setString(5, regDate);
			preparedStmt.setString(6, regPNo);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the customer.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readRegister() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Customer ID</th><th>Customer Name</th><th>Address</th><th>Email</th><th>Date</th><th>Contact No</th></tr>";
			String query = "select * from registerapi";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String regID = Integer.toString(rs.getInt("regID"));
				String regName = rs.getString("regName");
				String regAddress = rs.getString("regAddress");
				String regEmail = rs.getString("regEmail");
				String regDate = rs.getString("regDate");
				String regPNo = rs.getString("regPNo");

				// Add into the html table
				output += "<tr><td>" + regID + "</td>";
				output += "<td>" + regName + "</td>";
				output += "<td>" + regAddress + "</td>";
				output += "<td>" + regEmail + "</td>";
				output += "<td>" + regDate + "</td>";
				output += "<td>" + regPNo + "</td>";
				
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the customer.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateRegister(String regID, String regName, String regAddress, String regEmail, String regDate, String regPNo) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE registerapi SET regName=?,regAddress=?,regEmail=?,regDate=?,regPNo=?" + "WHERE regID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, regName);
			preparedStmt.setString(2, regAddress);
			preparedStmt.setString(3, regEmail);
			preparedStmt.setString(4, regDate);
			preparedStmt.setString(5, regPNo);
			preparedStmt.setInt(6, Integer.parseInt(regID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the customer.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String deleteRegister(String regID) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from registerapi where regID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(regID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the customer.";
			System.err.println(e.getMessage());
		}

		return output;
	}

}
