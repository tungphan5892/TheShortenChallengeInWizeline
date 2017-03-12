package tungpt.wizelineremotechallenge.views.activities;

import android.Manifest;
import android.app.Activity;
import android.app.SearchManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;


import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import tungpt.wizelineremotechallenge.R;
import tungpt.wizelineremotechallenge.databinding.MainLayoutWithNavigationDrawerBinding;
import tungpt.wizelineremotechallenge.databinding.ToolbarBinding;
import tungpt.wizelineremotechallenge.eventbus.FinishLoadingUserInfoEvent;
import tungpt.wizelineremotechallenge.manageconstants.IntentConstants;
import tungpt.wizelineremotechallenge.utils.Utils;
import tungpt.wizelineremotechallenge.views.viewmodels.BaseActivityVM;
import tungpt.wizelineremotechallenge.views.viewmodels.ToolbarVM;

/**
 * Created by Tung Phan on 2/15/2017.
 */

public class BaseActivity extends AppCompatActivity {
    private static final String TAG = BaseActivity.class.getSimpleName();
    private final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 0;
    private LocalBroadcastManager mLocalBroadcastManager;
    protected ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mPlanetTitles;
    private ToolbarBinding toolbarBinding;
    private BaseActivityVM baseActivityVM;
    protected MainLayoutWithNavigationDrawerBinding baseActivityBinding;
    private ToolbarVM toolbarVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreenFlags();
        baseActivityBinding = DataBindingUtil.setContentView(this, R.layout.main_layout_with_navigation_drawer);
        baseActivityVM = new BaseActivityVM(this);
        baseActivityBinding.setViewModel(baseActivityVM);
        if (Utils.isHigherThanMasmarlow()) {
            requestPermissionIfNeeded();
        }
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        initToolbar();
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    private void setFullScreenFlags() {
        if (Utils.isLowerThanJellyBeans()) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    protected void initToolbar() {
        toolbarBinding = DataBindingUtil.inflate(getLayoutInflater(),R.layout.toolbar
                ,baseActivityBinding.toolbar,true);
        toolbarVM = new ToolbarVM(this);
        toolbarBinding.setViewModel(toolbarVM);
        toolbarBinding.toolbar.setBackgroundColor(Color.WHITE);
        setSupportActionBar(toolbarBinding.toolbar);
    }

    protected void initNavigationDrawer() {
        mTitle = mDrawerTitle = getTitle();
        mPlanetTitles = getResources().getStringArray(R.array.planets_array);
        baseActivityBinding.drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                baseActivityBinding.drawerLayout,
                toolbarBinding.toolbar,
                R.string.drawer_open,
                R.string.drawer_close
        ) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        baseActivityBinding.drawerLayout.addDrawerListener(mDrawerToggle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = baseActivityBinding.drawerLayout
                .isDrawerOpen(baseActivityBinding.leftDrawer);
        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.action_websearch:
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, getSupportActionBar().getTitle());
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(this, R.string.app_not_available, Toast.LENGTH_LONG).show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (mDrawerToggle != null) {
            mDrawerToggle.syncState();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mLocalBroadcastManager.registerReceiver(mNetworkDisconnectBReceiver, new IntentFilter(IntentConstants.CUSTOM_BROADCAST_NETWORK_DISCONNECT));
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    private void requestPermissionIfNeeded() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestWriteExternalPermission();
        }
    }

    private void requestWriteExternalPermission() {
        ActivityCompat.requestPermissions(BaseActivity.this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                MY_PERMISSIONS_REQUEST_READ_CONTACTS);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS:
                if (grantResults.length > 0) {
                    if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                            showpermissionRationale();
                        } else {
                            Toast.makeText(getApplicationContext(), getResources()
                                            .getString(R.string.message_alert_dialog_please_turn_on_permission_in_setting)
                                    , Toast.LENGTH_LONG).show();
                            BaseActivity.this.finish();
                        }
                    }
                }
        }
    }

    private void showpermissionRationale() {
        AlertDialog.Builder alertDialogBuilder = customAlertDialogBuilder(getResources().getString(R.string.title_alert_dialog_permission_require),
                getResources().getString(R.string.message_alert_dialog_turn_on_permission_in_setting));
        alertDialogBuilder.setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                startSettings();
            }
        }).setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                BaseActivity.this.finish();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void startSettings() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setData(Uri.parse(getResources().getString(R.string.string_package) + getPackageName()));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        startActivity(intent);
    }

    private AlertDialog.Builder customAlertDialogBuilder(String title, String message) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder
                .setMessage(message)
                .setCancelable(false);
        return alertDialogBuilder;
    }

    @Override
    protected void onStop() {
        super.onStop();
        mLocalBroadcastManager.unregisterReceiver(mNetworkDisconnectBReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private BroadcastReceiver mNetworkDisconnectBReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equalsIgnoreCase(IntentConstants.CUSTOM_BROADCAST_NETWORK_DISCONNECT)) {
                showNetworkNotAvailable();
            }
        }
    };

    private void showNetworkNotAvailable() {
        AlertDialog.Builder alertDialogBuilder = customAlertDialogBuilder(getResources().getString(R.string.title_alert_dialog_network_problem),
                getResources().getString(R.string.message_alert_dialog_network_not_available));
        alertDialogBuilder.setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                BaseActivity.this.finish();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void doThis(FinishLoadingUserInfoEvent finishLoadingUserInfoEvent) {
        if(finishLoadingUserInfoEvent.getResultCode() == Activity.RESULT_OK){
            final ArrayList<View> outViews = new ArrayList<>();
            String contentDesc = getResources().getString(R.string.drawer_open);
            baseActivityBinding.toolbar.findViewsWithText(outViews, contentDesc
                    , View.FIND_VIEWS_WITH_CONTENT_DESCRIPTION);
            ImageButton imageButton = (ImageButton) outViews.get(0);
            Picasso.with(this)
                    .load(finishLoadingUserInfoEvent.getUserProfileImageUrl())
                    .placeholder(R.drawable.face)
                    .into(imageButton);
        }
    }

}
