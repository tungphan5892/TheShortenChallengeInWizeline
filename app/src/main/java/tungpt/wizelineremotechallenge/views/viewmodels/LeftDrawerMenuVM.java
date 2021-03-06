package tungpt.wizelineremotechallenge.views.viewmodels;

import android.app.Activity;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.design.widget.Snackbar;
import android.widget.ImageView;

import com.android.databinding.library.baseAdapters.BR;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tungpt.wizelineremotechallenge.App.WizelineApp;
import tungpt.wizelineremotechallenge.R;
import tungpt.wizelineremotechallenge.databinding.LeftDrawerMenuViewBinding;
import tungpt.wizelineremotechallenge.eventbus.FinishLoadingUserInfoEvent;
import tungpt.wizelineremotechallenge.listeners.ApiServices;
import tungpt.wizelineremotechallenge.networks.models.User;
import tungpt.wizelineremotechallenge.views.models.LeftDrawerMenuModel;

/**
 * Created by tungphan on 3/8/17.
 */

public class LeftDrawerMenuVM extends BaseObservable {

    private LeftDrawerMenuModel leftDrawerMenuModel = new LeftDrawerMenuModel();
    private User user;
    private Context context;
    private LeftDrawerMenuViewBinding leftDrawerMenuViewBinding;


    public LeftDrawerMenuVM(Context context, LeftDrawerMenuViewBinding leftDrawerMenuViewBinding) {
        this.context = context;
        this.leftDrawerMenuViewBinding = leftDrawerMenuViewBinding;
        leftDrawerMenuModel.setProfileImageSize(WizelineApp.getProfileImageSize());
    }

    public LeftDrawerMenuModel getLeftDrawerMenuModel() {
        return leftDrawerMenuModel;
    }

    @Bindable
    public boolean isShowLoading() {
        return leftDrawerMenuModel.isShowLoading();
    }

    public void setShowLoading(boolean showLoading) {
        leftDrawerMenuModel.setShowLoading(showLoading);
        notifyPropertyChanged(BR.showLoading);
    }

    public void setUserName(String userName) {
        leftDrawerMenuModel.setUserName(userName);
        notifyPropertyChanged(BR.userName);
    }

    @Bindable
    public String getUserName() {
        return leftDrawerMenuModel.getUserName();
    }

    public void setUserDescription(String userDescription) {
        leftDrawerMenuModel.setUserDescription(userDescription);
        notifyPropertyChanged(BR.userDescription);
    }

    @Bindable
    public String getUserDescription() {
        return leftDrawerMenuModel.getUserDescription();
    }

    @Bindable
    public String getProfileImageUrl() {
        return leftDrawerMenuModel.getProfileImageUrl();
    }

    public void setProfileImageUrl(String profileImageUrl) {
        leftDrawerMenuModel.setProfileImageUrl(profileImageUrl);
        notifyPropertyChanged(BR.profileImageUrl);
    }

    @Bindable
    public String getBackgroundImageUrl() {
        return leftDrawerMenuModel.getBackgroundImageUrl();
    }

    public void setBackgroundImageUrl(String backgroundImageUrl) {
        leftDrawerMenuModel.setBackgroundImageUrl(backgroundImageUrl);
        notifyPropertyChanged(BR.backgroundImageUrl);
    }

    private void loadImage(ImageView imageView, String imageUrl) {
        Picasso.with(imageView.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.face)
                .into(imageView);
    }

    public void loadingUserDataForView(ApiServices apiServices) {
        setShowLoading(true);
        Call<User> call = apiServices.getUserJson();
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                setShowLoading(false);
                if (response.isSuccessful()) {
                    user = response.body();
                    if (user != null) {
                        setUserName(user.getName());
                        setUserDescription(user.getDescription());
                        setProfileImageUrl(user.getProfileImageUrl());
                        setBackgroundImageUrl(user.getProfileBackgroundImageUrl());
                        successLoadingUserData();
                    }
                } else {
                    errorLoadingUserData();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable throwable) {
                setShowLoading(false);
                errorLoadingUserData();
            }
        });
    }

    private void successLoadingUserData() {
        EventBus.getDefault().post(new FinishLoadingUserInfoEvent(Activity.RESULT_OK
                , user.getProfileImageUrl()));
        loadImage(leftDrawerMenuViewBinding.userProfileImage, user.getProfileImageUrl());
        loadImage(leftDrawerMenuViewBinding.userBackgroundImage, user.getProfileBackgroundImageUrl());
    }

    private void errorLoadingUserData() {
        Snackbar.make(leftDrawerMenuViewBinding.leftDrawerLayout, R.string.error_data_in_response
                , Snackbar.LENGTH_LONG).show();
    }
}
