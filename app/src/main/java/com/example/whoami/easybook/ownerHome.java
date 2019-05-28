package com.example.whoami.easybook;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class ownerHome extends AppCompatActivity{
    private CardView addVehicle, manageDriver, myVehicleOwner,trackVehicleOwner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_home);

        BottomNavigationView navView = (BottomNavigationView) findViewById(R.id.navigationid);

        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.navigation_home){
                }
                else if(item.getItemId()==R.id.navigation_history){
                    AlertDialog.Builder homeBuilder = new AlertDialog.Builder(ownerHome.this);
                    View mView = getLayoutInflater().inflate(R.layout.nav_history,null);
                    homeBuilder.setView(mView);
                    AlertDialog dialog = homeBuilder.create();
                    dialog.show();
                }
                else if(item.getItemId()==R.id.navigation_notifications){
                    AlertDialog.Builder homeBuilder = new AlertDialog.Builder(ownerHome.this);
                    View mView = getLayoutInflater().inflate(R.layout.nav_notification,null);
                    homeBuilder.setView(mView);
                    AlertDialog dialog = homeBuilder.create();
                    dialog.show();

                }
                else if(item.getItemId()==R.id.navigation_more){
                    Toast.makeText(getApplicationContext(),"hi......",Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });


        addVehicle = (CardView) findViewById(R.id.addVehicleId);
        manageDriver =  (CardView) findViewById(R.id.manageDriverId);
        myVehicleOwner = (CardView) findViewById(R.id.myVehicleId);
        trackVehicleOwner = (CardView) findViewById(R.id.trackingId);

        addVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAddVehicle = new Intent(ownerHome.this, AddVehicle.class);
                startActivity(intentAddVehicle);
            }
        });
        manageDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentManageDriver = new Intent(ownerHome.this, ManageDriver.class);
                startActivity(intentManageDriver);
            }
        });
        myVehicleOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ownerVehicles = new Intent(ownerHome.this,OwnerVehicles.class);
                startActivity(ownerVehicles);
                //Toast.makeText(getApplicationContext(),"will be available soon",Toast.LENGTH_SHORT).show();
            }
        });
        trackVehicleOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"will be available soon",Toast.LENGTH_SHORT).show();
            }
        });

    }


}
