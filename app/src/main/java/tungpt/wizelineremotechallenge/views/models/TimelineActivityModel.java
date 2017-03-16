package tungpt.wizelineremotechallenge.views.models;


/**
 * Created by tungphan on 3/8/17.
 */

public class TimelineActivityModel {

    private boolean showLoading = false;
    private boolean tweetRecyclerViewVisible = false;

    public boolean isShowLoading() {
        return showLoading;
    }

    public void setShowLoading(boolean showLoading) {
        this.showLoading = showLoading;
    }

    public boolean getTweetRecyclerViewVisible() {
        return tweetRecyclerViewVisible;
    }

    public void setTweetRecyclerViewVisible(boolean tweetRecyclerViewVisible) {
        this.tweetRecyclerViewVisible = tweetRecyclerViewVisible;
    }
}
