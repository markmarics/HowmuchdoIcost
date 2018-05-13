package hu.uniobuda.nik.howmuchdoicost.activity;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import hu.uniobuda.nik.howmuchdoicost.R;
import hu.uniobuda.nik.howmuchdoicost.adapters.DBHandler;

public class StatisticsActivity extends AppCompatActivity {

    ListView transactionListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        transactionListView = findViewById(R.id.transactionListView);

        DBHandler dbHandler = new DBHandler(this);
        Cursor cursor = dbHandler.loadTransactions();
    }
}
