package fr.sleeptight.data.acces.APIClient;

import android.util.Log;

import fr.sleeptight.data.traitement.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by User on 6/13/2016.
 * these methods static is write for app designer
 *
 */


public class AsyncCall {

    public static void resetCall(final String newName, final String newPassword) {
        APIService client = ServiceGenerator.createService(APIService.class);
        APIClass.SimpleUsr usr = new APIClass.SimpleUsr(newName, newPassword);
        Call<APIClass.ProfileResetting> call = client.ProfileReset(usr);
        call.enqueue(new Callback<APIClass.ProfileResetting>() {
            @Override
            public void onResponse(Call<APIClass.ProfileResetting> call, Response<APIClass.ProfileResetting> response) {
                if (response.isSuccessful()) {
                    // tasks available
                    APIClass.ProfileResetting profile = response.body();
                    User.getInstance().setPassword(newPassword);
                    User.getInstance().setUsername(newName);
                    Log.d("APIClass.ProfileResetting", profile.username + " / " + profile.pw_hash);
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
}
