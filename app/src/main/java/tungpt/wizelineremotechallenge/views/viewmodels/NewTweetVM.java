package tungpt.wizelineremotechallenge.views.viewmodels;


import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;

import tungpt.wizelineremotechallenge.BR;
import tungpt.wizelineremotechallenge.databinding.NewTweetActivityBinding;
import tungpt.wizelineremotechallenge.views.iviewlistener.INewTweetViewModel;
import tungpt.wizelineremotechallenge.views.models.NewTweetModel;

/**
 * Created by tungphan on 3/9/17.
 */

public class NewTweetVM extends BaseObservable implements INewTweetViewModel {
    private static final int MAX_TWEET_LENGTH = 140;
    private final NewTweetModel newTweetModel = new NewTweetModel();
    private NewTweetActivityBinding newTweetActivityBinding;
//    private boolean keyboardListenersAttached = false;
//    private int previousRootViewHeight;

//    private ViewTreeObserver.OnGlobalLayoutListener keyboardLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
//        @Override
//        public void onGlobalLayout() {
//            int newHeight = newTweetActivityBinding.newTweetRootLayout.getHeight();
//            Rect rect = new Rect();
//            newTweetActivityBinding.newTweetRootLayout.getWindowVisibleDisplayFrame(rect);
//            int screenHeight = newTweetActivityBinding.newTweetRootLayout.getRootView().getHeight();
//            int keyboardHeight = screenHeight - (rect.bottom);
//            if (previousRootViewHeight != 0) {
//                if (previousRootViewHeight > newHeight) {
//                } else if (previousRootViewHeight < newHeight) {
//                } else {
//                    Log.e("TFunk", "nothing");
//                }
//            }
//            previousRootViewHeight = newHeight;
//        }
//    };

    public NewTweetVM(NewTweetActivityBinding newTweetActivityBinding) {
        this.newTweetActivityBinding = newTweetActivityBinding;
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
        attachKeyboardListeners();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onDestroy() {
//        if (keyboardListenersAttached) {
//            newTweetActivityBinding.newTweetRootLayout.getViewTreeObserver()
//                    .removeOnGlobalLayoutListener(keyboardLayoutListener);
//        }
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

    private void attachKeyboardListeners() {
//        if (keyboardListenersAttached) {
//            return;
//        }
//        newTweetActivityBinding.newTweetRootLayout.getViewTreeObserver()
//                .addOnGlobalLayoutListener(keyboardLayoutListener);
//        keyboardListenersAttached = true;
    }

    @BindingAdapter("android:layout_height")
    public static void setLayoutHeight(View view, float height) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) height;
        view.setLayoutParams(layoutParams);
    }
}
