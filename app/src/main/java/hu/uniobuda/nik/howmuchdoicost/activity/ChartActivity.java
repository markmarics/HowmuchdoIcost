package hu.uniobuda.nik.howmuchdoicost.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

import hu.uniobuda.nik.howmuchdoicost.R;
import hu.uniobuda.nik.howmuchdoicost.adapters.DBAdapter;
import hu.uniobuda.nik.howmuchdoicost.models.Transaction;

public class ChartActivity extends AppCompatActivity {

    private static String TAG = "ChartActivity";

    DBAdapter db;
    PieChart pieChart;
   // private String[] yData;
    private int[] yData=new int[5];
    private String[] xData= {"food", "drink","rent","travel","other"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        ArrayList<Transaction> transactionslist = db.loadTransactions();
        for (int i =0;i<transactionslist.size();i++){
            for (int j =0;i<xData.length;i++){
                if(xData[j]==transactionslist.get(i).getType()){
                    yData[j]+=transactionslist.get(i).getPrice();
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
    }

    private void addDataSet(PieChart pieChart) {

        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();
        for (int i =0;i<yData.length;i++){
            yEntrys.add(new PieEntry(yData[i],i));
        }

        for (int i =1;i<xData.length;i++){
            xEntrys.add(xData[i]);
        }


        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Költségek");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.MAGENTA);
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.CYAN);

        pieDataSet.setColors(colors);

        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }
}
