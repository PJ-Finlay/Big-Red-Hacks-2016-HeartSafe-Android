package com.example.mlh_admin.bigredhacks;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by MLH-Admin on 9/16/2016.
 */
public class StorageManager {
    Context context;
    public StorageManager(Context context){
        this.context = context;
    }


    /*
    Format:
    Name%PhoneNumber%Message:...
     */
    public ArrayList<EmergencyContact> getEmergencyContacts(){
        SharedPreferences settings = context.getSharedPreferences("prefs", 0);
        String json = settings.getString("emergencyContacts", "");

        ArrayList<EmergencyContact> toReturn = new ArrayList<>();

        String[] contacts = json.split(":");
        for(String contact : contacts){
            String[] attributes = contact.split("%");
            if(attributes.length > 2) {
                EmergencyContact toAdd = new EmergencyContact(attributes[0], attributes[1], attributes[2]);
                toReturn.add(toAdd);
            }
        }

        return toReturn;
    }

    public void setEmergencyContacts(ArrayList<EmergencyContact> contacts){
        SharedPreferences settings = context.getSharedPreferences("prefs", 0);
        SharedPreferences.Editor editor = settings.edit();

        String json = "";

        for(int i = 0; i < contacts.size(); i++){
            EmergencyContact contact = contacts.get(i);
            String toAdd = contact.name + "%" + contact.phoneNumber + "%" + contact.message;
            if(i != 0) json+= ":";
            json += toAdd;
        }


        editor.putString("emergencyContacts", json);

        // Commit the edits!
        editor.commit();
    }

}
