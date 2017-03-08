package tungpt.wizelineremotechallenge.views.viewmodels;

import android.content.Context;
import android.databinding.BaseObservable;

import tungpt.wizelineremotechallenge.R;
import tungpt.wizelineremotechallenge.views.models.LeftDrawerMenuModel;

/**
 * Created by tungphan on 3/8/17.
 */

public class LeftDrawerMenuVM extends BaseObservable {

    private LeftDrawerMenuModel leftDrawerMenuModel = new LeftDrawerMenuModel();
    private Context context;

    public LeftDrawerMenuVM(Context context){
        this.context = context;
        int profileImageSize = (int) context.getResources().getDimension(R.dimen.profile_image_size);
        leftDrawerMenuModel.setProfileImageSize(profileImageSize);
    }

    public LeftDrawerMenuModel getLeftDrawerMenuModel() {
        return leftDrawerMenuModel;
    }

    public void showLoading() {
        leftDrawerMenuModel.setShowLoading(true);
    }

    public void hideLoading() {
        leftDrawerMenuModel.setShowLoading(false);
    }

    public void setUserName(String userName) {
        leftDrawerMenuModel.setUserName(userName);
    }

    public void setUserDescription(String userDescription) {
        leftDrawerMenuModel.setUserDescription(userDescription);
    }

}
