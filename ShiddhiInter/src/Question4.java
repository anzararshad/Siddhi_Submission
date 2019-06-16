import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.log4j.BasicConfigurator;
import org.json.JSONArray;
import org.json.JSONObject;

import io.siddhi.core.SiddhiAppRuntime;
import io.siddhi.core.SiddhiManager;
import io.siddhi.core.event.Event;
import io.siddhi.core.stream.input.InputHandler;
import io.siddhi.core.stream.output.StreamCallback;
import io.siddhi.core.util.EventPrinter;

public class Question4 {


	public static void main(String[] args) throws InterruptedException{
		BasicConfigurator.configure();
		   
        SiddhiManager siddhiManager = new SiddhiManager(); 
        mongo ss=new mongo();

			String QueryD ="" +
	                "define stream Dstream (Fine_amount double,RP_State_Plate string); " +
	                "from Dstream#window.timeBatch(1 min) " +
	                "select max(Fine_amount) as MaXFine,RP_State_Plate " +
	                "group by RP_State_Plate "+
	                "insert into OutputStreamD ;";


            SiddhiAppRuntime siddhiAppRuntimeD = siddhiManager.createSiddhiAppRuntime(QueryD);
           

	        siddhiAppRuntimeD.addCallback("OutputStreamD", new StreamCallback() {
	        	
				@Override
				public void receive(Event[] inEvents) {
					
					 EventPrinter.print(inEvents);
		             String chn=inEvents[0].toString();
		             //System.currentTimeMillis()
		             
		             String chng=chn.substring(5).replace("=", ":");
		             JSONObject obj = new JSONObject(chng);
		             long timestamp=obj.getLong("timestamp");
		             JSONArray arr = obj.getJSONArray("data");
		             ss.fourth(timestamp, arr.get(0).toString(), arr.get(1).toString());
		             //System.out.println();
//		             System.out.println(arr.get(0).toString());
//		             System.out.println(arr.get(1).toString());
//		             System.out.println(arr.get(2).toString());
					
				}

	      });
	        
	        String Jss="{'Ticket_number': '1103341116',\r\n" + 
					" 'Issue_Date': '2015-12-21T00:00:00',\r\n" + 
					" 'Issue_time': '1251',\r\n" + 
					" 'Meter_Id': '',\r\n" + 
					" 'Marked_Time': '',\r\n" + 
					" 'RP_State_Plate': 'CA',\r\n" + 
					" 'Plate_Expiry_Date': '200304',\r\n" + 
					" 'VIN': '',\r\n" + 
					" 'Make': 'HOND',\r\n" + 
					" 'Body_Style': 'PA',\r\n" + 
					" 'Color': 'GY',\r\n" + 
					" 'Location': '13147 WELBY WAY',\r\n" + 
					" 'Route': '1521',\r\n" + 
					" 'Agency': '1',\r\n" + 
					" 'Violation_code': '4000A1',\r\n" + 
					" 'Violation_Description': 'NO EVIDENCE OF REG',\r\n" + 
					" 'Fine_amount': '50',\r\n" + 
					" 'Latitude': '99999',\r\n" + 
					" 'Longitude': '99999'}";
	        String Jss2="{'Ticket_number': '1106500452',\r\n" + 
	        		" 'Issue_Date': '2015-12-17T00:00:00',\r\n" + 
	        		" 'Issue_time': '1710',\r\n" + 
	        		" 'Meter_Id': '',\r\n" + 
	        		" 'Marked_Time': '',\r\n" + 
	        		" 'RP_State_Plate': 'CA',\r\n" + 
	        		" 'Plate_Expiry_Date': '201605',\r\n" + 
	        		" 'VIN': '',\r\n" + 
	        		" 'Make': 'MAZD',\r\n" + 
	        		" 'Body_Style': 'PA',\r\n" + 
	        		" 'Color': 'BL',\r\n" + 
	        		" 'Location': 'SUNSET/ALVARADO',\r\n" + 
	        		" 'Route': '217',\r\n" + 
	        		" 'Agency': '1',\r\n" + 
	        		" 'Violation_code': '8070',\r\n" + 
	        		" 'Violation_Description': 'PARK IN GRID LOCK ZN',\r\n" + 
	        		" 'Fine_amount': '163',\r\n" + 
	        		" 'Latitude': '99999',\r\n" + 
	        		" 'Longitude': '99999'}";
	        
//	        JSONObject obj = new JSONObject(Jss);
//	        JSONObject obj2 = new JSONObject(Jss2);
//	        
	        InputHandler inputHandlerD = siddhiAppRuntimeD.getInputHandler("Dstream");
	        
	        siddhiAppRuntimeD.start();
//	        int x=10;
//	        while(x<20) {
//	        inputHandlerD.send(new Object[] {obj.getDouble("Fine_amount"),obj.getString("RP_State_Plate")});
//	        inputHandlerD.send(new Object[] {obj2.getDouble("Fine_amount"),obj2.getString("RP_State_Plate")});
//	        inputHandlerD.send(new Object[] {obj.getDouble("Fine_amount"),obj.getString("RP_State_Plate")});
//	        inputHandlerD.send(new Object[] {obj2.getDouble("Fine_amount"),obj2.getString("RP_State_Plate")});
//	        x++;
//	        }
//	        
//	        Thread.sleep(500);
//	        //Shutdown runtime
//	      
//	       
//	        siddhiAppRuntimeD.shutdown();
//
//	        siddhiManager.shutdown();
	        
  /////////////////////////////////  KAFKA CONSUMER   ///////////////////////////////////////////////
	        
	        Properties properties = new Properties();

	        // kafka bootstrap server
	        properties.setProperty("bootstrap.servers", "192.168.99.100:9092");
	        properties.setProperty("key.deserializer", StringDeserializer.class.getName());
	        properties.setProperty("value.deserializer", StringDeserializer.class.getName());

	        properties.setProperty("group.id", "test");
	        properties.setProperty("enable.auto.commit", "false");
//	        properties.setProperty("auto.commit.interval.ms", "1000");
	        properties.setProperty("auto.offset.reset", "earliest");

	        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(properties);
	        kafkaConsumer.subscribe(Arrays.asList("parking"));
	        
	        
	        while(true) {
	            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(100);
	            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
	            	JSONObject obj3 = new JSONObject(consumerRecord.value());
	           	    inputHandlerD.send(new Object[] {obj3.getDouble("Fine_amount"),obj3.getString("RP_State_Plate")});
	           	 
	                System.out.println("Subscriber Data is "+obj3.getString("Violation_code")+"---------------"+consumerRecord.value());
	            	 
	            	 Thread.sleep(500);
	   	 
	            }
	            //kafkaConsumer.commitSync();
	           // siddhiAppRuntimeA.shutdown();
	          //  siddhiManager.shutdown();
	        }
}
}