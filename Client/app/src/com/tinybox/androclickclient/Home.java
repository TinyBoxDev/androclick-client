package com.tinybox.androclickclient;

import java.util.Observable;
import java.util.Observer;
import com.client.infoclick.AndroclickBusModel;
import com.client.infoclick.AndroclickDataController;
import com.client.infoclick.AndroclickSpareMessageModel;
import com.client.infoclick.AndroclickStopModel;
import com.client.infoclick.factory.Stop;
import com.client.infoclick.factory.Bus;
//import com.google.ads.AdSize;
//import com.google.ads.AdView;
//import com.google.ads.AdRequest;
import com.tinybox.androclickclient.home.BusesFragment;
import com.tinybox.androclickclient.home.FindFragment;
import com.tinybox.androclickclient.home.StarredFragment;
import com.tinybox.androclickclient.resources.StarredFile;
import com.tinybox.androclickclient.resources.StarredAdapter;
import com.tinybox.tinygui.R;
import com.tinybox.tinygui.TinyActivity;
import com.tinybox.tinygui.dialog.TinyDialog;
//import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ListView;

public class Home extends TinyActivity implements Observer {
	
	private final String _CLASS_NAME = this.getClass().getName();
	
	AndroclickSpareMessageModel currentSpareModel;
	AndroclickStopModel currentStopModel;
	AndroclickBusModel currentBusModel;
		
//	Intent serviceToCall;
	
//	private AdView adView = null;
//	private final String ADMOB_ID = "a14f1fcb9c5d0df";

	//AndroclickDataController currentController;
	public AsyncTask<Object, Void, String> runningThread = null;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        this.addTab(R.string.starred_tab, StarredFragment.class);
        this.addTab(R.string.buses_tab, BusesFragment.class);
        this.addTab(R.string.find_tab, FindFragment.class);
        
        //currentController = new AndroclickDataController();
        
        currentSpareModel = AndroclickSpareMessageModel.getInstance();
        currentStopModel = AndroclickStopModel.getInstance();
        currentBusModel = AndroclickBusModel.getInstance();
        
        currentSpareModel.addObserver(this);
        currentStopModel.addObserver(this);
        currentBusModel.addObserver(this);
        
//        adView = new AdView(this, AdSize.BANNER, this.ADMOB_ID);
//        LinearLayout layout = (LinearLayout)findViewById(R.id.home_linear_layout);
//        layout.addView(adView);
//        adView.loadAd(new AdRequest());
        
    }
    
//    @Override
//    public void onDestroy() {
//      if (this.adView != null) {
//        this.adView.destroy();
//      }
//      super.onDestroy();
//    }
    
    public void doSearch(Object... searchParameters) {
    	this.showDialog(TinyDialog.TYPE_PROGRESS_DIALOG, "Invento alcune cose...");
    	this.runningThread = new AndroclickDataController().execute(searchParameters[0], searchParameters[1], searchParameters[2]);
    }
    
    public void onSearchRequestFromButton(View button) {
    	((InputMethodManager) getSystemService(Home.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(button.getWindowToken(), 0);
    	switch(button.getId() - 1) {
    	case (R.id.stop_search_insert_label) :
    		String stopNumber = ((EditText)findViewById(R.id.stop_search_insert_label)).getText().toString();
    		if (stopNumber.length() == 4) {
    			doSearch(AndroclickDataController.STOP_REQUEST_ID, stopNumber, null);
    		} else {
    			((EditText)findViewById(R.id.stop_search_insert_label)).setError("Qui vanno 4 cifre!");
    		}
    		break;
    	case (R.id.line_search_insert_label) :
    		String line = ((EditText)findViewById(R.id.line_search_insert_label)).getText().toString();
    		if (line.length() != 0) {
    			doSearch(AndroclickDataController.LINE_REQUEST_ID, line, null);
    		} else {
    			((EditText)findViewById(R.id.line_search_insert_label)).setError("Inserisci una linea!");
    		}
    		break;
    	case (R.id.location_search_insert_label) :
    		String locationName = ((Spinner)findViewById(R.id.location_spinner)).getSelectedItem().toString().toUpperCase();
    		String streetName = ((EditText)findViewById(R.id.location_search_insert_label)).getText().toString();
    		if (streetName.length() != 0) {
    			doSearch(AndroclickDataController.LOCATION_REQUEST_ID, locationName, streetName);
    		} else {
        		((EditText)findViewById(R.id.location_search_insert_label)).setError("Inserisci una strada!");
        	}
    		break;
    	}
    }
    
	@Override
    public void onSearchPressed(){
		String stopNumber = ((EditText)this.findViewById(R.id.title_search)).getText().toString();
    	if (stopNumber.length() == 4)
    		doSearch(AndroclickDataController.STOP_REQUEST_ID, stopNumber);
    	else
    		((EditText)this.findViewById(R.id.title_search)).setError("Qui vanno 4 cifre!");
    }
    
	@Override
	public void update(Observable observable, Object data) {
		//Intent resultIntent = new Intent(this, Results.class);
		this.removeDialog();
		if (observable instanceof AndroclickSpareMessageModel) 
			this.showDialog(TinyDialog.TYPE_ALERT_DIALOG, currentSpareModel.getSpareMessage());
		else if (observable instanceof AndroclickStopModel) {
			//this.removeDialog();
			String results = new String();
			TextView res = (TextView)findViewById(R.id.search_long_hint);
			res.setText("");
			for (Stop currentStop : currentStopModel.getStop()) 
				results += (currentStop.getNumber() + ", " + currentStop.getStreet() + "\n");
			res.setText(results);
			//startActivity(resultIntent);
		} else if (observable instanceof AndroclickBusModel) {
			//this.removeDialog();
			String results = new String();
			TextView res = (TextView)findViewById(R.id.stop_search_long_hint);
			res.setText("");
			for (Bus currentBus : currentBusModel.getBus())
				results += (currentBus.getLine() + " previsto alle " + currentBus.getTime() + "\n");
			res.setText(results);
			//startActivity(resultIntent);
		}
	}
	
	@Override
	public void onProgressDialogCancel(){
		if(this.runningThread!=null)
			this.runningThread.cancel(true);
		Log.v(this._CLASS_NAME, "Thread cancelled!");
	}
	
	public void saveStarred(View view){
		StarredFile sf = StarredFile.getFile(this);
		String label = ((EditText)view.getRootView().findViewById(R.id.labelView)).getText().toString();
		String infos = ((EditText)view.getRootView().findViewById(R.id.infoView)).getText().toString();
		String number = ((EditText)view.getRootView().findViewById(R.id.numberView)).getText().toString();
		
		try {
			sf.addItem(label, infos, number);
			StarredAdapter sa = (StarredAdapter)((ListView)this.findViewById(android.R.id.list)).getAdapter();
			sa.notifyDataSetChanged();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.removeDialog();
	}

}