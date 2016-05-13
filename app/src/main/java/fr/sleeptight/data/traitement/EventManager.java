package fr.sleeptight.data.traitement;

import java.util.List;
import java.util.Date;
import com.google.api.services.calendar.model.Event;

/**
 * Created by Zhengrui on 2016/5/10.
 *
 * cette interface a pour gérer les evenements
 *
 * la methode getEventFromGoogle sert a obtenir
 * tous les evenements dans begin~end. le mininum
 * unite de Date est second
 *
 */
public interface EventManager {
    List<Event> getEventFromGoogle(Date begin, Date end);
    EventList updateEventToGoogle(SingleEvent event);
    EventList updateEventToSleepTight(List<SingleEvent> events);
    EventList deleteEventFromGoogle(String eventId);
    EventList deleteEventFromSleepTight(String eventId);
}
