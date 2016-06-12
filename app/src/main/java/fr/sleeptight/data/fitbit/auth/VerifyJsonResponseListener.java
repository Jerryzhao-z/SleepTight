package fr.sleeptight.data.fitbit.auth;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import com.yolanda.nohttp.OnResponseListener;
import com.yolanda.nohttp.Response;

import fr.sleeptight.data.acces.CONSTANT;
import fr.sleeptight.data.traitement.User;
import fr.sleeptight.ui.ContextHolder;

/**
 * Created by User on 6/12/2016.
 */
public class VerifyJsonResponseListener implements OnResponseListener<JSONObject> {

    private User user;
    private String purpose = "VERIFYFITBIT";
    private int purposeNumber = CONSTANT.VERIFYFITBIT;

    public VerifyJsonResponseListener() {
        this.user = User.getInstance();
    }

    @Override
    public void onStart(int what) {
        Log.d(purpose + ":", "User " + purpose + " started");
    }


    @Override
    public void onSucceed(int what, Response<JSONObject> response) {
        if (what == this.purposeNumber) {
            try {
                String usernameReponse = response.get().getString("user");
                String logReponse = response.get().getString("log");
                Context context = ContextHolder.getContext();
                SharedPreferences sharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor
                        = sharedPreferences.edit();
                editor.putString("fitbit", logReponse);
                if (usernameReponse.equals(this.user.getUsername()) && (!logReponse.equals("unset"))) {
                    String fitbit_callback_code = response.get().getString("fitbit_callback_code");
                    String fitbit_access_token = response.get().getString("fitbit_access_token");
                    String fitbit_token_type = response.get().getString("fitbit_token_type");
                    String fitbit_refresh_token = response.get().getString("fitbit_refresh_token");
                    Log.d(purpose+":", " Succeed" );
                    Log.d(purpose+":",  fitbit_callback_code);
                    Log.d(purpose+":", fitbit_access_token);
                    Log.d(purpose+":", fitbit_token_type);
                    Log.d(purpose+":", fitbit_refresh_token);
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
