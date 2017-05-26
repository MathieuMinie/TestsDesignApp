package com.example.mathieu.testsdesignapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import android.view.View.OnClickListener;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class Main extends AppCompatActivity implements OnClickListener {

    String[] choice = new String[] {"Graph","Values","Graph + Values"};
    Button btnZone1;
    Button btnZone2;
    Button btnZone3;
    Button btnZone4;

    EditText txtZone1;
    EditText txtZone2;
    EditText txtZone3;
    EditText txtZone4;

    LineChart chart1;
    LineChart chart2;
    LineChart chart3;
    LineChart chart4;

    public int[] var1 = new int[10];
    public int[] var2 = new int[20];
    public Float[] var3 = new Float[50];
    public int[] var4 = new int[100];

    public ArrayList<Entry> entries1 = new ArrayList<>();
    public ArrayList<Entry> entries2 = new ArrayList<>();
    public ArrayList<Entry> entries3 = new ArrayList<>();
    public ArrayList<Entry> entries4 = new ArrayList<>();
    public ArrayList<String> xAxis = new ArrayList<>();

    LineDataSet dataSet1;
    LineDataSet dataSet2;
    LineDataSet dataSet3;
    LineDataSet dataSet4;

    ArrayAdapter adapter;
    AlertDialog.Builder alertDialog;
    MyValueFormatter DecimalValue = new MyValueFormatter();

    LineData lineData3;
    double x =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chart1 = (LineChart) findViewById(R.id.chart1);
        chart2 = (LineChart) findViewById(R.id.chart2);
        chart3 = (LineChart) findViewById(R.id.chart3);
        chart4 = (LineChart) findViewById(R.id.chart4);
        btnZone1 = (Button)findViewById(R.id.btnZone1);
        btnZone1.setOnClickListener(this);
        btnZone2 = (Button)findViewById(R.id.btnZone2);
        btnZone2.setOnClickListener(this);
        btnZone3 = (Button)findViewById(R.id.btnZone3);
        btnZone3.setOnClickListener(this);
        btnZone4 = (Button)findViewById(R.id.btnZone4);
        btnZone4.setOnClickListener(this);

        txtZone1 = (EditText)findViewById(R.id.txtZone1);
        txtZone2 = (EditText)findViewById(R.id.txtZone2);
        txtZone3 = (EditText)findViewById(R.id.txtZone3);
        txtZone4 = (EditText)findViewById(R.id.txtZone4);


        createVars();
        Log.d("Creating vars :"," SUCCESS");
        createEntries();
        Log.d("Creating entries :"," SUCCESS");
        createDataSets();
        Log.d("Creating DataSets :"," SUCCESS");
        createGraphs();
        Log.d("Creating Graphs :"," SUCCESS");

    }

    public void dialogDataType (final int zone){    // création d'une liste déroulante à 3 choix : afficher un graphe, afficher des valeurs simples, ou afficher les deux
                                                    // pour chaque zone de choix
        alertDialog = new AlertDialog.Builder(this);  //utilisation d'un objet de typer alertDialog
        adapter = new ArrayAdapter(this, android.R.layout.select_dialog_singlechoice, choice); //on met dans un arrayAdapter les trois choix sous forme de Strings

        alertDialog.setSingleChoiceItems(adapter, -1, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
                int choices = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
                // TODO save deviceAddress
                showPanel(zone,choices);
            }
        });
        alertDialog.setTitle("Choose your values showing");
        alertDialog.show();
    }

    public void onClick(View v) {
        int id = v.getId();
        Intent intent = null;
        switch (id) {
            case R.id.btnZone1:
                dialogDataType(0);
                break;
            case R.id.btnZone2:
                dialogDataType(1);
                break;
            case R.id.btnZone3:
                dialogDataType(2);
                break;
            case R.id.btnZone4:
                dialogDataType(3);
                break;
        }
    }

    private void showPanel(int position,int choice){  // affichage du choix effectué par l'utilisateur
        switch (position) {
            case 0:  // pour la zone 1
                switch(choice){
                    case 0:
                        btnZone1.setVisibility(View.INVISIBLE);
                        chart1.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        btnZone1.setVisibility(View.INVISIBLE);
                        txtZone1.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        btnZone1.setVisibility(View.INVISIBLE);
                        break;
                }
                break;
            case 1:  // pour la zone 2
                switch(choice){
                    case 0:
                        btnZone2.setVisibility(View.INVISIBLE);
                        chart2.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        btnZone2.setVisibility(View.INVISIBLE);
                        txtZone2.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        btnZone2.setVisibility(View.INVISIBLE);
                        break;
                }
                break;
            case 2:  // pour la zone 3
                switch(choice){
                    case 0:
                        btnZone3.setVisibility(View.INVISIBLE);
                        chart3.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        btnZone3.setVisibility(View.INVISIBLE);
                        txtZone3.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        btnZone3.setVisibility(View.INVISIBLE);
                        break;
                }
                break;
            case 3:  // pour la zone 4
                switch(choice){
                    case 0:
                        btnZone4.setVisibility(View.INVISIBLE);
                        chart4.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        btnZone4.setVisibility(View.INVISIBLE);
                        txtZone4.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        btnZone4.setVisibility(View.INVISIBLE);
                        break;
                }
                break;
        }

    }

    private void createVars(){

        for(int i=0;i<var1.length;i++){
            var1[i]=i;
        }
        Log.d("Creating Var1 :"," SUCCESS");
        for(int j=0;j<var2.length;j++){
            var2[j]=j*2;
        }
        Log.d("Creating Var2 :"," SUCCESS");

        for(int k=0;k<var3.length;k++){
            Double d = k*0.5;
            var3[k]=Float.parseFloat(String.valueOf(d));
        }
        Log.d("Creating Var3 :"," SUCCESS");

        for(int l=0;l<var4.length;l++){
            var4[l]=l*3;
        }
        Log.d("Creating Var4 :"," SUCCESS");

    }

    private void createEntries(){

        for (int i=0;i<var1.length;i++){
            // turn your data into Entry objects
            entries1.add(new Entry(var1[i],i));
        }
        Log.d("Creating Entry1 :"," SUCCESS");

        for (int j=0;j<var2.length;j++){
            // turn your data into Entry objects
            entries2.add(new Entry(var2[j],j));
        }
        Log.d("Creating Entry2 :"," SUCCESS");
        for(float k=0;k<var3.length;k++){
            // turn your data into Entry objects
            entries3.add(new Entry(k+0.5f,k));
        }

        Log.d("Creating Entry3 :"," SUCCESS"+entries3);
        for(int l=0;l<var4.length;l++){
            // turn your data into Entry objects
            entries4.add(new Entry(var4[l],l));
        }
        Log.d("Creating Entry4 :"," SUCCESS");

    }

    private void createDataSets(){

        dataSet1 = new LineDataSet(entries1, "Label"); // add entries to dataset
        dataSet1.setColors(Color.RED);
        dataSet1.setValueTextColor(Color.RED); // styling, ...
        Log.d("Creating Dataset1 :"," SUCCESS" +dataSet1);

        dataSet2 = new LineDataSet(entries2, "Label"); // add entries to dataset
        dataSet2.setColors(R.color.Green);
        dataSet2.setValueTextColor(Color.parseColor("#FF0000")); // styling, ...
        Log.d("Creating Dataset2 :"," SUCCESS");


        dataSet3 = new LineDataSet(entries3, "Label"); // add entries to dataset
        Log.d("Creating Dataset3 :"," SUCCESS" +dataSet3);
        dataSet3.setColors(R.color.Blue);
        dataSet3.setValueTextColor(R.color.Blue); // styling, ...
        Log.d("Creating Dataset3 :"," SUCCESS");


        dataSet4 = new LineDataSet(entries4, "Label"); // add entries to dataset
        dataSet4.setColors(R.color.Black);
        dataSet4.setValueTextColor(R.color.Black); // styling, ...
        Log.d("Creating Dataset4 :"," SUCCESS");

    }

    private void createGraphs(){

        LineData lineData1 = new LineData(dataSet1);
        chart1.setData(lineData1);
        Description desc1 = new Description();
        desc1.setText("1");
        chart1.setDescription(desc1);
        desc1.setTextColor(R.color.Blue);
        XAxis chart1X = chart1.getXAxis();
        YAxis chart1Y = chart1.getAxisLeft();
        YAxis chart1Yi = chart1.getAxisRight();
        chart1Yi.setEnabled(false);
        chart1X.setEnabled(true);
        chart1X.setPosition(XAxis.XAxisPosition.BOTTOM);
        chart1X.setDrawGridLines(false);
        chart1.setBackgroundColor(Color.parseColor("#00FF00"));
        chart1.invalidate(); // refresh
        Log.d("Creating Graph1 :"," SUCCESS");


        LineData lineData2 = new LineData(dataSet2);
        chart2.setData(lineData2);
        chart2.invalidate(); // refresh
        Log.d("Creating Graph2 :"," SUCCESS");


        lineData3 = new LineData(dataSet3);
        Log.d("Creating Graph3 :"," SUCCESS"+lineData3);
        lineData3.setValueFormatter(DecimalValue);
        Log.d("Creating Graph3 :"," SUCCESS"+lineData3);
        chart3.setData(lineData3);
        chart3.invalidate(); // refresh
        Log.d("Creating Graph3 :"," SUCCESS");


        LineData lineData4 = new LineData(dataSet4);
        chart4.setData(lineData4);
        chart4.invalidate(); // refresh
        Log.d("Creating Graph4 :"," SUCCESS");

    }
}
