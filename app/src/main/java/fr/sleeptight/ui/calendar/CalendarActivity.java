package fr.sleeptight.ui.calendar;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.github.tibolte.agendacalendarview.AgendaCalendarView;
import com.github.tibolte.agendacalendarview.CalendarPickerController;
import com.github.tibolte.agendacalendarview.models.BaseCalendarEvent;
import com.github.tibolte.agendacalendarview.models.CalendarEvent;
import com.github.tibolte.agendacalendarview.models.DayItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import fr.sleeptight.R;
import fr.sleeptight.ui.calendar.dialog.EditEventDialogFragment;
import fr.sleeptight.ui.calendar.widget.NewEventButton;

public class CalendarActivity extends AppCompatActivity implements CalendarPickerController, fr.sleeptight.ui.calendar.dialog.EditEventDialogFragment. {

    private static final String LOG_TAG = CalendarActivity.class.getSimpleName();

    private List<CalendarEvent> eventList = new ArrayList<>();


    Calendar minDate = Calendar.getInstance();
    Calendar maxDate = Calendar.getInstance();

    @Bind(R.id.activity_calendar_toolbar)
    Toolbar mToolbar;
    @Bind(R.id.agenda_calendar_view)
    AgendaCalendarView mAgendaCalendarView;
    @Bind(R.id.new_event_button)
    NewEventButton newEventButton;




    // region Lifecycle methods

    public List<CalendarEvent> getEventList(){ return eventList; }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        newEventButton.setBackgroundColor(Color.YELLOW);
        newEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditEventDialogFragment editEventDialogFragment = EditEventDialogFragment.newInstance();
                //editEventDialogFragment.getDialog().getWindow().setLayout(300,300);
                editEventDialogFragment.show(getFragmentManager(),"New Event Settings");

            }
        });

        // minimum and maximum date of our calendar
        // 2 month behind, one year ahead, example: March 2015 <-> May 2015 <-> May 2016
        minDate.add(Calendar.MONTH, -2);
        minDate.set(Calendar.DAY_OF_MONTH, 1);
        maxDate.add(Calendar.YEAR, 1);




    }



    @Override
    protected void onResume() {
        mockList(eventList);
        mAgendaCalendarView.init(eventList, minDate, maxDate, Locale.getDefault(), this);
        mAgendaCalendarView.addEventRenderer(new DrawableEventRenderer());
        super.onResume();
    }





    // endregion

    // region Interface - CalendarPickerController

    @Override
    public void onDaySelected(DayItem dayItem) {
        Log.d(LOG_TAG, String.format("Selected day: %s", dayItem));
    }

    @Override
    public void onEventSelected(CalendarEvent event) {
        Log.d(LOG_TAG, String.format("Selected event: %s", event));
    }

    @Override
    public void onScrollToDate(Calendar calendar) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()));
        }
    }

    // endregion

    // region Private Methods

    private void mockList(List<CalendarEvent> eventList) {
        Calendar startTime1 = Calendar.getInstance();
        Calendar endTime1 = Calendar.getInstance();
        endTime1.add(Calendar.MONTH, 1);
        BaseCalendarEvent event1 = new BaseCalendarEvent("Johnny traveled in Africa", "A wonderful journey", "Africa",
                ContextCompat.getColor(this, R.color.orange_dark), startTime1, endTime1, true);
        eventList.add(event1);

        Calendar startTime2 = Calendar.getInstance();
        startTime2.add(Calendar.DAY_OF_YEAR, 1);
        Calendar endTime2 = Calendar.getInstance();
        endTime2.add(Calendar.DAY_OF_YEAR, 3);
        BaseCalendarEvent event2 = new BaseCalendarEvent("Visit of Egypt", "It was a great experience", "Egypt",
                ContextCompat.getColor(this, R.color.yellow), startTime2, endTime2, true);
        eventList.add(event2);

        Calendar startTime3 = Calendar.getInstance();
        Calendar endTime3 = Calendar.getInstance();
        startTime3.set(Calendar.HOUR_OF_DAY, 14);
        startTime3.set(Calendar.MINUTE, 0);
        endTime3.set(Calendar.HOUR_OF_DAY, 15);
        endTime3.set(Calendar.MINUTE, 0);
        DrawableCalendarEvent event3 = new DrawableCalendarEvent("Johnny was fucked by a dog", "ROFL", "South Africa",
                ContextCompat.getColor(this, R.color.blue_dark), startTime3, endTime3, false, R.drawable.event_fucked_by_dog);
        eventList.add(event3);
    }

    // endregion
}
