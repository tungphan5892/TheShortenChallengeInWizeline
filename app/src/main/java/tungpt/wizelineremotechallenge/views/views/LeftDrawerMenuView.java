package tungpt.wizelineremotechallenge.views.views;

import android.annotation.TargetApi;
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

import tungpt.wizelineremotechallenge.R;
import tungpt.wizelineremotechallenge.databinding.LeftDrawerMenuViewBinding;
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
        leftDrawerMenuVM.setiLeftDrawerMenuListener(this);
        leftDrawerMenuViewBinding.setViewModel(leftDrawerMenuVM);
    }

    @Override
    public void onClick(View view, int position) {

    }

    @Override
    public void errorLoadingData() {
        Snackbar.make(this, R.string.error_data_in_response, Snackbar.LENGTH_LONG).show();
    }

    public void successLoadingData(User user) {
        loadImage(leftDrawerMenuViewBinding.userProfileImage, user.getProfileImageUrl());
        loadImage(leftDrawerMenuViewBinding.userBackgroundImage, user.getProfileBackgroundImageUrl());
    }

    private void loadImage(ImageView imageView, String imageUrl) {
        Picasso.with(context).load(imageUrl)
                .resize(imageView.getWidth(),
                        imageView.getHeight()).onlyScaleDown()
                .into(leftDrawerMenuViewBinding.userBackgroundImage);
    }
}
