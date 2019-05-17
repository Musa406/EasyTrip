package com.example.whoami.easybook;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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

public class OwnerVehicles extends AppCompatActivity{

    List<String> mylist = new ArrayList<String>();
    List<String> mylistShow = new ArrayList<String>();
    List<String> driverChildKey = new ArrayList<String>();

    String[] myArrayStr;
    int count=0,i=0,totalVehicle;

    String numberPlateStr[];
    TextView numberPlateView;
    String currentuser;

    AlertDialog dialogManageVehicle;
    AlertDialog dialogDriverList;

    ListView availableVehicleList;
    Button setDriverBtn,removeVehicleBtn;
    DatabaseReference databaseReference,dbReferenceDriver;
    Query query,queryDriver;

    private CustomTruckListAdaptorOwner customTruckListAdaptor;

    private List<VehicleInfoSaveRetrieve> vehicleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_vehicles);

        availableVehicleList = (ListView) findViewById(R.id.availableVehicleListId);
        vehicleList = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("Vehicle");
        dbReferenceDriver = FirebaseDatabase.getInstance().getReference("Driver");


        //..................query  object..............................................................

        currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        query = FirebaseDatabase.getInstance().getReference("Vehicle")
                        .orderByChild("vehicleOwnerUID")
                        .equalTo(currentuser);



        queryDriver = FirebaseDatabase.getInstance().getReference("Driver")
                        .orderByChild("ownerID")
                        .equalTo(currentuser);
       //...........................................................................................


        //set driver and remove vehicles...................
        availableVehicleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                numberPlateView = view.findViewById(R.id.vehicleNumberPlateId);
                String VN = numberPlateView.getText().toString().trim();
                numberPlateStr = VN.split("#");

                //display setDriver & remove vehicle button.......................................................................................
                AlertDialog.Builder manageVehicleBuilder = new AlertDialog.Builder(OwnerVehicles.this);
                View mView = getLayoutInflater().inflate(R.layout.vehicle_remove_driver_add,null);
                manageVehicleBuilder.setView(mView);
                dialogManageVehicle = manageVehicleBuilder.create();
                dialogManageVehicle.show();
                //................................................................................................................................

                //............set remove button in dialog.........................
                setDriverBtn = mView.findViewById(R.id.setDriverId);
                setDriverBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogManageVehicle.dismiss();
                        setDriver();
                    }
                });

                removeVehicleBtn = mView.findViewById(R.id.removeVehicleId);
                removeVehicleBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(),"remove vehicle",Toast.LENGTH_SHORT).show();
                        dialogManageVehicle.dismiss();
                    }
                });
            //..........................set remove...end..........................
            }
        });
        //.............vehicle list onItemClickListener...........................................................end..............................
    }

    private void setDriver() {

        AlertDialog.Builder driverListBuilder = new AlertDialog.Builder(OwnerVehicles.this);
        driverListBuilder.setTitle("Select Driver");

            driverListBuilder.setItems(myArrayStr, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    String sss = mylist.get(which);
                    final String []str = sss.split("#"); //str[1] driverUID

                    for(int j=0; j<totalVehicle; j++){
                        if(vehicleList.get(j).getNumberPlate().equals(numberPlateStr[1])){

                            //Toast.makeText(getApplicationContext(),"ball "+driverChildKey.get(i).toString()+" total vehicle: "+totalVehicle,Toast.LENGTH_SHORT).show();

                            String NP = vehicleList.get(j).getNumberPlate();
                            String CN = vehicleList.get(j).getChassisNumber();
                            String size = vehicleList.get(j).getCarryingCapacity();
                            String weight = vehicleList.get(j).getWeightCapacity();
                            String type = vehicleList.get(j).getVehicleType();
                            String ownerUID = vehicleList.get(j).getVehicleOwnerUID();
                            String source = vehicleList.get(j).getSource();
                            String dest = vehicleList.get(j).getDestination();
                            boolean confirm = vehicleList.get(j).getConfirmed();
                            boolean ontheway = vehicleList.get(j).getOntheway();
                            //String DriverID = vehicleList.get(i).getDriverUID();
                            String TripID = vehicleList.get(j).getTripID();

                            Toast.makeText(getApplicationContext(),"ball "+driverChildKey.get(j).toString()+" total vehicle: "+totalVehicle,Toast.LENGTH_SHORT).show();

                            VehicleInfoSaveRetrieve vehicleInfoSaveRetrieve = new VehicleInfoSaveRetrieve(NP,CN,size,weight,type,ownerUID,source,dest,confirm,ontheway,str[1],TripID);
                            databaseReference.child(driverChildKey.get(j).toString().trim()).setValue(vehicleInfoSaveRetrieve);

                            dbReferenceDriver.child(str[1]).child("available").setValue("no");

                        }
                    }

                }
            });
        dialogDriverList = driverListBuilder.create();
            dialogDriverList.show();

        //.......................................

    }

    @Override
    protected void onStart() {

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                vehicleList.clear();
                driverChildKey.clear();
                totalVehicle=0;

                for(DataSnapshot trucklistsnapshot: dataSnapshot.getChildren()){

                    VehicleInfoSaveRetrieve trucklist = trucklistsnapshot.getValue(VehicleInfoSaveRetrieve.class);

                    vehicleList.add(trucklist);
                    driverChildKey.add(trucklistsnapshot.getKey());
                    totalVehicle++;

                }

                customTruckListAdaptor = new CustomTruckListAdaptorOwner(OwnerVehicles.this, vehicleList);
                availableVehicleList.setAdapter(customTruckListAdaptor);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"something happened wrong2",Toast.LENGTH_SHORT).show();
            }
        });



        //.............................................finding drivers from database.....................................................
        queryDriver.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                count=0;
                for(DataSnapshot driverListsnapshot: dataSnapshot.getChildren()){
                    DriverInfoSaveRetrieve driverlist = driverListsnapshot.getValue(DriverInfoSaveRetrieve.class);

                    if(driverlist.getAvailable().equals("yes")){

                        String s = driverlist.getName()+"#"+driverlist.getDriverID()+"#"+driverlist.getOwnerID();
                        mylist.add(s);
                        String ss = "name:" +driverlist.getName()+"  phone: "+driverlist.getMobile();
                        mylistShow.add(ss);
                        count++;
                        Toast.makeText(getApplicationContext(),"count: "+count+" myListShowo: "+mylistShow.get(count-1), Toast.LENGTH_SHORT).show();
                    }
                    else{
                        continue;
                    }
                }

                myArrayStr= new String[count];
                for(int i=0; i<count; i++){
                    myArrayStr[i] = mylistShow.get(i);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"something happened wrong2",Toast.LENGTH_SHORT).show();
            }
        });

        super.onStart();
    }
}
