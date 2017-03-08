package tungpt.wizelineremotechallenge.broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import tungpt.wizelineremotechallenge.manageconstants.IntentConstants;

public class NetworkReceiver extends BroadcastReceiver {
    private static final String TAG = NetworkReceiver.class.getSimpleName();

    public NetworkReceiver(){
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            if (activeNetwork != null) { // connected to the internet
                if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                    Log.e(TAG,"wifi");
                } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                    Log.e(TAG,"mobile");
                }
            } else {
                try{
                    Log.e(TAG,"disconnect");
                    Intent customIntentNetworkDisconnect = new Intent(IntentConstants.CUSTOM_BROADCAST_NETWORK_DISCONNECT);
                    LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(context);
                    localBroadcastManager.sendBroadcast(customIntentNetworkDisconnect);
                }catch(Exception ex){
                    Log.e(TAG,ex.getMessage());
                }
            }
        }
    }
}
