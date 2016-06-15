package fr.sleeptight.ui.calendar.dialog;

import android.app.Activity;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.github.tibolte.agendacalendarview.models.BaseCalendarEvent;

import java.util.Calendar;

import fr.sleeptight.R;
import fr.sleeptight.ui.calendar.CalendarActivity;

/**
 * Created by Yifan on 2016/6/11.
 */
public class EditEventDialogFragment extends DialogFragment{

    private BaseCalendarEvent newEvent = getNewEvent();

    public void setNewEvent(BaseCalendarEvent newEvent) { this.newEvent = newEvent; }

    public BaseCalendarEvent getNewEvent() { return newEvent; }

    private TextView startDate;

    public TextView getStartDate() { return startDate; }

    private EditText title;
    private EditText place;

    OnSaveEventListener onSaveEventListener;

    interface OnSaveEventListener {
        public void saveEvent(BaseCalendarEvent newEvent);
    }



    private OnClickListener cbOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            dismiss();
        }
    };

    private OnCheckedChangeListener imOnClickListener = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

        }
    };




    public static EditEventDialogFragment newInstance() {
        EditEventDialogFragment dialogFragment = new EditEventDialogFragment();

        Bundle bundle = new Bundle();
        bundle.putString("title","New Event settings" );
        dialogFragment.setArguments(bundle);
        return dialogFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //rendering configuration
        this.setNewEvent(new BaseCalendarEvent(Calendar.getInstance(), "Unknown"));

        //Create a new BasicCalendarEvent instance


        //Text view, title and place setters
        View v = inflater.inflate(R.layout.view_edit_event_dialog, container,false);
        title = (EditText) v.findViewById(R.id.dialog_event_title);
        place = (EditText) v.findViewById(R.id.dialog_event_place);
        TextView dialogTitle = (TextView) v.findViewById(R.id.dialog_title_text);
        TextView startCalendarText = (TextView) v.findViewById(R.id.dialog_text_startDate);
        TextView endCalendarText = (TextView) v.findViewById(R.id.dialog_text_endDate);
        TextView importanceTitleText = (TextView) v.findViewById(R.id.dialog_event_importance_title);


        //Importance setters and their listeners
        RadioGroup importance = (RadioGroup) v.findViewById(R.id.dialog_event_importance);

        RadioButton rb1 = (RadioButton) v.findViewById(R.id.dialog_event_importance_1);
        rb1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getNewEvent().setColor(R.color.orange_dark);
            }
        });

        RadioButton rb2 = (RadioButton) v.findViewById(R.id.dialog_event_importance_2);
        rb2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getNewEvent().setColor(R.color.yellow);
            }
        });

        RadioButton rb3 = (RadioButton) v.findViewById(R.id.dialog_event_importance_2);
        rb3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getNewEvent().setColor(R.color.blue_dark);
            }
        });


        //Date setters and their listeners
        startDate = (TextView) v.findViewById(R.id.dialog_event_startDate);
        startDate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                StartDatePickerDialogFragment startDatePickerFragment = new StartDatePickerDialogFragment(getNewEvent());
                startDatePickerFragment.show(getFragmentManager(), "StartDateSetter");
            }
        });
        TextView startTime = (TextView) v.findViewById(R.id.dialog_event_startTime);
        startTime.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                StartTimePickerDialogFragment startTimePickerFragment = new StartTimePickerDialogFragment(getNewEvent());
                startTimePickerFragment.show(getFragmentManager(), "StartTimeSetter");
            }
        });
        TextView endDate = (TextView) v.findViewById(R.id.dialog_event_endDate);
        endDate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                EndDatePickerDialogFragment endDatePickerFragment = new EndDatePickerDialogFragment(getNewEvent());
                endDatePickerFragment.show(getFragmentManager(), "EndDateSetter");
            }
        });
        TextView endTime = (TextView) v.findViewById(R.id.dialog_event_endTime);
        endTime.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                EndTimePickerDialogFragment endTimePickerFragment = new EndTimePickerDialogFragment(getNewEvent());
                endTimePickerFragment.show(getFragmentManager(), "EndTimeSetter");
            }
        });


        //two listener for two buttons
        Button saveBtn = (Button)v.findViewById(R.id.button_save);
        saveBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                getNewEvent().setTitle(title.getText().toString());
                getNewEvent().setLocation(place.getText().toString());




                ((CalendarActivity)getActivity()).getEventList().add(newEvent);
                Log.v("wo ccao", newEvent.getTitle());
                Log.v("wo cao",((CalendarActivity)getActivity()).getEventList().get(3).getTitle());
                onSaveEventListener.saveEvent(newEvent);
                dismiss();

                //CharSequence text = "Title:" + newEvent.getTitle() + " Location:" + newEvent.getLocation() + "Start date" + newEvent.getStartTime().toString();
                //Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();


            }
        }
        );
        Button cancelBtn = (Button)v.findViewById(R.id.button_cancel);
        cancelBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return v;
    }

    @Override
    public void onAttach(Activity a) {
        super.onAttach(a);
        onSaveEventListener = (OnSaveEventListener) a;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(true);
        setStyle(0,0);
    }


    @Override
    public void onStart() {
        super.onStart();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindow().getWindowManager().getDefaultDisplay().getMetrics(dm);
        getDialog().getWindow().setLayout(dm.widthPixels, (int)(dm.heightPixels*0.77));
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.rgb(230,230,230)));
    }


}
