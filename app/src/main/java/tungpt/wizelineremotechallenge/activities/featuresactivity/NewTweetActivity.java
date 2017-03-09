package tungpt.wizelineremotechallenge.activities.featuresactivity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import tungpt.wizelineremotechallenge.R;
import tungpt.wizelineremotechallenge.activities.BaseActivity;
import tungpt.wizelineremotechallenge.databinding.NewTweetActivityBinding;
import tungpt.wizelineremotechallenge.views.viewmodels.NewTweetVM;

/**
 * Created by tungphan on 3/9/17.
 */

public class NewTweetActivity extends BaseActivity {

    private NewTweetActivityBinding newTweetActivityBinding;
    private NewTweetVM newTweetVM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newTweetActivityBinding = DataBindingUtil.setContentView(this,R.layout.new_tweet_activity);
        newTweetVM = new NewTweetVM(this);
        newTweetActivityBinding.setViewModel(newTweetVM);
    }
}
