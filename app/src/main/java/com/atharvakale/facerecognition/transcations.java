package com.atharvakale.facerecognition;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class transcations extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transcations);

        Button deposite = findViewById(R.id.deposite);
        Button withdraw = findViewById(R.id.withdraw);



        deposite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendepositeDialog();
            }
        });

        withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openwithdrawDialog();
            }
        });

    }
    private void opendepositeDialog() {
        DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference();
       androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter the amount to deposite");

        // Set up the input
        final EditText input1 = new EditText(this);


        input1.setInputType(InputType.TYPE_CLASS_TEXT );
        builder.setView(input1);






        // Set up the buttons
        builder.setPositiveButton("ADD", new DialogInterface.OnClickListener() {


                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(context, input.getText().toString(), Toast.LENGTH_SHORT).show();
                        //Create and Initialize new object with Face embeddings and Name.
                        String inputText = input1.getText().toString();
                        double numericValue = Double.parseDouble(inputText);
                        Intent intent = getIntent();
                        String acc = intent.getStringExtra("account");

                        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child(acc).child("0").child("Transactions");
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                        String transactionDate = sdf.format(new Date()); // Get the current date

// Create an item


// Create an item
                        Item item = new Item(inputText, "Deposite",transactionDate);

// Push the item to the database
                        databaseRef.push().setValue(item);


                        databaseReference.child(acc).child("0").child("myprofile").child("0").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    // Retrieve the numeric value
                                    Double balance = snapshot.child("Balance").getValue(Double.class);
                                    balance = balance+numericValue;



                                        // Use the reference to update the value in Firebase Database
                                        databaseReference.child(acc).child("0").child("myprofile").child("0").child("Balance").setValue(balance);
                                        Toast.makeText(getApplicationContext(),"amount deposited succesfully",Toast.LENGTH_LONG).show();


                                    // Use the numericValue in your code
                                    // For example, display it in a TextView or perform calculations
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }


                // Create a unique key for the new data





        });


                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                });

                builder.show();
            }

    private void openwithdrawDialog() {
        DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference();
        androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter the amount to withdraw");

        // Set up the input
        final EditText input1 = new EditText(this);


        input1.setInputType(InputType.TYPE_CLASS_TEXT );
        builder.setView(input1);






        // Set up the buttons
        builder.setPositiveButton("withdraw", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(context, input.getText().toString(), Toast.LENGTH_SHORT).show();
                //Create and Initialize new object with Face embeddings and Name.
                String inputText = input1.getText().toString();
                double numericValue = Double.parseDouble(inputText);
                Intent intent = getIntent();
                String acc = intent.getStringExtra("account");

                DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child(acc).child("0").child("Transactions");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                String transactionDate = sdf.format(new Date()); // Get the current date

// Create an item
                Item item = new Item(inputText, "Withdraw",transactionDate);

// Push the item to the database
                databaseRef.push().setValue(item);

                databaseReference.child(acc).child("0").child("myprofile").child("0").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            // Retrieve the numeric value
                            Double balance = snapshot.child("Balance").getValue(Double.class);
                            balance = balance-numericValue;



                            // Use the reference to update the value in Firebase Database
                            databaseReference.child(acc).child("0").child("myprofile").child("0").child("Balance").setValue(balance);
                            Toast.makeText(getApplicationContext(),"amount withdraw succesfully",Toast.LENGTH_LONG).show();


                            // Use the numericValue in your code
                            // For example, display it in a TextView or perform calculations
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });

        builder.show();
    }


}

