package tungpt.wizelineremotechallenge.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by Tung Phan on 2/15/2017.
 */

public class Utils {

    public static boolean isLowerThanJellyBeans() {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN;
    }

    public static boolean isLowerThanLollipop() {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP;
    }

    public static boolean isHigherThanMasmarlow() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }
}
