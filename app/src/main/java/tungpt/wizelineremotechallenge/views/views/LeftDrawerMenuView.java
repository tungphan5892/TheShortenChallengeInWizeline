package tungpt.wizelineremotechallenge.views.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tungpt.wizelineremotechallenge.R;
import tungpt.wizelineremotechallenge.databinding.LeftDrawerMenuViewBinding;
import tungpt.wizelineremotechallenge.listeners.ApiServices;
import tungpt.wizelineremotechallenge.networks.RetroClient;
import tungpt.wizelineremotechallenge.networks.models.User;
import tungpt.wizelineremotechallenge.views.adapters.PlanetAdapter;
import tungpt.wizelineremotechallenge.views.viewmodels.LeftDrawerMenuVM;

/**
 * Created by tungphan on 3/8/17.
 */

public class LeftDrawerMenuView extends RelativeLayout implements PlanetAdapter.OnItemClickListener {

    private LeftDrawerMenuViewBinding leftDrawerMenuViewBinding;
    private LeftDrawerMenuVM leftDrawerMenuVM;
    private Context context;

    public LeftDrawerMenuView(Context context) {
        super(context);
        this.context = context;
        initViews();
    }

    public LeftDrawerMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initViews();
    }

    public LeftDrawerMenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initViews();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LeftDrawerMenuView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
        initViews();
    }

    public void initViews() {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        leftDrawerMenuViewBinding = DataBindingUtil.inflate(layoutInflater, R.layout.left_drawer_menu_view, this, true);
        leftDrawerMenuVM = new LeftDrawerMenuVM(context);
        leftDrawerMenuViewBinding.setViewModel(leftDrawerMenuVM);
        loadingUserDataForView();
    }

    @Override
    public void onClick(View view, int position) {

    }

    private User user;

    private void loadingUserDataForView() {
        leftDrawerMenuVM.showLoading();
        ApiServices apiServices = RetroClient.getApiServices();
        Call<User> call = apiServices.getUserJson();
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                leftDrawerMenuVM.hideLoading();
                if (response.isSuccessful()) {
                    user = response.body();
                    if (user != null) {
                        leftDrawerMenuVM.setUserName(user.getName());
                        leftDrawerMenuVM.setUserDescription(user.getDescription());
                        int profileImageHeight = leftDrawerMenuViewBinding.userProfileImage
                                .getHeight();
                        int profileImageWidth = leftDrawerMenuViewBinding.userProfileImage
                                .getWidth();
                        Picasso.with(context).load(user.getProfileImageUrl()).resize(
                                profileImageHeight,
                                profileImageWidth).onlyScaleDown()
                                .into(leftDrawerMenuViewBinding.userProfileImage);
                        int backgroundImageHeight = leftDrawerMenuViewBinding.userBackgroundImage
                                .getHeight();
                        int backgroundImageWidth = leftDrawerMenuViewBinding.userBackgroundImage
                                .getWidth();
                        Picasso.with(context).load(user.getProfileBackgroundImageUrl())
                                .resize(backgroundImageWidth,
                                        backgroundImageHeight).onlyScaleDown()
                                .into(leftDrawerMenuViewBinding.userBackgroundImage);
                    }
                } else {
//                    Snackbar.make(, R.string.error_data_in_response, Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable throwable) {
                leftDrawerMenuVM.hideLoading();
            }
        });
    }
}
