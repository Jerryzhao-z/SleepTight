package fr.sleeptight.ui.page;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TimePicker;

import com.philips.lighting.hue.sdk.PHHueSDK;
import com.philips.lighting.model.PHBridge;

import java.util.Calendar;

import fr.sleeptight.R;
import fr.sleeptight.data.light.LightManager;
import fr.sleeptight.data.light.LightManagerImpl;
import fr.sleeptight.ui.simulation.SimulationActivity;

/**
 * Created by Qifan on 2016/6/2.
 * cette interface a defini le sleep plan activity view
 */

public class SleepPlanSimulation extends AppCompatActivity {
    private PHHueSDK phHueSDK;
    Switch manual;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_sim);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Calendar wakeupStartTime = Calendar.getInstance();
        final Calendar wakeupEndTime = Calendar.getInstance();


        phHueSDK = PHHueSDK.getInstance();
        final PHBridge bridge = phHueSDK.getSelectedBridge();
        final LightManager lightManager = new LightManagerImpl(bridge);


        TimePicker wakeupTimePicker = (TimePicker) findViewById(R.id.wakeup_time_picker);
        wakeupTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                wakeupStartTime.set(Calendar.HOUR_OF_DAY, hourOfDay-2);
                wakeupStartTime.set(Calendar.MINUTE, minute);
                wakeupEndTime.set(Calendar.HOUR_OF_DAY, hourOfDay-2);
                wakeupEndTime.set(Calendar.MINUTE, minute+3);
                Log.i("On time changed", wakeupEndTime.toString());
            }
        });

        manual = (Switch) findViewById(R.id.switch1);

        Button simulation = (Button) findViewById(R.id.simulation_button);


        simulation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(manual.isChecked()){


                    Log.v("switch", "manual");
                    if (bridge != null) {
                        lightManager.alarm("1", wakeupStartTime, wakeupEndTime, 10, 180);
                    } else {
                        Log.v("Light connection error", "Light connection failed");
                    }
                } else {
                    Log.v("switch", "automatique");
                    if (bridge != null) {
                        lightManager.alarm("1", Calendar.getInstance(), Calendar.getInstance(), 10, 180);
                    } else {
                        Log.v("Light connection error", "Light connection failed");
                    }

                }
                Intent intent = new Intent(getApplicationContext(), SimulationActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
