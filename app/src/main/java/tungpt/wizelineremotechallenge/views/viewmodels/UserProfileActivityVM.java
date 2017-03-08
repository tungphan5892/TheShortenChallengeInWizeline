package tungpt.wizelineremotechallenge.views.viewmodels;

import android.content.Context;
import android.support.design.widget.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tungpt.wizelineremotechallenge.R;
import tungpt.wizelineremotechallenge.activities.featuresactivity.UserProfileActivity;
import tungpt.wizelineremotechallenge.listeners.ApiServices;
import tungpt.wizelineremotechallenge.networks.RetroClient;
import tungpt.wizelineremotechallenge.networks.models.Tweet;
import tungpt.wizelineremotechallenge.views.adapters.TweetsListRecyclerAdapter;
import tungpt.wizelineremotechallenge.views.models.UserProfileActivityModel;

/**
 * Created by tungphan on 3/8/17.
 */

public class UserProfileActivityVM {
    private UserProfileActivityModel userProfileActivityModel = new UserProfileActivityModel();
    private Context context;
    public UserProfileActivityVM(Context context){
        this.context = context;
    }

    public UserProfileActivityModel getUserProfileActivityModel(){
        return userProfileActivityModel;
    }

    public void showLoading(){
        userProfileActivityModel.setShowLoading(true);
    }

    public void hideLoading(){
        userProfileActivityModel.setShowLoading(false);
    }

}
