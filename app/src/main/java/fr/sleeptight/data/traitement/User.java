package fr.sleeptight.data.traitement;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.yolanda.nohttp.JsonObjectRequest;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.OnResponseListener;
import com.yolanda.nohttp.Request;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.RequestQueue;
import com.yolanda.nohttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import fr.sleeptight.data.acces.JsonRequest;
import fr.sleeptight.data.acces.SignupFormat;
import fr.sleeptight.data.acces.SignupResponseFormat;

/**
 * Created by Zhengrui on 2016/5/13.
 */
public class User {
    private static User currentUser;

    private String password;
    private String id;
    private String userName;
    private EventList recentEvents;

    private RequestQueue requestQueue = NoHttp.newRequestQueue();
    private static final int SIGNUP = 0x0001;

    private User(){}

    private User(String userName, String pw)
    {
        currentUser = new User();
        currentUser.setUsername(userName)
            .setPassword(pw);
    }

    public static synchronized User getInstance()
    {
        if(currentUser == null){
            currentUser = new User();
        }
        return currentUser;
    }

    public static synchronized User getInstance(String userName, String pw)
    {
        currentUser = new User(userName, pw);
        return currentUser;
    }

    // setters for properties
    public User setUsername(String username)
    {
        currentUser.userName = username;
        return currentUser;
    }

    public User setPassword(String pw)
    {
        currentUser.password = pw;
        return currentUser;
    }

    public User setId()
    {
        try {
            currentUser.createAccountInServer();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }finally {
            return currentUser;
        }
    }

    /* create an account, get generated id from server
     * if the user is anonyme, create AccountInserver
     * will return null, otherwise, the id generated.
     */
    private User createAccountInServer() throws UnsupportedEncodingException {
        if(password != null && userName != null)
        {
            //json
            SignupFormat user = new SignupFormat(currentUser.userName, currentUser.password);
            String json_request = JSON.toJSONString(user);
            JsonRequest request = new JsonRequest("http://sleeptight2016.herokuapp.com/api/v1.0/users/", RequestMethod.POST);
            request.setRequestBody(json_request.getBytes(NoHttp.CHARSET_UTF8));
            request.setContentType("application/json");

            requestQueue.add(SIGNUP, request, authResponseListener);
        }
        return this;
    }

    private OnResponseListener<JSONObject> authResponseListener = new OnResponseListener<JSONObject>()
    {
        @Override
        public void onStart(int what)
        {
            Log.d("SIGNUP:", "User sign up started");
        }


        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
            if(what == SIGNUP) {
                try {
                    String usernameReponse = response.get().getString("username");
                    String idReponse = response.get().getString("id");
                    if (usernameReponse == currentUser.userName)
                        currentUser.id = idReponse;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            Log.d("SIGNUP:", "User sign up succeeded");

        }

        @Override
        public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
            Log.d("SIGNUP:", "User sign up Faied");
            Log.d("SIGNUP:Failed", exception.getMessage());
        }

        @Override
        public void onFinish(int what) {
            Log.d("SIGNUP:", "User sign up finished");
        }
    };


    //TODO: verifier s'il existe son id dans la base de donnée
    public boolean exists()
    {
        return true;
    }

    public User setRecentEvent(EventList recentEvents)
    {
        currentUser.recentEvents = recentEvents;
        return currentUser;
    }

    public EventList getRecentEvents()
    {
        return this.recentEvents;
    }
    //TODO: getEvent depuis begin jusqu'à end from Server
    public EventList getEvents(Date begin, Date end)
    {
        return new EventList();
    }
    //TODO: find a usr with userid
    public static User findByUserName()
    {
        return null;
    }
}
