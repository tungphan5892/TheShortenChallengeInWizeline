package tungpt.wizelineremotechallenge.views.viewmodels;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.view.ViewGroup;

import tungpt.wizelineremotechallenge.App.WizelineApp;
import tungpt.wizelineremotechallenge.BR;
import tungpt.wizelineremotechallenge.R;
import tungpt.wizelineremotechallenge.views.models.BaseActivityModel;


/**
 * Created by tungphan on 3/9/17.
 */

public class BaseActivityVM extends BaseObservable {
    private Context context;
    private BaseActivityModel baseActivityModel;

    public BaseActivityVM(Context context) {
        this.context = context;
        baseActivityModel = new BaseActivityModel();
        int drawerLayoutMarginRight = (int) context.getResources()
                .getDimension(R.dimen.navigation_drawer_margin_right_on_phone);
        int drawerLayoutWidth = WizelineApp.getScreenWidth() - drawerLayoutMarginRight;
        setDrawerLayoutWidth(drawerLayoutWidth);
    }

    public void setDrawerLayoutWidth(int drawerLayoutWidth){
        baseActivityModel.setDrawerLayoutWidth(drawerLayoutWidth);
        notifyPropertyChanged(BR.drawerLayoutWidth);
    }

    @Bindable
    public int getDrawerLayoutWidth(){
        return baseActivityModel.getDrawerLayoutWidth();
    }

    @BindingAdapter("android:layout_width")
    public static void setLayoutWidth(View view, float width) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = (int) width;
        view.setLayoutParams(layoutParams);
    }
}
