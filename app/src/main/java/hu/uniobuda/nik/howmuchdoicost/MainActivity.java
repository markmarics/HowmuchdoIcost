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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


}
