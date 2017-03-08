package tungpt.wizelineremotechallenge.views.models;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.design.widget.Snackbar;

import com.android.databinding.library.baseAdapters.BR;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tungpt.wizelineremotechallenge.R;
import tungpt.wizelineremotechallenge.activities.featuresactivity.UserProfileActivity;
import tungpt.wizelineremotechallenge.listeners.ApiServices;
import tungpt.wizelineremotechallenge.networks.RetroClient;
import tungpt.wizelineremotechallenge.networks.models.User;

/**
 * Created by tungphan on 3/8/17.
 */

public class LeftDrawerMenuModel extends BaseObservable {

    private boolean showLoading = false;
    private String userName;
    private String userDescription;
    private int profileImageSize;

    @Bindable
    public boolean isShowLoading() {
        return showLoading;
    }

    public void setShowLoading(boolean showLoading) {
        this.showLoading = showLoading;
        notifyPropertyChanged(BR.showLoading);
    }

    @Bindable
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        notifyPropertyChanged(BR.userName);
    }

    @Bindable
    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
        notifyPropertyChanged(BR.userDescription);
    }

    public int getProfileImageSize() {
        return profileImageSize;
    }

    public void setProfileImageSize(int profileImageSize) {
        this.profileImageSize = profileImageSize;
    }
}
