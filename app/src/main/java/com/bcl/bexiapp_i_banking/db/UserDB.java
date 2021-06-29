package com.bcl.bexiapp_i_banking.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import io.reactivex.annotations.NonNull;

public class UserDB extends SQLiteOpenHelper {

    private static final String DB_NAME = "user.db";
    private static final int VERSION = 1;


    //create table table_name (column1 datatype, column2 datatype)
    //USER_TABLE
    static String USER = "user";
    static String U_ID = "_id";
    static String U_NAME = "uname";
    static String U_MOBILE = "umobile";
    static String U_DOB = "udob";
    static String U_EMAIL = "uemail";
    static String U_C_DATE = "ucdate";
    static String USER_TABLE_SCRIPT = "create table "+ USER + " ( "+U_ID+" integer primary key autoincrement, "+U_NAME+" text, "+U_MOBILE+" text, "+U_DOB+" text, "+U_EMAIL+" text, "+U_C_DATE+" text)";
    static String USER_TABLE_DROP = "DROP TABLE IF EXISTS "+ USER;


    public UserDB(Context context) {
        super(context, DB_NAME, null, VERSION);
        Log.e(DB_NAME,"DB CREATED");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(USER_TABLE_SCRIPT);
        Log.e(DB_NAME,"TABLE CREATED");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL(USER_TABLE_DROP);
        onCreate(sqLiteDatabase);//Create DB Again
        Log.e(DB_NAME,"TABLE UPGRADED New Version: "+i +" Old Version: "+ i1);
    }


    ////////////////////////insert into user (username, mobile) values ("","")
    public long saveUser(UserM userM) {
        SQLiteDatabase db = this.getWritableDatabase();

        Log.e(DB_NAME, userM.getName());

        ContentValues cv = new ContentValues();
        cv.put(U_NAME, userM.getName());
        cv.put(U_MOBILE, userM.getMobile());
        cv.put(U_EMAIL, userM.getEmail());
        cv.put(U_DOB, userM.getDob());

        long res = db.insert(USER, null, cv);

        //db.close();

//        if (res > 0) {
//            return "Insert Successful!";
//        } else {
//            return "Insert Failed!";
//        }
        return res;
    }
/////////////////////////////////////////////////////////////////////////////////////////

    ///////////// fetch data from local database .//////////////////////


    public ArrayList<UserM> getUsers(){

        ArrayList<UserM> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT * FROM "+ USER;

        Cursor cursor = db.rawQuery(sql, null);


        if (cursor != null && cursor.moveToFirst()) {//Action

            do {
                //data read
                UserM model = new UserM();

                model.setId(cursor.getString(cursor.getColumnIndex(U_ID)));
                model.setName(cursor.getString(cursor.getColumnIndex(U_NAME)));
                model.setMobile(cursor.getString(cursor.getColumnIndex(U_MOBILE)));
                model.setEmail(cursor.getString(cursor.getColumnIndex(U_EMAIL)));
                model.setDob(cursor.getString(cursor.getColumnIndex(U_DOB)));

                Log.e(DB_NAME, "Name: " + model.getName());

                list.add(model);
            } while (cursor.moveToNext());//Condition
        }



        try {
            db.close();
            cursor.close();
        }catch (Exception e){}

        return list;

    }

    /////////////////////////////////////////////////////////////////////////////////////
    ////////////////// delete all data ////////////////////////////////////////////

    public int deleteAllUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(USER,null, null);
    }
    ////////////////////////////////////////////////////////////////////////////////

    //////////////////////// delete single user ////////////////////////////////////

    public int deleteUser(@NonNull String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(USER,""+U_ID +" = ?", new String[]{id});
    }

    //////////////////////////////////////////////////////////////////////////

    ////////////////////////// update user //////////////////////////////////

    public int updateUser( UserM userM){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(U_NAME, userM.getName());
        cv.put(U_MOBILE, userM.getMobile());
        cv.put(U_EMAIL, userM.getEmail());
        cv.put(U_DOB, userM.getDob());

        return  db.update(USER, cv, ""+U_ID +" = ?", new String[]{userM.getId()});
    }


}
