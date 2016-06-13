package fr.sleeptight.ui.light;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import fr.sleeptight.data.light.LightManager;
import fr.sleeptight.data.light.LightManagerImpl;
import com.philips.lighting.hue.listener.PHLightListener;
import com.philips.lighting.hue.listener.PHScheduleListener;
import com.philips.lighting.hue.sdk.PHHueSDK;
import com.philips.lighting.model.PHBridge;
import com.philips.lighting.model.PHBridgeResource;
import com.philips.lighting.model.PHGroup;
import com.philips.lighting.model.PHHueError;
import com.philips.lighting.model.PHLight;
import com.philips.lighting.model.PHSchedule;
import fr.sleeptight.R;
import fr.sleeptight.ui.BasicPage;

/**
 * LightControlActivity - The starting point for creating your own Hue App.
 * Currently contains a simple view with a button to change your lights to random colours.  Remove this and add your own app implementation here! Have fun!
 *
 * @author SteveyO
 *
 */
public class LightControlActivity extends BasicPage {
    private PHHueSDK phHueSDK;
    private static final int MAX_HUE=65535;
    public static final String TAG = "QuickStart";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.app_name);
        setContentView(R.layout.activity_light_main);
        phHueSDK = PHHueSDK.getInstance();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Slide_Bar(toolbar);

        PHBridge bridge = phHueSDK.getSelectedBridge();
        final LightManager lightManager = new LightManagerImpl(bridge);

        ToggleButton lightSwitch = (ToggleButton) findViewById(R.id.light1_switch);
        lightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) lightManager.turnOn("1");
                else lightManager.turnOff("1");

            }
        });

    }

    public void randomLights() {
        PHBridge bridge = phHueSDK.getSelectedBridge();
        LightManager lightManager = new LightManagerImpl(bridge);
        /*PHSchedule schedule = new PHSchedule("My Alarm");
        PHLightState lightState = new PHLightState();
        lightState.setOn(false);

        schedule.setLightState(lightState);
        schedule.setLightIdentifier("1");
        schedule.setDate(date);  // Create your date object here with your desired start time

        bridge.createSchedule(schedule, new PHScheduleListener(){});*/

        PHGroup group = new PHGroup();
        List  <String> lightIdentifiers = new ArrayList<String>();
        lightIdentifiers.add("1");   // Alternatively get these from the Lights Cache (identifiers).
        lightIdentifiers.add("2");
        group.setLightIdentifiers(lightIdentifiers);
        bridge.createGroup(group, null);   // No callback when group is created. If the lights don't exist this will return an error.

        Calendar start = Calendar.getInstance();
        start.set(Calendar.SECOND, 20);
        start.set(Calendar.MINUTE, 35);
        start.set(Calendar.HOUR_OF_DAY,12);

        Calendar end = Calendar.getInstance();
        end.set(Calendar.SECOND, 50);
        end.set(Calendar.MINUTE, 35);
        end.set(Calendar.HOUR_OF_DAY,12);

        lightManager.alarm("1",start, end, 20, 170);

    }
    // If you want to handle the response from the bridge, create a PHLightListener object.
    PHLightListener listener = new PHLightListener() {

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

    PHScheduleListener scheduleListener = new PHScheduleListener() {
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

    @Override
    protected void onDestroy() {
        PHBridge bridge = phHueSDK.getSelectedBridge();
        if (bridge != null) {

            if (phHueSDK.isHeartbeatEnabled(bridge)) {
                phHueSDK.disableHeartbeat(bridge);
            }

            phHueSDK.disconnect(bridge);
            super.onDestroy();
        }
    }
}
