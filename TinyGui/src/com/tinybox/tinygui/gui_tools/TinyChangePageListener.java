package com.tinybox.tinygui.gui_tools;

import com.actionbarsherlock.app.ActionBar;

import android.support.v4.view.ViewPager;

public class TinyChangePageListener implements ViewPager.OnPageChangeListener{

	//private final String _CLASS_NAME = TinyChangePageListener.class.getName();
	
	private final ActionBar homeActionBar;	
	
	public TinyChangePageListener(ActionBar relatedActionBar){
		//Log.v(this._CLASS_NAME, "--> Bulding HomeChangePageListener");
		this.homeActionBar = relatedActionBar;
		//Log.v(this._CLASS_NAME, "<-- HomeChangePageListener build");
		
	}
	
	@Override
	public void onPageScrollStateChanged(int state) {
		//Log.v(this._CLASS_NAME, "--> Entering in onPageScrollStateChanged method");
		//Log.v(this._CLASS_NAME, "Nothing to do!");
		//Log.v(this._CLASS_NAME, "<-- Exiting from onPageScrollStateChanged method");
		
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
		//Log.v(this._CLASS_NAME, "--> Entering in onPageScrolled method");
		//Log.v(this._CLASS_NAME, "Nothing to do!");
		//Log.v(this._CLASS_NAME, "<-- Exiting from onPageScrolled method");
	}

	@Override
	public void onPageSelected(int position) {
		//Log.v(this._CLASS_NAME, "--> Entering in onPageSelected method");
		//Log.v(this._CLASS_NAME, "Selecting tab " + position + " into the ActionBar");
		this.homeActionBar.setSelectedNavigationItem(position);
		//Log.v(this._CLASS_NAME, "Done!");
		//Log.v(this._CLASS_NAME, "<-- Exiting from onPageSelected method");
		
	}

}
