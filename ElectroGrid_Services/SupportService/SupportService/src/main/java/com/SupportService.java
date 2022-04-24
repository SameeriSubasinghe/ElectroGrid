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
	
	public String insertComplaint
	(@FormParam("accountNum") String accountNum,
	@FormParam("complaintName") String complaintName,
	@FormParam("complaintAdd") String complaintAdd,
	@FormParam("complaintPhone") String complaintPhone,
	@FormParam("complaintEmail") String complaintEmail,
	@FormParam("complaintMessage") String complaintMessage)
	
	{
	String output = supportObj .insertComplaint(accountNum, complaintName, complaintAdd, complaintPhone,complaintEmail,complaintMessage);
	return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateComplaints(String complaintData)
	{
	//Convert the input string to a JSON object
	JsonObject compObject = new JsonParser().parse(complaintData).getAsJsonObject();
	//Read the values from the JSON object
	String complaintID = compObject.get("complaintID").getAsString();
	String accountNum = compObject.get("accountNum").getAsString();
	String complaintName = compObject.get("complaintName").getAsString();
	String complaintAdd = compObject.get("complaintAdd").getAsString();
	String complaintPhone = compObject.get("complaintPhone").getAsString();
	String complaintEmail = compObject.get("complaintEmail").getAsString();
	String complaintMessage = compObject.get("complaintMessage").getAsString();
	
	String output = supportObj.updateComplaints(complaintID, accountNum,complaintName, complaintAdd, complaintPhone, complaintEmail,complaintMessage);		
			
	return output;
	}
	


	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteComplaint(String ComplaintDetails)
	{
	//Convert the input string to an XML document
	Document doc = Jsoup.parse(ComplaintDetails, "", Parser.xmlParser());
	//Read the value from the element <complaintID>
	String complaintID = doc.select("complaintID").text();
	String output =supportObj .deleteComplaint(complaintID);
	return output;
	}
}

