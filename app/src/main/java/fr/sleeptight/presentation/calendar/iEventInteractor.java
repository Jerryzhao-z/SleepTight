package fr.sleeptight.presentation.calendar;

import java.util.Date;

import fr.sleeptight.data.traitement.EventList;
import rx.Observable;
/**
 * Created by Zhengrui on 5/17/2016.
 */
public interface iEventInteractor {

    Observable<EventList> getEventList(String userid, Date time);

}


