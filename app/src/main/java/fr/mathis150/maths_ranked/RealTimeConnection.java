package fr.mathis150.maths_ranked;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RealTimeConnection {

    public DatabaseReference database;

    RealTimeConnection(String path) {
        database = FirebaseDatabase.getInstance().getReference(path);
    }

    public void Write(String s) {
        database.setValue(s);
    }
}
