import java.io.IOException;
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
import io.siddhi.core.query.output.callback.QueryCallback;
import io.siddhi.core.stream.input.InputHandler;
import io.siddhi.core.stream.output.StreamCallback;
import io.siddhi.core.util.EventPrinter;


public class connector {

    public static void main(String[] args) throws InterruptedException {
    	
    	BasicConfigurator.configure();


        SiddhiManager siddhiManager = new SiddhiManager();
        mongo ss=new mongo();
        
        //////////////////////////// A Question //////////////////////////////
        

		String QueryA =
                "define stream Astream (Ticket_number string,Make string); " +
                "from Astream#window.time(1 min) " +
                "select count(Ticket_number) AS CountOfCarTypes,Make " +
                "group by Make "+
                "insert into questiona; ";


        SiddhiAppRuntime siddhiAppRuntimeA = siddhiManager.createSiddhiAppRuntime(QueryA);
        
        
        siddhiAppRuntimeA.addCallback("questiona", new StreamCallback() {
        	
			@Override
			public void receive(Event[] inEvents) {
				
				 EventPrinter.print(inEvents);
	             String chn=inEvents[0].toString();
	             String chng=chn.substring(5).replace("=", ":");
	             JSONObject obj = new JSONObject(chng);
	             long timestamp=obj.getLong("timestamp");
	             JSONArray arr = obj.getJSONArray("data");
	             ss.FirstQuest(timestamp, arr.get(0).toString(), arr.get(1).toString());
//	             System.out.println(chn);
//	             System.out.println(arr.get(0).toString());
//	             System.out.println(arr.get(1).toString());
//	             System.out.println(arr.get(2).toString());
				
			}

      });
        
        InputHandler inputHandlerA = siddhiAppRuntimeA.getInputHandler("Astream");
        
        siddhiAppRuntimeA.start(); 
        
       
        
        
       ////////////////////////////////////Question B ////////////////////////////////////////////////////////////////
        
        String QueryB ="" +
                "define stream Bstream (Violation_code string,Ticket_number string,Fine_amount double); " +
				"@info(name = 'query1')"+
                "from Bstream[Fine_amount>50]#window.timeBatch(1 min) " +
                "select Violation_code,Ticket_number,avg(Fine_amount) as fmm " +
                "group by Violation_code "+
                "insert into OutputStream ;";


        SiddhiAppRuntime siddhiAppRuntimeB = siddhiManager.createSiddhiAppRuntime(QueryB);
       
        siddhiAppRuntimeB.addCallback("OutputStream", new StreamCallback() {
        	
			@Override
			public void receive(Event[] inEvents) {
				
				 EventPrinter.print(inEvents);
	             String chn=inEvents[0].toString();
	             String chng=chn.substring(5).replace("=", ":");
	             JSONObject obj = new JSONObject(chng);
	             long timestamp=obj.getLong("timestamp");
	             JSONArray arr = obj.getJSONArray("data");
	             ss.secondQuest(timestamp, arr.get(0).toString(), arr.get(1).toString(), arr.get(2).toString());
//	             System.out.println(chn);
//	             System.out.println(arr.get(0).toString());
//	             System.out.println(arr.get(1).toString());
//	             System.out.println(arr.get(2).toString());
				
			}

      });
        
        InputHandler inputHandlerB = siddhiAppRuntimeB.getInputHandler("Bstream");
        
        siddhiAppRuntimeB.start();
        
        ///////////////////////////////////Question c/////////////////////////////////////////////////
        
        String QueryC ="" +
                "define stream Cstream (Violation_code string,Ticket_number string,Violation_Description string); " +
                "from Cstream#window.time(20 millisecond) " +
                "select count(Ticket_number) as tkk,Violation_code,Violation_Description " +
                "group by Violation_code "+
                "insert into OutputStreamC ;";


        SiddhiAppRuntime siddhiAppRuntimeC = siddhiManager.createSiddhiAppRuntime(QueryC);
       

        siddhiAppRuntimeC.addCallback("OutputStreamC", new StreamCallback() {
        	
			@Override
			public void receive(Event[] inEvents) {
				
				 EventPrinter.print(inEvents);
	             String chn=inEvents[0].toString();
	             //System.currentTimeMillis()
	             
	             String chng=chn.substring(5).replace("=", ":");
	             JSONObject obj = new JSONObject(chng);
	             long timestamp=obj.getLong("timestamp");
	             JSONArray arr = obj.getJSONArray("data");
	             ss.thirdQuest(timestamp, arr.get(0).toString(), arr.get(1).toString(), arr.get(2).toString());
	             //System.out.println();
//	             System.out.println(arr.get(0).toString());
//	             System.out.println(arr.get(1).toString());
//	             System.out.println(arr.get(2).toString());
				
			}

      });
        
        InputHandler inputHandlerC = siddhiAppRuntimeC.getInputHandler("Cstream");
        
        siddhiAppRuntimeC.start();
        
        
       ///////////////////////////////////////Question 4 ///////////////////////////////////////////////////////////////
        

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
//	             System.out.println(arr.get(0).toString());
//	             System.out.println(arr.get(1).toString());
//	             System.out.println(arr.get(2).toString());
				
			}

      });
        
        InputHandler inputHandlerD = siddhiAppRuntimeD.getInputHandler("Dstream");
        
        siddhiAppRuntimeD.start();
        
        
        /////////////////////////////////  KAFKA CONSUMER   ///////////////////////////////////////////////
        
        Properties properties = new Properties();

        // kafka bootstrap server
        properties.setProperty("bootstrap.servers", "192.168.99.100:9092");
        properties.setProperty("key.deserializer", StringDeserializer.class.getName());
        properties.setProperty("value.deserializer", StringDeserializer.class.getName());

        properties.setProperty("group.id", "test");
        properties.setProperty("enable.auto.commit", "false");
//        properties.setProperty("auto.commit.interval.ms", "1000");
        properties.setProperty("auto.offset.reset", "earliest");

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(properties);
        kafkaConsumer.subscribe(Arrays.asList("parking"));
        
        
        while(true) {
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(100);
            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
            	JSONObject obj = new JSONObject(consumerRecord.value());
           	    inputHandlerA.send(new Object[] {obj.getString("Ticket_number"),obj.getString("Make")});
           	    inputHandlerB.send(new Object[] {obj.getString("Violation_code"),obj.getString("Ticket_number"),obj.getDouble("Fine_amount")});
           	    inputHandlerC.send(new Object[] {obj.getString("Violation_code"),obj.getString("Ticket_number"),obj.getString("Violation_Description")});
             	inputHandlerD.send(new Object[] {obj.getDouble("Fine_amount"),obj.getString("RP_State_Plate")});
                System.out.println("Subscriber Data is "+obj.getString("Ticket_number")+"---------------"+consumerRecord.value());
            	 
            	 Thread.sleep(500);
            	 
            	 
            	 
            }
            //kafkaConsumer.commitSync();
           // siddhiAppRuntimeA.shutdown();
          //  siddhiManager.shutdown();
        }
        
        
    }
}



