package com;

import model.SupportS;
import javax.ws.rs.*; //For REST Service
import javax.ws.rs.core.MediaType;
import com.google.gson.*; //For JSON
import org.jsoup.*;//For XML
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/SupportS")

	public class SupportService {
	SupportS supportObj = new SupportS();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readComplaints()
	{
	return supportObj.readComplaints();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String insertComplaint(@FormParam("accountNum") String accountNum,
	@FormParam("complaintName") String complaintName,
	@FormParam("complaintAdd") String complaintAdd,
	@FormParam("complaintPhone") String complaintPhone,
	@FormParam("complaintEmail") String complaintEmail,
	@FormParam("complaintMessage") String complaintMessage)
	
	{
	String output = supportObj .insertComplaint(accountNum, complaintName, complaintAdd, complaintPhone,complaintEmail,complaintMessage);
	return output;
	}

	// //complaintID`,`accountNum`,`complaintName`,`complaintAdd`,`complaintPhone`,`complaintEmail,`complaintMessage`
	
//@PUT
//@Path("/")
//@Consumes(MediaType.APPLICATION_JSON)
//@Produces(MediaType.TEXT_PLAIN)
//public String updateItem(String itemData)
//{
////Convert the input string to a JSON object
//JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
////Read the values from the JSON object
//String itemID = itemObject.get("itemID").getAsString();
//String itemCode = itemObject.get("itemCode").getAsString();
//String itemName = itemObject.get("itemName").getAsString();
//String itemPrice = itemObject.get("itemPrice").getAsString();
//String itemDesc = itemObject.get("itemDesc").getAsString();
//String output = supportObj .updateItem(itemID, itemCode, itemName, itemPrice, itemDesc);
//return output;
//}
//
//@DELETE
//@Path("/")
//@Consumes(MediaType.APPLICATION_XML)
//@Produces(MediaType.TEXT_PLAIN)
//public String deleteItem(String itemData)
//{
////Convert the input string to an XML document
//Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());
////Read the value from the element <itemID>
//String itemID = doc.select("itemID").text();
//String output =supportObj .deleteItem(itemID);
//return output;
//}
}

