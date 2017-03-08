package tungpt.wizelineremotechallenge.utils;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;

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

    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return dp * ((float) displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public static float convertPixelsToDp(float pixel, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return pixel / ((float) displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }
}
