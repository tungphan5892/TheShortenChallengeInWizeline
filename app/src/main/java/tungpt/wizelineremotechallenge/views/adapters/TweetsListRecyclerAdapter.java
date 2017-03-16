package tungpt.wizelineremotechallenge.views.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import tungpt.wizelineremotechallenge.manageconstants.IntentConstants;
import tungpt.wizelineremotechallenge.R;
import tungpt.wizelineremotechallenge.views.activities.featuresactivity.SingleTweetActivity;
import tungpt.wizelineremotechallenge.networks.models.Tweet;

/**
 * Created by Tung Phan on 2/15/2017.
 */

public class TweetsListRecyclerAdapter extends RecyclerView.Adapter<TweetsListRecyclerAdapter.ViewHolder> {

    private List<Tweet> mTimeLine;
    private Context mContext;
    private float mProfileImageWidth;
    private float mProfileImageHeight;

    public TweetsListRecyclerAdapter(Context context, List<Tweet> timeline) {
        mContext = context;
        mTimeLine = timeline;
        mProfileImageWidth = context.getResources().getDimension(R.dimen.tweet_recycler_image_size);
        mProfileImageHeight = context.getResources().getDimension(R.dimen.tweet_recycler_image_size);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.item_timeline_recycler_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        final Tweet tweet = mTimeLine.get(position);
        TextView nameTextView = viewHolder.mNameTview;
        TextView tweetTextView = viewHolder.mTweetTView;
        ImageView profilePicImView = viewHolder.mProfilePicImView;
        LinearLayout itemWrapLayout = viewHolder.mItemWrapLayout;
        nameTextView.setText(tweet.getUser().getName());
        tweetTextView.setText(tweet.getText());
        Picasso.with(mContext)
                .load(tweet.getUser().getProfileImageUrl())
                .placeholder(R.drawable.face)
                .resize((int) mProfileImageWidth, (int) mProfileImageWidth)
                .onlyScaleDown()
                .into(profilePicImView);
        final Context context = viewHolder.mContext;
        itemWrapLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SingleTweetActivity.class);
                intent.putExtra(IntentConstants.OWNER_NAME, tweet.getUser().getName());
                intent.putExtra(IntentConstants.TWEET_DESCRIPTION, tweet.getText());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTimeLine.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mNameTview;
        public TextView mTweetTView;
        public ImageView mProfilePicImView;
        public LinearLayout mItemWrapLayout;
        public Context mContext;

        public ViewHolder(View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            mNameTview = (TextView) itemView.findViewById(R.id.user_name_view);
            mTweetTView = (TextView) itemView.findViewById(R.id.tweet_view);
            mProfilePicImView = (ImageView) itemView.findViewById(R.id.profile_image_view);
            mItemWrapLayout = (LinearLayout) itemView.findViewById(R.id.item_wrap_layout);
        }
    }
}
