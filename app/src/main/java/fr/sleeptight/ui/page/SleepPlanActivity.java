package fr.sleeptight.ui.page;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import fr.sleeptight.R;
import fr.sleeptight.ui.simulation.SimulationActivity;

/**
 * Created by Qifan on 2016/6/2.
 * cette interface a defini le sleep plan activity view
 */

public class SleepPlanActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_plan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button simulation = (Button) findViewById(R.id.simulation_button);
        simulation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SimulationActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
