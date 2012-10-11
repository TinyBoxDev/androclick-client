package com.tinybox.tinygui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

public class TinyCustomDialog extends TinyDialog{

	private int layoutToInflate;
	
	public TinyCustomDialog(int dialogTitle, int dialogIcon, int layout) {
		super(dialogTitle, dialogIcon, null);
		this.layoutToInflate = layout;

	}
	
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
		LayoutInflater inflater = (LayoutInflater) this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(this.layoutToInflate, null);
		
 		AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
		builder.setView(layout);
		builder.setTitle(this.thisDialogTitle);
		builder.setIcon(this.thisDialogIcon);
		builder.setInverseBackgroundForced(true);
		
		return builder.create();
		
	}

}
