package tungpt.wizelineremotechallenge.activities.featuresactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import tungpt.wizelineremotechallenge.R;
import tungpt.wizelineremotechallenge.activities.BaseActivity;
import tungpt.wizelineremotechallenge.manageconstants.IntentConstants;

/**
 * Created by Tung Phan on 2/15/2017.
 */

public class SingleTweetActivity extends BaseActivity {
    private static final String TAG = SingleTweetActivity.class.getSimpleName();
    private TextView mTweetOwnerName;
    private TextView mTweetDescription;
    private Button mBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_tweet);
        initViews();
        processDataInIntent();
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initViews() {
        mTweetOwnerName = (TextView) findViewById(R.id.tweet_owner_name);
        mTweetDescription = (TextView) findViewById(R.id.tweet_description);
        mBackButton = (Button) findViewById(R.id.back_button);
    }

    private void processDataInIntent() {
        Intent intent = getIntent();
        String ownerName = "";
        String tweetDescription = "";
        if (intent != null) {
            if (intent.getExtras() != null) {
                ownerName = intent.getStringExtra(IntentConstants.OWNER_NAME);
                tweetDescription = intent.getStringExtra(IntentConstants.TWEET_DESCRIPTION);
            }
        }
        if (ownerName != null && !ownerName.equalsIgnoreCase("")) {
            mTweetOwnerName.setText(ownerName);
        }
        if (tweetDescription != null && !tweetDescription.equalsIgnoreCase("")) {
            mTweetDescription.setText(tweetDescription);
        }
    }
}
