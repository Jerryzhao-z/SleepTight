package fr.sleeptight.data.light;

import java.util.Calendar;

/**
 * Created by Yifan on 2016/6/9.
 */
public interface LightManager {

    void turnOn(String lightIdentifier);

    void turnOff(String lightIdentifier);

    String getState(String lightIdentifier);

    void modifyBri(String lightIdentifier, int value);

    void alarm(String lightIdentifier, Calendar start, Calendar end, int transitionTime, int maxiBri);

}
