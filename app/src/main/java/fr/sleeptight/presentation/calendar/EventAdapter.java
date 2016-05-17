package fr.sleeptight.presentation.calendar;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import fr.sleeptight.R;
import fr.sleeptight.data.traitement.SingleEvent;

/**
 * Created by Zhengrui on 5/15/2016.
 */
public class EventAdapter extends ArrayAdapter<SingleEvent>{

    private int resourceId;

    public EventAdapter(Context context, int textViewResourceId, List<SingleEvent> objects)
    {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        //TODO: set the images and texts for view
        SingleEvent event = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        //ImageView eventImage = (ImageView) view.findViewById(R.id.event_image);
        TextView eventTime = (TextView)view.findViewById(R.id.show_time);
        TextView eventDescription = (TextView)view.findViewById(R.id.show_description);
        //eventImage.setImageBitmap(event.getImageId());
        eventTime.setText(new SimpleDateFormat("H:mm").format(event.getTime()));
        eventDescription.setText(event.getName());
        return view;
    }


}
