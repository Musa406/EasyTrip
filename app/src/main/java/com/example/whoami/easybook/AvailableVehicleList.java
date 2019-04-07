package com.example.whoami.easybook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AvailableVehicleList extends AppCompatActivity {

    ListView availableVehicleList;
    DatabaseReference databaseReference;
    private CustomTruckListAdaptor customTruckListAdaptor;

    private List<VehicleInfoSaveRetrieve>vehicleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_vehicle_list);

        databaseReference = FirebaseDatabase.getInstance().getReference("Vehicle");
        availableVehicleList = (ListView) findViewById(R.id.availableVehicleListId);

        vehicleList = new ArrayList<>();

        customTruckListAdaptor = new CustomTruckListAdaptor(AvailableVehicleList.this,vehicleList);

    }

    @Override
    protected void onStart() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                vehicleList.clear();

                for(DataSnapshot trucklistsnapshot: dataSnapshot.getChildren()){

                    VehicleInfoSaveRetrieve trucklist = trucklistsnapshot.getValue(VehicleInfoSaveRetrieve.class);

                    vehicleList.add(trucklist);
                }

                availableVehicleList.setAdapter(customTruckListAdaptor);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"something happened wrong2",Toast.LENGTH_SHORT).show();
            }
        });
        super.onStart();
    }
}
