package fr.sleeptight.data.fitbit.sleep;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.yolanda.nohttp.OnResponseListener;
import com.yolanda.nohttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import fr.sleeptight.data.acces.CONSTANT;
import fr.sleeptight.data.localdb.Sleep;
import fr.sleeptight.data.traitement.User;
import fr.sleeptight.ui.ContextHolder;

/**
 * Created by User on 6/12/2016.
 */
public class SleepJsonResponseListener implements OnResponseListener<JSONObject> {

        private User user;
        private String purpose = "GETSLEEP";
        private int purposeNumber = CONSTANT.GETSLEEP;
        private int year;
        private int month;
        private int day;

        public SleepJsonResponseListener(int year, int month, int day) {
            this.user = User.getInstance();
            this.year = year;
            this.month = month;
            this.day = day;
        }

        @Override
        public void onStart(int what) {
            Log.d(purpose + ":", "User " + purpose + " started");
        }


        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
            if (what == this.purposeNumber) {
                try {
                    JSONArray sleeplogs = response.get().getJSONArray("sleeptestlist");
                    for(int index=0; index<sleeplogs.length(); index++)
                    {
                        JSONObject sleeplog = sleeplogs.getJSONObject(index);
                        Date startTime = new Date(sleeplog.getString("startTime"));
                        int duration = sleeplog.getInt("duration");
                        int awakeDuration = sleeplog.getInt("awakeDuration");
                        int reslessDuration = sleeplog.getInt("restlessDuration");
                        int efficiency = sleeplog.getInt("efficiency");
                        Log.d(purpose+": ","StartTime is"+startTime.toString()+" duration is "+Integer.toString(duration)+
                                " awakeDuration is "+Integer.toString(awakeDuration)+" restlessDuration is "+Integer.toString(reslessDuration)+
                                " efficiency is "+efficiency);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }

        @Override
        public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
            Log.d(purpose + ":", "User "+purpose+" Faied");
            Log.d(purpose+":Failed", exception.getMessage());
        }

        @Override
        public void onFinish(int what) {
            Log.d(purpose + ":", "User"+purpose+"Finished");
        }
}
