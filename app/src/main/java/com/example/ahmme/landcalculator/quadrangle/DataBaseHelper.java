package com.example.ahmme.landcalculator.quadrangle;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by BITM Trainer - 401 on 7/10/2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

   private static final String DATABASE_NAME = "land_db";
   private static final int DATABASE_VERSION = 1;

    public static final String TABLE_LAND_INFO = "land_info";


    public static final String COL_ID = "id";
    public static final String COL_CAPTION = "caption";
    public static final String COL_lENGTH_1 = "lenth_1";
    public static final String COL_lENGTH_2 = "lenth_2";
    public static final String COL_WIDTH_1 = "width_1";
    public static final String COL_WIDTH_2 = "width_2";







    private static final String CREATE_MESS_MEAL_TABLE = " CREATE TABLE " + TABLE_LAND_INFO +
            "( " + COL_ID + " INTEGER PRIMARY KEY," + COL_CAPTION + " TEXT, " + COL_lENGTH_1 + " TEXT, " +
            COL_lENGTH_2 + " TEXT, " + COL_WIDTH_1 + " TEXT, " + COL_WIDTH_2 + " TEXT )";




    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_MESS_MEAL_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


    }
}
