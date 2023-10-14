package com.atharvakale.facerecognition;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Myprofile extends AppCompatActivity {
   private ListView listView;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);

        Intent intent = getIntent();
        String acc = intent.getStringExtra("account");
        listView =findViewById(R.id.listView);

        ArrayList<String> list =new ArrayList<>();
        ArrayAdapter adapter =new ArrayAdapter <String>( this,R.layout.list_item,list);
      listView.setAdapter(adapter);
        databaseReference = FirebaseDatabase.getInstance().getReference().child(acc).child("0").child("myprofile").child("0");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                   list.add(snapshot1.getKey().toString()+" : "+snapshot1.getValue().toString());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}