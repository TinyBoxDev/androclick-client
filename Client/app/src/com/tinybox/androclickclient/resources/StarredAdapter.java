package com.tinybox.androclickclient.resources;

import java.util.List;

import com.tinybox.androclickclient.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class StarredAdapter extends ArrayAdapter<StarredItem>{
	
	public StarredAdapter(Context context, int textViewResourceId) {
			super(context, textViewResourceId);
	}
	
	public StarredAdapter(Context context, int resource, int textViewResourceId) {
			super(context, resource, textViewResourceId);
	}
	
	public StarredAdapter(Context context, int resource, int textViewResourceId, List<StarredItem> objects) {
			super(context, resource, textViewResourceId, objects);
	}
	
	public StarredAdapter(Context context, int resource, int textViewResourceId, StarredItem[] objects) {
			super(context, resource, textViewResourceId, objects);
	}
	
	public StarredAdapter(Context context, int textViewResourceId, List<StarredItem> objects) {
			super(context, textViewResourceId, objects);
	}
	
		public StarredAdapter(Context context, int textViewResourceId, StarredItem[] objects) {
			super(context, textViewResourceId, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		LayoutInflater inflater = ((Activity) this.getContext()).getLayoutInflater();
		LinearLayout viewItem = (LinearLayout)inflater.inflate(R.layout.starred_item_layout, null);
		
		if(position==0)
			viewItem.findViewById(R.id.img_star).setVisibility(View.VISIBLE);
		
		StarredItem currentItem = this.getItem(position);
		((TextView)viewItem.findViewById(R.id.label)).setText(currentItem.getLabel());
		((TextView)viewItem.findViewById(R.id.number)).setText(currentItem.getNumber());
		((TextView)viewItem.findViewById(R.id.desc)).setText(currentItem.getInfos());
		
		
		
		return viewItem;
		
	}


}
