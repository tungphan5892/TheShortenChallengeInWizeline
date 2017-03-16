package tungpt.wizelineremotechallenge.views.models;

/**
 * Created by tungphan on 3/9/17.
 */

public class SingleTweetModel {

    private String userName;
    private String profilePictureUrl;
    private String tweetContent;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getTweetContent() {
        return tweetContent;
    }

    public void setTweetContent(String tweetContent) {
        this.tweetContent = tweetContent;
    }
}
