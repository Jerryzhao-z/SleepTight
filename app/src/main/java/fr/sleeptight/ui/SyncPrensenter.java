package fr.sleeptight.ui;

import android.text.format.Time;
import android.util.Log;

import java.util.Calendar;
import java.util.List;

import fr.sleeptight.data.acces.APIClient.AsyncCall;
import fr.sleeptight.data.localdb.CommitUtils;
import fr.sleeptight.data.localdb.Sleep;
import fr.sleeptight.data.localdb.SleepDetail;

/**
 * Created by User on 6/14/2016.
 */
public class SyncPrensenter {

    public static void listAllSleepData() {
        if (ContextHolder.getContext() != null) {
            CommitUtils sql = new CommitUtils(ContextHolder.getContext());
            List<Sleep> sleeps = sql.ListAllSleep();
            for (Sleep sleep : sleeps) {
                Log.d(sleep.getDateOfSleep().toString(), Long.toString(sleep.getId()));
                List<SleepDetail> sleepDetails = sql.ListSleepDetailsOfSleep(sleep.getId());
                for (SleepDetail sleepDetail : sleepDetails)
                    Log.d(Long.toString(sleepDetail.getSleepId()), sleepDetail.getTime().toString());
            }
        } else {
            Log.v("BasicChartPage", ContextHolder.getContext().toString());
        }
    }


    public static void getSleepOfWeek()
    {
        Calendar date = Calendar.getInstance();
        for(int i=0; i>(-7); i--) {
            date.add(Calendar.DATE, i);
            Log.d("Envoyer Request", date.toString());
            AsyncCall.getSleepCall(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH));
        }
    }

}
