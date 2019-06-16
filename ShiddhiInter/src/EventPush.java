import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EventPush {
	
	private static DatabaseReference childReference;
	private String Event;
	
	
	public String getEvent() {
        return Event;
    }

    public void setId(String Event) {
        this.Event = Event;
    }
    
    public void arshad() {
    	
    	
    	System.out.print("dei dei dei"+Event+"--------------------------------");
    	
    	
    	
    	
    }
    public static class User {

    	  public String date_of_birth;
    	  public String full_name;
    	  public String nickname;

    	  public User(String dateOfBirth, String fullName) {
    	    // ...
    	  }

    	  public User(String dateOfBirth, String fullName, String nickname) {
    	    // ...
    	  }

    	}
    
}


