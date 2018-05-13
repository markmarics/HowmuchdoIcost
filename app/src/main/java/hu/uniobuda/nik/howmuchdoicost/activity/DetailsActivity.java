package hu.uniobuda.nik.howmuchdoicost.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import hu.uniobuda.nik.howmuchdoicost.R;
import hu.uniobuda.nik.howmuchdoicost.adapters.DBAdapter;
import hu.uniobuda.nik.howmuchdoicost.models.Transaction;

public class DetailsActivity extends AppCompatActivity {


    TextView textViewTypes, textViewDate, textViewPlace, editTextName, editTextPrice, editTextComment;
    RatingBar ratingBar;
    DBAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        editTextName = findViewById(R.id.editTextName);
        editTextComment = findViewById(R.id.editTextComment);
        editTextPrice = findViewById(R.id.editTextPrice);
        textViewDate = findViewById(R.id.textviewDate);
        textViewPlace = findViewById(R.id.textviewPlace);
        textViewTypes = findViewById(R.id.textViewTypes);
        ratingBar = findViewById(R.id.rating);

        Intent intent = getIntent();
        int id = intent.getExtras().getInt("TRANSACTION_ID");
        dbAdapter = new DBAdapter(DetailsActivity.this);
        Transaction transaction = dbAdapter.loadTransactions().get(id);
        editTextName.setText(transaction.getName().toString(), TextView.BufferType.EDITABLE);
        editTextComment.setText(transaction.getComment().toString(), TextView.BufferType.EDITABLE);
        editTextPrice.setText(Double.toString(transaction.getPrice()), TextView.BufferType.EDITABLE);
        textViewTypes.setText(transaction.getType().toString(), TextView.BufferType.EDITABLE);
        textViewDate.setText(transaction.getDate().toString(), TextView.BufferType.EDITABLE);
        textViewPlace.setText(transaction.getPlace().toString(), TextView.BufferType.EDITABLE);
        ratingBar.setRating(transaction.getRating());
    }
}
