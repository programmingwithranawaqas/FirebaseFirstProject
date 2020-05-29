package com.example.firebasefirstproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText etusername, etcontact;
    Button btnAddRecord, btnviewrecords;
    DatabaseReference mRef;
    TextView tvContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        btnAddRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddRecord();
            }
        });

        btnviewrecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ShowContactsActivity.class));
            }
        });
    }

    private void AddRecord() {
        String username = etusername.getText().toString().trim();
        String contact = etcontact.getText().toString().trim();
        String id;
        id = mRef.push().getKey(); // unique key
        Contact object = new Contact(username,contact);
        mRef.child(id).setValue(object);
        Toast.makeText(this, "Contact Added Successfully", Toast.LENGTH_SHORT).show();
    }

    void init()
    {
        etusername = findViewById(R.id.etusername);
        etcontact = findViewById(R.id.etcontact);
        btnAddRecord = findViewById(R.id.btnAddRecord);
        btnviewrecords = findViewById(R.id.btnviewrecords);

        mRef = FirebaseDatabase.getInstance().getReference("Contacts");
        tvContacts = findViewById(R.id.tvContacts);
        tvContacts.setText("");
    }

    void viewRecords()
    {
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String text = "";
                for(DataSnapshot data: dataSnapshot.getChildren())
                {
                    text+= data.child("username").getValue().toString()+" "+data.child("contact").getValue().toString()+"\n";
                }
                tvContacts.setText(text);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Error Occurred", Toast.LENGTH_SHORT).show();
            }
        });
        
    }
}
