package fr.sleeptight.ui.chart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.api.services.calendar.Calendar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.SynchronousQueue;

import fr.sleeptight.R;
import fr.sleeptight.data.acces.APIClient.AsyncCall;
import fr.sleeptight.data.localdb.CommitUtils;
import fr.sleeptight.data.localdb.DaoManager;
import fr.sleeptight.data.localdb.DaoMaster;
import fr.sleeptight.data.localdb.DaoSession;
import fr.sleeptight.data.localdb.Sleep;
import fr.sleeptight.data.localdb.SleepDao;
import fr.sleeptight.data.localdb.SleepDetail;
import fr.sleeptight.data.localdb.SleepDetailDao;
import fr.sleeptight.ui.BasicPage;
import fr.sleeptight.ui.ContextHolder;
import fr.sleeptight.ui.SyncPrensenter;

public class BasicChartPage extends BasicPage {

    protected static int WEEK=0;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_chart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.chartPie: {
                Intent intent  = new Intent(getApplicationContext(), PieChartActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.charBar: {
                Intent intent  = new Intent(getApplicationContext(), BarChartActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.chartLine: {
                Intent intent  = new Intent(getApplicationContext(), LineChartActivity.class);
                startActivity(intent);
                break;

            }
            case R.id.listData: {
                SyncPrensenter.listAllSleepData();
                break;

            }

            case R.id.delay: {
                WEEK++;
                Intent refresh = new Intent(getApplicationContext(), this.getClass());
                startActivity(refresh);//Start the same Activity
                finish(); //finish Activity.
                break;

            }

            case R.id.reset: {
                WEEK=0;
                Intent refresh = new Intent(getApplicationContext(), this.getClass());
                startActivity(refresh);//Start the same Activity
                finish(); //finish Activity.
                break;
            }

            case R.id.testData: {
                WEEK=0;
                DaoManager daoManager = DaoManager.getInstance();
                DaoSession daoSession = daoManager.getDaoSession();
                SleepDao sleepDao = daoSession.getSleepDao();
                SleepDetailDao sleepDetailDao = daoSession.getSleepDetailDao();
                sleepDao.dropTable(daoManager.getDatabase(), true);
                sleepDao.createTable(daoManager.getDatabase(), true);
                sleepDetailDao.dropTable(daoManager.getDatabase(), true);
                sleepDao.createTable(daoManager.getDatabase(), true);
                SyncPrensenter.generationSeveralDayData(get_Date_yy(0),30);
                Intent refresh = new Intent(getApplicationContext(), this.getClass());
                startActivity(refresh);//Start the same Activity
                finish(); //finish Activity.
                break;
            }
        }
        return true;
    }

    public String get_Date(int day_to_today){
        final int ONEDAY=24*3600*1000;
        Date date= new Date(new Date().getTime() - day_to_today*ONEDAY);
        return new SimpleDateFormat("M/dd").format(date).toString();
    }

    public String get_Date_yy(int day_to_today){
        final int ONEDAY=24*3600*1000;
        Date date= new Date(new Date().getTime() - day_to_today*ONEDAY);
        return AsyncCall.dateFormatter.format(date).toString();
    }

}
