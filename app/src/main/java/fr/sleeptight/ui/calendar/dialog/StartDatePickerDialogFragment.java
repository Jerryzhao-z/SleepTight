package fr.sleeptight.ui.calendar.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import com.github.tibolte.agendacalendarview.models.BaseCalendarEvent;

import java.util.Calendar;

/**
 * Created by Yifan on 2016/6/12.
 */
public class StartDatePickerDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private BaseCalendarEvent newEvent;

    public StartDatePickerDialogFragment(BaseCalendarEvent newEvent) { this.newEvent = newEvent; }




    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);


        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);


    }




    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar startDate = Calendar.getInstance();
        startDate.set(year, month, day);
        newEvent.setStartTime(startDate);

    }




}