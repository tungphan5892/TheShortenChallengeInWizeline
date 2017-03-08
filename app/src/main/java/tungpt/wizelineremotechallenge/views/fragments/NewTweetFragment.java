package tungpt.wizelineremotechallenge.views.fragments;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import java.util.ArrayList;

import tungpt.wizelineremotechallenge.R;
import tungpt.wizelineremotechallenge.views.adapters.PhoneImagesGridVAdapter;

/**
 * Created by Tung Phan on 2/23/2017.
 */

public class NewTweetFragment extends Fragment {
    private static final String TAG = NewTweetFragment.class.getSimpleName();
    private GridView mGalleryImagesGridView;
    private View mParentView;
    private Button mCancelButton;
    private Button mTweetButton;
    private EditText mTweetEdittext;
    private NewTweetFragmentInterface mNewTweetFragmentInterface;

    public void setNewTweetFragmentInterface(NewTweetFragmentInterface newTweetFragmentInterface){
        mNewTweetFragmentInterface = newTweetFragmentInterface;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mParentView = inflater.inflate(R.layout.fragment_new_tweet, container, false);
        return mParentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mGalleryImagesGridView = (GridView) mParentView.findViewById(R.id.gallery_images_grid_view);
        mCancelButton = (Button) mParentView.findViewById(R.id.cancel_btn);
        mTweetButton = (Button) mParentView.findViewById(R.id.tweet_btn);
        mTweetEdittext = (EditText) mParentView.findViewById(R.id.editext_tweet);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNewTweetFragmentInterface.pressCancelButton();
            }
        });
        mTweetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postTweet();
            }
        });
        PhoneImagesGridVAdapter phoneImagesGridVAdapter = new PhoneImagesGridVAdapter(getContext()
                , getAllShownImagesPath(getActivity()));
        mGalleryImagesGridView.setAdapter(phoneImagesGridVAdapter);
    }

    private void postTweet(){
//        String newTweet = mTweetEdittext.getText().toString();
//        ApiServices apiServiceUser = RetroClient.getApiServices();
//        Call<ResponseBody> call = apiServiceUser.postNewTweet("test");
//        call.enqueue(newTweet);
    }

    public static ArrayList<String> getAllShownImagesPath(Activity activity) {
        Uri uri;
        Cursor cursor;
        int column_index_data; //column_index_folder_name
        ArrayList<String> listOfAllImages = new ArrayList<String>();
        String absolutePathOfImage = null;
        try {
            uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            String[] projection = {MediaStore.MediaColumns.DATA,
                    MediaStore.Images.Media.BUCKET_DISPLAY_NAME};
            cursor = activity.getContentResolver().query(uri, projection, null,
                    null, null);
            column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
//        column_index_folder_name = cursor
//                .getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
            while (cursor.moveToNext()) {
                absolutePathOfImage = cursor.getString(column_index_data);
                listOfAllImages.add(absolutePathOfImage);
            }
        } catch (Exception ex) {
            Log.e(TAG,ex.getMessage());
        }
        return listOfAllImages;
    }

    public interface NewTweetFragmentInterface{
        void pressCancelButton();
    }
}
