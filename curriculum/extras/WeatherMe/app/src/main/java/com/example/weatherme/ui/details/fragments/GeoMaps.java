package com.example.weatherme.ui.details.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.weatherme.R;
import com.example.weatherme.ui.details.GeoActivity;

public class GeoMaps extends Fragment {

    public GeoMaps() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_geo_maps, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button button = getActivity().findViewById(R.id.geo_button);
        button.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), GeoActivity.class));
        });
    }
}
