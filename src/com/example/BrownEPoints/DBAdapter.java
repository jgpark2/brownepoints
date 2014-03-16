package com.example.BrownEPoints;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter {

    private static final String TAG = "DBAdapter";

    // Version of the DB for debugging
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "Database";


    //Table names
    public static final String USER_TABLE = "UserTable";
    public static final String COMPANY_TABLE = "CompanyTable";
    public static final String AD_TABLE = "AdTable";
    public static final String WATCHES_TABLE = "WatchesTable";
    public static final String RATES_TABLE = "RatesTable";

/*
--------------------------------------------------------------------------------------------
                            TABLE ATTRIBUTES
--------------------------------------------------------------------------------------------
*/
    //Attributes for User
    public static final String KEY_EMAIL = "Email";
    public static final String KEY_USERNAME = "Username";
    public static final String KEY_CREDIBILITY = "Credibility";
    public static final String KEY_POINTS = "Points";
    public static final String KEY_ETHNICITY = "Ethnicity";
    public static final String KEY_AGE = "Age";
    public static final String KEY_COUNTRY = "Country";
    public static final String KEY_STATE = "State";
    public static final String KEY_GENDER = "Gender";

//    //Column Numbers
//    public static final int COL_EMAIL = 1;
//    public static final int COL_USERNAME = 2;
//    public static final int COL_CREDIBILITY = 3;
//    public static final int COL_POINTS = 4;
//    public static final int COL_ETHNICITY = 5;
//    public static final int COL_AGE = 6;
//    public static final int COL_COUNTRY = 7;
//    public static final int COL_STATE = 8;
//    public static final int COL_GENDER = 9;

    //Attributes for Company
    public static final String KEY_COMPANYID = "CompanyID";
    public static final String KEY_COMPANYNAME = "CompanyName";
    public static final String KEY_ADDRESS= "Address";
    public static final String KEY_URL = "URL";


    //Attributes for Ad
    public static final String KEY_ADID = "AdID";
        //public static final String KEY_COMPANYID = "CompanyID";
    public static final String KEY_POINTVALUE= "PointValue";
    public static final String KEY_TYPE = "Type";
    public static final String KEY_DATEUPLOADED = "DateUploaded";

    //Attributes for Watches
    public static final String KEY_RATING = "Rating";
    public static final String KEY_DATEWATCHED= "DateWatched";
        //public static final String KEY_EMAIL = "Email";
        // public static final String KEY_ADID = "AdID";

    //Attributes for Rates
    public static final String KEY_DATERATED = "DateRated";
        //public static final String KEY_RATING = "Rating";
        //public static final String KEY_EMAIL = "Email";
        //public static final String KEY_COMPANYID = "CompanyID";

/*
--------------------------------------------------------------------------------------------
                            CREATE TABLE SQL
--------------------------------------------------------------------------------------------
*/


    //SQL to create the User table
    private static final String CREATE_TABLE_USER =
            "create table " + USER_TABLE
                    + " ("
                    + KEY_EMAIL + "text not null " + "PRIMARY KEY, "
                    + KEY_USERNAME + "text not null, "
                    + KEY_CREDIBILITY + "integer not null, "
                    + KEY_POINTS + "integer not null, "
                    + KEY_ETHNICITY + "text not null, "
                    + KEY_AGE + "integer not null, "
                    + KEY_COUNTRY + "text not null, "
                    + KEY_STATE + "text not null, "
                    + KEY_GENDER + "text not null "
                    + ");";

    //SQL to create the Company table
    private static final String CREATE_TABLE_COMPANY =
            "create table " + COMPANY_TABLE
                    + " ("
                    + KEY_COMPANYID + "integer not null " + "PRIMARY KEY, "
                    + KEY_COMPANYNAME + "text not null, "
                    + KEY_ADDRESS + "text not null, "
                    + KEY_URL + "text not null "
                    + ");";

    //SQL to create the Ad table
    private static final String CREATE_TABLE_AD =
            "create table " + AD_TABLE
                    + " ("
                    + KEY_ADID + "integer not null " + "PRIMARY KEY, "
                    + KEY_COMPANYID + "integer not null, "
                    + KEY_POINTVALUE + "integer not null, "
                    + KEY_TYPE + "text not null, "
                    + KEY_DATEUPLOADED + "datetime not null"
                    + ");";

    //SQL to create the Watches table
    private static final String CREATE_TABLE_WATCHES =
            "create table " + WATCHES_TABLE
                    + " ("
                    + KEY_ADID + "integer not null " + "FOREIGN KEY REFERENCES " + AD_TABLE + "(" + KEY_ADID + "), "
                    + KEY_EMAIL + "text not null " + "FOREIGN KEY REFERENCES " + USER_TABLE + "(" + KEY_EMAIL + "), "
                    + KEY_DATEWATCHED + "datetime not null, "
                    + KEY_RATING + "text not null "
                    + ");";

    //SQL to create the Rates table
    private static final String CREATE_TABLE_RATES =
            "create table " + RATES_TABLE
                    + " ("
                    + KEY_COMPANYID + "integer not null " + "FOREIGN KEY REFERENCES " + COMPANY_TABLE + "(" + KEY_COMPANYID + "), "
                    + KEY_EMAIL + "text not null " + "FOREIGN KEY REFERENCES " + USER_TABLE + "(" + KEY_EMAIL + "), "
                    + KEY_DATERATED + "datetime not null, "
                    + KEY_RATING + "integer not null "
                    + ");";



    private static class DatabaseHelper extends SQLiteOpenHelper
    {



        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_USER);
            db.execSQL(CREATE_TABLE_WATCHES);
            db.execSQL(CREATE_TABLE_AD);
            db.execSQL(CREATE_TABLE_COMPANY);
            db.execSQL(CREATE_TABLE_RATES);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }



}
