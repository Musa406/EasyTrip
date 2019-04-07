package com.example.whoami.easybook;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button loginbtn;

    private TextView linkForgotPassword,linkSignUP;
    //sign in variables
    private EditText email;
    private EditText password;
    private Spinner userSelectionSpinerSignIn;
    FirebaseAuth mAuth;
    private  String selectedUserForSignIn;
    private ProgressBar progressBarSignIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //for sign in

        loginbtn = (Button) findViewById(R.id.login_btn);
        email = (EditText) findViewById(R.id.emailId);
        password = (EditText) findViewById(R.id.passwordid);
        userSelectionSpinerSignIn = (Spinner) findViewById(R.id.userSelectionSignInId);
        mAuth = FirebaseAuth.getInstance();
        progressBarSignIn = findViewById(R.id.progressbarSignInId);


    //...........................................................................................................
        linkForgotPassword = (TextView) findViewById(R.id.forgotPasswordId);
        linkSignUP = (TextView) findViewById(R.id.signUpId);

        String text1 = linkForgotPassword.getText().toString();
        String text2 = linkSignUP.getText().toString();

        SpannableString ss1 = new SpannableString(text1);
        SpannableString ss2 = new SpannableString(text2);

        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(MainActivity.this,"forgot password",Toast.LENGTH_SHORT).show();
            }
        };
        ClickableSpan clickableSpan2 = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent signUpActivity = new Intent(MainActivity.this,Signup.class);
                startActivity(signUpActivity);
                Toast.makeText(MainActivity.this,"create account",Toast.LENGTH_SHORT).show();
            }
        };

        ss1.setSpan(clickableSpan1,17,27, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss2.setSpan(clickableSpan2,23,31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        linkForgotPassword.setText(ss1);
        linkForgotPassword.setMovementMethod(LinkMovementMethod.getInstance());

        linkSignUP.setText(ss2);
        linkSignUP.setMovementMethod(LinkMovementMethod.getInstance());

        //.........................................................................................................


        ArrayAdapter<String>myAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.userSelectionSignIn));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userSelectionSpinerSignIn.setAdapter(myAdapter);
        userSelectionSpinerSignIn.setOnItemSelectedListener(this );


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBarSignIn.setVisibility(View.VISIBLE);

                try {
                    String userName = email.getText().toString();
                    String passWord = password.getText().toString();

                    userSignIn(userName,passWord);


                }catch (Exception e){


                }
            }
        });

    }

    private void userSignIn(String mail, String pass) {
        if(mail.isEmpty()) {
            email.setError("Enter an email address");
            email.requestFocus();
            return;
        }
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            email.setError("Enter a valid email address");
            email.requestFocus();
            return;
        } //checking the validity of the password
        if(pass.isEmpty()) {
            password.setError("Enter a password");
            password.requestFocus();
            return;
        }
        if(password.length()<6){
            password.setError("Minimum length of password should be 6");
            password.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    progressBarSignIn.setVisibility(View.GONE);

                    if(selectedUserForSignIn.equals("Owner")){
                        Intent intentOwnerHomeFromSignUp = new Intent(MainActivity.this,ownerHome.class);
                        intentOwnerHomeFromSignUp.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intentOwnerHomeFromSignUp);
                    }
                    else if(selectedUserForSignIn.equals("Borrower")){
                        Intent intentBorrowerHomeFromSignUp = new Intent(MainActivity.this,UserHomeInterface.class);
                        intentBorrowerHomeFromSignUp.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intentBorrowerHomeFromSignUp);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"under construction",Toast.LENGTH_SHORT).show();
                    }

                }

                else{
                    progressBarSignIn.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),"Login Unsuccessful",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedUserForSignIn = parent.getItemAtPosition(position).toString();
        Toast.makeText(MainActivity.this, selectedUserForSignIn,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
