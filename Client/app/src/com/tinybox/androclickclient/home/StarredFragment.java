package com.tinybox.androclickclient.home;

import java.util.ArrayList;

import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockListFragment;
import com.client.infoclick.AndroclickDataController;
import com.tinybox.androclickclient.R;
import com.tinybox.androclickclient.Home;
import com.tinybox.androclickclient.resources.StarredAdapter;
import com.tinybox.androclickclient.resources.StarredFile;
import com.tinybox.androclickclient.resources.StarredItem;
import com.tinybox.tinygui.dialog.TinyDialog;
import android.view.ContextMenu;

public class StarredFragment extends SherlockListFragment {
		
	private final int DELETE_MENU_FIELD = 0;
	private final String _CLASS_NAME = this.getClass().getName();
	
	private ArrayList<StarredItem> starredList;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
            
		View view = inflater.inflate(R.layout.starred_layout, container, false);
		StarredFile itemsFile=null;
		itemsFile = StarredFile.getFile(this.getActivity());
		this.starredList = itemsFile.getItemsList();
		
		setListAdapter(new StarredAdapter(getActivity(), R.layout.starred_item_layout, this.starredList));
		
		return view;
    
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
	    super.onViewCreated(view, savedInstanceState);
	    registerForContextMenu(getListView());
		Log.v(this._CLASS_NAME, "Registrato context menu");

	}
	
	@Override
	public void onListItemClick(ListView list, View view, int position, long id){
		Log.v(this._CLASS_NAME, "Item " + 0 + " catched");
		Home homeActivity = (Home)this.getActivity();
		if(position==0){
			homeActivity.showDialog(TinyDialog.TYPE_CUSTOM, R.layout.add_starred_dialog);
		} else{
			//homeActivity.doSearch(AndroclickDataController.STOP_REQUEST, this.starredList.get(position).getNumber());
			//homeActivity.runningThread = new AndroclickDataController().execute(AndroclickDataController.LINE_REQUEST, this.starredList.get(position).getNumber());
			//homeActivity.showDialog(TinyDialog.TYPE_PROGRESS_DIALOG, "searchStopFromView");
		}
		
	}
	
	@Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		 super.onCreateContextMenu(menu, v, menuInfo);
		 AdapterContextMenuInfo info = (AdapterContextMenuInfo)menuInfo;
		 if(info.position!=0){
			 menu.add(Menu.NONE, this.DELETE_MENU_FIELD, this.DELETE_MENU_FIELD, "Delete");
		 }
    }
	
	@Override
    public boolean onContextItemSelected(MenuItem item) {
		if(item.getItemId()==this.DELETE_MENU_FIELD){
			StarredFile itemsFile = StarredFile.getFile(this.getActivity());
			AdapterContextMenuInfo info = (AdapterContextMenuInfo)item.getMenuInfo();
			itemsFile.removeItem(info.position);
			StarredAdapter sa = (StarredAdapter)this.getListAdapter();
			sa.notifyDataSetChanged();
		}
		
		return true;
	}
}
