package tungpt.wizelineremotechallenge.views.viewmodels;


import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Toast;


import java.util.ArrayList;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tungpt.wizelineremotechallenge.App.WizelineApp;
import tungpt.wizelineremotechallenge.BR;
import tungpt.wizelineremotechallenge.R;
import tungpt.wizelineremotechallenge.databinding.NewTweetActivityBinding;
import tungpt.wizelineremotechallenge.listeners.ApiServices;
import tungpt.wizelineremotechallenge.manageconstants.ActivityRequestCode;
import tungpt.wizelineremotechallenge.manageconstants.IntentConstants;
import tungpt.wizelineremotechallenge.views.adapters.PhoneImagesGridVAdapter;
import tungpt.wizelineremotechallenge.views.iviewlistener.INewTweetViewModel;
import tungpt.wizelineremotechallenge.views.models.NewTweetModel;

/**
 * Created by tungphan on 3/9/17.
 */

public class NewTweetVM extends BaseObservable implements INewTweetViewModel, LoaderManager.LoaderCallbacks {
    private final int MAX_TWEET_LENGTH = 140;
    private final int EXTERNAL_IMAGES_LOADER_ID = 0;
    private final NewTweetModel newTweetModel = new NewTweetModel();
    private NewTweetActivityBinding newTweetActivityBinding;
    private PhoneImagesGridVAdapter phoneImagesGridVAdapter;
    private Activity activity;
    private ArrayList<String> imagesPath = new ArrayList<>();
    private Uri imagesUri;
    private ApiServices apiServices;

    public static String getBucketId(String path) {
        return String.valueOf(path.toLowerCase().hashCode());
    }

    public NewTweetVM(Activity activity, NewTweetActivityBinding newTweetActivityBinding
            , ApiServices apiServices) {
        this.activity = activity;
        this.newTweetActivityBinding = newTweetActivityBinding;
        this.apiServices = apiServices;
    }

    public INewTweetViewModel getINewTweetViewModel() {
        return this;
    }

    public void setTweetCount(int tweetCount) {
        newTweetModel.setTweetCount(tweetCount);
        notifyPropertyChanged(BR.tweetCount);
    }

    @Bindable
    public int getTweetCount() {
        return newTweetModel.getTweetCount();
    }

    public void setTweetCountText(String tweetCountText) {
        newTweetModel.setTweetCountText(tweetCountText);
        notifyPropertyChanged(BR.tweetCountText);
    }

    @Bindable
    public String getTweetCountText() {
        return newTweetModel.getTweetCountText();
    }

    @Override
    public void onCreate() {
        setTweetDescriptionChanged();
        setupSoftKeyboardListener();
        activity.getLoaderManager().initLoader(EXTERNAL_IMAGES_LOADER_ID, null, this);
    }

    private void setupSoftKeyboardListener() {
        final View contentView = activity
                .findViewById(android.R.id.content);
        contentView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            private int previousHeight;

            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                newTweetActivityBinding.tweetDescription.getWindowVisibleDisplayFrame(rect);
                int newHeight = rect.height();
                if (previousHeight != 0) {
                    if (previousHeight > newHeight) {
                        newTweetActivityBinding.tweetDescription.setHeight(
                                WizelineApp.getInstance().getPixelFromResources(R.dimen.new_tweet_description_height_keyboard_shown)
                        );
                    } else if (previousHeight < newHeight) {
                        newTweetActivityBinding.tweetDescription.setHeight(
                                WizelineApp.getInstance().getPixelFromResources(R.dimen.default_new_tweet_description_height)
                        );
                    } else {
                    }
                }
                previousHeight = newHeight;
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onDestroy() {
    }

    @Override
    public void onResume() {
    }

    private void setTweetDescriptionChanged() {
        newTweetActivityBinding.tweetDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String tweetContent = newTweetActivityBinding.tweetDescription.getText().toString();
                setTweetCountText(String.valueOf(MAX_TWEET_LENGTH - tweetContent.length()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void setShowKeyboard(boolean showKeyboard) {
        newTweetModel.setShowKeyboard(showKeyboard);
        notifyPropertyChanged(BR.showKeyboard);
    }

    @Bindable
    public boolean getShowKeyboard() {
        return newTweetModel.getShowKeyboard();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                MediaStore.Images.Media.DATA
        };
        return new CursorLoader(activity
                , MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, projection, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {
        Cursor cursor = (Cursor) data;
        if (data != null) {
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            if (cursor.moveToFirst()) {
                do {
                    imagesPath.add(cursor.getString(columnIndex));
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        phoneImagesGridVAdapter = new PhoneImagesGridVAdapter(activity, imagesPath);
        newTweetActivityBinding.gridviewCategory
                .setOnScrollListener(phoneImagesGridVAdapter.getOnScrollListener());
        newTweetActivityBinding.gridviewCategory.setAdapter(phoneImagesGridVAdapter);
    }

    @Override
    public void onLoaderReset(Loader loader) {

    }

    public void clickNewTweetButton(@NonNull final View view) {
//        showLoading();
        if (apiServices != null) {
            Call<ResponseBody> call = apiServices.postNewTweet(getTweetDescription());
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                hideLoading();
                    if (response.isSuccessful()) {
                        activity.setResult(Activity.RESULT_OK);
                    } else {
                        activity.setResult(Activity.RESULT_CANCELED);
                    }
                    activity.finish();
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable throwable) {
//                hideLoading();
                    activity.setResult(Activity.RESULT_CANCELED);
                    activity.finish();
                }
            });
        }
    }

    public String getTweetDescription() {
        return newTweetActivityBinding.tweetDescription.getText().toString();
    }
}
