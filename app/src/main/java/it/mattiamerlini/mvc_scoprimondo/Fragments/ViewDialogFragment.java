package it.mattiamerlini.mvc_scoprimondo.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import it.mattiamerlini.mvc_scoprimondo.Views.ImageSpinner.ImageSpinnerView;

/**
 * Created by mattia on 05/02/16.
 */
public abstract class ViewDialogFragment extends DialogFragment
{
    private ImageSpinnerView content;
    private String positiveButton;
    private String negativeButton;

    public ViewDialogFragment(ImageSpinnerView content, String positiveButton, String negativeButton)
    {
        this.content = content;
        this.positiveButton = positiveButton;
        this.negativeButton = negativeButton;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(content);
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
        //builder.create().show();

        return builder.create();
    }

    protected abstract void onNegativeClick(DialogInterface dialog, int which);

    protected abstract void onPositiveClick(DialogInterface dialog, int which);
}
