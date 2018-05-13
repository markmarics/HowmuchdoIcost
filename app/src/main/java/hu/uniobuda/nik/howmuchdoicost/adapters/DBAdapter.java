package hu.uniobuda.nik.howmuchdoicost.adapters;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import hu.uniobuda.nik.howmuchdoicost.data.DataBase;
import hu.uniobuda.nik.howmuchdoicost.models.Transaction;

public class DBAdapter {
    private DataBase db;

    public DBAdapter(Context context) {
        db= new DataBase(context);

    }


    public boolean addTransaction(Transaction transaction){
        return db.insertTransaction(transaction);
    }

    public ArrayList<Transaction> loadTransactions(){
        Cursor dbTransactions = db.loadTransactions();
        ArrayList<Transaction> tempTrans = new ArrayList<>();
        dbTransactions.moveToFirst();
        while (!dbTransactions.isAfterLast()){
            tempTrans.add(new Transaction(dbTransactions.getString(dbTransactions.getColumnIndex("type")),
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
