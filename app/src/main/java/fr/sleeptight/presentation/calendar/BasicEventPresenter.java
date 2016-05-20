package fr.sleeptight.presentation.calendar;

import fr.sleeptight.data.traitement.EventList;
import fr.sleeptight.data.traitement.SingleEvent;
import rx.Subscription;

/**
 * Created by Zhengrui on 5/20/2016.
 */
public class BasicEventPresenter implements iEventPresenter{

    private iEventView eventView;
    private iEventInteractor eventInteractor;

    public BasicEventPresenter(iEventView eventView, iEventInteractor eventInteractor)
    {
        this.eventView = eventView;
        this.eventInteractor = eventInteractor;
    }
    @Override
    private void showEvent(SingleEvent event)
    {
        eventView.showEvent(event);
    }
    @Override
    public Subscription showEvents(EventList eventlist)
    {
        return null;
    }
    @Override
    public void showRelatedImage(SingleEvent event)
    {

    }
    @Override
    public void onModificationClick(SingleEvent event)
    {

    }

}
