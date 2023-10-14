package com.atharvakale.facerecognition;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DashBoard extends AppCompatActivity {
    boolean faceRecognized = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);


        Intent intent = getIntent();

        // Retrieve the string extra using the key "message_key"
        String name = intent.getStringExtra("name");
        String acc = intent.getStringExtra("account");
        // Display the received value in a TextView
        TextView messageTextView = findViewById(R.id.dashboardName);
        messageTextView.setText(name);
        Button Myprofile = findViewById(R.id.Myprofile);
         Myprofile.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(DashBoard.this, Myprofile.class);
                 intent.putExtra("account",acc);
                 Toast.makeText(getApplicationContext(),"account: "+acc+" ",Toast.LENGTH_LONG).show();

                 startActivity(intent);

             }
         });

        Button balance = findViewById(R.id.balance);
        balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to launch SecondActivity
                Intent intent = new Intent(DashBoard.this, balance.class);

                // Add values to the Intent using putExtra()
                intent.putExtra("account",acc);
                Toast.makeText(getApplicationContext(),"account: "+acc+" ",Toast.LENGTH_LONG).show();

                startActivity(intent);
            }
        });

        Button transcation = findViewById(R.id.transcation);
        transcation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to launch SecondActivity
                Intent intent = new Intent(DashBoard.this, transcations.class);

                // Add values to the Intent using putExtra()
                intent.putExtra("account",acc);

                startActivity(intent);
            }
        });
        Button transcationHistory = findViewById(R.id.button7);
        transcationHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to launch SecondActivity
                Intent intent = new Intent(DashBoard.this, transactionHistory.class);

                // Add values to the Intent using putExtra()
                intent.putExtra("account",acc);

                startActivity(intent);
            }
        });

    }
    @Override
    public void onBackPressed() {

        Intent resultIntent = new Intent();
        resultIntent.putExtra("fromDashBoard", faceRecognized);
        setResult(Activity.RESULT_OK, resultIntent);
         globalVariables.getInstance().setGlobalVariable(false);

        finish(); // Close the second activity

    }

}