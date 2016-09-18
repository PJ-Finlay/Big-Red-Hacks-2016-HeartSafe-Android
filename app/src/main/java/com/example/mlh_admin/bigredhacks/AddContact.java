package com.example.mlh_admin.bigredhacks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by MLH-Admin on 9/17/2016.
 */
public class AddContact extends Activity {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact);
        context = this;

        Button saveButton = (Button) findViewById(R.id.save_button);
        saveButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StorageManager sm = new StorageManager(context);
                ArrayList<EmergencyContact> contacts = sm.getEmergencyContacts();

                EditText name = (EditText)findViewById(R.id.name_edit_text);
                EditText number = (EditText)findViewById(R.id.number_edit_text);
                EditText message = (EditText)findViewById(R.id.message_edit_text);

                contacts.add(new EmergencyContact(name.getText().toString(),number.getText().toString(),message.getText().toString()));
                sm.setEmergencyContacts(contacts);

                Intent intent= new Intent(AddContact.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
