package hu.uniobuda.nik.howmuchdoicost.activity;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import hu.uniobuda.nik.howmuchdoicost.R;
import hu.uniobuda.nik.howmuchdoicost.adapters.DBAdapter;
import hu.uniobuda.nik.howmuchdoicost.models.Transaction;

public class StatisticsActivity extends AppCompatActivity {

    ListView transactionListView;
    ArrayAdapter<Transaction> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        transactionListView = findViewById(R.id.transactionListView);

        DBAdapter db = new DBAdapter(this);
        ArrayList translist = db.loadTransactions();

        adapter= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,translist);
        transactionListView.setAdapter(adapter);
    }

}
