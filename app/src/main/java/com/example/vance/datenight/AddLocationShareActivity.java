package com.example.vance.datenight;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;


/**
 * Created by Vance on 3/27/2018.
 */

public class AddLocationShareActivity extends AppCompatActivity {

    EditText nameText;
    EditText addressText;
    EditText phoneText;
    EditText urlText;
    String address;
    String phone;
    String name;
    String google;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location_share);


        nameText=(EditText)findViewById(R.id.nameText);
        addressText=(EditText)findViewById(R.id.addressText);
        phoneText=(EditText)findViewById(R.id.phoneText);
        urlText=(EditText)findViewById(R.id.urlText);

        Bundle extras=getIntent().getExtras();
        if(extras==null)
        {
            return;
        }


        String sharedText = extras.getString("data");
        String[] temp = sharedText.split("\n");

        Character par=')';

        for(String t : temp) {

            if (t.equals(temp[0]))
            {
                name=t;
            }

            if (t != null && !t.equals(""))
            {
                if (t.startsWith("http"))
                {
                    google=t;
                }
                else if (t.startsWith("(") && par.equals(t.charAt(4)))
                {
                    phone=t;
                }
                else
                {
                    address=t;
                }
            }


        }



        nameText.setText(name);
        addressText.setText(address);
        phoneText.setText(phone);
        urlText.setText(google);



    }




    public void addLocation(View view) {

        Location add = new Location(0,name,address, phone, google);

        dbHandler db =new dbHandler(this,null,null,3);
        db.addLocation(add);


        Intent i = new Intent(this, ViewLocationActivity.class);
        String temp =add.getName() + "\n" + add.getAddress() + "\n" + add.getPhone() + "\n" + add.getUrl();
        i.putExtra("data", temp );
        startActivity(i);
    }
}
