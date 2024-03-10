package fr.mathis150.maths_ranked;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NonBlocking;

public class Connection {

    public CollectionReference database;

    Connection(String col) {
        database = FirebaseFirestore.getInstance().collection(col);
    }

    public void Write(Object data) {
        database.add(data).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d("FireStore", "Successfully pushed data");
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("FireStore", "Failed to push data");
            }
        });
    }

    public Task<QuerySnapshot> FindEloRange(Integer min,Integer max) {
        return database.whereLessThan("elo",max).whereGreaterThan("elo",min).get();
    }

    public Task<QuerySnapshot> FindUsername(String username) {
        Log.d("Database","Fetching with username");
        return database.whereEqualTo("username",username).get();
    }
}
