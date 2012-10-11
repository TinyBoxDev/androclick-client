package com.client.infoclick;

/*
import java.util.Observable;
import java.util.Observer;
import com.client.infoclick.factory.Bus;
import com.client.infoclick.factory.Stop;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
*/

public class RevampedInfoclickAndroidClientActivity {
	
	//private final String _CLASS_NAME = AndroclickDataController.class.getName();
	
	/*
	Spinner locationSpinner;
	Intent serviceToCall;
	InputMethodManager imm;
	
	AndroclickSpareMessageModel currentSpareModel;
	AndroclickStopModel currentStopModel;
	AndroclickBusModel currentBusModel;
	
	AndroclickDataController currentController;
	*/
	/** Called when the activity is first created. */
    //@Override
    //public void onCreate(Bundle savedInstanceState) {
      //  super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        /*
        currentController = new AndroclickDataController();
        
        currentSpareModel = AndroclickSpareMessageModel.getInstance();
        currentStopModel = AndroclickStopModel.getInstance();
        currentBusModel = AndroclickBusModel.getInstance();
        
        currentSpareModel.addObserver(this);
        currentStopModel.addObserver(this);
        currentBusModel.addObserver(this);
        
        imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        locationSpinner = (Spinner)findViewById(R.id.locationSpinner);
        locationSpinner.setSelection(10);
        */
        
  //  }
    
    /*
    public void searchStopFromView(View button) {
    	this.imm.hideSoftInputFromWindow(button.getWindowToken(), 0);
    	Log.v(_CLASS_NAME, "View method: stop button pressed!");
    	String stopNumber = ((EditText)findViewById(R.id.editText1)).getText().toString();
    	if (stopNumber.length() == 4) {
    		serviceToCall = new Intent(RevampedInfoclickAndroidClientActivity.this, AndroclickDataController.class);
    		Bundle bundle = new Bundle();
    		bundle.putCharSequence("stopCall", stopNumber);
    		serviceToCall.putExtras(bundle);
    		Log.v(_CLASS_NAME, "Valid request: calling service!");
    		startService(serviceToCall);
    		Log.v(_CLASS_NAME, "Service called, we have done here!");
    	}
    }
    
    
    public void searchLocationFromView(View button) {
    	this.imm.hideSoftInputFromWindow(button.getWindowToken(), 0);
    	Log.v(_CLASS_NAME, "View method: street button pressed!");
    	String streetName = ((EditText)findViewById(R.id.editText2)).getText().toString();
    	String locationName = locationSpinner.getSelectedItem().toString().toUpperCase();
    	if (streetName.length() != 0) {
    		serviceToCall = new Intent(RevampedInfoclickAndroidClientActivity.this, AndroclickDataController.class);
    		Bundle bundle = new Bundle();
    		bundle.putCharSequence("locationCall", streetName);
    		bundle.putCharSequence("locationArrayValue", locationName);
    		serviceToCall.putExtras(bundle);
    		Log.v(_CLASS_NAME, "Valid request: calling service!");
    		startService(serviceToCall);
    		Log.v(_CLASS_NAME, "Service called, we have done here!");
    	}
    }
    
    public void searchLineFromView(View button) {
    	this.imm.hideSoftInputFromWindow(button.getWindowToken(), 0);
    	Log.v(_CLASS_NAME, "View method: street button pressed!");
    	String line = ((EditText)findViewById(R.id.editText3)).getText().toString();
    	if (line.length() != 0) {
    		serviceToCall = new Intent(RevampedInfoclickAndroidClientActivity.this, AndroclickDataController.class);
    		Bundle bundle = new Bundle();
    		bundle.putCharSequence("lineCall", line);
    		serviceToCall.putExtras(bundle);
    		Log.v(_CLASS_NAME, "Valid request: calling service!");
    		startService(serviceToCall);
    		Log.v(_CLASS_NAME, "Service called, we have done here!");
    	}
    }
    */
    
    /**
     * This is the observer interface. It manages update events.
     */
    /*
	@Override
	public void update(Observable observable, Object data) {
		TextView shownResults = (TextView)findViewById(R.id.textView1);
		
		if (observable instanceof AndroclickSpareMessageModel) {
			shownResults.setText("");
			shownResults.setText(currentSpareModel.getSpareMessage());
		} else if (observable instanceof AndroclickStopModel) {
			shownResults.setText("");
			for (Stop currentStop : currentStopModel.getStop()) {
				shownResults.append(currentStop.getNumber() + ", " + currentStop.getStreet().toLowerCase() + "\n");
			}
		} else if (observable instanceof AndroclickBusModel) {
			shownResults.setText("");
			for (Bus currentBus : currentBusModel.getBus()) {
				shownResults.append(currentBus.getLine() + ", " + currentBus.getTime() + "\n");
			}
		}
	}
	*/
	
    /*
	@Override
	public void onDestroy() {
		if(serviceToCall != null) {
			stopService(serviceToCall);
		}
		super.onDestroy();
		Log.v("Exit", "Exiting ############################################################################################");
	}
	*/
	

}