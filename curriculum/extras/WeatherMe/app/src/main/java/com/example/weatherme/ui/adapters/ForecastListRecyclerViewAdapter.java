package com.example.weatherme.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherme.R;
import com.example.weatherme.data.remote.Day;

import java.util.List;

public class ForecastListRecyclerViewAdapter extends RecyclerView.Adapter<ForecastListRecyclerViewAdapter.ForeCastViewHolder> {
    private final List<Day> days;

    public ForecastListRecyclerViewAdapter(List<Day> days) {
        this.days = days;
    }

    @NonNull
    @Override
    public ForeCastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_view_forecast, parent, false);
        return new ForeCastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForeCastViewHolder holder, int position) {
        holder.date.setText(days.get(position).getDate());
        holder.sunrise.setText(days.get(position).getSunrise_time());
    }

    @Override
    public int getItemCount() {
        return days.size();
    }

    public static class ForeCastViewHolder extends RecyclerView.ViewHolder {

        TextView date;
        TextView sunrise;

        public ForeCastViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.date);
            sunrise = itemView.findViewById(R.id.sunrise);
        }
    }
}
