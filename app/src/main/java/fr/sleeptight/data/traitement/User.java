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

import fr.sleeptight.data.acces.*;

/**
 * Created by Zhengrui on 2016/5/13.
 */
public class User {
    private static User currentUser;

    private String password;
    private String id;
    private String userName;
    private EventList recentEvents;

    private static RequestQueue requestQueue = NoHttp.newRequestQueue();
    private User(){}

    private User(String userName, String pw)
    {
        this.setUsername(userName)
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
        this.userName = username;
        return this;
    }

    public User setPassword(String pw)
    {
        this.password = pw;
        return this;
    }

    public User signup() // = sign up in server
    {
        try {
            this.createAccountInServer();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }finally {
            return this;
        }
    }

    public User login() // = sign up in server
    {
        try {
            this.loginInServer();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }finally {
            return this;
        }
    }

    public User setId(String id)
    {
        this.id = id;
        return this;
    }

    //getter
    public String getUsername()
    {
        if(this.userName == null)
            return "unset";
        return this.userName;
    }

    public String getPassword()
    {
        if(this.password == null)
            return "unset";
        return this.password;
    }

    public String getId() // = sign up in server
    {
        if(this.id == null)
            return "unset";
        return this.id;
    }

    /* create an account, get generated id from server
     * if the user is anonyme, create AccountInserver
     * will return null, otherwise, the id generated.
     */
    public User createAccountInServer() throws UnsupportedEncodingException {
        if(this.password != null && this.userName != null)
        {
            //json
            SignupFormat user = new SignupFormat(this.userName, this.password);
            String json_request = JSON.toJSONString(user);
            JsonRequest request = new JsonRequest("http://sleeptight2016.herokuapp.com/api/v1.0/users/", RequestMethod.POST);
            request.setRequestBody(json_request.getBytes(NoHttp.CHARSET_UTF8));
            request.setContentType("application/json");
            //authResponseListener = new JsonSignUpResponseListener(this);
            requestQueue.add(CONSTANT.SIGNUP, request, authResponseListener);
        }
        return this;
    }

    OnResponseListener<JSONObject> authResponseListener = new JsonResponseListener(this, "SIGNUP", CONSTANT.SIGNUP);

    /*class JsonSignUpResponseListener implements OnResponseListener<JSONObject>
    {
        private User user;

        public JsonSignUpResponseListener(User user)
        {
            this.user = user;
        }

        @Override
        public void onStart(int what)
        {
            Log.d("SIGNUP:", "User sign up started");
        }


        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
            if(what == CONSTANT.SIGNUP) {
                try {
                    String usernameReponse = response.get().getString("username");
                    String idReponse = response.get().getString("id");
                    if (usernameReponse == this.user.userName)
                        this.user.setId(idReponse);
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
    }*/


    /*login methods
     *return id of user in server
     *
     */
    public User loginInServer() throws UnsupportedEncodingException {
        if(this.password != null && this.userName != null)
        {
            //json
            SignupFormat user = new SignupFormat(this.userName, this.password);
            String json_request = JSON.toJSONString(user);
            JsonRequest request = new JsonRequest("http://sleeptight2016.herokuapp.com/api/v1.0/users/login", RequestMethod.POST);
            request.setRequestBody(json_request.getBytes(NoHttp.CHARSET_UTF8));
            request.setContentType("application/json");
            //loginResponseListener = new JsonLoginResponseListener(this);
            requestQueue.add(CONSTANT.LOGIN, request, loginResponseListener);
        }
        return this;
    }

    OnResponseListener<JSONObject> loginResponseListener = new JsonResponseListener(this, "LOGIN", CONSTANT.LOGIN);


/*
    class JsonLoginResponseListener implements OnResponseListener<JSONObject>
    {
        private User user;

        public JsonLoginResponseListener(User user)
        {
            this.user = user;
        }

        @Override
        public void onStart(int what)
        {
            Log.d("LOGIN:", "User login started");
        }


        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
            if(what == CONSTANT.LOGIN) {
                try {
                    String usernameReponse = response.get().getString("username");
                    String idReponse = response.get().getString("id");
                    if (usernameReponse == this.user.userName)
                        this.user.setId(idReponse);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            Log.d("LOGIN:", "User login succeeded");

        }

        @Override
        public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
            Log.d("LOGIN:", "User login Faied");
            Log.d("LOGIN:Failed", exception.getMessage());
        }

        @Override
        public void onFinish(int what) {
            Log.d("LOGIN:", "User login finished");
        }
    }
*/

    //TODO: verifier s'il existe son id dans la base de donnée
    public boolean exists()
    {
        if(id != null)
            return true;
        else
            return false;
    }

    public User setRecentEvent(EventList recentEvents)
    {
        this.recentEvents = recentEvents;
        return this;
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

