import java.io.IOException;

import org.json.JSONObject;

public class JsonTry {
	
	public static void main(String[] args) {
		
		String Jss="{'Ticket number': '1103341116',\r\n" + 
				" 'Issue Date': '2015-12-21T00:00:00',\r\n" + 
				" 'Issue time': '1251',\r\n" + 
				" 'Meter Id': '',\r\n" + 
				" 'Marked Time': '',\r\n" + 
				" 'RP State Plate': 'CA',\r\n" + 
				" 'Plate Expiry Date': '200304',\r\n" + 
				" 'VIN': '',\r\n" + 
				" 'Make': 'HOND',\r\n" + 
				" 'Body Style': 'PA',\r\n" + 
				" 'Color': 'GY',\r\n" + 
				" 'Location': '13147 WELBY WAY',\r\n" + 
				" 'Route': '1521',\r\n" + 
				" 'Agency': '1',\r\n" + 
				" 'Violation code': '4000A1',\r\n" + 
				" 'Violation Description': 'NO EVIDENCE OF REG',\r\n" + 
				" 'Fine amount': '50',\r\n" + 
				" 'Latitude': '99999',\r\n" + 
				" 'Longitude': '99999'}";
	      JSONObject obj = new JSONObject(Jss);
	      System.out.println(obj.getString("Violation code"));
	      System.out.println(obj.get("Ticket number"));
	      System.out.println(obj.get("Fine amount"));
	      FIreBaseCon fire=new FIreBaseCon();
          
          try {
				fire.initFirebase(obj.getString("Violation code"));
			} catch (IOException e) {
				
				e.printStackTrace();
				System.out.println("Error Inside Siddhi Out Put Stream Passing");
			}

	    
	   }

}




