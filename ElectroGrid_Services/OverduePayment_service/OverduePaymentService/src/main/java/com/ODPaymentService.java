package com; 
import model.ODPayment; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 
@Path("/ODPayments") 
public class ODPaymentService {
 ODPayment ODPObj = new ODPayment(); 
 
 
@GET
@Path("/") 
@Produces(MediaType.TEXT_HTML) 
public String readODPayment() 
 { 
 return ODPObj.readODPayment(); 
 } 

@POST
@Path("/") 
@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
@Produces(MediaType.TEXT_PLAIN) 
public String insertODPayment(
  @FormParam("ODCode") String ODCode, 
  @FormParam("dueAmount") String dueAmount, 
  @FormParam("dueMonthsNo") Integer dueMonthsNo, 
  @FormParam("dueMonths") String dueMonths,
  @FormParam("accountNo") String accountNo,
  @FormParam("IsSuspend") Boolean IsSuspend
  ) 

{ 
 String output = ODPObj.insertODPayment(ODCode, dueAmount, dueMonthsNo, dueMonths,accountNo,IsSuspend); 
return output; 
}



@PUT
@Path("/") 
@Consumes(MediaType.APPLICATION_JSON) 
@Produces(MediaType.TEXT_PLAIN) 
public String updateODPayment(String ODPData) 
{ 
	
//Convert the input string to a JSON object 
JsonObject ODPObject = new JsonParser().parse(ODPData).getAsJsonObject(); 


//Read the values from the JSON object
String ODPaymentID = ODPObject.get("ODPaymentID").getAsString(); 
String ODCode = ODPObject.get("ODCode").getAsString(); 
String dueAmount = ODPObject.get("dueAmount").getAsString(); 
String dueMonthsNo = ODPObject.get("dueMonthsNo").getAsString(); 
String dueMonths = ODPObject.get("dueMonths").getAsString(); 
String accountNo = ODPObject.get("accountNo").getAsString(); 
String IsSuspend = ODPObject.get("IsSuspend").getAsString();
String output =ODPObj.updateODPayment(ODPaymentID, ODCode, dueAmount, dueMonthsNo, dueMonths, accountNo,IsSuspend); 
return output; 
}


@DELETE
@Path("/") 
@Consumes(MediaType.APPLICATION_XML) 
@Produces(MediaType.TEXT_PLAIN) 


public String deleteODPayment(String ODPData) 
{ 
//Convert the input string to an XML document
Document doc = Jsoup.parse(ODPData, "", Parser.xmlParser()); 

//Read the value from the element <ODPaymentID>
String ODPaymentID = doc.select("ODPaymentID").text(); 
String output = ODPObj.deleteODPayment(ODPaymentID); 
return output; 
}






}