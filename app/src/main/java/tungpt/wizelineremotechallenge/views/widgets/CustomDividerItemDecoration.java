package tungpt.wizelineremotechallenge.views.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import tungpt.wizelineremotechallenge.R;
import tungpt.wizelineremotechallenge.utils.Utils;

/**
 * Created by Tung Phan on 2/15/2017.
 */

public class CustomDividerItemDecoration extends RecyclerView.ItemDecoration {
    private Drawable mDivider;

    public CustomDividerItemDecoration(Context context) {
        if(Utils.isLowerThanLollipop()) {
            mDivider = context.getResources().getDrawable(R.drawable.line_divider);
        }else{
            mDivider = context.getResources().getDrawable(R.drawable.line_divider,null);
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }
}
