package hu.uniobuda.nik.howmuchdoicost;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Date;

import hu.uniobuda.nik.howmuchdoicost.adapters.DBHandler;
import hu.uniobuda.nik.howmuchdoicost.models.Transaction;

public class MainActivity extends AppCompatActivity {

    private Transaction transaction;
    private TextView textView;
    DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView= (TextView) findViewById(R.id.textview);
        dbHandler= new DBHandler(this);
        transaction=new Transaction(1,"kaja","meki",
                2000,new Date(2018,04,14,14,14),"MCDONALDS",10,null);

        StringBuilder stringBuilder = new StringBuilder();
        dbHandler.insertTransaction(1,"kaja","meki",
                2000,new Date(2018,04,14,14,14),"MCDONALDS",10,null);
        Cursor cursor = dbHandler.loadTransactions();


            long id = cursor.getLong(2);
            String name = cursor.getString(cursor.getColumnIndex("name"));
            stringBuilder.append(name).append("\n");

        textView.setText(stringBuilder.toString());
    }
}
