package com.example.kraftwerk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String format = "NORMAL";
    int tage = 330;
    int linie = 22;
    int index = 0;
    int findex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        zeigemhd();
        zeigelot();
        dialog();
        format();
    }


    public void zeigemhd() {
        String message = tassimoDatum.mhdDat(tage,format);
        TextView textView = findViewById(R.id.textView3);
        textView.setText(message);
    }
    public void zeigelot() {
        String message = "LXB"+linie+tassimoDatum.lotDat();
        TextView textView = findViewById(R.id.textView4);
        textView.setText(message);
    }
    public void dialog() {

        int wahl = tassimoDialog.mhdialog(index);

        TextView textview = findViewById(R.id.textView2);
        textview.setText(""+wahl+" Tage");

    }
    public void format() {

        String wahl = tassimoDialog.formatdialog(findex);

        TextView textview = findViewById(R.id.textView);
        textview.setText(""+wahl);
    }

    public void linie() {
        TextView textview = findViewById(R.id.textView11);
        textview.setText(""+linie);
    }
    public void changeMhd(View view) {

        if (index < 3)
            index++;
        else
            index = 0;
        tage = tassimoDialog.mhdialog(index);
        zeigemhd();
        dialog();
    }
    public void changeFormat(View view) {

        if (findex < 3)
            findex++;
        else
            findex = 0;
        format = tassimoDialog.formatdialog(findex);
        format();
        zeigemhd();
        //format();
    }

    public void addLine(View view){
        if (linie < 29)
            linie ++;

        else
            linie = 22;
        zeigelot();
        linie();
    }
    public void subLine(View view) {
        if (linie > 22)
            linie--;

        else
            linie = 29;
        zeigelot();
        linie();
    }
}