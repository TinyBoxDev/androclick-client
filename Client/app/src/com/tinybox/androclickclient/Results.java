package com.tinybox.androclickclient;

import android.os.Bundle;
import android.widget.TextView;

import com.client.infoclick.AndroclickBusModel;
import com.client.infoclick.AndroclickSpareMessageModel;
import com.client.infoclick.AndroclickStopModel;
import com.client.infoclick.factory.Stop;
import com.tinybox.androclickclient.results.MapFragment;
import com.tinybox.androclickclient.results.ResultFragment;
import com.tinybox.tinygui.R;
import com.tinybox.tinygui.TinyActivity;

public class Results extends TinyActivity {
	
	//private final String _CLASS_NAME = Results.class.getName();
	
	private AndroclickSpareMessageModel spareMessageModel;
	private AndroclickBusModel busModel;
	private AndroclickStopModel stopModel;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        
        this.addTab(R.string.results_tab, ResultFragment.class);
        this.addTab(R.string.map_tab, MapFragment.class);
        
        this.spareMessageModel = AndroclickSpareMessageModel.getInstance();
		this.busModel = AndroclickBusModel.getInstance();
		this.stopModel = AndroclickStopModel.getInstance();
        
        //this.setResults();
        
    }
	
	private void setResults() {
		TextView shownResults = (TextView)findViewById(R.id.result_string);
		String myResults = "";
		for (Stop currentStop : stopModel.getStop()) {
			myResults += (currentStop.getNumber() + ", " + currentStop.getStreet() + "\n");
		}
		shownResults.setText(myResults);
	}
	
}
