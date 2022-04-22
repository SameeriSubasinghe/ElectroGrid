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

	/*@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readRegister() {
		return registerObj.readRegister();
	}*/
	
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
	
	/*@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateRegister(String regData)
	{
	}*/
	}


