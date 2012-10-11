package com.tinybox.tinygui.dialog;

import com.tinybox.tinygui.R;

import android.support.v4.app.DialogFragment;

public class TinyDialog extends DialogFragment{

	
	public static final int TYPE_PROGRESS_DIALOG = 0;
	public static final int TYPE_ALERT_DIALOG = 1;
	public static final int TYPE_CUSTOM = 2;

	int thisDialogTitle;
	int thisDialogIcon;
	String thisDialogMessage;
	int thisDialogTheme;

	public TinyDialog(int dialogTitle, int dialogIcon, String dialogMessage){
		super();
		
		this.thisDialogTitle = dialogTitle;
		this.thisDialogMessage =  dialogMessage;
		this.thisDialogIcon = dialogIcon;
		this.thisDialogTheme = R.style.Theme_Sherlock_Light_Dialog;
		
	}
	
}
