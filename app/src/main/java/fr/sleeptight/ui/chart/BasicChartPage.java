package fr.sleeptight.ui.chart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import fr.sleeptight.R;
import fr.sleeptight.data.localdb.CommitUtils;
import fr.sleeptight.data.localdb.Sleep;
import fr.sleeptight.data.localdb.SleepDetail;
import fr.sleeptight.ui.BasicPage;
import fr.sleeptight.ui.ContextHolder;

public class BasicChartPage extends BasicPage {


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
                // get data from database sqlite
                if(ContextHolder.getContext() != null ) {
                    CommitUtils sql = new CommitUtils(ContextHolder.getContext());
                    List<Sleep> sleeps = sql.ListAllSleep();
                    List<SleepDetail> sleepDetails = sql.ListAllSleepDetail();
                    for (Sleep sleep : sleeps) {
                        Log.d(sleep.getDateOfSleep().toString(), Long.toString(sleep.getId()));
                        for (SleepDetail sleepDetail : sleepDetails)
                            Log.d(Long.toString(sleepDetail.getSleepId()), sleepDetail.getTime().toString());
                    }
                    break;
                }else
                {
                    Log.v("BasicChartPage", ContextHolder.getContext().toString());
                }
            }
        }
        return true;
    }
}
