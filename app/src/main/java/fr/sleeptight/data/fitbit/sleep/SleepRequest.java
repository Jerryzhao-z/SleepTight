package fr.sleeptight.data.fitbit.sleep;

import org.json.JSONObject;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.OnResponseListener;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.RequestQueue;

import java.io.UnsupportedEncodingException;

import fr.sleeptight.data.acces.CONSTANT;
import fr.sleeptight.data.acces.JsonRequest;
import fr.sleeptight.data.acces.UserJsonResponseListener;
import fr.sleeptight.data.traitement.User;

/**
 * Created by User on 6/12/2016.
 */
public class SleepRequest {
    private static RequestQueue requestQueue = NoHttp.newRequestQueue();

    public void VerifyFitbitAccountInServer() throws UnsupportedEncodingException {
        User user = User.getInstance();
        if(user.getPassword() != null && user.getUsername() != null)
        {
            //json
            JsonRequest request = new JsonRequest("http://sleeptight2016.herokuapp.com/api/v1.0/users/", RequestMethod.GET);
            //authResponseListener = new JsonSignUpResponseListener(this);
            requestQueue.add(CONSTANT.VERIFYFITBIT, request, fitbitVerifyResponseListener);
        }
    }

    OnResponseListener<JSONObject> fitbitVerifyResponseListener = new SleepJsonResponseListener("VERIFYFITBIT", CONSTANT.VERIFYFITBIT);


}
