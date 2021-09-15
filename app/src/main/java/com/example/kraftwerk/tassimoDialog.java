package com.example.kraftwerk;

public class tassimoDialog {

    static int mhdialog(int index) {

        final CharSequence[] items = {"330", "360", "420", "540"};

        String help = items[index].toString();
        return Integer.parseInt(help);
    }
    static String formatdialog(int findex){
        final CharSequence[] fitems = {"NORMAL", "KHC", "PRO", "PRO2"};
        return fitems[findex].toString();
        // return help;
    }
}
