package tungpt.wizelineremotechallenge.views.models;

/**
 * Created by tungphan on 3/8/17.
 */

public class LeftDrawerMenuModel{

    private boolean showLoading = false;
    private String userName;
    private String userDescription;
    private String profileImageUrl;
    private String backgroundImageUrl;
    private int profileImageSize;

    public boolean isShowLoading() {
        return showLoading;
    }

    public void setShowLoading(boolean showLoading) {
        this.showLoading = showLoading;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

    public int getProfileImageSize() {
        return profileImageSize;
    }

    public void setProfileImageSize(int profileImageSize) {
        this.profileImageSize = profileImageSize;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getBackgroundImageUrl() {
        return backgroundImageUrl;
    }

    public void setBackgroundImageUrl(String backgroundImageUrl) {
        this.backgroundImageUrl = backgroundImageUrl;
    }
}
