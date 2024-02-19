package fr.mathis150.maths_ranked;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Connection {

    public DatabaseReference matchDatabase;
    public String lastRead = "null";

    Connection() {
        matchDatabase = FirebaseDatabase.getInstance().getReference("match");

        matchDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                lastRead = dataSnapshot.getValue(String.class);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("APPX", "Failed to read value.", error.toException());
            }
        });
    }

    public void Write(String s) {
        matchDatabase.setValue(s);
    }

    public String Read() {
        return lastRead;
    }
}
