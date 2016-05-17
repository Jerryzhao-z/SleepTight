package fr.sleeptight.data.traitement;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhengrui on 2016/5/13.
 */
public class EventList {

    List<SingleEvent> SingleEventList;
    EventComparatorByDate comparatorbydate;

    public EventList()
    {
        this.SingleEventList = new LinkedList<SingleEvent>();
        this.comparatorbydate = new EventComparatorByDate();
    }

    public EventList(List<SingleEvent> SingleEventList)
    {
        this.SingleEventList = SingleEventList;
        this.comparatorbydate = new EventComparatorByDate();
    }

    public EventList add(SingleEvent event)
    {
        SingleEventList.add(event);
        Collections.sort(SingleEventList, comparatorbydate);
        return this;
    }

    public EventList addAll(Collection<SingleEvent> eventList)
    {
        SingleEventList.addAll(eventList);
        Collections.sort(SingleEventList, comparatorbydate);
        return this;
    }

    public EventList clear()
    {
        SingleEventList.clear();
        return this;
    }

    public int size()
    {
        return SingleEventList.size();
    }

    public EventList remove(int index)
    {
        SingleEventList.remove(index);
        return this;
    }

    public EventList remove(String name)
    {
        for(int i=0; i<SingleEventList.size(); i++)
        {
            if (SingleEventList.get(i).getName().equals(name))
            {
                this.remove(i);
                break;
            }
        }
        return this;
    }

    public EventList remove(Date time)
    {
        for (int i = 0; i < SingleEventList.size(); i++) {
            if (SingleEventList.get(i).getTime().equals(time)) {
                this.remove(i);
                break;
            }
        }
        return this;
    }

    public List<SingleEvent> getSingleEventList()
    {
        return SingleEventList;
    }

    public SingleEvent getSingleEvent(int index)
    {
        return SingleEventList.get(index);
    }

    public SingleEvent getSingleEvent(String name)
    {
        SingleEvent res = null;
        for(SingleEvent event: SingleEventList)
        {
            if (event.getName().equals(name))
            {
                res = event;
            }
        }
        return res;
    }

    public SingleEvent getSingleEvent(Date time)
    {
        SingleEvent res = null;
        for(SingleEvent event: SingleEventList)
        {
            if (event.getTime().equals(time))
            {
                res = event;
            }
        }
        return res;
    }

    class EventComparatorByDate implements Comparator<SingleEvent>
    {
        @Override
        public int compare(SingleEvent event_one, SingleEvent event_two)
        {
            return event_one.getTime().compareTo(event_two.getTime());
        }
    }

}
