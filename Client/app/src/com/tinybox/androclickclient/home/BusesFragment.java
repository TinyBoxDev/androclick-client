package com.tinybox.androclickclient.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.tinybox.androclickclient.R;

public class BusesFragment extends SherlockFragment {
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
            
		View view = inflater.inflate(R.layout.buses_layout, container, false);
		return view;
    }
}
