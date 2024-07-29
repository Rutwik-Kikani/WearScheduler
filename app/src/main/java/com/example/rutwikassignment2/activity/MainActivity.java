package com.example.rutwikassignment2.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.rutwikassignment2.adapter.AppointmentsAdapter;
import com.example.rutwikassignment2.databinding.ActivityMainBinding;
import com.example.rutwikassignment2.utils.SharedPreferencesHelper;
import com.example.rutwikassignment2.model.Appointment;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private AppointmentsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedPreferencesHelper = new SharedPreferencesHelper(this);

        // Get appointments from SharedPreferences
        List<Appointment> appointments = sharedPreferencesHelper.getAppointments();

        // Set up RecyclerView
        binding.appointmentsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AppointmentsAdapter(appointments);
        binding.appointmentsRecyclerView.setAdapter(adapter);

        binding.addAppointmentButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddAppointmentActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh the data and update the adapter
        List<Appointment> appointments = sharedPreferencesHelper.getAppointments();
        adapter = new AppointmentsAdapter(appointments);
        binding.appointmentsRecyclerView.setAdapter(adapter);
    }
}