package tungpt.wizelineremotechallenge.views.viewmodels;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.android.databinding.library.baseAdapters.BR;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tungpt.wizelineremotechallenge.R;
import tungpt.wizelineremotechallenge.listeners.ApiServices;
import tungpt.wizelineremotechallenge.networks.RetroClient;
import tungpt.wizelineremotechallenge.networks.models.User;
import tungpt.wizelineremotechallenge.views.iviewlistener.ILeftDrawerMenuListener;
import tungpt.wizelineremotechallenge.views.models.LeftDrawerMenuModel;

/**
 * Created by tungphan on 3/8/17.
 */

public class LeftDrawerMenuVM extends BaseObservable {

    private LeftDrawerMenuModel leftDrawerMenuModel = new LeftDrawerMenuModel();
    private Context context;
    private User user;
    private ILeftDrawerMenuListener iLeftDrawerMenuListener;

    public void setiLeftDrawerMenuListener(ILeftDrawerMenuListener iLeftDrawerMenuListener) {
        this.iLeftDrawerMenuListener = iLeftDrawerMenuListener;
    }


    public LeftDrawerMenuVM(Context context) {
        this.context = context;
        int profileImageSize = (int) context.getResources().getDimension(R.dimen.profile_image_size);
        leftDrawerMenuModel.setProfileImageSize(profileImageSize);
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

//    @BindingAdapter("app:imageUrl")
    public static void loadImage(ImageView imageView, String imageUrl) {
        Picasso.with(imageView.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.face)
                .into(imageView);
    }

    public void loadingUserDataForView() {
        setShowLoading(true);
        ApiServices apiServices = RetroClient.getApiServices();
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
                        iLeftDrawerMenuListener.successLoadingData(user);
                    }
                } else {
                    iLeftDrawerMenuListener.errorLoadingData();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable throwable) {
                setShowLoading(false);
            }
        });
    }
}
