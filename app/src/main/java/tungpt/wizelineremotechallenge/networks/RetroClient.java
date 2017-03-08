package tungpt.wizelineremotechallenge.networks;

import retrofit2.Retrofit;
import tungpt.wizelineremotechallenge.listeners.ApiServices;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tung Phan on 2/17/2017.
 */

public class RetroClient {
    private static final String ROOT_URL = "https://wizetwitterproxy.herokuapp.com";

    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiServices getApiServices() {
        return getRetrofitInstance().create(ApiServices.class);
    }

}
