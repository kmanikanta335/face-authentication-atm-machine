package com.atharvakale.facerecognition;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class balance extends AppCompatActivity {
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        Intent intent = getIntent();
        String acc = intent.getStringExtra("account");
        Toast.makeText(getApplicationContext(),"account: "+acc+"",Toast.LENGTH_LONG).show();


        // Get a reference to the TextView where you want to display the retrieved data
        TextView dataTextView = findViewById(R.id.textView4);

        // Attach a listener to retrieve the data
        databaseReference.child(acc).child("0").child("myprofile").child("0").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Long balance = snapshot.child("Balance").getValue(Long.class);
                dataTextView.setText("Balance: "+balance);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }



                // This method is called once with the initial value and again
                // whenever data at this location is updated.





        });
    }
}