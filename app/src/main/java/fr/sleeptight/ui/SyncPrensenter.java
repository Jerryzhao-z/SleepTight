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
import fr.sleeptight.data.traitement.User;

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
    public static final int DURATION = 0x0001;//unite hour
    public static final int SLEEPTIME = 0x0002;//睡眠时间 , unite minute
    public static final int AWAKEDURATION = 0x0003;//清醒时间, unite minute
    public static final int RESTLESSDURATION = 0x0004;//浅睡眠, unite minute
    public static float getDataOfDay(String dateString, int dataType)
    {

        Date date = null;
        try {
            date = AsyncCall.dateFormatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //Date date = dateTransfert(dateToTransfert);
        CommitUtils commitUtils = new CommitUtils(ContextHolder.getContext());
        float longeur = 0;
        for(Sleep sleep: commitUtils.QuerySleepSpecifitqueDay(date))
        {
            switch(dataType) {
                case DURATION:
                    longeur = longeur + sleep.getDuration()/3600000;
                    break;
                case SLEEPTIME:
                    longeur = longeur + sleep.getMinutesAsleep();
                    break;
                case AWAKEDURATION:
                    longeur = longeur + sleep.getAwakeDuration();
                    break;
                case RESTLESSDURATION:
                    longeur = longeur + sleep.getRestlessDuration();
                    break;
                default:
                    break;
            }
        }
        Log.d("Sync", dateString + Float.toString(longeur));
        return longeur;
    }

    public static float getEvaluation(String date)
    {
        if(!User.isUserLoggedIn())
            return 0;
        else{
            User user = User.getInstance();
            float timeToSleep = 0;
            if(user.getGender())
            {//woman
                if(user.getAge()>45)
                    timeToSleep = (float) 7.5;
                else
                    timeToSleep = 8;
            }else
            {//man
                if(user.getAge()<18)
                    timeToSleep = 8;
                else if(user.getAge()>45)
                    timeToSleep = (float) 6.5;
                else
                    timeToSleep = 7;
            }
            float longeur = getDataOfDay(date, SyncPrensenter.SLEEPTIME);
            if(timeToSleep == 0)
                return longeur/timeToSleep;
            else
                return 0;
        }
    }

}
