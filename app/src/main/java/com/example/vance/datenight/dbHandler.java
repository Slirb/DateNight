package com.example.vance.datenight;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Vance on 7/23/2017.
 */

    //TODO fix date entry into database


public class dbHandler extends SQLiteOpenHelper {

        private static final int DATABASE_VERSION = 3;
        private static final String DATABASE_NAME = "DateNight.db";

        //Table names
        public static final String TABLE_LOCATIONS = "locations";


        //Location table column names
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_VISITED = "visited";
        public static final String COLUMN_DATE_VISITED = "date_visited";
        public static final String COLUMN_URL = "url";
        public static final String COLUMN_LIKED = "user_liked";
        public static final String COLUMN_DELETED = "deleted";


        public dbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
            super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            //Create Location table
            String CREATE_LOCATIONS_TABLE = "CREATE TABLE " + TABLE_LOCATIONS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME
                    + " TEXT," + COLUMN_ADDRESS + " TEXT," + COLUMN_PHONE + " TEXT," + COLUMN_URL + " TEXT," + COLUMN_VISITED + " INTEGER,"
                    + COLUMN_DATE_VISITED + " TEXT," + COLUMN_LIKED + " INTEGER," + COLUMN_DELETED + " INTEGER" + ")";
            db.execSQL(CREATE_LOCATIONS_TABLE);
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

            db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATIONS);
            onCreate(db);
        }


    //Returns a list of all locations in the database that have been deleted
    public ArrayList<Location> listDeletedLocations(){

        String query = "SELECT * FROM " + TABLE_LOCATIONS + " WHERE " + COLUMN_DELETED + " = 1";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        ArrayList<Location> locations = new ArrayList<Location>();

        try
        {
            while(cursor.moveToNext())
            {
                Location temp= new Location();
                temp.setId(cursor.getInt(0));
                temp.setName(cursor.getString(1));
                temp.setAddress(cursor.getString(2));
                temp.setPhone(cursor.getString(3));
                temp.setUrl(cursor.getString(4));
                temp.setVisited(cursor.getInt(5));
                temp.setDateVisited(cursor.getString(6));
                temp.setUserLiked(cursor.getInt(7));
                temp.setDeleted(cursor.getInt(8));
                locations.add(temp);


            }
        }
        catch (Exception e)
        {
            Location temp=null;
            locations.add(temp);
        }

        cursor.close();
        db.close();

        return locations;

    }


        //Returns a list of all locations in the database that aren't deleted
        public ArrayList<Location> listLocations(){

            String query = "SELECT * FROM " + TABLE_LOCATIONS + " WHERE " + COLUMN_DELETED + " != 1";

            SQLiteDatabase db = this.getWritableDatabase();

            Cursor cursor = db.rawQuery(query, null);

            ArrayList<Location> locations = new ArrayList<Location>();

            try
            {
                while(cursor.moveToNext())
                {
                    Location temp= new Location();
                    temp.setId(cursor.getInt(0));
                    temp.setName(cursor.getString(1));
                    temp.setAddress(cursor.getString(2));
                    temp.setPhone(cursor.getString(3));
                    temp.setUrl(cursor.getString(4));
                    temp.setVisited(cursor.getInt(5));
                    temp.setDateVisited(cursor.getString(6));
                    temp.setUserLiked(cursor.getInt(7));
                    temp.setDeleted(cursor.getInt(8));
                    locations.add(temp);


                }
            }
            catch (Exception e)
            {
                Location temp=null;
                locations.add(temp);
            }

            cursor.close();
            db.close();

            return locations;

        }


        //Adds a new location to the database
        public void addLocation(Location newLocation){

            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, newLocation.getName());
            values.put(COLUMN_ADDRESS, newLocation.getAddress());
            values.put(COLUMN_PHONE, newLocation.getPhone());
            values.put(COLUMN_URL, newLocation.getUrl());
            values.put(COLUMN_VISITED, newLocation.getVisited());
            values.put(COLUMN_DATE_VISITED, newLocation.getDateVisited());
            values.put(COLUMN_LIKED, newLocation.getUserLiked());
            values.put(COLUMN_DELETED, newLocation.getDeleted());



            SQLiteDatabase db = this.getWritableDatabase();

            db.insertOrThrow(TABLE_LOCATIONS, null, values);
            db.close();
        }

        //Returns a count of Locations in the db
        public int locationCount(){

            int count=0;
            String countq = "SELECT COUNT(*) FROM " + TABLE_LOCATIONS + "";

            SQLiteDatabase cdb = this.getWritableDatabase();

            Cursor countcursor = cdb.rawQuery(countq, null);
            if(countcursor.moveToFirst())
            {

                count=Integer.parseInt(countcursor.getString(0));

            }
            else
            {
                count=1;
            }
            countcursor.close();
            return count;
        }


        //Returns a specific location
        public Location findLocation(String name){

            String query = "SELECT * FROM " + TABLE_LOCATIONS + " WHERE " + COLUMN_NAME + " = \"" + name + "\"";

            SQLiteDatabase db = this.getWritableDatabase();

            Cursor cursor = db.rawQuery(query, null);

            Location location = new Location();



            if(cursor.moveToFirst())
            {
                cursor.moveToFirst();
                Location temp= new Location();
                temp.setId(Integer.parseInt(cursor.getString(0)));
                temp.setName(cursor.getString(1));
                temp.setAddress(cursor.getString(2));
                temp.setPhone(cursor.getString(3));
                temp.setUrl(cursor.getString(4));
                temp.setVisited(cursor.getInt(5));
                temp.setDateVisited(cursor.getString(6));
                temp.setUserLiked(cursor.getInt(7));
                temp.setDeleted(cursor.getInt(8));

                location =temp;
            }
            else
            {
                location =null;
            }

            cursor.close();
            db.close();
            return location;

        }


        //Updates a Location
        public void updateLocation(Location v){

            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, v.getName());
            values.put(COLUMN_ADDRESS, v.getAddress());
            values.put(COLUMN_PHONE, v.getPhone());
            values.put(COLUMN_URL, v.getUrl());
            values.put(COLUMN_VISITED, v.getVisited());
            values.put(COLUMN_DATE_VISITED, v.getDateVisited());
            values.put(COLUMN_LIKED, v.getUserLiked());
            values.put(COLUMN_DELETED, v.getDeleted());


            SQLiteDatabase db = this.getWritableDatabase();

            db.update(TABLE_LOCATIONS, values, COLUMN_ID + " = " + v.getId(), null);
            db.close();

        }

        //Flags a record as deleted or not
        public void deleteLocation(Location v){


            String query = "SELECT * FROM " + TABLE_LOCATIONS + " WHERE " + COLUMN_ID + " = \"" + v.getId() + "\"";

            SQLiteDatabase db = this.getWritableDatabase();

            Cursor cursor = db.rawQuery(query, null);

            if(cursor.moveToFirst()) {
                if (v.getDeleted()==0)
                {v.setDeleted(1);
                updateLocation(v);}
                else
                {
                    v.setDeleted(0);
                    updateLocation(v);
                }
            }

            db.close();
        }


    }

