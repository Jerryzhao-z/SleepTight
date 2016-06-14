package fr.sleeptight.data.acces.APIClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by User on 6/13/2016.
 */
public interface APIService {

    @POST("users/reset")
    Call<APIClass.ProfileResetting> profileReset(@Body APIClass.SimpleUsr usr);

    @GET("users/fitbit/sleeps/testdata/{year}/{month}/{day}")
    Call<List<APIClass.SleepData>> getSleepLogTest(@Path("year") int year, @Path("month") int month, @Path("day") int day);

   // @GET("test/fitbit")

    @POST("users/set")
    Call<APIClass.ReponseProfile> setProfile(@Body APIClass.Profile profile);
}
