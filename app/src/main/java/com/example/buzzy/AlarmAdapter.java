package com.example.buzzy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder> {

    private List<Alarm> alarmList;

    public AlarmAdapter(List<Alarm> alarmList) {
        this.alarmList = alarmList;
    }

    @NonNull
    @Override
    public AlarmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.alarm_item, parent, false);
        return new AlarmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmViewHolder holder, int position) {
        Alarm alarm = alarmList.get(position);
        holder.textViewAlarmTime.setText(String.format("%02d:%02d", alarm.getHour(), alarm.getMinute()));
    }

    @Override
    public int getItemCount() {
        return alarmList.size();
    }

    static class AlarmViewHolder extends RecyclerView.ViewHolder {

        TextView textViewAlarmTime;

        public AlarmViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewAlarmTime = itemView.findViewById(R.id.textViewAlarmTime);
        }
    }
}

