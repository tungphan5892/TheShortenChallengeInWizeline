package tungpt.wizelineremotechallenge.views.viewmodels;

import android.app.Activity;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;

import tungpt.wizelineremotechallenge.views.models.ToolbarModel;

/**
 * Created by tungphan on 3/8/17.
 */

public class ToolbarVM extends BaseObservable {

    private Context context;
    private final ToolbarModel toolbarModel = new ToolbarModel();

    public ToolbarVM(Context context) {
        this.context = context;
    }

    public void setBtnCloseVisible(boolean visible) {
        toolbarModel.setVisibleBtnClose(visible);
        notifyPropertyChanged(BR.btnCloseVisible);
    }

    @Bindable
    public boolean isBtnCloseVisible() {
        return toolbarModel.getVisibleBtnClose();
    }

    public void setBtnSearchVisible(boolean visible) {
        toolbarModel.setVisibleBtnSearch(visible);
        notifyPropertyChanged(BR.btnSearchVisible);
    }

    @Bindable
    public boolean isBtnSearchVisible() {
        return toolbarModel.getVisibleBtnSearch();
    }


    public void clickCloseButton(@NonNull final View view) {
        try {
            ((Activity) context).finish();
        } catch (ClassCastException ex) {
            Log.e("TFunk", ex.getMessage());
        }
    }

}
