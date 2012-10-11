package com.tinybox.tinygui.gui_tools;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuItem.OnActionExpandListener;

public class ExpandableViewListener implements OnActionExpandListener {
	
	//private final String _CLASS_NAME = this.getClass().getName();
	
	Context ctx=null;
	EditText relatedView=null;
	
	public ExpandableViewListener(Context thisActivity, EditText collapsableView){
		this.ctx = thisActivity;
		this.relatedView = collapsableView;
	}
	
	@Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
		//Log.v(this._CLASS_NAME, "Starting method onMenuItemActionCollapse on item: " + item.getItemId() + " - " + item.getTitle());
		//Log.v(this._CLASS_NAME, "First of all, bye bye stupid keyboard!");
		InputMethodManager imm = (InputMethodManager)ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(this.relatedView.getWindowToken(), 0);
		//Log.v(this._CLASS_NAME, "The stupid keyboard should be collapsed, right?");
    	return true;  // Return true to collapse action view
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
    	//Log.v(this._CLASS_NAME, "Starting method onMenuItemActionExpand on item: " + item.getItemId() + " - " + item.getTitle());
    	return true;  // Return true to expand action view
    }
}