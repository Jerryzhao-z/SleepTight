package fr.sleeptight.ui;

import android.text.format.Time;
import android.util.Log;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
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
        Log.d("Envoyer Request first", date.toString());
        AsyncCall.getSleepCall(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH));
        for(int i=0; i<6; i++) {
            Log.d("Date ", Integer.toString(Calendar.DATE));
            date.add(Calendar.DATE, -1);
            Log.d("Envoyer Request", date.toString());
            AsyncCall.getSleepCall(date.get(Calendar.YEAR), date.get(Calendar.MONTH)+1, date.get(Calendar.DAY_OF_MONTH));
        }
    }


    //get sleeping hours of a specified day
    public static float getDurationOfDay(String dateString)
    {

        Date date = null;
        try {
            date = AsyncCall.dateFormatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //Date date = dateTransfert(dateToTransfert);
        CommitUtils commitUtils = new CommitUtils(ContextHolder.getContext());
        float duration = 0;
        for(Sleep sleep: commitUtils.QuerySleepSpecifitqueDay(date))
        {
            duration += sleep.getDuration();
        }
        duration /= 3600000;
        Log.d("Sync", dateString + Float.toString(duration));
        return duration;
    }

    public static Date dateTransfert(Date dateToTransfert)
    {
        return dateToTransfert;
    }
}
