package it.mattiamerlini.mvc_scoprimondo.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by mattia on 01/02/16.
 */
public abstract class AlertMessageFragment extends DialogFragment
{
    private String title;
    private String message;
    private String neutralButton;

    public AlertMessageFragment(String title, String message, String neutralButton)
    {
        this.title = title;
        this.message = message;
        this.neutralButton = neutralButton;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(this.title);
        builder.setMessage(this.message);
        builder.setNeutralButton(this.neutralButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onNeutralClick(dialog, which);
            }
        });

        return builder.create();
    }

    protected abstract void onNeutralClick(DialogInterface dialog, int which);
}
