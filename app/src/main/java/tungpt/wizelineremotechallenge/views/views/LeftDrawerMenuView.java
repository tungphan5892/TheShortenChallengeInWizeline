package tungpt.wizelineremotechallenge.views.views;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import tungpt.wizelineremotechallenge.R;
import tungpt.wizelineremotechallenge.databinding.LeftDrawerMenuViewBinding;
import tungpt.wizelineremotechallenge.eventbus.FinishLoadingUserInfoEvent;
import tungpt.wizelineremotechallenge.listeners.ApiServices;
import tungpt.wizelineremotechallenge.networks.models.User;
import tungpt.wizelineremotechallenge.views.adapters.PlanetAdapter;
import tungpt.wizelineremotechallenge.views.iviewlistener.ILeftDrawerMenuListener;
import tungpt.wizelineremotechallenge.views.viewmodels.LeftDrawerMenuVM;

/**
 * Created by tungphan on 3/8/17.
 */

public class LeftDrawerMenuView extends RelativeLayout implements PlanetAdapter.OnItemClickListener, ILeftDrawerMenuListener {

    private LeftDrawerMenuViewBinding leftDrawerMenuViewBinding;
    private LeftDrawerMenuVM leftDrawerMenuVM;

    public void loadUserData(ApiServices apiServices){
        leftDrawerMenuVM.loadingUserDataForView(apiServices);
    }

    public LeftDrawerMenuView(Context context) {
        this(context,null);
    }

    public LeftDrawerMenuView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LeftDrawerMenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LeftDrawerMenuView(Context context, AttributeSet attrs, int defStyleAttr
            , int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initViews();
    }

    public void initViews() {
        LayoutInflater layoutInflater = LayoutInflater.from(this.getContext());
        leftDrawerMenuViewBinding = DataBindingUtil.inflate(layoutInflater
                , R.layout.left_drawer_menu_view, this, true);
        leftDrawerMenuVM = new LeftDrawerMenuVM();
        leftDrawerMenuVM.setiLeftDrawerMenuListener(this);
        leftDrawerMenuViewBinding.setViewModel(leftDrawerMenuVM);
    }

    @Override
    public void onClick(View view, int position) {

    }

    @Override
    public void errorLoadingData() {
        Snackbar.make(this, R.string.error_data_in_response
                , Snackbar.LENGTH_LONG).show();
    }

    public void successLoadingData(User user) {
        EventBus.getDefault().post(new FinishLoadingUserInfoEvent(Activity.RESULT_OK
                , user.getProfileImageUrl()));
        loadImage(leftDrawerMenuViewBinding.userProfileImage, user.getProfileImageUrl());
        loadImage(leftDrawerMenuViewBinding.userBackgroundImage, user.getProfileBackgroundImageUrl());
    }

    private void loadImage(ImageView imageView, String imageUrl) {
        Picasso.with(this.getContext()).load(imageUrl)
                .resize(imageView.getWidth(),
                        imageView.getHeight()).onlyScaleDown()
                .into(imageView);
    }
}
