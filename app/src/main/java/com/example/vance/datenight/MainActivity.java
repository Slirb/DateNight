package com.example.vance.datenight;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //In case of database table field errors
        //this.deleteDatabase("DateNight.db");


         //Get intent, action and MIME type
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                handleSendText(intent); // Handle text being sent
            } else if (type.startsWith("image/")) {
                handleSendImage(intent); // Handle single image being sent
            }
        } else if (Intent.ACTION_SEND_MULTIPLE.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                handleMultipleTexts(intent); // Handle text being sent
            }
        } else {
            // Handle other intents, such as being started from the home screen
        }
    }

    //Selects a random Location
    public void randomSelect(View view){

        ArrayList<Location> location = new ArrayList<Location>();


        dbHandler dbHandler=new dbHandler(this,null,null,3);
        location= dbHandler.listLocations();

        int size=  location.size();

        Random rand = new Random();
        int randNum = rand.nextInt(size);

        Intent i = new Intent(this, ViewLocationActivity.class);
        String temp = location.get(randNum).getName() + "\n" + location.get(randNum).getAddress() + "\n" + location.get(randNum).getPhone() + "\n" + location.get(randNum).getUrl();
        i.putExtra("data", temp );
        startActivity(i);



    }

    //Manually enter a new Location
    public void newLocation(View view) {

        Intent i = new Intent(this, AddLocationManualActivity.class);
        startActivity(i);
    }

    //View all Locations that are not deleted
    public void viewLocations(View view){

        Intent i =new Intent(this, DisplayLocationsActivity.class);
        startActivity(i);
    }

    //View Locations flagged as deleted
    public void viewDeletedLocations(View view){

        Intent i =new Intent(this, DisplayDeletedLocationsActivity.class);
        startActivity(i);
    }


    //Handles Locations shared from outside sources
    void handleSendText(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);


        if (sharedText != null) {
            Intent i = new Intent(this, AddLocationShareActivity.class);
            i.putExtra("data",sharedText);
            startActivity(i);

        }

    }

    //TODO see if this is needed
    void handleSendImage(Intent intent) {
        Uri imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
        if (imageUri != null) {
            // Update UI to reflect image being shared
        }
    }
    //TODO see if this is needed
    void handleMultipleTexts(Intent intent) {
        ArrayList<Uri> imageUris = intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM);
        if (imageUris != null) {
            // Update UI to reflect multiple images being shared
        }
    }

}
