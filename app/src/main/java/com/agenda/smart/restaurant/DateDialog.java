package com.agenda.smart.restaurant;

/**
 * Created by Abdelrahman on 10/17/2018.
 */


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;


public class DateDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {


    TextView txtView;


    public DateDialog() {

    }

    @SuppressLint("ValidFragment")
    public DateDialog(View view) {
        this.txtView = (TextView) view;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        String selectedDate = year + "-" + (month + 1) + "-" + day;
        txtView.setText(selectedDate);

    }
}
