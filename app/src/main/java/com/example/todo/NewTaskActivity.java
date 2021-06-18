package com.example.todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class NewTaskActivity extends AppCompatActivity {

    TextView pageTitle, lblTitle, lblDesc, lblTimeline;
    EditText addTitle, addDesc, addTimeline;
    Button btnCreate, btnCancel;
    DatabaseReference reference;
    Integer itemId = new Random().nextInt();
    String key = itemId.toString();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        pageTitle = findViewById(R.id.pageTitle);

        lblTitle = findViewById(R.id.lblTitle);
        addTitle = findViewById(R.id.addTitle);

        lblDesc = findViewById(R.id.lblDesc);
        addDesc = findViewById(R.id.addDesc);

        lblTimeline = findViewById(R.id.lblTimeline);
        addTimeline = findViewById(R.id.addTimeline);

        btnCancel = findViewById(R.id.btnCancel);
        btnCreate = findViewById(R.id.btnCreate);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //inset data to database
                reference = FirebaseDatabase.getInstance().getReference().child("Todo").child("Item" + itemId);
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("title").setValue(addTitle.getText().toString());
                        dataSnapshot.getRef().child("desc").setValue(addDesc.getText().toString());
                        dataSnapshot.getRef().child("date").setValue(addTimeline.getText().toString());
                        dataSnapshot.getRef().child("key").setValue(key);

                        Intent intent = new Intent(NewTaskActivity.this, MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
