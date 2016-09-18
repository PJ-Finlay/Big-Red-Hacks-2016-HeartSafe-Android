package com.example.mlh_admin.bigredhacks;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;


public class EditEmergencyContacts extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_emergency_contacts);

        StorageManager sm = new StorageManager(this);
        ArrayList<EmergencyContact> contacts = sm.getEmergencyContacts();

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.emergency_contacts_layout);
        for(int i = 0; i < contacts.size(); i++){
            EmergencyContact contact = contacts.get(i);
            Button contactButton = new Button(this);
            contactButton.setText(contact.name);
            linearLayout.addView(contactButton);
            final int index = i;
            contactButton.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(EditEmergencyContacts.this,EditEmergencyContact.class);
                    intent.putExtra("index",index);
                    startActivity(intent);
                }
            });
        }

        //Connect Add button
        Button addButton = (Button) findViewById(R.id.add_contact);
        addButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(EditEmergencyContacts.this,AddContact.class);
                startActivity(intent);
            }
        });

    }

}
