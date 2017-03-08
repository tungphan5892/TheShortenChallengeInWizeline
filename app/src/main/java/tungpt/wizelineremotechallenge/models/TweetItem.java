package tungpt.wizelineremotechallenge.models;

/**
 * Created by Tung Phan on 2/15/2017.
 */

public class TweetItem {

    private String mUserName;
    private String mTweet;
    private String mProfileUrl;

    public TweetItem(String userName, String tweet, String profileUrl) {
        mUserName = userName;
        mTweet = tweet;
        mProfileUrl = profileUrl;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        this.mUserName = userName;
    }

    public String getTweet() {
        return mTweet;
    }

    public void setTweet(String tweet) {
        this.mTweet = tweet;
    }

    public String getProfileUrl() {
        return mProfileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.mProfileUrl = profileUrl;
    }

}
