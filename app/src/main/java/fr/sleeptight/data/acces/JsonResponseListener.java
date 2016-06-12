package fr.sleeptight.data.acces;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.yolanda.nohttp.OnResponseListener;
import com.yolanda.nohttp.Response;
import org.json.JSONException;
import org.json.JSONObject;
import fr.sleeptight.data.traitement.User;
import fr.sleeptight.ui.ContextHolder;

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
                if (usernameReponse.equals(this.user.getUsername())) {
                    this.user.setId(idReponse);
                    //
                    Context context = ContextHolder.getContext();
                    SharedPreferences sharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor
                            = sharedPreferences.edit();
                    editor.putString("username", user.getUsername());
                    editor.putString("password", user.getPassword());
                    editor.putString("id", user.getId());
                    editor.commit();

                    //lecture
                    String name = sharedPreferences.getString("username", "");
                    String pw = sharedPreferences.getString("password", "");
                    String id = sharedPreferences.getString("id","");
                    Log.d(purpose + ":", "UserUtils " + purpose + " succeeded");
                    Log.d(purpose + ":", "read from sharedpreference, username:"+name);
                    Log.d(purpose + ":", "read from sharedpreference, pw:"+pw);
                    Log.d(purpose + ":", "read from sharedpreference, id:"+id);
                    //
                    Log.d(purpose + ":", "User"+purpose+"succeeded");
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
