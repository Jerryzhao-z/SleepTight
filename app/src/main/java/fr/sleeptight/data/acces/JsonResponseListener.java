package fr.sleeptight.data.acces;

import android.util.Log;
import com.yolanda.nohttp.OnResponseListener;
import com.yolanda.nohttp.Response;
import org.json.JSONException;
import org.json.JSONObject;
import fr.sleeptight.data.traitement.User;

/**
 * Created by zhengrui on 6/9/2016.
 */
public class JsonResponseListener implements OnResponseListener<JSONObject> {


    private User user;
    private String purpose;
    private int purposeNumber;

    public JsonResponseListener(User user, String purpose, int purposeNumber) {
        this.user = user;
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
                if (usernameReponse == this.user.getUsername())
                    this.user.setId(idReponse);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Log.d(purpose + ":", "User"+purpose+"succeeded");
    }

    @Override
    public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
        Log.d(purpose + ":", "User"+purpose+"Faied");
        Log.d(purpose+":Failed", exception.getMessage());
    }

    @Override
    public void onFinish(int what) {
        Log.d(purpose + ":", "User"+purpose+"Finished");
    }
}
