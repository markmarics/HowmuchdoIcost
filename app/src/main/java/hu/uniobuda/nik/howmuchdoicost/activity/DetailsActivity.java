package hu.uniobuda.nik.howmuchdoicost.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import hu.uniobuda.nik.howmuchdoicost.R;
import hu.uniobuda.nik.howmuchdoicost.adapters.DBAdapter;
import hu.uniobuda.nik.howmuchdoicost.models.Transaction;

public class DetailsActivity extends AppCompatActivity {

    EditText editTextName;
    DBAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        editTextName = findViewById(R.id.editTextName);


        Intent intent = getIntent();
        int id = intent.getExtras().getInt("TRANSACTION_ID");
        dbAdapter = new DBAdapter(DetailsActivity.this);
        Transaction transaction = dbAdapter.loadTransactions().get(id);
        editTextName.setText(transaction.getName().toString(), TextView.BufferType.EDITABLE);
    }
}
