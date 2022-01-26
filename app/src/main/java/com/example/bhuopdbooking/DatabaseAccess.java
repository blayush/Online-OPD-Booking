package com.example.bhuopdbooking;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseAccess {
    private SQLiteAssetHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;
    Cursor c=null,c2=null;
    //private constructor,so that object creation from outside the class is avoided
    private DatabaseAccess(Context context){
        this.openHelper=new DatabaseOpenHelper(context);
    }
    public static DatabaseAccess getInstance(Context context){
        if(instance==null){
            instance=new DatabaseAccess(context);
        }
        return instance;
    }
    public void open(){
        this.db=openHelper.getWritableDatabase();
    }
    public void close(){
        if(db!=null){
            this.db.close();
        }
    }
    public String getDoctorNames(String day,String department){
        StringBuffer buffer = new StringBuffer();
        try {
            c = db.rawQuery("select " + day + " from [Modern Medicine] where Department = '" + department + "'", new String[]{});
            while (c.moveToNext()) {
                String doctor = c.getString(0);
                buffer.append("" + doctor);
            }
            c.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return buffer.toString();
    }
    public String getRoomNo(String department){
        StringBuffer buffer = new StringBuffer();
        try {
            c = db.rawQuery("select " + "RoomNo" + " from [Modern Medicine] where Department = '" + department + "'", new String[]{});
            while (c.moveToNext()) {
                String room = c.getString(0);
                buffer.append("" + room);
            }
            c.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return buffer.toString();
    }
}
