package com.example.kraftwerk;

import static java.util.Calendar.DATE;
import static java.util.Calendar.DAY_OF_WEEK;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.WEEK_OF_YEAR;
import static java.util.Calendar.YEAR;

import java.util.Calendar;

public class tassimoDatum {


    protected static String mhdDat(int wahl, String format) {
        final CharSequence[] khc = {"JA", "FE", "MR", "AL", "MA", "JN",
                "JL", "AU", "SE", "OC", "NO", "DE"};
        String help ="";
        Calendar mhd = lot();
        mhd.add(DATE, wahl);
        int monat = mhd.get(MONTH);
        //String smonat

        lot();
        int pmonat = lot().get(MONTH);
        lot();
        int ptag   = lot().get(DATE);
        lot();
        int pjahr =  lot().get(YEAR);
        switch (format) {
            case "NORMAL":
                help =stag(mhd.get(DATE)) + " " + smonat(monat + 1) + " " + mhd.get(YEAR);
                break;
            case "KHC":
                help = mhd.get(YEAR) + " " + khc[monat] + " " + stag(mhd.get(DATE));
                break;
            /*case "PRO":

                help = "Von " + stag(ptag) + " " + smonat(pmonat + 1) + " " + pjahr +
                        " \nBis " +stag(mhd.get(DATE)) + " " + smonat(monat + 1) + " " + mhd.get(YEAR);
                break;*/
            case "PRO":

                help = "" + stag(ptag) + " " + smonat(pmonat + 1) + " " + pjahr +
                        " B " +stag(mhd.get(DATE)) + " " + smonat(monat + 1) + " " + mhd.get(YEAR);
                break;
            case "PRO2":

                help =  "B " +stag(mhd.get(DATE)) + " " + smonat(monat + 1) + " " + mhd.get(YEAR)
                        + "/" + stag(ptag) + " " + smonat(pmonat + 1) + " " + pjahr ;

                break;
        }

        return help;

    }

    protected static String lotDat() {

        return lotjahr()+""+swoche(lot().get(WEEK_OF_YEAR))+""+tagderwoche()+""+schicht();
    }
    private static char lotjahr(){
        lot();
        String jahr = ""+ lot().get(YEAR);
        return jahr.charAt(3);
    }
    private static String schicht() {
        int help = lot().get(HOUR_OF_DAY);

        if (help >= 0 && help < 8) {
            return "1";
        } else if (help >= 8 && help < 16) {
            return "2";
        } else {
            return "3";
        }
    }

    private static int tagderwoche() {
        int t = lot().get(DAY_OF_WEEK);
        if (t > 1) {
            t = t - 1;}
        else
            t = 7;

        return t;
    }
    private static Calendar lot () {
        Calendar lot = Calendar.getInstance();
        lot.add(Calendar.HOUR, -6);
        return lot;
    }
    private static String smonat(int monat) {
        if (monat < 10)
            return "0" + monat;
        else
            return "" + monat;
    }
    private static String swoche(int woche) {
        if (woche< 10)
            return "0" + woche;
        else
            return "" + woche;
    }
    private static String stag(int tag) {
        if (tag<10)
            return "0" + tag;
        else
            return "" + tag;
    }
}
