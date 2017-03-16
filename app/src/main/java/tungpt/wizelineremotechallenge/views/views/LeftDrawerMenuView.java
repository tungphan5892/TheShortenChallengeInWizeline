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

import tungpt.wizelineremotechallenge.R;
import tungpt.wizelineremotechallenge.databinding.LeftDrawerMenuViewBinding;
import tungpt.wizelineremotechallenge.eventbus.FinishLoadingUserInfoEvent;
import tungpt.wizelineremotechallenge.listeners.ApiServices;
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

    public void loadUserData(ApiServices apiServices) {
        leftDrawerMenuVM.loadingUserDataForView(apiServices);
    }

    public LeftDrawerMenuView(Context context) {
        this(context, null);
    }

    public LeftDrawerMenuView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LeftDrawerMenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initViews();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LeftDrawerMenuView(Context context, AttributeSet attrs, int defStyleAttr
            , int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
        initViews();
    }

    public void initViews() {
        LayoutInflater layoutInflater = LayoutInflater.from(this.getContext());
        leftDrawerMenuViewBinding = DataBindingUtil.inflate(layoutInflater
                , R.layout.left_drawer_menu_view, this, true);
        leftDrawerMenuVM = new LeftDrawerMenuVM(context, leftDrawerMenuViewBinding);
        leftDrawerMenuViewBinding.setViewModel(leftDrawerMenuVM);
    }

    @Override
    public void onClick(View view, int position) {

    }
}
