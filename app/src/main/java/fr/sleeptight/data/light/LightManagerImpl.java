package fr.sleeptight.data.light;

import android.util.Log;

import com.philips.lighting.hue.listener.PHLightListener;
import com.philips.lighting.hue.listener.PHScheduleListener;
import com.philips.lighting.model.PHBridge;
import com.philips.lighting.model.PHBridgeResource;
import com.philips.lighting.model.PHHueError;
import com.philips.lighting.model.PHLight;
import com.philips.lighting.model.PHLightState;
import com.philips.lighting.model.PHSchedule;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Created by Yifan on 2016/6/9.
 */
public class LightManagerImpl implements LightManager {

    private PHBridge bridge;
    public static final String TAG = "QuickStart";
    private PHLightListener listener = new PHLightListener() {

        @Override
        public void onSuccess() {
        }

        @Override
        public void onStateUpdate(Map<String, String> arg0, List<PHHueError> arg1) {
            Log.w(TAG, "Light has updated");
        }

        @Override
        public void onError(int arg0, String arg1) {}

        @Override
        public void onReceivingLightDetails(PHLight arg0) {}

        @Override
        public void onReceivingLights(List<PHBridgeResource> arg0) {}

        @Override
        public void onSearchComplete() {}
    };
    private PHScheduleListener scheduleListener = new PHScheduleListener() {
        @Override
        public void onCreated(PHSchedule phSchedule) {}

        @Override
        public void onSuccess() {}

        @Override
        public void onError(int i, String s) {}

        @Override
        public void onStateUpdate(Map<String, String> map, List<PHHueError> list) {
            Log.w(TAG, "Schedule has updated");
        }
    };

    public LightManagerImpl(PHBridge bridge) {
        this.bridge = bridge;
    }

    @Override
    public void turnOn(String lightIdentifier) {
        PHLightState lightState = new PHLightState();
        lightState.setOn(true);
        bridge.updateLightState(lightIdentifier, lightState, listener);
    }

    @Override
    public void turnOff(String lightIdentifier) {
        PHLightState lightState = new PHLightState();
        lightState.setOn(false);
        bridge.updateLightState(lightIdentifier, lightState, listener);
    }

    @Override
    public String getState(String lightIdentifier) {
        return bridge.toString();
    }

    @Override
    public void modifyBri(String lightIdentifier, int value) {
        PHLightState lightState = new PHLightState();
        lightState.setOn(true);
        lightState.setBrightness(value);
        bridge.updateLightState(lightIdentifier, lightState, listener);
    }


    @Override
    public void alarm(String lightIdentifier, Calendar start, Calendar end, int transitionTime, int maxiBri) {
        PHSchedule firstPeriod = new PHSchedule("Alarm-1");
        PHSchedule secondPeriod = new PHSchedule("Alarm-2");

        PHLightState lightState1 = new PHLightState();
        lightState1.setOn(true);
        lightState1.setBrightness(maxiBri);
        lightState1.setTransitionTime(10 * transitionTime);
        firstPeriod.setLightState(lightState1);
        firstPeriod.setLightIdentifier(lightIdentifier);
        firstPeriod.setLocalTime(true);
        firstPeriod.setDate(start.getTime());

        PHLightState lightState2 = new PHLightState();
        lightState2.setOn(false);
        lightState2.setTransitionTime(30);
        secondPeriod.setLightState(lightState2);
        secondPeriod.setLightIdentifier(lightIdentifier);
        secondPeriod.setLocalTime(true);
        secondPeriod.setDate(end.getTime());

        bridge.createSchedule(firstPeriod, scheduleListener);
        bridge.createSchedule(secondPeriod, scheduleListener);

    }




}
