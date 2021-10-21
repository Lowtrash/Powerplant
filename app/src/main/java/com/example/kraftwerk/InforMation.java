package com.example.kraftwerk;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class InforMation {

    static void showtoast (Context context){
        Toast toast = new Toast(context);
        View view = LayoutInflater.from(context)
                .inflate(R.layout.customtoast_layout, null);
        TextView tvMessage = view.findViewById(R.id.toast_Message);
        tvMessage.setText(R.string.toast_message1);
        ImageView image = view.findViewById(R.id.toast_image);
        image.setImageResource(R.drawable.ic_toastok_layout_foreground);
        toast.setGravity(Gravity.CENTER,0,375);
        toast.setView(view);
        toast.show();
    }
}
