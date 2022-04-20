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
  @FormParam("accountNo") String accountNo) 
{ 
 String output = ODPObj.insertODPayment(ODCode, dueAmount, dueMonthsNo, dueMonths,accountNo); 
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