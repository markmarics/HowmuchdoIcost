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
import hu.uniobuda.nik.howmuchdoicost.adapters.DBHandler;
import hu.uniobuda.nik.howmuchdoicost.models.Transaction;

public class MainActivity extends AppCompatActivity {
    private Button buttonTrans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonTrans = (Button)findViewById(R.id.Button_add);
        buttonTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTransactionActivity();
            }
        });

    }


    public void openTransactionActivity(){
        Intent AddTransActivity = new Intent(this, AddTransactionActivity.class);
        startActivity(AddTransActivity);
    }
}
