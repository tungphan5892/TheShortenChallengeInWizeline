package tungpt.wizelineremotechallenge.views.activities.featuresactivity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import tungpt.wizelineremotechallenge.R;
import tungpt.wizelineremotechallenge.views.activities.BaseActivity;
import tungpt.wizelineremotechallenge.databinding.NewTweetActivityBinding;
import tungpt.wizelineremotechallenge.views.viewmodels.NewTweetVM;
import tungpt.wizelineremotechallenge.views.viewmodels.SingleTweetActivityVM;

/**
 * Created by tungphan on 3/9/17.
 */

public class NewTweetActivity extends BaseActivity {

    private NewTweetActivityBinding newTweetActivityBinding;
    private NewTweetVM newTweetVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newTweetVM = new NewTweetVM(this);
        newTweetActivityBinding = DataBindingUtil.inflate(getLayoutInflater()
                , R.layout.new_tweet_activity, baseActivityBinding.contentFrame, true);
        newTweetActivityBinding.setViewModel(newTweetVM);
    }
}
