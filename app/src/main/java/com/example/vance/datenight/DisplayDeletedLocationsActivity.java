package com.example.vance.datenight;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;


public class DisplayDeletedLocationsActivity extends AppCompatActivity {

    private ArrayList<Location> location;



    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_locations);

        Bundle extras=getIntent().getExtras();
        if(extras==null)
        {
            return;
        }


        location=new ArrayList<Location>();


        //generate list
        dbHandler dbHandler=new dbHandler(this,null,null,3);
        location= dbHandler.listDeletedLocations();
        //instantiate custom adapter
        myListAdapter adapter = new myListAdapter(location,this, "temp");

        //handle listview and assign adapter
        ListView lView = (ListView)findViewById(R.id.listView);
        lView.setAdapter(adapter);

    }


    public void loadList(){
        location=new ArrayList<Location>();


        //generate list
        dbHandler dbHandler=new dbHandler(this,null,null,3);
        location= dbHandler.listDeletedLocations();
        //instantiate custom adapter
        myListAdapter adapter = new myListAdapter(location,this, "temp");

        //handle listview and assign adapter
        ListView lView = (ListView)findViewById(R.id.listView);
        lView.setAdapter(adapter);

    }

    //Returns to main menu
    public void mainActivity(View view){

        finish();
    }


    @Override
    public void onResume() {
        super.onResume();

        loadList();

    }

}
