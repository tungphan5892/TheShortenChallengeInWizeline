package tungpt.wizelineremotechallenge.App;

import android.app.Application;
import android.content.Context;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import tungpt.wizelineremotechallenge.R;
import tungpt.wizelineremotechallenge.dagger.components.AppComponent;
import tungpt.wizelineremotechallenge.dagger.components.DaggerAppComponent;
import tungpt.wizelineremotechallenge.dagger.modules.AppModule;
import tungpt.wizelineremotechallenge.networks.RetroClient;

/**
 * Created by tungphan on 3/10/17.
 */

public class WizelineApp extends Application {
    private AppComponent appComponent;
    private static WizelineApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public static WizelineApp getInstance() {
        return instance;
    }

    public static boolean isConnectToNetwork() {
        return instance.checkIfHasNetwork();
    }

    public boolean checkIfHasNetwork() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    public static int getScreenWidth() {
        return instance.sreenWidth();
    }

    public int sreenWidth() {
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }


    public float convertDpToPixel(float dp) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return dp * ((float) displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public float convertPixelsToDp(float pixel) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return pixel / ((float) displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public int profileImageSize() {
        return (int) getResources().getDimension(R.dimen.profile_image_size);
    }

    public int getPixelFromResources(int id){
        return (int) getResources().getDimension(id);
    }

    public static int getProfileImageSize(){
        return instance.profileImageSize();
    }
}
