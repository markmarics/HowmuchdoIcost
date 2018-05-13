package hu.uniobuda.nik.howmuchdoicost;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import hu.uniobuda.nik.howmuchdoicost.activity.AddTransactionActivity;
import hu.uniobuda.nik.howmuchdoicost.activity.StatisticsActivity;
import hu.uniobuda.nik.howmuchdoicost.adapters.DBHandler;
import hu.uniobuda.nik.howmuchdoicost.models.Transaction;

public class MainActivity extends AppCompatActivity {
    private Button buttonTrans, buttonStat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonTrans = (Button)findViewById(R.id.Button_add);
        buttonStat = (Button)findViewById(R.id.Button_stat);

        buttonTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTransactionActivity();
            }
        });

        buttonStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStatisticsActivity();
            }
        });

    }


    public void openTransactionActivity(){
        Intent AddTransActivity = new Intent(this, AddTransactionActivity.class);
        startActivity(AddTransActivity);
    }

    public void openStatisticsActivity(){
        Intent StatisticsActivity = new Intent(this, StatisticsActivity.class);
        startActivity(StatisticsActivity);
    }
}
