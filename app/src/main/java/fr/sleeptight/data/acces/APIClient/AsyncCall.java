package fr.sleeptight.data.acces.APIClient;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import fr.sleeptight.data.localdb.CommitUtils;
import fr.sleeptight.data.localdb.Sleep;
import fr.sleeptight.data.localdb.SleepDetail;
import fr.sleeptight.data.traitement.User;
import fr.sleeptight.ui.ContextHolder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by User on 6/13/2016.
 * these methods static is write for app designer
 *
 */


public class AsyncCall {
    public static String DateTimePattern = "yyyy-MM-dd'T'HH:mm:ss.SSS";
    public static String DatePattern = "yyyy-MM-dd";
    public static String TimePattern = "HH:mm:ss";
    public static SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(DateTimePattern);
    public static SimpleDateFormat dateFormatter = new SimpleDateFormat(DatePattern);
    public static SimpleDateFormat timeFormatter = new SimpleDateFormat(TimePattern);

    public static void resetCall(final String newName, final String newPassword) {
        APIService client = ServiceGenerator.createService(APIService.class);
        APIClass.SimpleUsr usr = new APIClass.SimpleUsr(newName, newPassword);
        Call<APIClass.ProfileResetting> call = client.profileReset(usr);
        call.enqueue(new Callback<APIClass.ProfileResetting>() {
            @Override
            public void onResponse(Call<APIClass.ProfileResetting> call, Response<APIClass.ProfileResetting> response) {
                if (response.isSuccessful()) {
                    // tasks available
                    APIClass.ProfileResetting profile = response.body();
                    User.getInstance().setPassword(newPassword);
                    User.getInstance().setUsername(newName);
                    Context context = ContextHolder.getContext();
                    SharedPreferences sharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor
                            = sharedPreferences.edit();
                    editor.remove("username");
                    editor.remove("password");
                    editor.putString("username", User.getInstance().getUsername());
                    editor.putString("password", User.getInstance().getPassword());
                    editor.commit();
                    Log.d("USER", User.getInstance().getUsername() + " / " + User.getInstance().getPassword());
                } else {
                    // error response, no access to resource?
                    Log.d("APIClass.ProfileResetting", response.message());

                }

            }

            @Override
            public void onFailure(Call<APIClass.ProfileResetting> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

    public static void getSleepCall(int year, int month, int day)
    {
        APIService client = ServiceGenerator.createService(APIService.class);
        Call<List<APIClass.SleepData>> call =  client.getSleepLogTest(year,month,day);
        call.enqueue(new Callback<List<APIClass.SleepData>>() {
            @Override
            public void onResponse(Call<List<APIClass.SleepData>> call, Response<List<APIClass.SleepData>> response) {
                if (response.isSuccessful()) {
                    // tasks available
                    List<APIClass.SleepData> sleeplogs = response.body();
                    CommitUtils commitUtils = new CommitUtils(ContextHolder.getContext());

                    for(APIClass.SleepData sleep: sleeplogs)
                    {
                        Date startTime = null;
                        Date dateofSleep = null;
                        try {
                            startTime = dateTimeFormatter.parse(sleep.startTime);
                            dateofSleep = dateFormatter.parse(sleep.dateOfSleep);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        Sleep sleepobject = new Sleep(null, startTime,
                                sleep.restlessDuration, sleep.efficiency, sleep.awakeCount, sleep.awakeningsCount,
                                sleep.awakeDuration, dateofSleep, sleep.duration, sleep.isMainSleep, sleep.minutesAfterWakeup,
                                sleep.minutesAwake, sleep.minutesAsleep, sleep.minutesToFallAsleep, sleep.restlessCount, sleep.timeInBed);
                        List<Sleep> sleepsInData = commitUtils.QuerySleepSpecifiqueTime(startTime,dateofSleep);
                        if(sleepsInData.size()>0)
                        {
                            for(Sleep sleeplog: sleepsInData)
                            {
                                List<SleepDetail> sleepDetails = commitUtils.ListSleepDetailsOfSleep(sleeplog.getId());
                                for(SleepDetail sleepDetail: sleepDetails)
                                {
                                    commitUtils.deleteSleepDetail(sleepDetail);
                                }
                                commitUtils.deleteSleep(sleeplog);
                            }
                        }
                        commitUtils.insertSleep(sleepobject);
                        Log.d("APIClass.SleepData", "dateofSleep: "+sleep.dateOfSleep);
                        Log.d("APIClass.SleepData", "minutesAwake: "+sleep.minutesAwake);
                        Log.d("APIClass.SleepData", "startTime: "+sleep.startTime);
                        Log.d("APIClass.SleepData", "isMainSleep: "+sleep.isMainSleep);
                        for(String time: sleep.dateTimeStateReallyAwake)
                        {
                            Date timeInDate = null;
                            try {
                                timeInDate = timeFormatter.parse(time);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            if(timeInDate != null) {
                                commitUtils.insertSleepDetail(new SleepDetail(
                                        null, SleepDetail.REALLYAWAKE, timeInDate, sleepobject.getId()));
                                Log.d("APIClass.SleepData", "dateTimeStateReallyAwake: " + time);
                            }
                        }
                        for(String time: sleep.dateTimeStateAwake)
                        {
                            Date timeInDate = null;
                            try {
                                timeInDate = timeFormatter.parse(time);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            if(timeInDate != null) {
                                commitUtils.insertSleepDetail(new SleepDetail(
                                        null, SleepDetail.AWAKE, timeInDate, sleepobject.getId()));
                                Log.d("APIClass.SleepData", "dateTimeStateAwake: " + time);
                            }
                        }
                    }

                } else {
                    // error response, no access to resource?
                    Log.d("APIClass.SleepData", response.message());

                }
            }

            @Override
            public void onFailure(Call<List<APIClass.SleepData>> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }

        });
    }

    public static void setProfileCall()
    {
        User user = User.getInstance();
        APIService client = ServiceGenerator.createService(APIService.class);
        Call<APIClass.ReponseProfile> call = client.setProfile(new APIClass.Profile(user.getGender(), user.getAge()));
        call.enqueue(new Callback<APIClass.ReponseProfile>() {
                         @Override
                         public void onResponse(Call<APIClass.ReponseProfile> call, Response<APIClass.ReponseProfile> response) {
                             if (response.isSuccessful()) {
                                 // tasks available
                                 APIClass.ReponseProfile reponseProfile = response.body();
                                 if(reponseProfile.returns.equals("invalide"))
                                     return;
                                 Context context = ContextHolder.getContext();
                                 SharedPreferences sharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
                                 SharedPreferences.Editor editor
                                         = sharedPreferences.edit();
                                 if(User.getInstance().getGender())
                                    editor.putString("gender", "F");
                                 else
                                    editor.putString("gender", "M");
                                 editor.putString("age", Integer.toString(User.getInstance().getAge()));
                                 editor.commit();
                             } else {
                                 // error response, no access to resource?
                                 Log.d("APIClass.ProfileResetting", response.message());

                             }
                         }

                         @Override
                         public void onFailure(Call<APIClass.ReponseProfile> call, Throwable t) {
                             Log.d("Error", t.getMessage());
                         }
                     });


    }
}
