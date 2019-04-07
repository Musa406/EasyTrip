package com.example.whoami.easybook;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity{

    private EditText userName,mobileNo,email,nid,password,retypePassword;
    private ProgressBar progressBarForSignUp;
    private Button signupDoneButton;
    private RadioButton selectedUserForSignUP;
    private RadioGroup userRadioGroup;
    private String selectedUserTypeName;
    private String usernameStr,mobileStr,nidStr,userTypeStr,emailStr,passwordStr;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        userName = (EditText) findViewById(R.id.nameId);
        mobileNo = (EditText) findViewById(R.id.mobileId);
        email = (EditText) findViewById(R.id.emailID);
        nid = (EditText) findViewById(R.id.nationalID);
        password = (EditText) findViewById(R.id.passwordId);
        retypePassword = (EditText) findViewById(R.id.retypePassId);
        signupDoneButton = (Button) findViewById(R.id.signUpDoneButtonId);
        progressBarForSignUp = findViewById(R.id.progressbarSignUpId);
        userRadioGroup = (RadioGroup) findViewById(R.id.userTypeRadioGroupId);



        mAuth = FirebaseAuth.getInstance();


        signupDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBarForSignUp.setVisibility(View.VISIBLE);
                  userRegister();
            }
        });

    }


    private void userRegister() {
         emailStr = email.getText().toString().trim();
         passwordStr = password.getText().toString().trim();
         usernameStr = userName.getText().toString().trim();
         mobileStr = mobileNo.getText().toString().trim();
         nidStr = nid.getText().toString().trim();




        if(emailStr.isEmpty()) {
            email.setError("Enter an email address");
            email.requestFocus();
            return;
        }
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(emailStr).matches()) {
            email.setError("Enter a valid email address");
            email.requestFocus();
            return;
        } //checking the validity of the password
         if(passwordStr.isEmpty()) {
             password.setError("Enter a password");
             password.requestFocus();
             return;
         }
         if(password.length()<6){
             password.setError("Minimum length of password should be 6");
             password.requestFocus();
             return;
         }

         mAuth.createUserWithEmailAndPassword(emailStr,passwordStr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
             @Override
             public void onComplete(@NonNull Task<AuthResult> task) {
                 if (task.isSuccessful()) {
                     try{
                         int selectedUserPos = userRadioGroup.getCheckedRadioButtonId();
                         selectedUserForSignUP = (RadioButton) findViewById(selectedUserPos);
                         userTypeStr = selectedUserForSignUP.getText().toString();
                         databaseReference = FirebaseDatabase.getInstance().getReference(userTypeStr );

                         if(userTypeStr.equals("Owner")){

                             UserInfoSaveRetrieve saveRetrieveUserInfo = new UserInfoSaveRetrieve(emailStr,usernameStr,mobileStr,nidStr,passwordStr,userTypeStr,"#1122");
                             databaseReference.child(mobileStr).setValue(saveRetrieveUserInfo);

                             Toast.makeText(getApplicationContext(),"SignUp succesful",Toast.LENGTH_SHORT).show();
                             progressBarForSignUp.setVisibility(View.GONE);

                             Intent intentOwnerHomeFromSignUp = new Intent(Signup.this,ownerHome.class);
                             startActivity(intentOwnerHomeFromSignUp);
                         }

                         else{
                             UserInfoSaveRetrieve saveRetrieveUserInfo = new UserInfoSaveRetrieve(emailStr,usernameStr,mobileStr,nidStr,passwordStr,userTypeStr,"#1122");
                             databaseReference.child(mobileStr).setValue(saveRetrieveUserInfo);

                             Toast.makeText(getApplicationContext(),"SignUp succesful",Toast.LENGTH_SHORT).show();
                             progressBarForSignUp.setVisibility(View.GONE);

                             Intent intentBorrowerHomeFromSignUp = new Intent(Signup.this,UserHomeInterface.class);
                             startActivity(intentBorrowerHomeFromSignUp);
                         }
                     }
                     catch (NullPointerException e){
                         e.printStackTrace();
                     }

                 } else {
                     // If sign in fails, display a message to the user.
                     Toast.makeText(getApplicationContext(),"SignUp unsuccessful",Toast.LENGTH_SHORT).show();
                     progressBarForSignUp.setVisibility(View.GONE);

                 }
             }
         });
    }


}
