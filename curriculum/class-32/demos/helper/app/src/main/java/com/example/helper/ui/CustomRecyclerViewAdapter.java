package com.example.helper.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helper.R;
import com.example.helper.data.WeatherData;

import java.util.List;

public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<CustomRecyclerViewAdapter.CustomViewHolder> {

    List<WeatherData> dataList;
    CustomClickListener listener;

    public CustomRecyclerViewAdapter(List<WeatherData> dataList,
                                     CustomClickListener listener) {
        this.dataList = dataList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItemView = layoutInflater.inflate(R.layout.weather_item_layout, parent, false);
        return new CustomViewHolder(listItemView, listener);
    }

    // will be called multiple times to inject the data into the view holder object
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.city.setText(dataList.get(position).getCity());
        holder.temperature.setText(dataList.get(position).getTemperature().toString());
        holder.windSpeed.setText(dataList.get(position).getWindSpeed().toString());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView city;
        TextView temperature;
        TextView windSpeed;

        CustomClickListener listener;

        public CustomViewHolder(@NonNull View itemView, CustomClickListener listener) {
            super(itemView);

            this.listener = listener;

            city = itemView.findViewById(R.id.city);
            temperature = itemView.findViewById(R.id.temperature);
            windSpeed = itemView.findViewById(R.id.wind_speed);

            itemView.setOnClickListener(view -> listener.onWeatherItemClicked(getAdapterPosition()));
        }
    }

    public interface CustomClickListener {
        void onWeatherItemClicked(int position);
    }
}
