package com;

import model.Bill;


//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Bills")

public class BillService {

	Bill bill = new Bill();
	
	//Read Bills
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	
	public String readBills()
	{
		return bill.readBills();
	}
	
	//Insert Bills
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String insertBill( @FormParam("billCode") String billCode,
	 @FormParam("electricityAccountNo") String electricityAccountNo,
	 @FormParam("billMonth") String billMonth,
	 @FormParam("units") String units
	)
	{
		 String output = bill.insertBill(billCode, electricityAccountNo,  billMonth, units);
		 return output;
	}
	
	
	//Update Bills
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateBill(String billData)
	{
		//Convert the input string to a JSON object
		 JsonObject billObject = new JsonParser().parse(billData).getAsJsonObject(); 
		 
		//Read the values from the JSON object
		 String billID = billObject.get("billID").getAsString();
		 String billCode = billObject.get("billCode").getAsString();
		 String electricityAccountNo = billObject.get("electricityAccountNo").getAsString();
		 String billMonth = billObject.get("billMonth").getAsString();
		 String units = billObject.get("units").getAsString();
		 
		 String output = bill.updateBill(billID, billCode, electricityAccountNo, billMonth, units);
		 
		return output;
	}
	
	
	//Delete bills
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteBill(String billData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(billData, "", Parser.xmlParser());

	//Read the value from the element <billID>
	 String billID = doc.select("billID").text();
	 String output = bill.deleteBill(billID);
	return output;
	}
	
}
