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

    public Cursor loadTransactions(){
        Cursor dbTransactions = db.loadTransactions();
        return dbTransactions;
    }
}
