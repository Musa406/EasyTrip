package com.example.whoami.easybook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class ManageDriver extends AppCompatActivity {

    private CardView addDriver, remodveDriver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_driver);

        addDriver = (CardView) findViewById(R.id.addDriverId);

        addDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAddDriver = new Intent(ManageDriver.this, AddDriver.class);
                startActivity(intentAddDriver);
            }
        });


    }
}
