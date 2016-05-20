package fr.sleeptight.data.traitement;

import java.util.Date;

/**
 * Created by Zhengrui on 2016/5/13.
 */
public class User {

    private Token googleToken;
    private Token fitbitToken;
    private String pw;
    private String id;
    private String userName;
    private EventList recentEvents;

    public User(String userName)
    {
        this.userName = userName;
    }

    public User setGoogleToken(Token googleToken)
    {
        this.googleToken = googleToken;
        return this;
    }

    public User setFitbitToken(Token fitbitToken)
    {
        this.fitbitToken = fitbitToken;
        return this;
    }

    public User setPw(String pw)
    {
        this.pw = pw;
        return this;
    }
    //TODO: verifier s'il existe dans la base de donnée
    public boolean exists()
    {
        return true;
    }

    public User setRecentEvent(EventList recentEvents)
    {
        this.recentEvents = recentEvents;
        return this;
    }

    public EventList getRecentEvents()
    {
        return this.recentEvents;
    }
    //TODO: getEvent depuis begin jusqu'à end from Server
    public EventList getEvents(Date begin, Date end)
    {
        return new EventList();
    }
    //TODO: find a usr with userid
    public static User findByUserId()
    {
        return null;
    }
}
