package com.example.whoami.easybook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddDriver extends AppCompatActivity {

    private EditText driverName,mobileNo,drivingLiscence,nid,loginCode, driverEmail;
    private String driverNameStr,mobileNoStr,drivingLiscenceStr,nidStr,loginCodeStr,driverEmailStr;
    private Button addDriverButton;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_driver);

        driverName = (EditText) findViewById(R.id.driverNameId);
        mobileNo = (EditText) findViewById(R.id.driverMobileId);
        drivingLiscence = (EditText) findViewById(R.id.drivingLiscenceId);
        nid = (EditText) findViewById(R.id.driverNIDId);
        loginCode = (EditText) findViewById(R.id.driverLoginCodeId);
        driverEmail = (EditText) findViewById(R.id.driverEmailId);
        addDriverButton = (Button) findViewById(R.id.addDriverInfoId);

        databaseReference = FirebaseDatabase.getInstance().getReference("Driver");

        addDriverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                driverNameStr = driverName.getText().toString().trim();
                mobileNoStr = mobileNo.getText().toString().trim();
                drivingLiscenceStr = drivingLiscence.getText().toString().trim();
                nidStr = nid.getText().toString().trim();
                loginCodeStr = loginCode.getText().toString().trim();
                driverEmailStr = driverEmail.getText().toString().trim();

                DriverInfoSaveRetrieve driverInfoSaveRetrieve = new DriverInfoSaveRetrieve(driverNameStr, mobileNoStr, driverEmailStr, drivingLiscenceStr, nidStr, loginCodeStr,"#2222");

                String id = databaseReference.push().getKey();
                databaseReference.child(id).setValue(driverInfoSaveRetrieve);

            }
        });
    }
}
