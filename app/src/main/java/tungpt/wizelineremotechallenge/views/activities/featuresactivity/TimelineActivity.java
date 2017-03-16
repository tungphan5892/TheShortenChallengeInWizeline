package tungpt.wizelineremotechallenge.views.activities.featuresactivity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;

import java.util.List;

import javax.inject.Inject;

import tungpt.wizelineremotechallenge.R;
import tungpt.wizelineremotechallenge.dagger.components.AppComponent;
import tungpt.wizelineremotechallenge.listeners.ApiServices;
import tungpt.wizelineremotechallenge.views.activities.BaseActivity;
import tungpt.wizelineremotechallenge.databinding.TimelineActivityBinding;
import tungpt.wizelineremotechallenge.networks.models.Tweet;
import tungpt.wizelineremotechallenge.views.iviewlistener.ITimelineViewModel;
import tungpt.wizelineremotechallenge.views.viewmodels.TimelineActivityVM;
import tungpt.wizelineremotechallenge.views.adapters.TweetsListRecyclerAdapter;

/**
 * Created by Tung Phan on 02/15/2017
 */

public class TimelineActivity extends BaseActivity{
    private static final String TAG = TimelineActivity.class.getSimpleName();
    private TimelineActivityBinding timelineActivityBinding;
    private TimelineActivityVM timelineActivityVM;
    private ITimelineViewModel iTimelineViewModel;
    @Inject
    ApiServices apiServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        timelineActivityBinding = DataBindingUtil.inflate(getLayoutInflater()
                , R.layout.timeline_activity, baseActivityBinding.contentFrame, true);
        timelineActivityVM = new TimelineActivityVM(this,timelineActivityBinding,apiServices);
        iTimelineViewModel = timelineActivityVM.getITimelineViewModel();
        timelineActivityBinding.setViewModel(timelineActivityVM);
        initNavigationDrawer();
        loadUserDataInNavigationDrawer(apiServices);
        loadingTimeLine();
        iTimelineViewModel.onCreate();
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
        timelineActivityVM.loadingUserTimeline();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        iTimelineViewModel.onActivityResult(requestCode,resultCode,data);
    }
}
