package com.example.kraftwerk;

public class tassimoDialog {

    static int mhdialog(int i) {

        final CharSequence[] items = {"300", "330", "360", "420", "540"};

        String help = items[i].toString();
        return Integer.parseInt(help);
    }
    static String formatdialog(int i){
        final CharSequence[] items = {"NORMAL", "KHC", "PRO", "PRO2"};
        return items[i].toString();
        // return help;
    }
    static int discdialog(int i){
        final int[] items = {8,4,6,7};
        return items[i];
    }
    static String disksigndialog(int i){
        final CharSequence[] zeichen = {"T", "Z", "W", "U"};
        return zeichen[i].toString();
    }
}
