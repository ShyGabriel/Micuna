package com.example.micuna.providers;

import com.example.micuna.modelo.Conductor;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class DriverProvider {
    DatabaseReference mDatabase;

    public DriverProvider(){
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers");

    }

    public Task<Void> create(Conductor driver){

        Map<String, Object> map = new HashMap<>();
        map.put("name", driver.getName());
        map.put("email", driver.getEmail());
        map.put("vehicleBrand", driver.getVehicleBrand());
        map.put("vehiclePlate", driver.getVehiclePlate());
        return mDatabase.child(driver.getId()).updateChildren(map);
        /*return  mDatabase.child(driver.getId()).setValue(driver);*/
    }
}
