package fr.sleeptight.presentation.calendar;

import java.util.Date;

import fr.sleeptight.data.traitement.EventList;
import fr.sleeptight.data.traitement.User;
import rx.Observable;
import rx.functions.Func0;

/**
 * Created by Zhengrui on 5/20/2016.
 */
public class EventInteractor implements iEventInteractor{

    @Override
    public Observable<EventList> getEventList(final User user, final Date begin, final Date end)
    {
        return Observable.defer(new Func0<Observable<EventList>>() {
            @Override
            public Observable<EventList> call() {
                return Observable.just(get(user, begin, end));
            }

            private EventList get(final User user, final Date begin, final Date end) {
                return user.getEvents(begin, end);
            }
        });
    }

}
