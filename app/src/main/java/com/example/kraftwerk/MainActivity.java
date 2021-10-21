package com.example.kraftwerk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String format = "NORMAL";
    int tage = 300;
    int linie = 22;
    int index = 0;
    int findex = 0;
    int anzdisc = 8;
    int tindex =0;
    private KraftWerkDbHelper dbdates;
    List neue = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbdates = new KraftWerkDbHelper(MainActivity.this);

        zeigemhd();
        zeigelot();
        dialog();
        format();
        zeigesdisc();
        dbdates.erstelleFirstTable();
        TextView longbutton = (TextView) findViewById(R.id.textView11);
        longbutton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                dbdates.updateData(linie,tage,format,anzdisc,0);
                showToast();
                //Toast.makeText(getApplicationContext(), "Werte gespeichert", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    void showToast() {

        Toast toast = new Toast(MainActivity.this);

        View view = LayoutInflater.from(MainActivity.this)
                .inflate(R.layout.customtoast_layout, null);

        TextView tvMessage = view.findViewById(R.id.tvMessage);
        tvMessage.setText("Werte gespeichert");
        toast.setGravity(Gravity.CENTER,0,375);
        toast.setView(view);
        toast.show();

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
        String message = "LXB"+linie+tassimoDatum.lotDat()+" "+tassimoDialog.disksigndialog(tindex);
        TextView textView = findViewById(R.id.textView4);
        textView.setText(message);
    }
    public void dialog() {
        int wahl = tassimoDialog.mhdialog(index);
        String message =  ""+wahl+" Tage";
        TextView textview = findViewById(R.id.textView2);
        textview.setText(message);

    }
    public void format() {
        String wahl = tassimoDialog.formatdialog(findex);
        String message =  ""+wahl;
        TextView textview = findViewById(R.id.textView);
        textview.setText(message);
    }

    public void linie() {
        String message =  ""+linie;
        TextView textview = findViewById(R.id.textView11);
        textview.setText(message);
    }
    public void changeMhd(View view) {

        if (index < 4)
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
    }

    public void addLine(View view){
        if (linie < 29)
            linie ++;

        else
            linie = 22;
        loadlastdata();
        linie();
    }
    public void subLine(View view) {
        if (linie > 22)
            linie--;

        else
            linie = 29;
        loadlastdata();
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
    public void loadlastdata(){
        neue = dbdates.setData(linie);
        tage= (int) neue.get(1);
        format= neue.get(2).toString();
        anzdisc = (int) neue.get(3);
        TextView textview = findViewById(R.id.textView2);
        textview.setText(""+tage+" Tage");
        index = checkIndex(tage);
        TextView textview1 = findViewById(R.id.textView);
        textview1.setText(""+format);
        findex = checkFindex(format);
        TextView textview2 = findViewById(R.id.textView8);
        textview2.setText(""+anzdisc+" "+getString(R.string.t_disc_fs));
        tindex = checkTindex(anzdisc);
        zeigemhd();
        zeigelot();
    }
    public int checkIndex(int t){
        int help = 0;
        while (t!=tassimoDialog.mhdialog(help)){
            help++;
        }

        return help;
    }
    public int checkFindex(String s){
        int help=0;
        while(!s.equals(tassimoDialog.formatdialog(help))){
            help++;
        }
        return help;
    }
    public int checkTindex(int t){
        int help = 0;
        while (t!=tassimoDialog.discdialog(help)){
            help++;
        }

        return help;
    }
    @Override
    protected void onDestroy() {
        dbdates.updateData(linie,tage,format,anzdisc,0);
        super.onDestroy();
    }
}