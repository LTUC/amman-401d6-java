package com.example.weatherme.ui.adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.weatherme.ui.details.fragments.GeoMaps;
import com.example.weatherme.ui.details.fragments.GoogleMaps;

public class MyAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public MyAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                GoogleMaps googleMaps = new GoogleMaps(myContext);
                return googleMaps;
            case 1:
                GeoMaps geoMaps = new GeoMaps();
                return geoMaps;
            default:
                return null;
        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }

    // TODO: 6/16/22  Animation code
}
