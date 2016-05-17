package fr.sleeptight.view.calendar;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.sleeptight.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class CalendarFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("caledar Fragment", "fragment view is creating");
        return inflater.inflate(R.layout.fragement_calendar, container, false);
    }
}
