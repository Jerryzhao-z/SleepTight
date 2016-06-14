package fr.sleeptight.ui;

import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import com.refresh.menuitem.RefreshMenuItemHelper;

import fr.sleeptight.R;
import fr.sleeptight.data.acces.APIClient.AsyncCall;
import fr.sleeptight.ui.chart.PieChartActivity;
import fr.sleeptight.ui.listener.ChromeTabListener;
import fr.sleeptight.ui.page.SleepPlanActivity;
import fr.sleeptight.ui.page.SleepPlanSimulation;


public class HomePage extends BasicPage {

    private Button button1;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;
    RefreshMenuItemHelper refreshHelper;
    private static final String TAG = "HomePage";;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Slide_Bar(toolbar);

        refreshHelper = new RefreshMenuItemHelper();

        button1 = (Button) findViewById(R.id.but1);
        img2 = (ImageView) findViewById(R.id.imageView2);
        img3 = (ImageView) findViewById(R.id.imageView3);
        img4 = (ImageView) findViewById(R.id.imageView4);

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent newAct = new Intent(getApplicationContext(), SleepPlanActivity.class);
                startActivity(newAct);
                finish();
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
                SyncPrensenter.getSleepOfWeek();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // True to use Holo Dark, false for Holo Light
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:

                SyncPrensenter.getSleepOfWeek();

                refreshHelper.setMenuItem(item);
                new RefreshAsyncTask().execute();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void showToast(final String toast)
    {
        runOnUiThread(new Runnable() {
            public void run()
            {
                Toast.makeText(HomePage.this, toast, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private class RefreshAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            refreshHelper.startLoading();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            refreshHelper.stopLoading();
            showToast("Sync finished");
        }
    }
}
