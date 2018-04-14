package hu.uniobuda.nik.howmuchdoicost.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

public class DBHandler {

    protected final static String DB_NAME = "mydb";
    protected final static int DB_VERSION = 1;
    protected final static String TABLE_NAME = "transactions";

    protected DBHelper dbHelper;

    public DBHandler(Context context){
        dbHelper = new DBHelper(context);
    }

    public  void insertTransaction(int id, String type, String name, double price, Date date, String place, int rating, String comment){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id",id);
        cv.put("type",type);
        cv.put("name",name);
        cv.put("price",price);
        cv.put("date",date.toString());
        cv.put("place",place);
        cv.put("rating",rating);
        cv.put("comment",comment);
        db.insert(TABLE_NAME, null, cv);
        db.close();
    }

    public Cursor loadTransactions(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor result = db.query(TABLE_NAME, null, null, null, null, null, null);
        result.moveToFirst();
        db.close();
        return result;
    }



    public class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "type VARCHAR(255)," +
                    "name VARCHAR(255)," +
                    "price FLOAT," +
                    "date DATE," +
                    "place VARCHAR(255)," +
                    "rating INTEGER," +
                    "comment VARCHAR(255)"+
                    ")"
            );
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(sqLiteDatabase);
        }
    }
}
