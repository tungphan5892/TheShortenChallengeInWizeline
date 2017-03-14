package tungpt.wizelineremotechallenge.views.viewmodels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

import tungpt.wizelineremotechallenge.views.models.ToolbarModel;

/**
 * Created by tungphan on 3/8/17.
 */

public class ToolbarVM extends BaseObservable{


    private final ToolbarModel toolbarModel = new ToolbarModel();

    public ToolbarVM(){

    }

    public void setBtnCloseVisible(boolean visible){
        toolbarModel.setVisibleBtnClose(visible);
        notifyPropertyChanged(BR.btnCloseVisible);
    }

    @Bindable
    public boolean isBtnCloseVisible(){
        return toolbarModel.getVisibleBtnClose();
    }

    public void setBtnSearchVisible(boolean visible){
        toolbarModel.setVisibleBtnSearch(visible);
        notifyPropertyChanged(BR.btnSearchVisible);
    }

    @Bindable
    public boolean isBtnSearchVisible(){
        return toolbarModel.getVisibleBtnSearch();
    }


}
