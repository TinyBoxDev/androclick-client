package com.tinybox.tinygui.gui_tools;

import com.tinybox.tinygui.TinyActivity;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;

public class SearchKeyListener implements OnKeyListener{

	private TinyActivity referredActivity;
	
	public SearchKeyListener(TinyActivity activity){
		this.referredActivity = activity;
	}
	
	@Override
	public boolean onKey(View view, int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP){
			((InputMethodManager) this.referredActivity.getSystemService(TinyActivity.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(view.getWindowToken(), 0);
			//this.referredActivity.launchSearch(event);
			this.referredActivity.onSearchPressed();
			return true;
		}
		
		return false;
			
	}

}
