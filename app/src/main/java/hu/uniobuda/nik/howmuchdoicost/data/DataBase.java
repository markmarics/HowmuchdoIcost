package hu.uniobuda.nik.howmuchdoicost.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Date;

import hu.uniobuda.nik.howmuchdoicost.models.Transaction;

public class DataBase {


    private final static String DB_NAME = "mydb";
    private final static int DB_VERSION = 1;
    private final static String TABLE_NAME_TRANSACTIONS = "transactions";
    private final static String TABLE_NAME_TYPES = "types";
    DBHelper dbHelper;

    public DataBase(Context context) {
        dbHelper = new DBHelper(context);
    }

    public boolean insertType(String type){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        try{
            cv.put("type",type);
            long result = db.insert(TABLE_NAME_TYPES, null, cv);
            if(result==-1){
                return false;
            }else{
                return true;
            }
        }
        catch (NullPointerException e){
            Log.d("no type, error:",e.getMessage());
            return false;
        }
        catch (SQLException e){
            Log.d("hiba",e.getMessage());
            return false;
        }

    }

    public boolean deleteTransaction(long id) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        boolean success = sqLiteDatabase.delete(TABLE_NAME_TRANSACTIONS, " id = " + id, null) > 0;
        sqLiteDatabase.close();
        return success;
    }

    public boolean updateTransaction(long id, String newType, String newName, int newPrice,
                                     Date newDate, String newPlace, int newRating, String newComment){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        try {
            //  cv.put("_id", transaction.getId());
            cv.put("type", newType);
            cv.put("name", newName);
            cv.put("price", newPrice);
            cv.put("date", newDate.toString());
            cv.put("place", newPlace);
            cv.put("rating", newRating);
            cv.put("comment", newComment);
        }
        catch (NullPointerException e) {

        }
        boolean success = sqLiteDatabase.update("TABLE_NAME_TRANSACTIONS",cv," id = " + id,null)>0;
        return success;
    }
    public  boolean insertTransaction(Transaction transaction){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        try {
            //  cv.put("_id", transaction.getId());
            cv.put("type", transaction.getType());
            cv.put("name", transaction.getName());
            cv.put("price", transaction.getPrice());
            cv.put("date", transaction.getDate().toString());
            cv.put("place", transaction.getPlace());
            cv.put("rating", transaction.getRating());
            cv.put("comment", transaction.getComment());
        }
        catch (NullPointerException e) {

        }
        try{
            long result = db.insert(TABLE_NAME_TRANSACTIONS, null, cv);
            if(result==-1){
                return false;
            }else{
                return true;
            }
        }catch (SQLException e){
            Log.d("hiba",e.getMessage());
            return false;
        }



    }

    public Cursor loadTransactions(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM " + TABLE_NAME_TRANSACTIONS,null);
        result.moveToFirst();
        db.close();
        return  result;
    }

    public Cursor loadTypes(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM " + TABLE_NAME_TYPES,null);
        result.moveToFirst();
        db.close();
        return  result;
    }

    private class DBHelper extends SQLiteOpenHelper {


        public DBHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }




        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL("CREATE TABLE " + TABLE_NAME_TRANSACTIONS + " (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "type TEXT," +
                    "name TEXT," +
                    "price DOUBLE," +
                    "date TEXT," +
                    "place TEXT," +
                    "rating INTEGER," +
                    "comment TEXT"+
                    ")"
            );

            db.execSQL("CREATE TABLE " + TABLE_NAME_TYPES + " (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "type TEXT" +
                    ")"
            );
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_TRANSACTIONS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_TYPES);
            onCreate(db);

        }
    }


}
