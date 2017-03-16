package tungpt.wizelineremotechallenge.views.viewmodels;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.android.databinding.library.baseAdapters.BR;

import tungpt.wizelineremotechallenge.R;
import tungpt.wizelineremotechallenge.databinding.SingleTweetActivityBinding;
import tungpt.wizelineremotechallenge.manageconstants.ActivityRequestCode;
import tungpt.wizelineremotechallenge.views.iviewlistener.ISingleTweetViewModel;
import tungpt.wizelineremotechallenge.views.models.SingleTweetModel;

/**
 * Created by tungphan on 3/9/17.
 */

public class SingleTweetActivityVM extends BaseObservable implements ISingleTweetViewModel {
    private final String INTENT_TEXT_SHARE_TYPE = "text/plain";

    private Context context;
    private SingleTweetActivityBinding singleTweetActivityBinding;
    final private SingleTweetModel singleTweetModel = new SingleTweetModel();

    public ISingleTweetViewModel getISingleTweetViewModel() {
        return this;
    }

    public SingleTweetActivityVM(Context context, SingleTweetActivityBinding singleTweetActivityBinding) {
        this.context = context;
        this.singleTweetActivityBinding = singleTweetActivityBinding;
    }

    public void clickShareButton(@NonNull final View view) {
        String tweetContent = singleTweetActivityBinding.tweetDescription.getText().toString();
        Intent textShareIntent = new Intent(Intent.ACTION_SEND);
        textShareIntent.putExtra(Intent.EXTRA_TEXT, tweetContent);
        textShareIntent.setType(INTENT_TEXT_SHARE_TYPE);
        ((Activity) context).startActivityForResult(Intent.createChooser(textShareIntent
                , context.getResources().getString(R.string.text_share_tweet))
                , ActivityRequestCode.START_SHARE_TWEET_ACTIVITY_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ActivityRequestCode.START_NEW_TWEET_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(context, R.string.text_share_tweet_successful, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, R.string.text_share_tweet_fail, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void retreivedIntentFromOtherActivity(Intent intent) {
        if (intent == null) {
            return;
        }
        if (intent.getExtras() == null) {
            return;
        } else {
            Bundle bundle = intent.getExtras();
        }

    }

    @Bindable
    public String getUserName() {
        return singleTweetModel.getUserName();
    }

    public void setUserName(String userName) {
        singleTweetModel.setUserName(userName);
        notifyPropertyChanged(BR.userName);
    }

    @Bindable
    public String getProfilePictureUrl() {
        return singleTweetModel.getProfilePictureUrl();
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        singleTweetModel.setProfilePictureUrl(profilePictureUrl);
        notifyPropertyChanged(BR.profilePictureUrl);
    }

    @Bindable
    public String getTweetContent() {
        return singleTweetModel.getTweetContent();
    }

    public void setTweetContent(String tweetContent) {
        singleTweetModel.setUserName(tweetContent);
        notifyPropertyChanged(BR.tweetContent);
    }


}
