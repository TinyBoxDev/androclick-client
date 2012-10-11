package com.tinybox.androclickclient.results;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.tinybox.androclickclient.R;

public class ResultFragment  extends SherlockFragment {
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
            
		View view = inflater.inflate(R.layout.results_layout, container, false);
		return view;
    }
}