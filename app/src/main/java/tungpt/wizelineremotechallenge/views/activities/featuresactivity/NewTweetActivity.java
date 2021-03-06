package tungpt.wizelineremotechallenge.views.activities.featuresactivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import tungpt.wizelineremotechallenge.R;
import tungpt.wizelineremotechallenge.dagger.components.AppComponent;
import tungpt.wizelineremotechallenge.listeners.ApiServices;
import tungpt.wizelineremotechallenge.views.activities.BaseActivity;
import tungpt.wizelineremotechallenge.databinding.NewTweetActivityBinding;
import tungpt.wizelineremotechallenge.views.iviewlistener.INewTweetViewModel;
import tungpt.wizelineremotechallenge.views.viewmodels.NewTweetVM;
import tungpt.wizelineremotechallenge.views.viewmodels.SingleTweetActivityVM;

/**
 * Created by tungphan on 3/9/17.
 */

public class NewTweetActivity extends BaseActivity {
    private NewTweetActivityBinding newTweetActivityBinding;
    private NewTweetVM newTweetVM;
    private INewTweetViewModel iNewTweetViewModel;
    @Inject
    ApiServices apiServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseActivityBinding.leftDrawer.setVisibility(View.GONE);
        newTweetActivityBinding = DataBindingUtil.inflate(getLayoutInflater()
                , R.layout.new_tweet_activity, baseActivityBinding.contentFrame, true);
        newTweetVM = new NewTweetVM(this,newTweetActivityBinding,apiServices);
        newTweetActivityBinding.setViewModel(newTweetVM);
        iNewTweetViewModel = newTweetVM.getINewTweetViewModel();
        iNewTweetViewModel.onCreate();
    }

    @Override
    protected void initInjector(AppComponent appComponent) {
        appComponent.inject(this);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        toolbarVM.setBtnSearchVisible(false);
        toolbarVM.setBtnCloseVisible(true);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iNewTweetViewModel.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        iNewTweetViewModel.onResume();
    }
}
