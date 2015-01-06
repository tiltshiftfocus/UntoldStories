package com.untoldstories.signup;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.format.Time;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.untoldstories.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zm on 9/12/2014.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM, yyyy");
    private Button dobButton;

    @Override
    public Dialog onCreateDialog(Bundle state) {

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }


    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        dobButton = (Button) getActivity().findViewById(getArguments().getInt("buttonID"));


        Time mTime = new Time();
        mTime.set(dayOfMonth,monthOfYear,year);
        Date mDate = new Date(mTime.toMillis(false));

        dobButton.setText(dateFormat.format(mDate));

    }
}
