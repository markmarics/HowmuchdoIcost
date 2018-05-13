package hu.uniobuda.nik.howmuchdoicost.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import hu.uniobuda.nik.howmuchdoicost.R;
import hu.uniobuda.nik.howmuchdoicost.adapters.DBAdapter;
import hu.uniobuda.nik.howmuchdoicost.models.Transaction;

public class ChartActivity extends AppCompatActivity {

    private static String TAG = "ChartActivity";

    DBAdapter db;
    PieChart pieChart;
   // private String[] yData;
    private double[] yData;
    private ArrayList<String> xData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        xData=new ArrayList<>();
        db = new DBAdapter(this);
        ArrayList<Transaction> transactionslist = db.loadTransactions();
        for (Transaction item:transactionslist
             ) {
            if(!xData.contains(item.getType())){
                xData.add(item.getType());
            }
        }
        yData=new double[xData.size()];
        for(int i=0;i<xData.size();i++){
            for (Transaction item:transactionslist
                 ) {
                if(xData.get(i).equals(item.getType())){
                    yData[i]+=item.getPrice();
                }
            }
        }

        pieChart = (PieChart) findViewById(R.id.pieChart);

      //  pieChart.setDescription();
        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Költségek");
        pieChart.setCenterTextSize(10);
        pieChart.setDrawEntryLabels(true);

        addDataSet(pieChart);

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

            }

            @Override
            public void onNothingSelected() {

            }
        });
    }

    private void addDataSet(PieChart pieChart) {

        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.MAGENTA);
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.CYAN);
        for (int i =0;i<yData.length;i++){
            yEntrys.add(new PieEntry(((int) yData[i]),i));
        }

        for (int i =1;i<xData.toArray().length;i++){
            xEntrys.add(xData.get(i));
        }


        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Költségek");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);


        pieDataSet.setColors(colors);

        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }
}
