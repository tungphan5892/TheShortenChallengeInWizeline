package tungpt.wizelineremotechallenge.networks;

import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import tungpt.wizelineremotechallenge.App.WizelineApp;
import tungpt.wizelineremotechallenge.listeners.ApiServices;
import retrofit2.converter.gson.GsonConverterFactory;
import tungpt.wizelineremotechallenge.utils.Utils;
import tungpt.wizelineremotechallenge.views.activities.featuresactivity.TimelineActivity;

/**
 * Created by Tung Phan on 2/17/2017.
 */
@Module
public class RetroClient {
    private static final String TAG = TimelineActivity.class.getSimpleName();
    private static final int OFFLINE_EXPIRE_TIME_DAY = 7;
    private static final int EXPIRE_TIME_MINS = 2;
    private static final String CACHE_CONTROL = "cache_control";
    private static final int CACHE_SIZE = 10 * 1024 * 1024;
    private static final String HTTP_CACHE = "http_cache";
    private String baseUrl;

    public RetroClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    @Singleton
    Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(provideOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(provideOfflineCacheInterceptor())
                .addNetworkInterceptor(provideCacheInterceptor())
                .cache(provideCache())
                .build();
    }

    @Provides
    @Singleton
    Cache provideCache() {
        Cache cache = null;
        try {
            cache = new Cache(new File(WizelineApp.getInstance().getCacheDir(), HTTP_CACHE),
                    CACHE_SIZE); // 10 MB
        } catch (Exception e) {
            Log.e(TAG, "Could not create Cache!");
        }
        return cache;
    }

    public ApiServices getApiServices() {
        return getRetrofitInstance().create(ApiServices.class);
    }

    public static Interceptor provideCacheInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());
                CacheControl cacheControl = new CacheControl.Builder()
                        .maxAge(EXPIRE_TIME_MINS, TimeUnit.MINUTES)
                        .build();
                return response.newBuilder()
                        .header(CACHE_CONTROL, cacheControl.toString())
                        .build();
            }
        };
    }

    public static Interceptor provideOfflineCacheInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!WizelineApp.isConnectToNetwork()) {
                    CacheControl cacheControl = new CacheControl.Builder()
                            .maxStale(OFFLINE_EXPIRE_TIME_DAY, TimeUnit.DAYS)
                            .build();
                    request = request.newBuilder()
                            .cacheControl(cacheControl)
                            .build();
                }
                return chain.proceed(request);
            }
        };
    }

}
