package com.example.mlh_admin.bigredhacks;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class MainActivity extends Activity {
    private boolean showingShrunkHeart = false;
    Button heartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set beating heart
        heartButton = (Button) findViewById(R.id.pulsing_heart);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask()
        {
            public void run()
            {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        int imageId = R.drawable.heart;
                        if(showingShrunkHeart) imageId = R.drawable.heart_shrunk;
                        heartButton.setBackground(ContextCompat.getDrawable(getApplicationContext(),imageId));
                        showingShrunkHeart = !showingShrunkHeart;
                    }
                });
            }
        }, 0, 429);

        //Connect buttons
        Button alertEmergencyContactButton = (Button) findViewById(R.id.alert_emergency_contact_button);
        Button editEmergencyContactsButton = (Button) findViewById(R.id.edit_emergency_contacts_button);

        alertEmergencyContactButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,AlertEmergencyContact.class);
                startActivity(intent);
            }
        });
        editEmergencyContactsButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,EditEmergencyContacts.class);
                startActivity(intent);
            }
        });



        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        HttpClient client = new DefaultHttpClient();

        HttpGet request = new HttpGet("http://www.example.com");

        HttpResponse response;
        try {
            response = client.execute(request);

            Log.d("Response of GET request", response.toString());
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



    }

}
