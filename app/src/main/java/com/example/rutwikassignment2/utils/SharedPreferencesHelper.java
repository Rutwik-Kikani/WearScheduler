package com.example.rutwikassignment2.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.rutwikassignment2.model.Appointment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class SharedPreferencesHelper {
    private static final String PREFS_NAME = "appointments_prefs";
    private static final String APPOINTMENTS_KEY = "appointments";

    private SharedPreferences sharedPreferences;

    public SharedPreferencesHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void saveAppointment(Appointment appointment) {
        List<Appointment> appointments = getAppointments();
        appointments.add(appointment);
        JSONArray jsonArray = new JSONArray();
        for (Appointment appt : appointments) {
            jsonArray.put(appointmentToJson(appt));
        }
        sharedPreferences.edit().putString(APPOINTMENTS_KEY, jsonArray.toString()).apply();
    }

    public List<Appointment> getAppointments() {
        String json = sharedPreferences.getString(APPOINTMENTS_KEY, null);
        List<Appointment> appointments = new ArrayList<>();
        if (json != null) {
            try {
                JSONArray jsonArray = new JSONArray(json);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    appointments.add(jsonToAppointment(jsonObject));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return appointments;
    }

    private JSONObject appointmentToJson(Appointment appointment) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", appointment.getId());
            jsonObject.put("name", appointment.getName());
            jsonObject.put("date", appointment.getDate());
            jsonObject.put("time", appointment.getTime());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    private Appointment jsonToAppointment(JSONObject jsonObject) {
        try {
            String id = jsonObject.getString("id");
            String name = jsonObject.getString("name");
            String date = jsonObject.getString("date");
            String time = jsonObject.getString("time");
            return new Appointment(id, name, date, time);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
