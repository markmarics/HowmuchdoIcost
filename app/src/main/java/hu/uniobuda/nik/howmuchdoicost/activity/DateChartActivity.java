package hu.uniobuda.nik.howmuchdoicost.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;

import java.lang.reflect.Array;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import hu.uniobuda.nik.howmuchdoicost.R;
import hu.uniobuda.nik.howmuchdoicost.adapters.DBAdapter;
import hu.uniobuda.nik.howmuchdoicost.models.Transaction;

public class DateChartActivity extends AppCompatActivity {

    DBAdapter db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_chart);
        LineChart lineChart = findViewById(R.id.linechart);
        db = new DBAdapter(this);
        ArrayList<Transaction> transactions = db.loadTransactions();
        ArrayList<Entry> entries = new ArrayList<>();

        ArrayList<Date> dates = new ArrayList<>();
        String[] dateelements;
        for (Transaction item:transactions
             ) {
            dateelements=item.getDate().split("-");
            String[] month = new DateFormatSymbols().getMonths();
            int monthint;
            for (int i=0;i<month.length;i++){
                if(dateelements[2].equals(month[i])){
                    monthint=i;
                    dates.add(new Date(Integer.parseInt(dateelements[3]),monthint,Integer.parseInt(dateelements[1])));
                    break;
                }
            }

        }
    }
}
