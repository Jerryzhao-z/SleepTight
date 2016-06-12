package fr.sleeptight.data.fitbit.sleep;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import com.yolanda.nohttp.OnResponseListener;
import com.yolanda.nohttp.Response;

import fr.sleeptight.data.traitement.User;
import fr.sleeptight.ui.ContextHolder;

/**
 * Created by User on 6/12/2016.
 */
public class SleepJsonResponseListener implements OnResponseListener<JSONObject> {

    private User user;
    private String purpose;
    private int purposeNumber;

    public SleepJsonResponseListener(User user, String purpose, int purposeNumber) {
        this.user = user;
        this.purpose = purpose;
        this.purposeNumber = purposeNumber;
    }

    public SleepJsonResponseListener(String purpose, int purposeNumber) {
        this.user = User.getInstance();
        this.purpose = purpose;
        this.purposeNumber = purposeNumber;
    }

    @Override
    public void onStart(int what) {
        Log.d(purpose + ":", "User " + purpose + " started");
    }


    @Override
    public void onSucceed(int what, Response<JSONObject> response) {
        if (what == this.purposeNumber) {
            try {
                String usernameReponse = response.get().getString("username");
                String idReponse = response.get().getString("id");
                if (usernameReponse.equals(this.user.getUsername())) {

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
