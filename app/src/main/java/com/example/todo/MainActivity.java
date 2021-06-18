package com.example.todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView titlePage, subtitlePage, endPage;
    Button btnAddNew;
    DatabaseReference reference;
    RecyclerView itemsContainer;
    ArrayList<Item> itemsList;
    ItemsAdapter itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titlePage = findViewById(R.id.titlePage);
        subtitlePage = findViewById(R.id.subtitlePage);
        endPage = findViewById(R.id.endPage);
        btnAddNew = findViewById(R.id.btnAddNew);

        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewTaskActivity.class);
                startActivity(intent);
            }
        });

        //data
        itemsContainer = findViewById(R.id.itemsContainer);
        itemsContainer.setLayoutManager((new LinearLayoutManager(this)));
        itemsList = new ArrayList<Item>();

        //get data from firebase
        reference = FirebaseDatabase.getInstance().getReference().child("Todo");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.println(Log.DEBUG, "database", "onDataChange");

                //retrieve data and replace layout
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Item item = dataSnapshot1.getValue(Item.class);

                    itemsList.add(item);

                }
                //MainActivity.this
                itemsAdapter = new ItemsAdapter(MainActivity.this, itemsList);
                itemsContainer.setAdapter(itemsAdapter);
                itemsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //show an error
                Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
                Log.println(Log.ERROR, "database", databaseError.toString());
            }
        });
    }
}
