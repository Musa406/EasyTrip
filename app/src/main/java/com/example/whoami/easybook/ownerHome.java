package com.example.whoami.easybook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

public class ownerHome extends AppCompatActivity{
    private CardView addVehicle, manageDriver, myVehicleOwner,trackVehicleOwner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_home);

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
