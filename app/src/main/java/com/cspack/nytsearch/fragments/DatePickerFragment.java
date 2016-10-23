package com.cspack.nytsearch.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.cspack.nytsearch.models.FilterConfig;

import java.util.Calendar;
import java.util.Date;
import java.util.logging.Filter;

public class DatePickerFragment extends DialogFragment {
    private Calendar calendar;
    private DatePickerDialog.OnDateSetListener listener;
    public DatePickerFragment() {

    }
    public static DatePickerFragment newInstance(long dateMillis,
                                          DatePickerDialog.OnDateSetListener listener) {
        DatePickerFragment dpf = new DatePickerFragment();
        dpf.calendar = Calendar.getInstance();
        if (dateMillis > 0) {
            dpf.calendar.setTimeInMillis(dateMillis);
        }
        dpf.listener = listener;
        return dpf;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), listener, year, month, day);
    }
}
