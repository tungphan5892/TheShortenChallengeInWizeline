package tungpt.wizelineremotechallenge.views.models;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;


/**
 * Created by tungphan on 3/8/17.
 */

public class UserProfileActivityModel extends BaseObservable{

    private boolean showLoading = false;

    @Bindable
    public boolean isShowLoading() {
        return showLoading;
    }

    public void setShowLoading(boolean showLoading) {
        this.showLoading = showLoading;
        notifyPropertyChanged(BR.showLoading);
    }
}
