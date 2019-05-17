package com.example.whoami.easybook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddVehicle extends AppCompatActivity {

    private EditText numberPlate,chassisNo,vehicleSize,carryingCapacity,vehicleType;
    private String numberPlateStr,chassisNoStr,vehicleSizeStr,carryingCapacityStr,vehicleTypeStr;
    private Button addVehicleBtn;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);


        numberPlate = (EditText) findViewById(R.id.vehicleNumberId);
        chassisNo = (EditText) findViewById(R.id.chassisNumberId);
        vehicleSize = (EditText) findViewById(R.id.vehicleSizeId);
        carryingCapacity = (EditText) findViewById(R.id.carryingCapacityId);
        vehicleType = (EditText) findViewById(R.id.vehicleTypeId);
        addVehicleBtn = (Button) findViewById(R.id.add_Vehicle_infoId);


        addVehicleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPlateStr = numberPlate.getText().toString().trim();
                chassisNoStr = chassisNo.getText().toString().trim();
                vehicleSizeStr = vehicleSize.getText().toString().trim();
                carryingCapacityStr = carryingCapacity.getText().toString().trim();
                vehicleTypeStr = vehicleType.getText().toString().trim();

                String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
                databaseReference = FirebaseDatabase.getInstance().getReference("Vehicle");

                VehicleInfoSaveRetrieve vehicleInfoSaveRetrieve = new VehicleInfoSaveRetrieve(numberPlateStr, chassisNoStr,vehicleSizeStr,carryingCapacityStr,vehicleTypeStr,currentuser,"Dhaka","Dhaka",false,false,"  "," ");

                    String id = databaseReference.push().getKey();
                    databaseReference.child(id).setValue(vehicleInfoSaveRetrieve).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(AddVehicle.this,"Successfully added !!",Toast.LENGTH_SHORT).show();
                        }
                    });
            }
        });

    }
}
