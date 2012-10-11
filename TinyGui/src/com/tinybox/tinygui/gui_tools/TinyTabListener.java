package com.tinybox.tinygui.gui_tools;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;

public class TinyTabListener implements ActionBar.TabListener {

	//private final String _CLASS_NAME = TinyTabListener.class.getName();
	
	private final ViewPager homeViewPager;
	
	public TinyTabListener(ViewPager relatedViewPager){
		//Log.v(this._CLASS_NAME, "--> Bulding HomeChangePageListener");
		this.homeViewPager = relatedViewPager;
		//Log.v(this._CLASS_NAME, "<-- HomeChangePageListener build");
		
	}
	
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		//Log.v(this._CLASS_NAME, "--> Entering in onTabUnselected method");
        //Log.v(this._CLASS_NAME, "Selecting fragment at position " + tab.getPosition());
		this.homeViewPager.setCurrentItem(tab.getPosition());
        //Log.v(this._CLASS_NAME, "<-- Exiting from onTabUnselected method");
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		//Log.v(this._CLASS_NAME, "--> Entering in onTabUnselected method");
		//Log.v(this._CLASS_NAME, "Nothing to do!");
		//Log.v(this._CLASS_NAME, "<-- Exiting from onTabUnselected method");
		
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		//Log.v(this._CLASS_NAME, "--> Entering in onTabReselected method");
		//Log.v(this._CLASS_NAME, "Nothing to do!");
		//Log.v(this._CLASS_NAME, "<-- Exiting from onTabReselected method");
		
	}

}
