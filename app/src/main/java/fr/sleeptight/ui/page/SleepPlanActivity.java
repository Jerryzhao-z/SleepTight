package fr.sleeptight.ui.page;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import fr.sleeptight.R;
import fr.sleeptight.ui.BasicPage;
import fr.sleeptight.ui.chart.BarChartActivity;
import fr.sleeptight.ui.chart.PieChartActivity;

public class SleepPlanActivity extends BasicPage {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_plan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Slide_Bar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sleep_plan, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.simulation: {
                Intent intent  = new Intent(getApplicationContext(), SleepPlanSimulation.class);
                startActivity(intent);
                break;
            }
        }
        return true;
    }


}
