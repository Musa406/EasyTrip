package com.example.whoami.easybook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

public class UserHomeInterface extends AppCompatActivity{


    private CardView search,creatTrip,bookings,profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_interface);


        search = (CardView)findViewById(R.id.searchId);
        creatTrip = (CardView)findViewById(R.id.creatTripId);
        bookings = (CardView)findViewById(R.id.bookingId);
        profile = (CardView)findViewById(R.id.profileId);


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

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserHomeInterface.this,"Profile service is not available now",Toast.LENGTH_SHORT).show();
            }
        });

    }
}