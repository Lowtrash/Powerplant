package com.example.kraftwerk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String format = "NORMAL";
    int tage = 330;
    int linie = 22;
    int index = 0;
    int findex = 0;
    int anzdisc = 8;
    int tindex =0;
    private KraftWerkDbHelper dbdates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        zeigemhd();
        zeigelot();
        dialog();
        format();
        zeigesdisc();
        dbdates = new KraftWerkDbHelper(MainActivity.this);
        dbdates.erstelleFirstTable();
    }


    public void zeigemhd() {
        String message = tassimoDatum.mhdDat(tage,format);
        TextView textView = findViewById(R.id.textView3);
        textView.setText(message);
        textView.setMovementMethod(new ScrollingMovementMethod());
        textView.setHorizontallyScrolling(true);
    }
    public void zeigesdisc(){
        String message = anzdisc+" "+getString(R.string.t_disc_fs);
        TextView textView = findViewById(R.id.textView8);
        textView.setText(message);
    }
    public void zeigelot() {
        //String message = "LXB"+linie+tassimoDatum.lotDat();
        String message = "LXB"+linie+tassimoDatum.lotDat()+" "+tassimoDialog.disksigndialog(tindex);
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
    public void adddisk(View view){
        if (tindex < 3)
            tindex++;
        else
            tindex = 0;
        anzdisc = tassimoDialog.discdialog(tindex);
        zeigesdisc();
        zeigelot();
    }
    public void subdisk(View view) {
        if (tindex > 0)
            tindex--;
        else
            tindex = 3;
        anzdisc = tassimoDialog.discdialog(tindex);
        zeigesdisc();
        zeigelot();
    }

    @Override
    protected void onDestroy() {
        dbdates.updateData(linie,tage,format,anzdisc);
        super.onDestroy();
    }
}