package tungpt.wizelineremotechallenge.views.models;

import android.graphics.Bitmap;

/**
 * Created by Tung Phan on 2/23/2017.
 */

public class PhoneImageItem {
    private Bitmap mImage;

    public PhoneImageItem(Bitmap image) {
        super();
        this.mImage = image;
    }

    public Bitmap getImage() {
        return mImage;
    }

    public void setImage(Bitmap image) {
        this.mImage = image;
    }
}
