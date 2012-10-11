package com.tinybox.tinygui;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.tinybox.tinygui.dialog.TinyAlertDialog;
import com.tinybox.tinygui.dialog.TinyCustomDialog;
import com.tinybox.tinygui.dialog.TinyDialog;
import com.tinybox.tinygui.dialog.TinyProgressDialog;
import com.tinybox.tinygui.gui_tools.SearchKeyListener;
import com.tinybox.tinygui.gui_tools.TinyChangePageListener;
import com.tinybox.tinygui.gui_tools.TinyPagerAdapter;
import com.tinybox.tinygui.gui_tools.TinyTabListener;
import com.tinybox.tinygui.gui_tools.ExpandableViewListener;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.EditText;


public abstract class TinyActivity extends SherlockFragmentActivity {
	
	private final String _CLASS_NAME = this.getClass().getName();
	
	private ActionBar homeActionBar = null;
	private TinyTabListener tabListener = null;
	private TinyPagerAdapter tabsAdapter = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
		//Log.v(this._CLASS_NAME, "Welcome to the onCreate method");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
		
        this.homeActionBar = this.getSupportActionBar();
		//Log.v(this._CLASS_NAME, "I've loaded the action bar...");
        this.homeActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		//Log.v(this._CLASS_NAME, "...and setted the tabbed navigation mode!");
		
		ViewPager tabsPager = new ViewPager(this);
		tabsPager.setId(R.id.pager);
        setContentView(tabsPager);
		tabsPager.setOnPageChangeListener(new TinyChangePageListener(homeActionBar));
        
        this.tabsAdapter = new TinyPagerAdapter(this);
        tabsPager.setAdapter(this.tabsAdapter);

        this.tabListener = new TinyTabListener(tabsPager);
        
    }
    
    public void addTab(int tabTitle, Class<?> fragmentClass){
    	Tab newTab = this.homeActionBar.newTab();
    	newTab.setText(tabTitle);
		Log.v(this._CLASS_NAME, "I've generated the tab: " + tabTitle);
		newTab.setTabListener(this.tabListener);
		this.homeActionBar.addTab(newTab);
		this.tabsAdapter.addATab(fragmentClass);
		Log.v(this._CLASS_NAME, "and attached!");
    
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = this.getSupportMenuInflater();
        inflater.inflate(R.menu.home_menu_layout, menu);
        
        MenuItem menuSearchItem = menu.findItem(R.id.menu_search);
        EditText searchEditText = (EditText)menuSearchItem.getActionView();
        searchEditText.setOnKeyListener(new SearchKeyListener(this));
        menuSearchItem.setOnActionExpandListener(new ExpandableViewListener(this, searchEditText));
        
        return true;
    }
    
    public void showDialog(int dialogType, String message) {
    	FragmentTransaction currentTransaction = getSupportFragmentManager().beginTransaction();
    	
    	/*
    	Fragment oldDialog = getSupportFragmentManager().findFragmentByTag("tinyDialog");
        if (oldDialog != null)
        	currentTransaction.remove(oldDialog);
        */
        TinyDialog newDialog;
        
        switch(dialogType){
        case TinyDialog.TYPE_PROGRESS_DIALOG:
        	newDialog = new TinyProgressDialog(R.string.app_name, R.drawable.ic_launcher, message);
        	break;
        case TinyDialog.TYPE_ALERT_DIALOG:
        	newDialog = new TinyAlertDialog(R.string.app_name, R.drawable.ic_launcher, message);
        	break;
        default:
        	newDialog = null;
        }
        
        newDialog.show(currentTransaction, "tinyDialog");
        
    }
    
    public void showDialog(int dialogType, int layout) {
    	FragmentTransaction currentTransaction = getSupportFragmentManager().beginTransaction();
    	
    	Fragment oldDialog = getSupportFragmentManager().findFragmentByTag("tinyDialog");
        if (oldDialog != null)
        	currentTransaction.remove(oldDialog);
        
        TinyDialog newDialog;
        
        switch(dialogType){
        case TinyDialog.TYPE_CUSTOM:
        	newDialog = new TinyCustomDialog(R.string.app_name, R.drawable.ic_launcher, layout);
        	break;
        default:
        	newDialog = null;
        }
        
        newDialog.show(currentTransaction, "tinyDialog");
        
    }
    
    public void removeDialog(){
    	TinyDialog oldDialog = (TinyDialog) getSupportFragmentManager().findFragmentByTag("tinyDialog");
        if (oldDialog != null)
        	oldDialog.dismiss();
        
    }
    
    public void onProgressDialogDismiss(){
    	Log.v(this._CLASS_NAME, "onProgressDialogDismiss non implementato!");
    }
    
    public void onProgressDialogCancel(){
    	Log.v(this._CLASS_NAME, "onProgressDialogCancel non implementato!");    	
    }
    
    public void onSearchPressed(){
    	Log.v(this._CLASS_NAME, "onSearchPressed non implementato!");
    }
    
}


