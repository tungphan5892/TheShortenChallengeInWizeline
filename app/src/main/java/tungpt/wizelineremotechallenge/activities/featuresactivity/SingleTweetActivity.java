package tungpt.wizelineremotechallenge.activities.featuresactivity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import tungpt.wizelineremotechallenge.R;
import tungpt.wizelineremotechallenge.activities.BaseActivity;
import tungpt.wizelineremotechallenge.databinding.SingleTweetActivityBinding;
import tungpt.wizelineremotechallenge.views.viewmodels.SingleTweetActivityVM;

/**
 * Created by Tung Phan on 2/15/2017.
 */

public class SingleTweetActivity extends BaseActivity {
    private static final String TAG = SingleTweetActivity.class.getSimpleName();
    private SingleTweetActivityVM singleTweetActivityVM;
    private SingleTweetActivityBinding singleTweetActivityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        singleTweetActivityBinding = DataBindingUtil.setContentView(this, R.layout.single_tweet_activity);
        singleTweetActivityVM = new SingleTweetActivityVM(this);
        singleTweetActivityBinding.setViewModel(singleTweetActivityVM);
    }
}
