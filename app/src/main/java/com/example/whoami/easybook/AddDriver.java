package com.example.whoami.easybook;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddDriver extends AppCompatActivity {

    private EditText driverName,mobileNo,drivingLiscence,nid,loginCode, driverEmail;
    private String driverNameStr,mobileNoStr,drivingLiscenceStr,nidStr,loginCodeStr,driverEmailStr,currentuserOwner;
    private Button addDriverButton;
    DatabaseReference dbReference,dbReferenceDriver;
    FirebaseAuth mAuth;

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

        mAuth = FirebaseAuth.getInstance();
        dbReference = FirebaseDatabase.getInstance().getReference("Driver");
        currentuserOwner = FirebaseAuth.getInstance().getCurrentUser().getUid();

        addDriverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                driverNameStr = driverName.getText().toString().trim();
                mobileNoStr = mobileNo.getText().toString().trim();
                drivingLiscenceStr = drivingLiscence.getText().toString().trim();
                nidStr = nid.getText().toString().trim();
                loginCodeStr = loginCode.getText().toString().trim();
                driverEmailStr = driverEmail.getText().toString().trim();


                //............Authentication................
                mAuth.createUserWithEmailAndPassword(driverEmailStr, loginCodeStr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),"driver sign up successfully",Toast.LENGTH_SHORT).show();

                            String currentUserDriver = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            DriverInfoSaveRetrieve driverInfoSaveRetrieve = new DriverInfoSaveRetrieve(driverNameStr, mobileNoStr, driverEmailStr, drivingLiscenceStr, nidStr, loginCodeStr,currentuserOwner,"yes",currentUserDriver," ");

                            //String id = databaseReference.push().getKey();
                            dbReference.child(currentUserDriver).setValue(driverInfoSaveRetrieve);


                            //owner signup again.........
                            mAuth.signInWithEmailAndPassword(MainActivity.userName,MainActivity.passWord).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                }
                            });
                            //............................


                        } else {
                            Toast.makeText(getApplicationContext(),"driver signup unseccessful",Toast.LENGTH_SHORT).show();
                        }
                    }
                });



                //FirebaseAuth.getInstance().signOut();

                //..........................................




            }
        });
    }
}
