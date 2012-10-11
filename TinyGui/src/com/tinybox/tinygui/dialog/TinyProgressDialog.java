package com.tinybox.tinygui.dialog;

import com.tinybox.tinygui.TinyActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

public class TinyProgressDialog extends TinyDialog{
	
	private final String _CLASS_NAME = this.getClass().getName();
	
	public TinyProgressDialog(int dialogTitle, int dialogIcon, String dialogMessage) {
		super(dialogTitle, dialogIcon, dialogMessage);

	}

	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

		
		
		ProgressDialog thisDialog = new ProgressDialog(getActivity());
			
		thisDialog.setTitle(this.thisDialogTitle);
		thisDialog.setIcon(this.thisDialogIcon);
		thisDialog.setMessage(this.thisDialogMessage);
		thisDialog.setCancelable(false);
		thisDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		        dialog.cancel();
		    }
		});
		
		return thisDialog;
    }
	
	@Override
	public void onDismiss(DialogInterface dialog) {
		Log.v(this._CLASS_NAME, "catched dismiss");
		TinyActivity relatedActivity = (TinyActivity)getActivity();
		relatedActivity.onProgressDialogDismiss();
	}
	
	@Override
	public void onCancel(DialogInterface dialog) {
		Log.v(this._CLASS_NAME, "catched cancel");
		TinyActivity relatedActivity = (TinyActivity)getActivity();
		relatedActivity.onProgressDialogCancel();
	}
	
}
