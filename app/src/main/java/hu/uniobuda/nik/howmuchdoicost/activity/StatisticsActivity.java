package hu.uniobuda.nik.howmuchdoicost.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import hu.uniobuda.nik.howmuchdoicost.R;
import hu.uniobuda.nik.howmuchdoicost.adapters.DBAdapter;
import hu.uniobuda.nik.howmuchdoicost.models.Transaction;

public class StatisticsActivity extends AppCompatActivity {

    ListView transactionListView;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        transactionListView = findViewById(R.id.transactionListView);

        DBAdapter db = new DBAdapter(this);
        ArrayList<Transaction> translist = db.loadTransactions();

        ArrayList<String> output = new ArrayList<>();
        for (Transaction item : translist){
            output.add(item.getName() + " (" + item.getDate()+ ")");
        }

        adapter= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,output);
        transactionListView.setAdapter(adapter);
    }

}
