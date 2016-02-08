package it.mattiamerlini.mvc_scoprimondo.Fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by mattia on 29/01/16.
 */
public abstract class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        Calendar c = Calendar.getInstance();
        c.set(2000, 00, 01);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public abstract void onDateSet(DatePicker view, int year, int month, int day);
}