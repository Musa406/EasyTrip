package com.example.whoami.easybook;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserHomeInterface extends AppCompatActivity{
    DatabaseReference dbBorrower;
    String tripId;

    private CardView search,creatTrip,bookings,location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_interface);


        dbBorrower = FirebaseDatabase.getInstance().getReference("Borrower");
        search = (CardView)findViewById(R.id.searchId);
        creatTrip = (CardView)findViewById(R.id.creatTripId);
        bookings = (CardView)findViewById(R.id.bookingId);
        location = (CardView)findViewById(R.id.profileId);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSearch = new Intent(UserHomeInterface.this,SearchAvailability.class);
                startActivity(intentSearch);
               // Toast.makeText(UserHomeInterface.this,"Searching available vehicles",Toast.LENGTH_SHORT).show();
            }
        });

        creatTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserHomeInterface.this,"Let's creat a Trip",Toast.LENGTH_SHORT).show();
            }
        });

        bookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserHomeInterface.this,"Your bookings are...",Toast.LENGTH_SHORT).show();
            }
        });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent locationIntent = new Intent(UserHomeInterface.this, MapsActivity.class);
                locationIntent.putExtra("tripID",tripId);
                Toast.makeText(getApplicationContext(),tripId,Toast.LENGTH_SHORT).show();
                startActivity(locationIntent);
                //Toast.makeText(UserHomeInterface.this,"Profile service is not available now",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        dbBorrower.child(FirebaseAuth.getInstance().getUid()).child("tripId").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tripId = (String) dataSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
