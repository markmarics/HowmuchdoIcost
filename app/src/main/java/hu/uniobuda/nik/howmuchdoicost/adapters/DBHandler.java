package hu.uniobuda.nik.howmuchdoicost.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import hu.uniobuda.nik.howmuchdoicost.models.Transaction;

public class DBHandler extends SQLiteOpenHelper {

    private final static String DB_NAME = "mydb";
    private final static int DB_VERSION = 1;
    private final static String TABLE_NAME = "transactions";



    public DBHandler(Context context) {
        super(context, DB_NAME, null, 1);
    }

    public  boolean insertTransaction(Transaction transaction){
        SQLiteDatabase db = this.getWritableDatabase();
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
            long result = db.insert(TABLE_NAME, null, cv);
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
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        return  result;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "type TEXT," +
                "name TEXT," +
                "price DOUBLE," +
                "date DATE," +
                "place TEXT," +
                "rating INTEGER," +
                "comment TEXT"+
                ")"
        );



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
}
