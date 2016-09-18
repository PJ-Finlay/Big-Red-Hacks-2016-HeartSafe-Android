package com.example.mlh_admin.bigredhacks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by MLH-Admin on 9/17/2016.
 */
public class EditEmergencyContact  extends Activity {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact);
        context = this;

        Intent myIntent = getIntent(); // gets the previously created intent
        int index = myIntent.getIntExtra("index",0);

        StorageManager sm = new StorageManager(this);
        ArrayList<EmergencyContact> contacts = sm.getEmergencyContacts();

        final EmergencyContact contact = contacts.get(index);

        EditText name = (EditText)findViewById(R.id.name_edit_text);
        EditText number = (EditText)findViewById(R.id.number_edit_text);
        EditText message = (EditText)findViewById(R.id.message_edit_text);

        name.setText(contact.name);
        number.setText(contact.phoneNumber);
        message.setText(contact.message);

        final int fIndex = index;
        Button saveButton = (Button) findViewById(R.id.save_button);
        saveButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StorageManager sm = new StorageManager(context);
                ArrayList<EmergencyContact> contacts = sm.getEmergencyContacts();

                EditText name = (EditText)findViewById(R.id.name_edit_text);
                EditText number = (EditText)findViewById(R.id.number_edit_text);
                EditText message = (EditText)findViewById(R.id.message_edit_text);

                EmergencyContact toAdd = new EmergencyContact(name.getText().toString(),number.getText().toString(),message.getText().toString());
                contacts.set(fIndex,toAdd);
                sm.setEmergencyContacts(contacts);

                Intent intent= new Intent(EditEmergencyContact.this,MainActivity.class);
                startActivity(intent);
            }
        });

        Button deleteButton = new Button(this);
        deleteButton.setText("Delete Emergency Contact");
        deleteButton.setBackgroundColor(Color.parseColor("#D32F2F"));

        deleteButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StorageManager sm = new StorageManager(context);
                ArrayList<EmergencyContact> contacts = sm.getEmergencyContacts();

                contacts.remove(fIndex);

                sm.setEmergencyContacts(contacts);

                Intent intent= new Intent(EditEmergencyContact.this,MainActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout layout = (LinearLayout)findViewById(R.id.emergency_contacts_layout);
        layout.addView(deleteButton);

    }
}
