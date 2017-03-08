package tungpt.wizelineremotechallenge.activities.featuresactivity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tungpt.wizelineremotechallenge.R;
import tungpt.wizelineremotechallenge.activities.BaseActivity;
import tungpt.wizelineremotechallenge.databinding.UserProfileViewBinding;
import tungpt.wizelineremotechallenge.listeners.ApiServices;
import tungpt.wizelineremotechallenge.manageconstants.FragmentTagConstants;
import tungpt.wizelineremotechallenge.networks.models.Tweet;
import tungpt.wizelineremotechallenge.networks.RetroClient;
import tungpt.wizelineremotechallenge.views.fragments.NewTweetFragment;
import tungpt.wizelineremotechallenge.views.viewmodels.UserProfileActivityVM;
import tungpt.wizelineremotechallenge.views.adapters.TweetsListRecyclerAdapter;

/**
 * Created by Tung Phan on 02/15/2017
 */

public class UserProfileActivity extends BaseActivity {
    private static final String TAG = UserProfileActivity.class.getSimpleName();
    private FrameLayout mFragmentReplaceLayout;
    private UserProfileViewBinding userProfileViewBinding;
    private UserProfileActivityVM userProfileActivityVM;
    private List<Tweet> mTimeline;
    private Context context;

    private View.OnClickListener mAddTweetButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (getSupportFragmentManager().findFragmentByTag(FragmentTagConstants.NEW_TWEET_FRAGMENT_TAG) == null &&
                    mFragmentReplaceLayout.getVisibility() == View.GONE) {
                mFragmentReplaceLayout.setVisibility(View.VISIBLE);
                NewTweetFragment newTweetFragment = new NewTweetFragment();
                newTweetFragment.setNewTweetFragmentInterface(new NewTweetFragment.NewTweetFragmentInterface() {

                    @Override
                    public void pressCancelButton() {
                        if (getSupportFragmentManager().findFragmentByTag(FragmentTagConstants.NEW_TWEET_FRAGMENT_TAG) != null &&
                                mFragmentReplaceLayout.getVisibility() == View.VISIBLE) {
                            mFragmentReplaceLayout.setVisibility(View.GONE);
                            NewTweetFragment newTweetFragment = (NewTweetFragment) getSupportFragmentManager()
                                    .findFragmentByTag(FragmentTagConstants.NEW_TWEET_FRAGMENT_TAG);
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .remove(newTweetFragment).commit();
                        }
                    }
                });
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment_replace_layout, newTweetFragment, FragmentTagConstants.NEW_TWEET_FRAGMENT_TAG)
                        .commit();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        RelativeLayout userProfileActivityView = (RelativeLayout) getLayoutInflater().inflate(R.layout.user_profile_view,null);
        parentLayout.addView(userProfileActivityView);
        userProfileViewBinding = DataBindingUtil.getBinding(userProfileActivityView);
        userProfileActivityVM = new UserProfileActivityVM();
        userProfileViewBinding.setViewModel(userProfileActivityVM);
        initNavigationDrawer(savedInstanceState);
        mTimeline = new ArrayList<>();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (checkNetworkConnection()) {
        }
    }

    private void loadingUserTimeline() {
        userProfileActivityVM.showLoading();
        ApiServices apiServiceUser = RetroClient.getApiServices();
        Call<List<Tweet>> call = apiServiceUser.getUserTimelineJson();
        call.enqueue(new Callback<List<Tweet>>() {
            @Override
            public void onResponse(Call<List<Tweet>> call, Response<List<Tweet>> response) {
                userProfileActivityVM.hideLoading();
                if (response.isSuccessful()) {
                    mTimeline = response.body();
                    TweetsListRecyclerAdapter adapter = new TweetsListRecyclerAdapter(context, mTimeline);
                    userProfileViewBinding.tweetsRecyclerView.setAdapter(adapter);
                } else {
//                    Snackbar.make(, R.string.error_data_in_response, Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Tweet>> call, Throwable throwable) {
                userProfileActivityVM.hideLoading();
            }
        });

    }
}
