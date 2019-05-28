package com.example.whoami.easybook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TripConfirmation extends AppCompatActivity {

    private TextView from,to,date,time,cost,np,tid;
    private String fromStr,toStr,dateStr,timeStr,costStr,npStr;
    private Button okbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_confirmation);

        okbutton = findViewById(R.id.okConfirmID);
        okbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(TripConfirmation.this,UserHomeInterface.class);
                startActivity(intent);
            }
        });


        from = (TextView) findViewById(R.id.sourceID);
        to = (TextView) findViewById(R.id.destID);
        date = (TextView) findViewById(R.id.dateID);
        time = (TextView) findViewById(R.id.timeID);
        cost = (TextView) findViewById(R.id.costID);
        np = (TextView) findViewById(R.id.NPid);
        tid = (TextView) findViewById(R.id.tripID);

        final Intent intent = getIntent();
        String confirmationDetails = intent.getStringExtra("confirmDetails");

        String []details = confirmationDetails.split("#");
        from.setText("Pickup:  "+details[0]);
        to.setText("Unload:  "+details[1]);
        date.setText("Date:  "+details[2]);
        time.setText("time: "+details[3]);
        cost.setText("Fare: "+CustomTruckListAdaptor.tripFare+" Tk.");
        np.setText("VN: "+AvailableVehicleList.NP);
        tid.setText("Trip No.: AB-"+AvailableVehicleList.tripId);



    }
}
