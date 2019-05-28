package com.example.whoami.easybook;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
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

    private EditText numberPlate,chassisNo;
    private String numberPlateStr,chassisNoStr,vehicleSizeStr,carryingCapacityStr,vehicleTypeStr;
    private Button addVehicleBtn,carryingCapacityBtn,weightCapacityBtn,vehicleTypeBtn;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);


        numberPlate = (EditText) findViewById(R.id.vehicleNumberId);
        chassisNo = (EditText) findViewById(R.id.chassisNumberId);
        //vehicleType = (EditText) findViewById(R.id.vehicleTypeId);
        vehicleTypeBtn = findViewById(R.id.vehicleTypeId);
        carryingCapacityBtn = (Button) findViewById(R.id.carryingCapacityId);
        weightCapacityBtn = (Button) findViewById(R.id.weightCapacityId);
        addVehicleBtn = (Button) findViewById(R.id.add_Vehicle_infoId);


        vehicleTypeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddVehicle.this);
                builder.setTitle("Vehicle Type");
                final String []vehicletype = getResources().getStringArray(R.array.vehicleType);

                builder.setItems(vehicletype, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        vehicleTypeBtn.setText(vehicletype[which]);
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        carryingCapacityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddVehicle.this);
                builder.setTitle("Carrying Capacity");
                final String []carryingcapacity = getResources().getStringArray(R.array.carryingCapacity);

                builder.setItems(carryingcapacity, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        carryingCapacityBtn.setText(carryingcapacity[which]);
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        weightCapacityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddVehicle.this);
                builder.setTitle("Weight Capacity");
                final String []weightcapacity = getResources().getStringArray(R.array.weightCapacity);

                builder.setItems(weightcapacity, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        weightCapacityBtn.setText(weightcapacity[which]);
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        addVehicleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPlateStr = numberPlate.getText().toString().trim();
                chassisNoStr = chassisNo.getText().toString().trim();
                vehicleSizeStr = weightCapacityBtn.getText().toString().trim();
                carryingCapacityStr = carryingCapacityBtn.getText().toString().trim();
                vehicleTypeStr = vehicleTypeBtn.getText().toString().trim();

                String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
                databaseReference = FirebaseDatabase.getInstance().getReference("Vehicle");

                VehicleInfoSaveRetrieve vehicleInfoSaveRetrieve = new VehicleInfoSaveRetrieve(numberPlateStr, chassisNoStr,vehicleSizeStr,carryingCapacityStr,vehicleTypeStr,currentuser,"University of Dhaka,Dhaka","Dhaka",false,false,"  "," ");

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
