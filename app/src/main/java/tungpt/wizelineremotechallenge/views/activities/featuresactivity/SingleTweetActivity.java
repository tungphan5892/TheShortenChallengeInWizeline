package tungpt.wizelineremotechallenge.views.activities.featuresactivity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import tungpt.wizelineremotechallenge.R;
import tungpt.wizelineremotechallenge.dagger.components.AppComponent;
import tungpt.wizelineremotechallenge.views.activities.BaseActivity;
import tungpt.wizelineremotechallenge.databinding.SingleTweetActivityBinding;
import tungpt.wizelineremotechallenge.views.iviewlistener.ISingleTweetViewModel;
import tungpt.wizelineremotechallenge.views.viewmodels.SingleTweetActivityVM;

/**
 * Created by Tung Phan on 2/15/2017.
 */

public class SingleTweetActivity extends BaseActivity {
    private static final String TAG = SingleTweetActivity.class.getSimpleName();
    private SingleTweetActivityVM singleTweetActivityVM;
    private SingleTweetActivityBinding singleTweetActivityBinding;
    private ISingleTweetViewModel iSingleTweetViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        singleTweetActivityBinding = DataBindingUtil.inflate(getLayoutInflater()
                , R.layout.single_tweet_activity, baseActivityBinding.contentFrame, true);
        singleTweetActivityVM = new SingleTweetActivityVM(this, singleTweetActivityBinding);
        iSingleTweetViewModel = singleTweetActivityVM.getISingleTweetViewModel();
        singleTweetActivityBinding.setViewModel(singleTweetActivityVM);
        iSingleTweetViewModel.retreivedIntentFromOtherActivity(getIntent());
    }

    @Override
    protected void initInjector(AppComponent appComponent) {
        appComponent.inject(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        iSingleTweetViewModel.onActivityResult(requestCode, resultCode, data);
    }
}
