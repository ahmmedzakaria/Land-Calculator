package com.example.ahmme.landcalculator.quadrangle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Laptop Dream on 14-Jul-16.
 */
public class LandlInfoManager {
    private DataBaseHelper helper;
    private SQLiteDatabase database;
    LandInfo area =new LandInfo();
    private Context context;

    public LandlInfoManager(Context context) {
        this.context = context;
        helper = new DataBaseHelper(context);

    }

    private void open() {
        database = helper.getWritableDatabase();

    }

    private void close() {
        helper.close();
    }

    public boolean addLandHistory(LandInfo contact) {

        this.open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.COL_CAPTION, contact.getCaption());
        contentValues.put(DataBaseHelper.COL_lENGTH_1, contact.getLength_1());
        contentValues.put(DataBaseHelper.COL_lENGTH_2, contact.getLength_2());
        contentValues.put(DataBaseHelper.COL_WIDTH_1, contact.getWidth_1());
        contentValues.put(DataBaseHelper.COL_WIDTH_2, contact.getWidth_2());

        long inserted = database.insert(DataBaseHelper.TABLE_LAND_INFO, null, contentValues);
        this.close();

        database.close();

        if (inserted > 0) {
            return true;
        } else return false;

    }

    public ArrayList<LandInfo> getAllLandHistory() {

        this.open();

        ArrayList<LandInfo> contactList = new ArrayList<>();

        Cursor cursor = database.query(DataBaseHelper.TABLE_LAND_INFO, null, null, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++) {
                int id = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_ID));
                String caption = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_CAPTION));
                float lenth_1 = cursor.getFloat(cursor.getColumnIndex(DataBaseHelper.COL_lENGTH_1));
                float lenth_2 = cursor.getFloat(cursor.getColumnIndex(DataBaseHelper.COL_lENGTH_2));
                float width_1 = cursor.getFloat(cursor.getColumnIndex(DataBaseHelper.COL_WIDTH_1));
                float width_2 = cursor.getFloat(cursor.getColumnIndex(DataBaseHelper.COL_WIDTH_2));

                LandInfo LandInfo = new LandInfo(id,caption, lenth_1 ,lenth_2 ,width_1 ,width_2);
                contactList.add(LandInfo);
                cursor.moveToNext();
            }
            this.close();

        }
        return contactList;
    }


    public LandInfo getMemberMealInfoById(int id) {

        this.open();

        Cursor cursor = database.query(DataBaseHelper.TABLE_LAND_INFO, new String[]{DataBaseHelper.COL_ID,
                        DataBaseHelper.COL_CAPTION, DataBaseHelper.COL_lENGTH_1, DataBaseHelper.COL_lENGTH_2,
        DataBaseHelper.COL_WIDTH_1, DataBaseHelper.COL_WIDTH_2},
                DataBaseHelper.COL_ID + " = " + id, null, null, null, null);
        cursor.moveToFirst();

        int colId = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_ID));
        String caption = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_CAPTION));
        float length_1 = cursor.getFloat(cursor.getColumnIndex(DataBaseHelper.COL_lENGTH_1));
        float length_2 = cursor.getFloat(cursor.getColumnIndex(DataBaseHelper.COL_lENGTH_2));
        float width_1 = cursor.getFloat(cursor.getColumnIndex(DataBaseHelper.COL_WIDTH_1));
        float width_2 = cursor.getFloat(cursor.getColumnIndex(DataBaseHelper.COL_WIDTH_2));

        LandInfo contact = new LandInfo(colId, caption, length_1 ,length_2 ,width_1 ,width_2);
        this.close();
        return contact;
    }




    public boolean updateHistory(int id, LandInfo contact) {
        this.open();
        ContentValues cv = new ContentValues();
        cv.put(DataBaseHelper.COL_CAPTION, contact.getCaption());
        cv.put(DataBaseHelper.COL_lENGTH_1, contact.getLength_1());
        cv.put(DataBaseHelper.COL_lENGTH_2, contact.getLength_2());
        cv.put(DataBaseHelper.COL_WIDTH_1, contact.getWidth_1());
        cv.put(DataBaseHelper.COL_WIDTH_2, contact.getWidth_2());

        int updated = database.update(DataBaseHelper.TABLE_LAND_INFO, cv, DataBaseHelper.COL_ID + " = " + id, null);
        this.close();
        if (updated > 0) {
            return true;
        } else
            return false;
    }

    public boolean deleteAllHistory() {
        this.open();
        int deleted = database.delete(DataBaseHelper.TABLE_LAND_INFO,null,null);
        this.close();
        if (deleted > 0) {
            return true;
        } else return false;
    }
    public boolean deleteMealInfo(int id) {
        this.open();
        int deleted = database.delete(DataBaseHelper.TABLE_LAND_INFO, DataBaseHelper.COL_ID + " = " + id, null);
        this.close();
        if (deleted > 0) {
            return true;
        } else return false;

    }


}
