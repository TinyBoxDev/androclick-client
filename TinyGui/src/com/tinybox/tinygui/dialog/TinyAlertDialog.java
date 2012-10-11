package com.tinybox.tinygui.dialog;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.os.Bundle;

public class TinyAlertDialog extends TinyDialog{
	
	
	public TinyAlertDialog(int dialogTitle, int dialogIcon, String dialogMessage) {
		super(dialogTitle, dialogIcon, dialogMessage);

	}

	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

		
		Builder thisDialogBuilder = new AlertDialog.Builder(getActivity());
			
		
		thisDialogBuilder.setTitle(this.thisDialogTitle);
		thisDialogBuilder.setIcon(this.thisDialogIcon);
		thisDialogBuilder.setMessage(this.thisDialogMessage);

		
		return thisDialogBuilder.create();
    }
	
}
