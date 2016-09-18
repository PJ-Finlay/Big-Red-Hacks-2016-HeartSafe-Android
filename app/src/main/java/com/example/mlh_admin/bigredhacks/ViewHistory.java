package com.example.mlh_admin.bigredhacks;

import android.app.Activity;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;


public class ViewHistory extends Activity {
    private boolean showingShrunkHeart = false;
    Button heartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_history);


    }

}
