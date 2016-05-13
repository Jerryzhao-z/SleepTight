package fr.sleeptight.data.traitement;

/**
 * Created by Zhengrui on 2016/5/11.
 */
public interface LightManager {
    Light searchForLight();
    boolean reachable(int Lightid);
    Light turnOn();
    Light turnOff();
    String getState(int Lightid);
    Light modifColor(float x, float y);
    Light modifColorTemperature(short temperature);
    Light changeColorModeToXY();
    Light changeColorModeToCT();
}
