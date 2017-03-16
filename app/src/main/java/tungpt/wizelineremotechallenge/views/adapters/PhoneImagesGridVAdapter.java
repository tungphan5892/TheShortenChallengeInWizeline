package tungpt.wizelineremotechallenge.views.adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import tungpt.wizelineremotechallenge.App.WizelineApp;
import tungpt.wizelineremotechallenge.R;


/**
 * Created by Tung Phan on 2/23/2017.
 */

@TargetApi(Build.VERSION_CODES.M)
public class PhoneImagesGridVAdapter extends BaseAdapter implements AbsListView.OnScrollListener {

    private static final String FILE_PATH = "file://";
    private Context context;
    private ArrayList<String> data = new ArrayList<>();
    private final Object scrollTag = new Object();
    private int galleryImageSize;

    public AbsListView.OnScrollListener getOnScrollListener() {
        return this;
    }

    public PhoneImagesGridVAdapter(Context context, ArrayList<String> data) {
        this.context = context;
        this.data = data;
        galleryImageSize = WizelineApp.getInstance().getPixelFromResources(R.dimen.image_grid_view_size);

    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_image_grid_view, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String itemPath = data.get(position);
        Picasso.with(context)
                .load(FILE_PATH + itemPath)
//                .placeholder(R.drawable.default_placeholder)
                .resize(galleryImageSize,galleryImageSize)
                .centerCrop()
                .into(viewHolder.image);
        return convertView;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        Picasso picasso = Picasso.with(context);
        if (scrollState == SCROLL_STATE_IDLE) {
            Log.e("TFunk","SCROLL_STATE_IDLE");
            picasso.resumeTag(scrollTag);
        } else {
            Log.e("TFunk","! SCROLL_STATE_IDLE");
            picasso.pauseTag(scrollTag);
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
    }

    public static class ViewHolder {
        ImageView image;

        public ViewHolder(View itemView) {
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
