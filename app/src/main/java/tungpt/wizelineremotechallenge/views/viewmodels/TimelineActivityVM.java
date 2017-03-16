package tungpt.wizelineremotechallenge.views.viewmodels;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.android.databinding.library.baseAdapters.BR;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tungpt.wizelineremotechallenge.R;
import tungpt.wizelineremotechallenge.databinding.TimelineActivityBinding;
import tungpt.wizelineremotechallenge.manageconstants.ActivityRequestCode;
import tungpt.wizelineremotechallenge.views.activities.featuresactivity.NewTweetActivity;
import tungpt.wizelineremotechallenge.listeners.ApiServices;
import tungpt.wizelineremotechallenge.networks.models.Tweet;
import tungpt.wizelineremotechallenge.views.adapters.TweetsListRecyclerAdapter;
import tungpt.wizelineremotechallenge.views.iviewlistener.ITimelineViewModel;
import tungpt.wizelineremotechallenge.views.models.TimelineActivityModel;

/**
 * Created by tungphan on 3/8/17.
 */

public class TimelineActivityVM extends BaseObservable implements ITimelineViewModel {
    private final TimelineActivityModel timelineActivityModel = new TimelineActivityModel();
    private List<Tweet> timeline;
    private Context context;
    private TimelineActivityBinding timelineActivityBinding;
    private ApiServices apiServices;

    public TimelineActivityVM(Context context, TimelineActivityBinding timelineActivityBinding,
                              ApiServices apiServices) {
        this.context = context;
        this.timelineActivityBinding = timelineActivityBinding;
        this.apiServices = apiServices;
    }

    public TimelineActivityModel getTimelineActivityModel() {
        return timelineActivityModel;
    }

    public void setShowLoading(boolean showLoading) {
        timelineActivityModel.setShowLoading(showLoading);
        notifyPropertyChanged(BR.showLoading);
    }

    @Bindable
    public boolean isShowLoading() {
        return timelineActivityModel.isShowLoading();
    }

    public void showLoading() {
        timelineActivityModel.setShowLoading(true);
    }

    public void hideLoading() {
        timelineActivityModel.setShowLoading(false);
        notifyPropertyChanged(BR.showLoading);
    }

    public void clickAddTweetButton(@NonNull final View view) {
        Intent intent = new Intent(view.getContext(), NewTweetActivity.class);
        ((Activity) context).startActivityForResult(intent
                , ActivityRequestCode.START_NEW_TWEET_ACTIVITY_REQUEST_CODE);
    }

    public void loadingUserTimeline() {
        showLoading();
        Call<List<Tweet>> call = apiServices.getUserTimelineJson();
        call.enqueue(new Callback<List<Tweet>>() {
            @Override
            public void onResponse(Call<List<Tweet>> call, Response<List<Tweet>> response) {
                hideLoading();
                if (response.isSuccessful()) {
                    timeline = response.body();
                    setTweetRecyclerViewVisible(true);
                    finishLoadingTimeline();
                } else {
                    setTweetRecyclerViewVisible(true);
                    errorLoadingTimeline();
                }
                timelineActivityBinding.swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<Tweet>> call, Throwable throwable) {
                hideLoading();
                setTweetRecyclerViewVisible(true);
                errorLoadingTimeline();
                timelineActivityBinding.swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void finishLoadingTimeline(){
        TweetsListRecyclerAdapter adapter = new TweetsListRecyclerAdapter(context, timeline);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        timelineActivityBinding.tweetsRecyclerView.setLayoutManager(mLayoutManager);
        timelineActivityBinding.tweetsRecyclerView.setAdapter(adapter);
    }

    private void errorLoadingTimeline(){
        Snackbar.make(timelineActivityBinding.parentView, R.string.error_data_in_response
                , Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ActivityRequestCode.START_NEW_TWEET_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(context, R.string.tweet_successfully, Toast.LENGTH_SHORT).show();
                loadingUserTimeline();
            } else {
                Toast.makeText(context, R.string.tweet_fail, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onCreate() {
        timelineActivityBinding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadingUserTimeline();
            }
        });
    }

    public ITimelineViewModel getITimelineViewModel() {
        return this;
    }

    public void setTweetRecyclerViewVisible(boolean visible) {
        timelineActivityModel.setTweetRecyclerViewVisible(visible);
        notifyPropertyChanged(BR.tweetRecyclerViewVisible);
    }

    @Bindable
    public boolean getTweetRecyclerViewVisible() {
        return timelineActivityModel.getTweetRecyclerViewVisible();
    }
}
