package tungpt.wizelineremotechallenge.views.activities.featuresactivity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import tungpt.wizelineremotechallenge.App.WizelineApp;
import tungpt.wizelineremotechallenge.R;
import tungpt.wizelineremotechallenge.dagger.components.AppComponent;
import tungpt.wizelineremotechallenge.listeners.ApiServices;
import tungpt.wizelineremotechallenge.views.activities.BaseActivity;
import tungpt.wizelineremotechallenge.databinding.TimelineActivityBinding;
import tungpt.wizelineremotechallenge.networks.models.Tweet;
import tungpt.wizelineremotechallenge.utils.Utils;
import tungpt.wizelineremotechallenge.views.iviewlistener.IUserProfileActivityListener;
import tungpt.wizelineremotechallenge.views.viewmodels.TimelineActivityVM;
import tungpt.wizelineremotechallenge.views.adapters.TweetsListRecyclerAdapter;

/**
 * Created by Tung Phan on 02/15/2017
 */

public class TimelineActivity extends BaseActivity implements IUserProfileActivityListener {
    private static final String TAG = TimelineActivity.class.getSimpleName();
    private TimelineActivityBinding timelineActivityBinding;
    private TimelineActivityVM timelineActivityVM;
    @Inject
    ApiServices apiServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        timelineActivityVM = new TimelineActivityVM();
        timelineActivityVM.setIUserProfileActivityListener(this);
        timelineActivityBinding = DataBindingUtil.inflate(getLayoutInflater()
                , R.layout.timeline_activity, baseActivityBinding.contentFrame, true);
        timelineActivityBinding.setViewModel(timelineActivityVM);
        initNavigationDrawer();
        loadUserDataInNavigationDrawer(apiServices);
        loadingTimeLine();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        toolbarVM.setBtnSearchVisible(true);
        toolbarVM.setBtnCloseVisible(false);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void initInjector(AppComponent appComponent) {
        appComponent.inject(this);
    }

    private void loadingTimeLine() {
        timelineActivityVM.loadingUserTimeline(apiServices);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void finishLoadingTimeline(List<Tweet> timeline) {
        TweetsListRecyclerAdapter adapter = new TweetsListRecyclerAdapter(this, timeline);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        timelineActivityBinding.tweetsRecyclerView.setLayoutManager(mLayoutManager);
        timelineActivityBinding.tweetsRecyclerView.setAdapter(adapter);
    }

    @Override
    public void errorLoadingData() {
        Snackbar.make(timelineActivityBinding.parentView, R.string.error_data_in_response
                , Snackbar.LENGTH_LONG).show();
    }
}
