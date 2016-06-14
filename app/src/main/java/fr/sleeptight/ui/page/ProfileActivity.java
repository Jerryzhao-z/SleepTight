package fr.sleeptight.ui.page;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import fr.sleeptight.R;
import fr.sleeptight.ui.BasicPage;
import fr.sleeptight.ui.HomePage;
import fr.sleeptight.ui.simulation.SimulationActivity;

public class ProfileActivity extends BasicPage {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Slide_Bar(toolbar);



        Button simulation = (Button) findViewById(R.id.simulation_button);


        simulation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), HomePage.class);
                startActivity(intent);
                finish();
            }
        });




    }





}
