package hu.uniobuda.nik.howmuchdoicost.adapters;

import android.content.Context;
import android.database.Cursor;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import hu.uniobuda.nik.howmuchdoicost.data.DataBase;
import hu.uniobuda.nik.howmuchdoicost.models.Transaction;

public class DBAdapter {
    private DataBase db;

    public DBAdapter(Context context) {
        db= new DataBase(context);

    }

    public boolean addType(String type){
        return db.insertType(type);
    }

    public boolean addTransaction(Transaction transaction){
        return db.insertTransaction(transaction);
    }

    public ArrayList<String> loadTypes(){
        Cursor dbTypes = db.loadTypes();
        ArrayList<String> tempTypes = new ArrayList<>();
        dbTypes.moveToFirst();
        while (!dbTypes.isAfterLast()){
            tempTypes.add(dbTypes.getString(dbTypes.getColumnIndex("type")));
            dbTypes.moveToNext();
        }
        return tempTypes;
    }

    public ArrayList<Transaction> loadTransactions(){
        Cursor dbTransactions = db.loadTransactions();
        ArrayList<Transaction> tempTrans = new ArrayList<>();
        dbTransactions.moveToFirst();
        while (!dbTransactions.isAfterLast()){
            tempTrans.add(new Transaction(dbTransactions.getInt(dbTransactions.getColumnIndex("id")),
                    dbTransactions.getString(dbTransactions.getColumnIndex("type")),
                    dbTransactions.getString(dbTransactions.getColumnIndex("name")),
                    dbTransactions.getDouble(dbTransactions.getColumnIndex("price")),
                    dbTransactions.getString(dbTransactions.getColumnIndex("date")),
                    dbTransactions.getString(dbTransactions.getColumnIndex("place")),
                    dbTransactions.getInt(dbTransactions.getColumnIndex("rating")),
                    dbTransactions.getString(dbTransactions.getColumnIndex("comment"))
                    ));
            dbTransactions.moveToNext();
        }

        return tempTrans;
    }
}
