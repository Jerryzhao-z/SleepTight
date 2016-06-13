package fr.sleeptight.data.fitbit.sleep;

import android.util.Base64;
import android.util.Log;

import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.OnResponseListener;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.RequestQueue;

import java.io.UnsupportedEncodingException;

import fr.sleeptight.data.acces.AuthFormat;
import fr.sleeptight.data.acces.CONSTANT;
import fr.sleeptight.data.acces.JsonRequest;
import fr.sleeptight.data.acces.SignupFormat;
import fr.sleeptight.data.fitbit.auth.VerifyJsonResponseListener;
import fr.sleeptight.data.traitement.User;

/**
 * Created by User on 6/12/2016.
 */
public class SleepRequest {
    private static RequestQueue requestQueue = NoHttp.newRequestQueue();

    public static void VerifyFitbitAccount() throws UnsupportedEncodingException {
        User user = User.getInstance();
        if(user.getPassword() != null && user.getUsername() != null)
        {
            //json
            AuthFormat authFormat = new AuthFormat(user.getUsername(), user.getPassword(), user.getId());
            String json_request = JSON.toJSONString(authFormat);
            JsonRequest request = new JsonRequest("http://sleeptight2016.herokuapp.com/api/v1.0/test/fitbit/get", RequestMethod.POST);
            request.setRequestBody(json_request.getBytes(NoHttp.CHARSET_UTF8));
            request.setContentType("application/json");
            OnResponseListener<JSONObject> VerifyResponseListener = new VerifyJsonResponseListener();
            requestQueue.add(CONSTANT.GETSLEEP, setAuthHeader(request), VerifyResponseListener);

        }
    }

    public static void getfitbitToken() // = sign up in server
    {
        try {
            SleepRequest.VerifyFitbitAccount();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void getSleepLog(int year, int month, int day) // = sign up in server
    {
        try {
            SleepRequest.SleeplogFitbit(year, month, day);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void SleeplogFitbit(int year, int month, int day) throws UnsupportedEncodingException {
        User user = User.getInstance();
        if(user.getPassword() != null && user.getUsername() != null)
        {
            //json
            JsonRequest request = new JsonRequest("http://sleeptight2016.herokuapp.com/api/v1.0/users/fitbit/sleeps/testdata/"
                    +Integer.toString(year)+"/"+Integer.toString(month)+"/"+Integer.toString(day), RequestMethod.GET);
            OnResponseListener<JSONObject> SleeplogResponseListener = new SleepJsonResponseListener(year, month, day);
            requestQueue.add(CONSTANT.GETSLEEP, setAuthHeader(request), SleeplogResponseListener);


        }
    }

    public static JsonRequest setAuthHeader(JsonRequest request)
    {
        User user = User.getInstance();
        String auth = user.getUsername()+":"+user.getUsername();
        request.addHeader("Authorization", "Basic "+ Base64.encodeToString(auth.getBytes(), Base64.URL_SAFE | Base64.NO_WRAP));
        Log.d("authorization:",Base64.encodeToString(auth.getBytes(), Base64.URL_SAFE | Base64.NO_WRAP));
        return request;
    }

}
