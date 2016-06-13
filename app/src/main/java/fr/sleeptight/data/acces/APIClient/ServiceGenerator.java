package fr.sleeptight.data.acces.APIClient;

import java.io.IOException;

import fr.sleeptight.data.traitement.User;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by User on 6/13/2016.
 */
public class ServiceGenerator {

    public static final String API_BASE_URL = "http://sleeptight2016.herokuapp.com/api/v1.0/";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request.Builder ongoing = chain.request().newBuilder();
            ongoing.addHeader("Accept", "application/json;versions=1");
            if (User.isUserLoggedIn()) {
                ongoing.addHeader("Authorization", User.getToken());
            }
            return chain.proceed(ongoing.build());
        }
    });

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }
}
