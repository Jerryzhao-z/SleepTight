package fr.sleeptight.ui;

import android.text.format.Time;
import android.util.Log;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import fr.sleeptight.data.acces.APIClient.AsyncCall;
import fr.sleeptight.data.localdb.CommitUtils;
import fr.sleeptight.data.localdb.DaoManager;
import fr.sleeptight.data.localdb.DaoMaster;
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

    public static void getSleepOfWeek(Date datetime)
    {
        Calendar date = Calendar.getInstance();
        date.setTime(datetime);
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
    public static float getDataOfDay(String dateString, int dataType) {

        Date date = null;
        try {
            date = AsyncCall.dateFormatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //Date date = dateTransfert(dateToTransfert);
        CommitUtils commitUtils = new CommitUtils(ContextHolder.getContext());
        float longeur = 0;
        for (Sleep sleep : commitUtils.QuerySleepSpecifitqueDay(date)) {
            switch (dataType) {
                case DURATION:
                    longeur += sleep.getDuration();
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
        if (dataType == DURATION)
            longeur /= 3600000;

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
            if(timeToSleep != 0)
                return longeur/timeToSleep;
            else
                return 0;
        }
    }

    public static void generationSeveralDayData(String datetime, int nombre)
    {
        Date date = null;
        try {
            date = AsyncCall.dateFormatter.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if(User.isUserLoggedIn())
            generationTestData(AsyncCall.dateFormatter.format(date).toString()
                , AsyncCall.dateTimeFormatter.format(date).toString());
        else
            generationAllData(AsyncCall.dateFormatter.format(date).toString()
                , AsyncCall.dateTimeFormatter.format(date).toString());
        for(int i=0; i<nombre-1; i++) {
            calendar.add(Calendar.DATE, -1);
            Date thisDate = new Date(calendar.getTimeInMillis());
            if(User.isUserLoggedIn())
                generationTestData(AsyncCall.dateFormatter.format(thisDate).toString()
                    , AsyncCall.dateTimeFormatter.format(thisDate).toString());
            else
                generationAllData(AsyncCall.dateFormatter.format(thisDate).toString()
                    , AsyncCall.dateTimeFormatter.format(thisDate).toString());
        }
    }

    public static void generationTestData(String dateofSleepStr, String startTimeStr)
    {
        Date startTime = null;
        Date dateOfSleep = null;
        try {
            startTime = AsyncCall.dateTimeFormatter.parse(startTimeStr);
            dateOfSleep = AsyncCall.dateFormatter.parse(dateofSleepStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int restlessDuration = 30 + (int)(Math.random()*10) - (int)(Math.random()*10);
        int awakeCount = 2 ;
        int awakeningsCount = 3;
        int awakeDuration = 10 + (int)(Math.random()*5) - (int)(Math.random()*5);
        boolean isMainSleep = Boolean.TRUE;
        int minutesAfterWakeup = 0;
        int minutesAwake = awakeDuration + restlessDuration;
        int minutesAsleep = 375 + (int)(Math.random()*50) - (int)(Math.random()*50);
        int minutesToFallAsleep = 0;
        int restlessCount = 36;
        int timeInBed = minutesAfterWakeup+minutesAwake+minutesAsleep+minutesToFallAsleep;
        int efficiency = 100*minutesAsleep/timeInBed;
        int duration = timeInBed*60000;

        Sleep sleepobject = new Sleep(null, startTime,
                restlessDuration, efficiency, awakeCount, awakeningsCount,
                awakeDuration, dateOfSleep, duration, isMainSleep, minutesAfterWakeup,
                minutesAwake, minutesAsleep, minutesToFallAsleep, restlessCount, timeInBed);
        CommitUtils commitUtils = new CommitUtils(ContextHolder.getContext());
        List<Sleep> sleepsInData = commitUtils.QuerySleepSpecifiqueTime(startTime,dateOfSleep);
        if(sleepsInData.size() > 0)
            return;
        commitUtils.insertSleep(sleepobject);
    }

    public static void generationAllData(String dateofSleepStr, String startTimeStr)
    {
        Date startTime = null;
        Date dateOfSleep = null;
        try {
            startTime = AsyncCall.dateTimeFormatter.parse(startTimeStr);
            dateOfSleep = AsyncCall.dateFormatter.parse(dateofSleepStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int restlessDuration = 30 + (int)(Math.random()*10) - (int)(Math.random()*10);
        int awakeCount = 2 ;
        int awakeningsCount = 3;
        int awakeDuration = 10 + (int)(Math.random()*5) - (int)(Math.random()*5);
        boolean isMainSleep = Boolean.TRUE;
        int minutesAfterWakeup = 0;
        int minutesAwake = awakeDuration + restlessDuration;
        int minutesAsleep = 375 + (int)(Math.random()*50) - (int)(Math.random()*50);
        int minutesToFallAsleep = 0;
        int restlessCount = 36;
        int timeInBed = minutesAfterWakeup+minutesAwake+minutesAsleep+minutesToFallAsleep;
        int efficiency = 100*minutesAsleep/timeInBed;
        int duration = timeInBed*60000;

        Sleep sleepobject = new Sleep(null, startTime,
                restlessDuration, efficiency, awakeCount, awakeningsCount,
                awakeDuration, dateOfSleep, duration, isMainSleep, minutesAfterWakeup,
                minutesAwake, minutesAsleep, minutesToFallAsleep, restlessCount, timeInBed);
        CommitUtils commitUtils = new CommitUtils(ContextHolder.getContext());
        List<Sleep> sleepsInData = commitUtils.QuerySleepSpecifiqueTime(startTime,dateOfSleep);
        for(Sleep sleep: sleepsInData)
            commitUtils.deleteSleep(sleep);
        commitUtils.insertSleep(sleepobject);
    }

    public static void DropAllDB()
    {
        DaoManager daoManager = DaoManager.getInstance();
        DaoMaster master = daoManager.getDaoMaster();
        master.dropAllTables(daoManager.getDatabase(), true);
        master.createAllTables(daoManager.getDatabase(), true);
    }
}
