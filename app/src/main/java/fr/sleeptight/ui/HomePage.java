package fr.sleeptight.ui;

import android.content.Context;
import android.content.Intent;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import fr.sleeptight.R;
import fr.sleeptight.data.acces.APIClient.APIClass;
import fr.sleeptight.data.acces.APIClient.APIService;
import fr.sleeptight.data.acces.APIClient.ServiceGenerator;
import fr.sleeptight.data.fitbit.sleep.SleepRequest;
import fr.sleeptight.data.traitement.Light;
import fr.sleeptight.ui.chart.PieChartActivity;
import fr.sleeptight.ui.light.LightAuthActivity;
import fr.sleeptight.ui.listener.ChromeTabListener;
import fr.sleeptight.ui.page.Page2;
import fr.sleeptight.ui.page.SleepPlanActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomePage extends BasicPage {

    private Button button1;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Slide_Bar(toolbar);

        button1 = (Button) findViewById(R.id.but1);
        img2 = (ImageView) findViewById(R.id.imageView2);
        img3 = (ImageView) findViewById(R.id.imageView3);
        img4 = (ImageView) findViewById(R.id.imageView4);

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent newAct = new Intent(getApplicationContext(), SleepPlanActivity.class);
                startActivity(newAct);
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent newAct = new Intent(getApplicationContext(), PieChartActivity.class);
                startActivity(newAct);
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Intent newAct = new Intent(getApplicationContext(), LightAuthActivity.class);
                //startActivity(newAct);
                //zzr: get sleepdata
                APIService client = ServiceGenerator.createService(APIService.class);
                Call<List<APIClass.SleepData>> call =  client.getSleepLogTest(2016,6,3);
                call.enqueue(new Callback<List<APIClass.SleepData>>() {
                    @Override
                    public void onResponse(Call<List<APIClass.SleepData>> call, Response<List<APIClass.SleepData>> response) {
                        if (response.isSuccessful()) {
                            // tasks available
                            List<APIClass.SleepData> sleeplogs = response.body();
                            for(APIClass.SleepData sleep: sleeplogs)
                            {
                                Log.d("APIClass.SleepData", "dateofSleep: "+sleep.dateOfSleep);
                                Log.d("APIClass.SleepData", "minutesAwake: "+sleep.minutesAwake);
                                Log.d("APIClass.SleepData", "startTime: "+sleep.startTime);
                                Log.d("APIClass.SleepData", "isMainSleep: "+sleep.isMainSleep);
                                for(String time: sleep.dateTimeStateReallyAwake)
                                    Log.d("APIClass.SleepData", "dateTimeStateReallyAwake: "+time);
                            }

                        } else {
                            // error response, no access to resource?
                            Log.d("APIClass.ProfileResetting", response.message());

                        }
                    }

                    @Override
                    public void onFailure(Call<List<APIClass.SleepData>> call, Throwable t) {
                        Log.d("Error", t.getMessage());
                    }
                });
            }
        });
/*        img4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent newAct = new Intent(getApplicationContext(), Page2.class);
                startActivity(newAct);
            }
        });*/
        img4.setOnClickListener(new ChromeTabListener(img4.getId(), this, Boolean.TRUE));
/*        img4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SleepRequest.getSleepLog(2016,6,3);
            }

        });*/
    }
}
