package fr.sleeptight.data.acces;

//import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
/**
 * Created by User on 6/13/2016.
 */
public class ServiceGenerator {
    public static final String API_BASE_URL = "http://sleeptight2016.herokuapp.com/api/v1.0";

/*    private static OkHttpClient httpClient = new OkHttpClient();
    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static S createService(Class serviceClass) {
        Retrofit retrofit = builder.client(httpClient).build();
        return retrofit.create(serviceClass);
    }*/
}
