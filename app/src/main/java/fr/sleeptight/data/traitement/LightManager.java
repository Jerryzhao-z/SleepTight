package fr.sleeptight.data.traitement;

/**
 * Created by Zhengrui on 2016/5/11.
 */
public interface LightManager {
    boolean searchForLight();
    boolean reachable(int Lightid);
    boolean turnOn();
    boolean turnOff();
    boolean getState(int Lightid);
    boolean configuration();
    boolean modifColor(float x, float y);
    boolean modifColorTemperature(short temperature);
    boolean changeColorModeToXY();
    boolean changeColorModeToCT();
}
