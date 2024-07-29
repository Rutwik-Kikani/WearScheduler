package com.example.rutwikassignment2.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rutwikassignment2.databinding.ActivityAddAppointmentBinding;
import com.example.rutwikassignment2.model.Appointment;
import com.example.rutwikassignment2.utils.SharedPreferencesHelper;

import java.util.Calendar;
import java.util.UUID;


public class AddAppointmentActivity extends AppCompatActivity {

    private ActivityAddAppointmentBinding binding;
    private SharedPreferencesHelper sharedPreferencesHelper;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddAppointmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize SharedPreferencesHelper
        sharedPreferencesHelper = new SharedPreferencesHelper(this);

        // Pick Date button click listener
        binding.pickDateButton.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    AddAppointmentActivity.this,
                    (view, year1, month1, dayOfMonth) -> binding.selectedDate.setText(dayOfMonth + "/" + (month1 + 1) + "/" + year1),
                    year, month, day);
            datePickerDialog.show();
        });

        // Pick Time button click listener
        binding.pickTimeButton.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    AddAppointmentActivity.this,
                    (view, hourOfDay, minute1) -> binding.selectedTime.setText(hourOfDay + ":" + minute1),
                    hour, minute, true);
            timePickerDialog.show();
        });

        // Schedule button click listener
        binding.scheduleButton.setOnClickListener(v -> {
            String name = binding.appointmentName.getText().toString();
            String date = binding.selectedDate.getText().toString();
            String time = binding.selectedTime.getText().toString();

            if (name.isEmpty() || date.isEmpty() || time.isEmpty()) {
                Toast.makeText(AddAppointmentActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            String id = UUID.randomUUID().toString();
            Appointment appointment = new Appointment(id, name, date, time);

            sharedPreferencesHelper.saveAppointment(appointment);

            Toast.makeText(AddAppointmentActivity.this, "Appointment Scheduled", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}