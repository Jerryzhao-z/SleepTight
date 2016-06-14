package fr.sleeptight.ui;

import android.content.Context;
import android.content.Intent;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import com.refresh.menuitem.RefreshMenuItemHelper;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import fr.sleeptight.R;
import fr.sleeptight.data.acces.APIClient.APIClass;
import fr.sleeptight.data.acces.APIClient.APIService;
import fr.sleeptight.data.acces.APIClient.AsyncCall;
import fr.sleeptight.data.acces.APIClient.ServiceGenerator;
import fr.sleeptight.data.fitbit.sleep.SleepRequest;
import fr.sleeptight.data.traitement.Light;
import fr.sleeptight.data.traitement.User;
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
    RefreshMenuItemHelper refreshHelper;
    Thread thread;

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
                AsyncCall.getSleepCall(2016,6,3);

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
