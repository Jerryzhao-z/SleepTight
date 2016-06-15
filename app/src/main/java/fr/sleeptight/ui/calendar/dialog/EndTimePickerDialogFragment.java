package fr.sleeptight.ui.calendar.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import com.github.tibolte.agendacalendarview.models.BaseCalendarEvent;

import java.util.Calendar;

/**
 * Created by Yifan on 2016/6/12.
 */
public class EndTimePickerDialogFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    private BaseCalendarEvent newEvent;

    public EndTimePickerDialogFragment(BaseCalendarEvent newEvent) { this.newEvent = newEvent; }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        newEvent.getEndTime().set(Calendar.HOUR_OF_DAY, hourOfDay);
        newEvent.getEndTime().set(Calendar.MINUTE, minute);
    }
}