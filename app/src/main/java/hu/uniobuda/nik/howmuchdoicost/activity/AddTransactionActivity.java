package hu.uniobuda.nik.howmuchdoicost.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import org.w3c.dom.Text;

import java.io.IOException;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import hu.uniobuda.nik.howmuchdoicost.MainActivity;
import hu.uniobuda.nik.howmuchdoicost.R;
import hu.uniobuda.nik.howmuchdoicost.adapters.DBHandler;
import hu.uniobuda.nik.howmuchdoicost.models.Transaction;

public class AddTransactionActivity extends AppCompatActivity {
    private static final int PLACE_PICKER_REQUEST = 1;
    private static final String TAG = "AddTransactionActivity";

    EditText typeEditText;
    Spinner typeSpinner;
    EditText nameEditText;
    EditText priceEditText;
    Button saveButton;

    private TextView mDisplayDate, mDisplayPlace;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
        mDisplayDate = (TextView) findViewById(R.id.textviewDate);
        mDisplayDate = findViewById(R.id.textviewDate);
        mDisplayPlace = findViewById(R.id.textviewPlace);

        typeEditText = findViewById(R.id.editTextType);
        typeSpinner = findViewById(R.id.spinnerAllTypes);
        nameEditText = findViewById(R.id.editTextName);
        priceEditText = findViewById(R.id.editTextPrice);
        saveButton = findViewById(R.id.saveButton);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        AddTransactionActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,mDateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = new DateFormatSymbols().getMonths()[month]+"-"+Integer.toString(dayOfMonth)+"-"+Integer.toString(year);
                mDisplayDate.setText(date);
            }
        };


        mDisplayPlace = (TextView) findViewById(R.id.textviewPlace);

        mDisplayPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(AddTransactionActivity.this), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    Log.d("asdsadc", e.getMessage()) ;
                } catch (GooglePlayServicesNotAvailableException e) {
                    Log.d("asd", e.getMessage()) ;
                }
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                DBHandler dbHandler = new DBHandler(AddTransactionActivity.this);
                Transaction transaction = new Transaction();
                transaction.setName(nameEditText.getText().toString());
                transaction.setDate(new Date(2015, 04, 24));
                transaction.setPlace(mDisplayPlace.getText().toString());
                transaction.setRating(3);
                transaction.setType(typeEditText.getText().toString());
                transaction.setPrice(Integer.parseInt(priceEditText.getText().toString()));



                if ( dbHandler.insertTransaction(transaction)){
                    Toast.makeText(AddTransactionActivity.this, "done", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddTransactionActivity.this, "szar", Toast.LENGTH_SHORT).show();
                }}
                catch (NumberFormatException e){
                    Toast.makeText(AddTransactionActivity.this, "aggyá meg mindig valamit", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(this,data);
                mDisplayPlace.setText(place.getName());
            }
        }
    }
}