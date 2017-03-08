package tungpt.wizelineremotechallenge.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import tungpt.wizelineremotechallenge.R;


/**
 * Created by Tung Phan on 2/23/2017.
 */

public class PhoneImagesGridVAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<String> mData = new ArrayList<>();

    public PhoneImagesGridVAdapter(Context context, ArrayList<String> data) {
        mContext = context;
        mData = data;
    }

    public int getCount() {
        return mData.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_image_grid_view, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String itemPath = mData.get(position);
        Picasso.with(mContext)
                .load(itemPath)
                .centerCrop()
                .fit()
                .placeholder(R.drawable.face)
                .into(viewHolder.image);
        return convertView;
    }

    public static class ViewHolder {
        ImageView image;

        public ViewHolder(View itemView) {
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
