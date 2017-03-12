package tungpt.wizelineremotechallenge.localdata;

import android.content.Context;
import android.content.SharedPreferences;

import tungpt.wizelineremotechallenge.manageconstants.PrefConstants;


/**
 * Created by tungphan on 3/9/17.
 */

public class SharepreferenceManager {
    private static final String TAG = SharepreferenceManager.class.getSimpleName();

    private static SharepreferenceManager sInstance;
    private final SharedPreferences mPref;

    private SharepreferenceManager(Context context) {
        mPref = context.getSharedPreferences(PrefConstants.LOGIN_PREF, Context.MODE_PRIVATE);
    }

    public static synchronized void initializeInstance(Context context) {
        if (sInstance == null) {
            sInstance = new SharepreferenceManager(context);
        }
    }

    public static synchronized SharepreferenceManager getInstance() {
        if (sInstance == null) {
            throw new IllegalStateException(SharepreferenceManager.class.getSimpleName() +
                    " is not initialized, call initializeInstance(..) method first.");
        }
        return sInstance;
    }

    public void setStringValue(String key, String value) {
        mPref.edit()
                .putString(key, value)
                .apply();
    }

    public String getStringValue(String key) {
        return mPref.getString(key,"");
    }

    public void remove(String key) {
        mPref.edit()
                .remove(key)
                .apply();
    }

    public boolean clear() {
        return mPref.edit()
                .clear()
                .commit();
    }
}
