package com.example.whoami.easybook;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AvailableVehicleList extends AppCompatActivity {

    ListView availableVehicleList;
    TextView numberPlateView;
    String numberPlateStr;
    static String []numPlate;
    String tripID;
    String childIDTrip,childIDVehicle;
    static int tripId;
    static String NP;

    List<String> driverChildKey1 = new ArrayList<String>();
    List<String> driverChildKey2= new ArrayList<String>();

    DatabaseReference drForTripID,dbReferenceVehicle,dbReferenceDriver,dbReferenceBorrower;
    Query query;
    private CustomTruckListAdaptor customTruckListAdaptor;

    private List<VehicleInfoSaveRetrieve>vehicleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_vehicle_list);

        final Intent intent = getIntent();
        String pickup = intent.getStringExtra("pickup");

        dbReferenceVehicle = FirebaseDatabase.getInstance().getReference("Vehicle");
        dbReferenceDriver = FirebaseDatabase.getInstance().getReference("Driver");
        dbReferenceBorrower = FirebaseDatabase.getInstance().getReference("Borrower");

        query = FirebaseDatabase.getInstance().getReference("Vehicle")
                        .orderByChild("source")
                        .equalTo(pickup);


        drForTripID = FirebaseDatabase.getInstance().getReference("TripID");
//        query.addListenerForSingleValueEvent(databaseReference);

        availableVehicleList = (ListView) findViewById(R.id.availableVehicleListId);

        vehicleList = new ArrayList<>();

        availableVehicleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                childIDVehicle = driverChildKey1.get(position);

                numberPlateView = view.findViewById(R.id.vehicleNumberPlateId);
                numberPlateStr = numberPlateView.getText().toString().trim();
                numPlate = numberPlateStr.split("#");


                //Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_SHORT).show();
                AlertDialog.Builder confirmTruckBuilder = new AlertDialog.Builder(AvailableVehicleList.this);
                confirmTruckBuilder.setTitle("Confirm Booking");
                confirmTruckBuilder.setMessage("click Confirm for confirming trip. You can confirm trip via directly call");
                confirmTruckBuilder.setIcon(R.drawable.rsz_1rsz_1truck);

                confirmTruckBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        tripId = Integer.parseInt(tripID)+1;

                        for(int j=0; j<vehicleList.size(); j++){

                            if(vehicleList.get(j).getNumberPlate().equals(numPlate[1])) {
                                 NP = vehicleList.get(j).getNumberPlate();
                                String CN = vehicleList.get(j).getChassisNumber();
                                String size = vehicleList.get(j).getCarryingCapacity();
                                String weight = vehicleList.get(j).getWeightCapacity();
                                String type = vehicleList.get(j).getVehicleType();
                                String ownerUID = vehicleList.get(j).getVehicleOwnerUID();
                                String source = vehicleList.get(j).getSource();
                                String dest = vehicleList.get(j).getDestination();
                                boolean confirm = vehicleList.get(j).getConfirmed();
                                boolean ontheway = vehicleList.get(j).getOntheway();
                                String DriverID = vehicleList.get(j).getDriverUID();
                                //String TripID = vehicleList.get(j).getTripID();

                                VehicleInfoSaveRetrieve vehicleInfoSaveRetrieve = new VehicleInfoSaveRetrieve(NP, CN, size, weight, type, ownerUID, source, dest, true, ontheway, DriverID, ""+tripId);
                                dbReferenceVehicle.child(childIDVehicle).setValue(vehicleInfoSaveRetrieve);
                              //


                                TripIdSaveRetrieve tisr = new TripIdSaveRetrieve(""+tripId);
                                drForTripID.child(childIDTrip).setValue(tisr);

                                dbReferenceDriver.child(DriverID).child("tripID").setValue(""+tripId);
                                String currentUserBorrower = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                dbReferenceBorrower.child(currentUserBorrower).child("tripId").setValue(""+tripId);

                            }
                        }
                        Toast.makeText(getApplicationContext(),numberPlateStr,Toast.LENGTH_SHORT).show();

                        Intent showConfirmationIntent = new Intent(AvailableVehicleList.this,TripConfirmation.class);
                        String confirmationDetails = intent.getStringExtra("confirmDetails");
                        showConfirmationIntent.putExtra("confirmDetails",confirmationDetails);
                        startActivity(showConfirmationIntent);

                        finish();
                    }
                });
                confirmTruckBuilder.setNegativeButton("Call ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"This features isn't available now",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });

                confirmTruckBuilder.setCancelable(true);
                AlertDialog alertDialog = confirmTruckBuilder.create();
                alertDialog.show();
            }
        });

    }

    @Override
    protected void onStart() {

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                vehicleList.clear();

                for(DataSnapshot trucklistsnapshot: dataSnapshot.getChildren()){

                    VehicleInfoSaveRetrieve trucklist = trucklistsnapshot.getValue(VehicleInfoSaveRetrieve.class);

                    driverChildKey1.add(trucklistsnapshot.getKey());

                    if(trucklist.getConfirmed()==true)
                        continue;
                    else
                        vehicleList.add(trucklist);
                }

                customTruckListAdaptor = new CustomTruckListAdaptor(AvailableVehicleList.this, vehicleList);
                availableVehicleList.setAdapter(customTruckListAdaptor);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"something happened wrong2",Toast.LENGTH_SHORT).show();
            }
        });


        drForTripID.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot tripsnapshot: dataSnapshot.getChildren()){
                    driverChildKey2.add(tripsnapshot.getKey());
                    childIDTrip = driverChildKey2.get(0);

                    TripIdSaveRetrieve tripIdSaveRetrieve = tripsnapshot.getValue(TripIdSaveRetrieve.class);
                    tripID = tripIdSaveRetrieve.getTripID();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        super.onStart();
    }
}
