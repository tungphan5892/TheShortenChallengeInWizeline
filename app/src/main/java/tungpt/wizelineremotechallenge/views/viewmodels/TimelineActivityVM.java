package tungpt.wizelineremotechallenge.views.viewmodels;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tungpt.wizelineremotechallenge.views.activities.featuresactivity.NewTweetActivity;
import tungpt.wizelineremotechallenge.listeners.ApiServices;
import tungpt.wizelineremotechallenge.networks.RetroClient;
import tungpt.wizelineremotechallenge.networks.models.Tweet;
import tungpt.wizelineremotechallenge.views.iviewlistener.IUserProfileActivityListener;
import tungpt.wizelineremotechallenge.views.models.TimelineActivityModel;

/**
 * Created by tungphan on 3/8/17.
 */

public class TimelineActivityVM extends BaseObservable {
    private TimelineActivityModel timelineActivityModel = new TimelineActivityModel();
    private Context context;
    private List<Tweet> timeline;
    private IUserProfileActivityListener iUserProfileActivityListener;


    public void setIUserProfileActivityListener(IUserProfileActivityListener iUserProfileActivityListener) {
        this.iUserProfileActivityListener = iUserProfileActivityListener;
    }

    public TimelineActivityVM(Context context) {
        this.context = context;
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

    public void addTweetButtonClick(@NonNull final View view){
        Intent intent = new Intent(context,NewTweetActivity.class);
        context.startActivity(intent);
    }

    public void loadingUserTimeline() {
        showLoading();
        ApiServices apiServiceUser = RetroClient.getApiServices();
        Call<List<Tweet>> call = apiServiceUser.getUserTimelineJson();
        call.enqueue(new Callback<List<Tweet>>() {
            @Override
            public void onResponse(Call<List<Tweet>> call, Response<List<Tweet>> response) {
                hideLoading();
                if (response.isSuccessful()) {
                    timeline = response.body();
                    iUserProfileActivityListener.finishLoadingTimeline(timeline);
                } else {
                    iUserProfileActivityListener.errorLoadingData();
                }
            }

            @Override
            public void onFailure(Call<List<Tweet>> call, Throwable throwable) {
                hideLoading();
                iUserProfileActivityListener.errorLoadingData();
            }
        });
    }
}
