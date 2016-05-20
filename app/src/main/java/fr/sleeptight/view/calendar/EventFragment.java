package fr.sleeptight.view.calendar;

import android.graphics.Bitmap;
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
import fr.sleeptight.presentation.calendar.iEventPresenter;
import fr.sleeptight.presentation.calendar.iEventView;


////tutorial for Listfragment: http://www.cnblogs.com/smyhvae/p/4000483.html
/*
/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EventFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EventFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventFragment extends ListFragment implements iEventView {

    private ListView list;
    private View view;
    private List<SingleEvent> Testdata;
    private iEventPresenter eventPresenter;


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
        view = inflater.inflate(R.layout.fragment_event, container, false);
        list = (ListView)view.findViewById(android.R.id.list);
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

    @Override
    public void showDescription(String description)
    {

    }

    @Override
    public void showTime(Date time)
    {

    }

    @Override
    public void showImage(Bitmap image)
    {

    }

    @Override
    public void showImportance(int importance)
    {

    }

    @Override
    public void showEvent(SingleEvent event)
    {

    }
}
