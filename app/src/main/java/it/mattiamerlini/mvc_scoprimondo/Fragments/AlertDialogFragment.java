package it.mattiamerlini.mvc_scoprimondo.Fragments;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;


import com.example.enrico.mvc_scoprimondo.R;

/**
 * Created by mattia on 30/01/16.
 */
public abstract class AlertDialogFragment extends DialogFragment
{
    private String title;
    private String message;
    private String positiveButton;
    private String negativeButton;

    public AlertDialogFragment(String title, String message, String positiveButton, String negativeButton)
    {
        this.title = title;
        this.message = message;
        this.positiveButton = positiveButton;
        this.negativeButton = negativeButton;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(this.title);
        builder.setMessage(this.message);
        builder.setPositiveButton(this.positiveButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onPositiveClick(dialog, which);
            }
        });
        builder.setNegativeButton(this.negativeButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onNegativeClick(dialog, which);
            }
        });

        return builder.create();
    }

    protected abstract void onNegativeClick(DialogInterface dialog, int which);

    protected abstract void onPositiveClick(DialogInterface dialog, int which);
}