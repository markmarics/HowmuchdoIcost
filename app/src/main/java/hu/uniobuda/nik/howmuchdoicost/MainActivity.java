package hu.uniobuda.nik.howmuchdoicost;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

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
                2000,new Date(),"MCDONALDS",10,null);

        StringBuilder stringBuilder = new StringBuilder();
        if(dbHandler.loadTransactions()==null){
            if(dbHandler.insertTransaction(transaction)){
                toastMessage("Data successfully Inserted");

            } else{
                toastMessage("Something went wrong");
            }
        }
        Cursor cursor = dbHandler.loadTransactions();


        while (cursor.moveToNext()){

            String name = cursor.getString(2);

            stringBuilder.append(name).append("\n");

        }
        textView.setText(stringBuilder);


    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
