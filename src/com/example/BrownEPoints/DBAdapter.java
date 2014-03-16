package com.example.BrownEPoints;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter {


    private static final String TAG = "DBAdapter";

    // Version of the DB for debugging
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "Database";

    public static final String KEY_ROWID = "_id";
    public static final int COL_ROWID = 0;

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
    public static final String KEY_PASSWORD = "Password";
    public static final String KEY_USERNAME = "Username";
    public static final String KEY_CREDIBILITY = "Credibility";
    public static final String KEY_POINTS = "Points";
    public static final String KEY_ETHNICITY = "Ethnicity";
    public static final String KEY_AGE = "Age";
    public static final String KEY_COUNTRY = "Country";
    public static final String KEY_STATE = "State";
    public static final String KEY_GENDER = "Gender";


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
                    + " create table if not exists assignments ("
                    + KEY_EMAIL + "text not null " + "PRIMARY KEY, "
                    + KEY_PASSWORD + "text not null,"
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
                    + " create table if not exists assignments ("
                    + KEY_COMPANYID + "integer not null " + "PRIMARY KEY, "
                    + KEY_COMPANYNAME + "text not null, "
                    + KEY_ADDRESS + "text not null, "
                    + KEY_URL + "text not null "
                    + ");";

    //SQL to create the Ad table
    private static final String CREATE_TABLE_AD =
            "create table " + AD_TABLE
                    + " create table if not exists assignments ("
                    + KEY_ADID + "integer not null " + "PRIMARY KEY, "
                    + KEY_COMPANYID + "integer not null " + "FOREIGN KEY REFERENCES " + COMPANY_TABLE + "(" + KEY_COMPANYID + "), "
                    + KEY_POINTVALUE + "integer not null, "
                    + KEY_TYPE + "text not null, "
                    + KEY_DATEUPLOADED + "datetime not null"
                    + ");";

    //SQL to create the Watches table
    private static final String CREATE_TABLE_WATCHES =
            "create table " + WATCHES_TABLE
                    + " create table if not exists assignments ("
                    + KEY_ADID + "integer not null " + "FOREIGN KEY REFERENCES " + AD_TABLE + "(" + KEY_ADID + "), "
                    + KEY_EMAIL + "text not null " + "FOREIGN KEY REFERENCES " + USER_TABLE + "(" + KEY_EMAIL + "), "
                    + KEY_DATEWATCHED + "datetime not null, "
                    + KEY_RATING + "text not null "
                    + ");";

    //SQL to create the Rates table
    private static final String CREATE_TABLE_RATES =
            "create table " + RATES_TABLE
                    + " create table if not exists assignments ("
                    + KEY_COMPANYID + "integer not null " + "FOREIGN KEY REFERENCES " + COMPANY_TABLE + "(" + KEY_COMPANYID + "), "
                    + KEY_EMAIL + "text not null " + "FOREIGN KEY REFERENCES " + USER_TABLE + "(" + KEY_EMAIL + "), "
                    + KEY_DATERATED + "datetime not null, "
                    + KEY_RATING + "integer not null "
                    + ");";


    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;


    public DBAdapter(Context context)
    {
        this.context = context;
        DBHelper = new DatabaseHelper(context);
    }

//////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////
    private class DatabaseHelper extends SQLiteOpenHelper
    {



        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_USER);
//            db.execSQL(CREATE_TABLE_WATCHES);
//            db.execSQL(CREATE_TABLE_AD);
//            db.execSQL(CREATE_TABLE_COMPANY);
//            db.execSQL(CREATE_TABLE_RATES);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }


    // Open the database connection.
    public DBAdapter open() {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    // Close the database connection.
    public void close() {
        DBHelper.close();
    }

//////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////
/*
--------------------------------------------------------------------------------------------
                            USER TABLE QUERY FUNCTIONS
--------------------------------------------------------------------------------------------
*/


    public long insertUser(String Email,  String Password, String Username, String Credibility,
                           String Points, String Ethnicity, String Age, String Country,
                           String State, String Gender)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_EMAIL, Email);
        initialValues.put(KEY_PASSWORD, Password);
        initialValues.put(KEY_USERNAME, Username);
        initialValues.put(KEY_CREDIBILITY, Credibility);
        initialValues.put(KEY_POINTS, Points);
        initialValues.put(KEY_ETHNICITY, Ethnicity);
        initialValues.put(KEY_AGE, Age);
        initialValues.put(KEY_COUNTRY, Country);
        initialValues.put(KEY_STATE, State);
        initialValues.put(KEY_GENDER, Gender);

        return db.insert(USER_TABLE, null, initialValues);

    }


    public boolean deleteUser(String Email)
    {
        return db.delete(USER_TABLE, KEY_EMAIL + "=" + Email, null) > 0;
    }

    public Cursor getUser(String Email)
    {
        Cursor qCursor =
                db.query(true, USER_TABLE, new String[] {KEY_EMAIL, KEY_USERNAME, KEY_CREDIBILITY, KEY_POINTS,
                KEY_ETHNICITY, KEY_AGE, KEY_COUNTRY, KEY_STATE, KEY_GENDER}, KEY_EMAIL + "=" + Email, null, null, null, null, null);

        if (qCursor != null) {
            qCursor.moveToFirst();
        }
        return qCursor;

    }

    public boolean isUser(String Email)
    {
        if(getUser(Email) != null)
            return true;
        return false;
    }

    public boolean updateUser(String Email, String Password, String Username, String Credibility,
                              String Points, String Ethnicity, String Age, String Country,
                              String State, String Gender)
    {
        ContentValues newValues = new ContentValues();
        newValues.put(KEY_EMAIL, Email);
        newValues.put(KEY_PASSWORD, Password);
        newValues.put(KEY_USERNAME, Username);
        newValues.put(KEY_CREDIBILITY, Credibility);
        newValues.put(KEY_POINTS, Points);
        newValues.put(KEY_ETHNICITY, Ethnicity);
        newValues.put(KEY_AGE, Age);
        newValues.put(KEY_COUNTRY, Country);
        newValues.put(KEY_STATE, State);
        newValues.put(KEY_GENDER, Gender);
        return db.update(USER_TABLE, newValues, KEY_EMAIL + "=" + Email, null) > 0;
    }


/*
--------------------------------------------------------------------------------------------
                            COMPANY TABLE QUERY FUNCTIONS
--------------------------------------------------------------------------------------------
*/


/*
--------------------------------------------------------------------------------------------
                            AD TABLE QUERY FUNCTIONS
--------------------------------------------------------------------------------------------
*/


/*
--------------------------------------------------------------------------------------------
                            WATCHES TABLE QUERY FUNCTIONS
--------------------------------------------------------------------------------------------
*/


/*
--------------------------------------------------------------------------------------------
                            RATES TABLE QUERY FUNCTIONS
--------------------------------------------------------------------------------------------
*/

}
