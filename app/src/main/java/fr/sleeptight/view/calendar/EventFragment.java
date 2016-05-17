package fr.sleeptight.view.calendar;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import junit.framework.Test;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import fr.sleeptight.R;
import fr.sleeptight.data.traitement.SingleEvent;
import fr.sleeptight.presentation.calendar.EventAdapter;

/*
/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EventFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EventFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventFragment extends ListFragment {

    private ListView list;
    private View view;
    private List<SingleEvent> Testdata;
//TODO: tester l'adapter avec Testdata
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTestdata();
        EventAdapter adapter = new EventAdapter(this.getActivity(),R.layout.displayevent , Testdata);
        setListAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_event, container, false);
        //list = (ListView)view.findViewById(R.id.list_view);
        Log.d("event fragment", "events fragment is creating");
        return view;
    }

    public void setTestdata()
    {
        SingleEvent first = new SingleEvent("first", new Date(2016, 5, 17, 12, 0));
        SingleEvent second = new SingleEvent("Second", new Date(2016, 5, 17, 13, 0));
        SingleEvent third = new SingleEvent("third", new Date(2016, 5, 17, 14, 0));

        Testdata = new LinkedList<SingleEvent>();
        Testdata.add(first);
        Testdata.add(second);
        Testdata.add(third);
    }

}
