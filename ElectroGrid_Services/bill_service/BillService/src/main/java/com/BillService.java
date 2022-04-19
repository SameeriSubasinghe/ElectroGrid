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
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String insertBill(@FormParam("electricityAccountNo") String electricityAccountNo,
	 @FormParam("accountHolderName") String accountHolderName,
	 @FormParam("accountHolderAddress") String accountHolderAddress,
	 @FormParam("billMonth") String billMonth,
	 @FormParam("units") String units
	)
	{
	 String output = bill.insertBill(electricityAccountNo, accountHolderName, accountHolderAddress, billMonth, units);
	 return output;
	}
}
