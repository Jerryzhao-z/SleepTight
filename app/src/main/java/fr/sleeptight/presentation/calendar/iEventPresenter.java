package fr.sleeptight.presentation.calendar;

import fr.sleeptight.data.traitement.SingleEvent;
import rx.Subscription;

/**
 * Created by Zhengrui on 5/17/2016.
 */
public interface iEventPresenter {

    void showEvent(SingleEvent event);
    Subscription showEvents();
    void showRelatedImage(SingleEvent event);
    void onModificationClick(SingleEvent event);

}
