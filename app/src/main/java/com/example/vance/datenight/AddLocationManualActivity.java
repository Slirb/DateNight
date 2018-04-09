package com.example.vance.datenight;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Vance on 3/27/2018.
 */

public class AddLocationManualActivity extends AppCompatActivity {

    EditText nameText;
    EditText addressText;
    EditText phoneText;
    String address;
    String phone;
    String name;
    String google;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location_manual);


        nameText=(EditText)findViewById(R.id.nameText);
        addressText=(EditText)findViewById(R.id.addressText);
        phoneText=(EditText)findViewById(R.id.phoneText);



    }


    public void addLocation(View view) {

        name=nameText.getText().toString();
        address=addressText.getText().toString();
        phone=phoneText.getText().toString();
        google="";


        Location add = new Location(0,name,address, phone, google);
        dbHandler db =new dbHandler(this,null,null,3);
        db.addLocation(add);


        Intent i = new Intent(this, ViewLocationActivity.class);
        String temp =add.getName() + "\n" + add.getAddress() + "\n" + add.getPhone() + "\n" + add.getUrl();
        i.putExtra("data", temp );
        startActivity(i);
    }
}
