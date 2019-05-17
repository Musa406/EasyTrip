package com.example.whoami.easybook;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Date;

public class SearchAvailability extends AppCompatActivity implements View.OnClickListener{

    public DatePickerDialog datePickerDialog;
    public TimePickerDialog timePickerDialog;
    private Button dateBtn,timeBtn,picupPoint,destinationPoint;
    private Button searchButton;
    private AutoCompleteTextView sourceEditTextVeiw,destEditTextView;
    private String src,dest,date,time;

    String[] places = {"Dhaka","Rangpur","Sylhet","Rajshahi","Khulna","Cumilla","Chittagong","Noakhali","Barishal"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_availability);


        dateBtn = (Button) findViewById(R.id.selectDateId);
        timeBtn = (Button) findViewById(R.id.selectTimeId);
        sourceEditTextVeiw = findViewById(R.id.pickupID);
        destEditTextView = findViewById(R.id.destinatioID);


        ArrayAdapter<String>adapterSource = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,places);
        sourceEditTextVeiw.setAdapter(adapterSource);
        //TextView t = (TextView) sourceEditTextVeiw.getOnItemSelectedListener();
        //Toast.makeText(getApplicationContext(),t.getText().toString(),Toast.LENGTH_SHORT).show();

        sourceEditTextVeiw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                src = (String)parent.getItemAtPosition(position);
                //Toast.makeText(getApplicationContext(),selection,Toast.LENGTH_SHORT).show();
            }
        });





        ArrayAdapter<String>adapterDest = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,places);
        destEditTextView.setAdapter(adapterDest);

        destEditTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                dest = (String)adapterView.getItemAtPosition(i);
            }
        });
        //picupPoint = (Button) findViewById(R.id.pickUpLocaionId);
        //destinationPoint = (Button) findViewById(R.id.destinationId);
        searchButton = (Button) findViewById(R.id.searchVehicleButtonId);

        dateBtn.setOnClickListener(this);
        timeBtn.setOnClickListener(this);
        //picupPoint.setOnClickListener(this);
        //destinationPoint.setOnClickListener(this);
        searchButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.searchVehicleButtonId){

            if(src==null || dest ==null || date==null || time==null){
                Toast.makeText(SearchAvailability.this, "Please fill up all the neccessary",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getApplicationContext(),"searching",Toast.LENGTH_SHORT).show();
                Intent searchButtonIntent = new Intent(SearchAvailability.this,AvailableVehicleList.class);

                searchButtonIntent.putExtra("pickup",src);

                searchButtonIntent.putExtra("confirmDetails",src+"#"+dest+"#"+date+"#"+time);

                startActivity(searchButtonIntent);
            }
        }

        else if(v.getId()==R.id.selectDateId){
            DatePicker datePicker = new DatePicker(this);

            int currentYear = datePicker.getYear();
            int currentMonth = datePicker.getMonth();
            int currentDay  = datePicker.getDayOfMonth()+1;

            datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                     date = " "+month+"/"+dayOfMonth+"/"+year;
                    dateBtn.setText(date);
                }
            },currentYear,currentMonth,currentDay);
            datePickerDialog.show();
        }

        else if(v.getId()==R.id.selectTimeId){
            TimePicker timePicker = new TimePicker(this);
            int currentHour = timePicker.getCurrentHour();
            int currentMinute = timePicker.getCurrentMinute();

            timePickerDialog = new TimePickerDialog(SearchAvailability.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                     time = " "+hourOfDay+":"+minute;
                    timeBtn.setText(time);
                }
            }, currentHour, currentMinute,false);

            timePickerDialog.show();
        }

//        else if(v.getId()==R.id.pickUpLocaionId){
//            // setup the alert builder
//            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
//            builder1.setTitle("Select Pick up point");
//
//// add a list
//            final String[] animals1 = {"Dhaka","Rangpur","Sylhet","Rajshahi","Khulna","Cumilla","Chittagong","Noakhali","Barishal"};
//            builder1.setItems(animals1, new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//
//                            src = animals1[which];
//                            picupPoint.setText(src);
//
//                           // String src = animals1[which];
//                            //Toast.makeText(SearchAvailability.this,dest,Toast.LENGTH_SHORT).show();
//
//                }
//            });
//
//// create and show the alert dialog
//            AlertDialog dialog = builder1.create();
//            dialog.show();
//
//        }
//
//        else if(v.getId()==R.id.destinationId){
//            // setup the alert builder
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setTitle("Select Destination point");
//
//            // add a list
//            final String[] animals = {"Dhaka","Rangpur","Sylhet","Rajshahi","Khulna","Cumilla","Chittagong","Noakhali","Barishal"};
//            builder.setItems(animals, new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//
//                            dest = animals[which];  //dest is a String
//                            destinationPoint.setText(dest); //destinationpoint is button
//                            Toast.makeText(SearchAvailability.this,dest,Toast.LENGTH_SHORT).show();
//
//                }
//            });
//
//            AlertDialog dialog = builder.create();
//            dialog.show();
//
//        }
    }
}
