package tungpt.wizelineremotechallenge.activities.featuresactivity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import java.util.List;

import tungpt.wizelineremotechallenge.R;
import tungpt.wizelineremotechallenge.activities.BaseActivity;
import tungpt.wizelineremotechallenge.databinding.TimelineActivityBinding;
import tungpt.wizelineremotechallenge.networks.models.Tweet;
import tungpt.wizelineremotechallenge.views.iviewlistener.IUserProfileActivityListener;
import tungpt.wizelineremotechallenge.views.viewmodels.LeftDrawerMenuVM;
import tungpt.wizelineremotechallenge.views.viewmodels.TimelineActivityVM;
import tungpt.wizelineremotechallenge.views.adapters.TweetsListRecyclerAdapter;

/**
 * Created by Tung Phan on 02/15/2017
 */

public class TimelineActivity extends BaseActivity implements IUserProfileActivityListener {
    private static final String TAG = TimelineActivity.class.getSimpleName();
    private TimelineActivityBinding timelineActivityBinding;
    private TimelineActivityVM timelineActivityVM;

    private View.OnClickListener mAddTweetButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        timelineActivityVM = new TimelineActivityVM(this);
        timelineActivityVM.setIUserProfileActivityListener(this);
        timelineActivityBinding = DataBindingUtil.inflate(getLayoutInflater()
                ,R.layout.timeline_activity,parentLayout,true);
        timelineActivityBinding.setViewModel(timelineActivityVM);
        initNavigationDrawer(savedInstanceState);
        timelineActivityVM.load
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void finishLoadingTimeline(List<Tweet> timeline) {
        TweetsListRecyclerAdapter adapter = new TweetsListRecyclerAdapter(this, timeline);
        timelineActivityBinding.tweetsRecyclerView.setAdapter(adapter);
    }

    @Override
    public void errorLoadingData() {
        Snackbar.make(timelineActivityBinding.parentView, R.string.error_data_in_response
                , Snackbar.LENGTH_LONG).show();
    }
}
