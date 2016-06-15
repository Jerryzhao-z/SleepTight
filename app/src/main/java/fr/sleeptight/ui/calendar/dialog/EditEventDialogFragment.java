package fr.sleeptight.ui.calendar.dialog;

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

/**
 * Created by Yifan on 2016/6/11.
 */
public class EditEventDialogFragment extends DialogFragment{

    private BaseCalendarEvent newEvent = getNewEvent();

    public void setNewEvent(BaseCalendarEvent newEvent) { this.newEvent = newEvent; }

    public BaseCalendarEvent getNewEvent() { return newEvent; }

    private TextView startDate;

    public TextView getStartDate() { return startDate; }


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

    private OnClickListener scOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    private OnClickListener ecOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {

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

        //Create a new BasicCalendarEvent instance
        BaseCalendarEvent newEvent = new BaseCalendarEvent(Calendar.getInstance(), "Unknown");
        newEvent.setLocation("Paris");


        //Text view, title and place setters
        View v = inflater.inflate(R.layout.view_edit_event_dialog, container,false);
        EditText title = (EditText) v.findViewById(R.id.dialog_event_title);
        EditText place = (EditText) v.findViewById(R.id.dialog_event_place);
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
                newEvent.setColor(R.color.orange_dark);
            }
        });

        RadioButton rb2 = (RadioButton) v.findViewById(R.id.dialog_event_importance_2);
        rb2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                newEvent.setColor(R.color.yellow);
            }
        });

        RadioButton rb3 = (RadioButton) v.findViewById(R.id.dialog_event_importance_2);
        rb3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                newEvent.setColor(R.color.blue_dark);
            }
        });


        //Date setters and their listeners
        startDate = (TextView) v.findViewById(R.id.dialog_event_startDate);
        startDate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                StartDatePickerDialogFragment startDatePickerFragment = new StartDatePickerDialogFragment(newEvent);
                startDatePickerFragment.show(getFragmentManager(), "StartDateSetter");
            }
        });
        TextView startTime = (TextView) v.findViewById(R.id.dialog_event_startTime);
        startTime.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                StartTimePickerDialogFragment startTimePickerFragment = new StartTimePickerDialogFragment(newEvent);
                startTimePickerFragment.show(getFragmentManager(), "StartTimeSetter");
            }
        });
        TextView endDate = (TextView) v.findViewById(R.id.dialog_event_endDate);
        endDate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                EndDatePickerDialogFragment endDatePickerFragment = new EndDatePickerDialogFragment(newEvent);
                endDatePickerFragment.show(getFragmentManager(), "EndDateSetter");
            }
        });
        TextView endTime = (TextView) v.findViewById(R.id.dialog_event_endTime);
        endTime.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                EndTimePickerDialogFragment endTimePickerFragment = new EndTimePickerDialogFragment(newEvent);
                endTimePickerFragment.show(getFragmentManager(), "EndTimeSetter");
            }
        });


        //two listener for two buttons
        Button saveBtn = (Button)v.findViewById(R.id.button_save);
        saveBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                newEvent.setTitle(title.getText().toString());
                newEvent.setLocation(place.getText().toString());


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
