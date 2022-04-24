package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.Registration;

@Path("/Registration")
public class RegistrationAPI {
	Registration registerObj = new Registration();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readRegister() {
		return registerObj.readRegister();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertRegister(
	 @FormParam("regName") String regName,		
	 @FormParam("regAddress") String regAddress,
	 @FormParam("regEmail") String regEmail,
	 @FormParam("regDate") String regDate,
	 @FormParam("regPNo") String regPNo)
	{
	 String output = registerObj.insertRegister(regName, regAddress, regEmail, regDate, regPNo);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateRegister(String regData)
	{
	//Convert the input string to a JSON object
	 JsonObject regObject = new JsonParser().parse(regData).getAsJsonObject();
	//Read the values from the JSON object
	 String regID = regObject.get("regID").getAsString();
	 String regName = regObject.get("regName").getAsString();
	 String regAddress = regObject.get("regAddress").getAsString();
	 String regEmail = regObject.get("regEmail").getAsString();
	 String regDate = regObject.get("regDate").getAsString();
	 String regPNo = regObject.get("regPNo").getAsString();
	 String output = registerObj.updateRegister(regID, regName, regAddress, regEmail, regDate, regPNo);
	return output;
	} 
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteRegister(String regData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(regData, "", Parser.xmlParser());

	//Read the value from the element <ID>
	 String regID = doc.select("regID").text();
	 String output = registerObj.deleteRegister(regID);
	return output;
	}
}
