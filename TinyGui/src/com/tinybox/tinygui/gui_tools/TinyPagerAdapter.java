package com.tinybox.tinygui.gui_tools;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.actionbarsherlock.app.SherlockFragmentActivity;

public class TinyPagerAdapter extends FragmentPagerAdapter{

	//private final String _CLASS_NAME = TinyPagerAdapter.class.getName();
	
	private final Context ctx;
    private ArrayList<String> fragmentsList;
	
	public TinyPagerAdapter(SherlockFragmentActivity activity) {
		super(activity.getSupportFragmentManager());
		//Log.v(this._CLASS_NAME, "--> Bulding HomePagerAdapter");
		this.ctx = activity;
		this.fragmentsList = new ArrayList<String>();
		//Log.v(this._CLASS_NAME, "<-- HomePagerAdapter build");
        
	}
	
	public void addATab(Class<?> fragmentClass){
		//Log.v(this._CLASS_NAME, "--> Entering in addATab method");
		//Log.v(this._CLASS_NAME, "We are adding the fragment " + fragmentClass.getName() + " to the fragment list");
		//Log.v(this._CLASS_NAME, "The actual size of the list is " + this.fragmentsList.size());
		this.fragmentsList.add(fragmentClass.getName());
		//Log.v(this._CLASS_NAME, "now is " + this.fragmentsList.size() + ". It is incremented of one, right?");
		//Log.v(this._CLASS_NAME, "<-- Exiting from onPageScrollStateChanged method");
		
	}
	
	@Override
	public Fragment getItem(int tabIndex) {
		//Log.v(this._CLASS_NAME, "--> Entering in getItem method");
		//Log.v(this._CLASS_NAME, "You are trying to instantiate the tab with index " + tabIndex);
		String currentFragmentName = this.fragmentsList.get(tabIndex);
		//Log.v(this._CLASS_NAME, "It should be " + currentFragmentName);
		Fragment currentFragment = Fragment.instantiate(this.ctx, currentFragmentName, null);
		//Log.v(this._CLASS_NAME, "If not, I don't care... I've already instantiate it!");
		//Log.v(this._CLASS_NAME, "<-- Exiting from getItem method");
		
		return currentFragment;
		
	}

	@Override
	public int getCount() {
		//Log.v(this._CLASS_NAME, "--> Entering in getCount method");
		//Log.v(this._CLASS_NAME, "Simply... this is my size :D " + this.fragmentsList.size());
		//Log.v(this._CLASS_NAME, "<-- Exiting from getCount method");
		
		return this.fragmentsList.size();
	}


}
