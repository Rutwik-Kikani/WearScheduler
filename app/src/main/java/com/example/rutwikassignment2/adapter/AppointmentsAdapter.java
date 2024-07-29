package com.example.rutwikassignment2.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rutwikassignment2.databinding.ItemAppointmentLayoutBinding;
import com.example.rutwikassignment2.model.Appointment;

import java.util.List;

public class AppointmentsAdapter extends RecyclerView.Adapter<AppointmentsAdapter.AppointmentViewHolder> {

    private List<Appointment> appointmentList;

    public AppointmentsAdapter(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemAppointmentLayoutBinding binding = ItemAppointmentLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new AppointmentViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
        Appointment appointment = appointmentList.get(position);
        holder.binding.appointmentId.setText(appointment.getId());
        holder.binding.appointmentName.setText(appointment.getName());
        String dateTime = appointment.getDate() + " " + appointment.getTime();
        holder.binding.appointmentDateTime.setText(dateTime);
    }

    @Override
    public int getItemCount() {
        return appointmentList.size();
    }

    public static class AppointmentViewHolder extends RecyclerView.ViewHolder {
        private final ItemAppointmentLayoutBinding binding;

        public AppointmentViewHolder(ItemAppointmentLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}