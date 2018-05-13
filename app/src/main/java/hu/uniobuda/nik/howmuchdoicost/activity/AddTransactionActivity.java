package hu.uniobuda.nik.howmuchdoicost.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import hu.uniobuda.nik.howmuchdoicost.R;
import hu.uniobuda.nik.howmuchdoicost.adapters.DBAdapter;
import hu.uniobuda.nik.howmuchdoicost.models.Transaction;

public class AddTransactionActivity extends AppCompatActivity {
    private static final int PLACE_PICKER_REQUEST = 1;
    private static final String TAG = "AddTransactionActivity";

    EditText typeEditText;
    Spinner typeSpinner;
    EditText nameEditText;
    EditText priceEditText;
    Button saveButton, newCategoryButton;
    DBAdapter dbAdapter;

    private TextView dateTextView, mDisplayPlace;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private ArrayAdapter<String> Spinneradapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
        //dateTextView = (TextView) findViewById(R.id.textviewDate);
        dateTextView = findViewById(R.id.textviewDate);
        mDisplayPlace = findViewById(R.id.textviewPlace);
    //    typeEditText = findViewById(R.id.editTextType);
        typeSpinner = findViewById(R.id.spinnerAllTypes);
        nameEditText = findViewById(R.id.editTextName);
        priceEditText = findViewById(R.id.editTextPrice);
        saveButton = findViewById(R.id.saveButton);
        dbAdapter = new DBAdapter(AddTransactionActivity.this);
        ArrayList<String> types = new ArrayList<>();
        types.add("étel");
        types.add("ital");
        types.add("lakhatás");
        types.add("utazás");
        types.add("egyéb");

        Spinneradapter = new ArrayAdapter<>(AddTransactionActivity.
                this,android.R.layout.simple_list_item_1,types);
        Spinneradapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        typeSpinner.setAdapter(Spinneradapter);

        dateTextView.setOnClickListener(new View.OnClickListener() {
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
           //     dialog.setButton(DialogInterface.BUTTON_POSITIVE,"OK", (DialogInterface.OnClickListener) mDateSetListener);
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = new DateFormatSymbols().getMonths()[month]+"-"+Integer.toString(dayOfMonth)+"-"+Integer.toString(year);
                dateTextView.setText(date);
            }
        };


        //mDisplayPlace = (TextView) findViewById(R.id.textviewPlace);

        mDisplayPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(AddTransactionActivity.this), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    Log.d("Playservices error", e.getMessage()) ;
                } catch (GooglePlayServicesNotAvailableException e) {
                    Log.d("Playservices error", e.getMessage()) ;
                }
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                 if(nameEditText.getText().toString()==""||dateTextView.getText().toString()==""||
                         mDisplayPlace.getText().toString()==""||typeSpinner.getSelectedItem().toString()==""){
                     throw new NullPointerException();
                 }
                Transaction transaction = new Transaction();
                transaction.setName(nameEditText.getText().toString());
                transaction.setDate(dateTextView.getText().toString());
                transaction.setPlace(mDisplayPlace.getText().toString());
                transaction.setRating(3);
                transaction.setType(typeSpinner.getSelectedItem().toString());
                transaction.setPrice(Integer.parseInt(priceEditText.getText().toString()));
                if ( dbAdapter.addTransaction(transaction)){
                    Toast.makeText(AddTransactionActivity.this, "Sikeres!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddTransactionActivity.this, "Valami nem jó!", Toast.LENGTH_SHORT).show();
                }
                }
                catch (NullPointerException e){
                    Toast.makeText(AddTransactionActivity.this, "Mindent tölts ki!", Toast.LENGTH_SHORT).show();
                }
                catch (NumberFormatException e){
                    Toast.makeText(AddTransactionActivity.this, "Mindent tölts ki!", Toast.LENGTH_SHORT).show();

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