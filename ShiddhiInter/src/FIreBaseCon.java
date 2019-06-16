import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FIreBaseCon {
	
	private static DatabaseReference firebaseDatabase;
	
	public static void main(String[] args) throws IOException {
		initFirebase("mdnakj");
		
	}
	
		
	
public static  void initFirebase(String asd) throws IOException {
	


try {
            
            FirebaseOptions firebaseOptions = new FirebaseOptions.Builder()
                    .setDatabaseUrl("https://cepsiddhi.firebaseio.com/")
                    .setCredential((FirebaseCredentials.fromCertificate(new FileInputStream(new File("F:\\ShiddhiInter\\cepsiddhi-firebase-adminsdk-0ilc3-207ddbafcc.json")))))
                    .build();
            FirebaseApp.initializeApp(firebaseOptions);
            
            firebaseDatabase = FirebaseDatabase.getInstance().getReference();
             
         
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

            
}
}
