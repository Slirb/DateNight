package com.example.vance.datenight;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Vance on 3/27/2018.
 */

    //TODO, add thumbs up/down pictures and allow for updating when picture is clicked and ability to call the phone number

public class ViewLocationActivity extends AppCompatActivity {
    EditText nameBox;
    EditText addressBox;
    EditText phoneBox;
    EditText googleBox;
    String address;
    String phone;
    String name;
    String google;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_location);

        nameBox=(EditText)findViewById(R.id.nameEditText);
        addressBox=(EditText)findViewById(R.id.addressEditText);
        phoneBox=(EditText)findViewById(R.id.phoneEditText);
        googleBox=(EditText)findViewById(R.id.googleBox);


        loadData();

    }


    //Populates the form from passed data
    public void loadData()
    {
        Bundle extras=getIntent().getExtras();
        if(extras==null)
        {
            return;
        }


        String sharedText =extras.getString("data");
        String[] temp = sharedText.split("\n");
        try{
            name = temp[0];
        }
        catch (Exception e){

        }
        try{
            address = temp[1];
        }
        catch (Exception e){

        }
        try{
            phone = temp[2];
        }
        catch (Exception e){

        }
        try{
            google = temp[3];
        }
        catch (Exception e){

        }



        nameBox.setText(name);
        addressBox.setText(address);
        phoneBox.setText(phone);
        googleBox.setText(google);

    }

    @Override
    public void onResume() {
        super.onResume();


        loadData();

    }

    //Delete the current Location
    public void deleteLocation(View view){

        dbHandler db= new dbHandler(this, null,null,3);
        Location temp = db.findLocation(name);
        db.deleteLocation(temp);
        finish();

    }

    //Set Location as visited
    public void visitLocation(View view){

        dbHandler db= new dbHandler(this, null,null,3);
        Location temp = db.findLocation(name);
        temp.setVisited(1);
        db.updateLocation(temp);


    }

    //Updates the current location
    public void updateLocation(View view){

        dbHandler db= new dbHandler(this, null,null,3);
        Location temp = db.findLocation(name);
        temp.setName(nameBox.getText().toString());
        temp.setAddress(addressBox.getText().toString());
        temp.setPhone(phoneBox.getText().toString());
        temp.setUrl(googleBox.getText().toString());
        db.updateLocation(temp);

    }

    //Navigate to Location if a url exists
    public void navigate(View view){

        dbHandler db= new dbHandler(this, null,null,3);
        Location temp = db.findLocation(name);
        if(temp.getUrl().equals("") != true)
        {
            //TODO finish navigation process
        }


    }

}
