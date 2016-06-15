package fr.sleeptight.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


import com.yolanda.nohttp.NoHttp;

import fr.sleeptight.R;
import fr.sleeptight.ui.calendar.CalendarActivity;
import fr.sleeptight.ui.chart.BarChartActivity;
import fr.sleeptight.ui.user_branch.LoginActivity;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* call Home Page */
        Intent newAct = new Intent(getApplicationContext(), CalendarActivity.class);
        startActivity(newAct);
        finish();

    }



}
