package tungpt.wizelineremotechallenge.views.models;

import android.graphics.Bitmap;

/**
 * Created by tungphan on 3/14/17.
 */

public class ImageItem {
    private Bitmap image;

    public ImageItem(Bitmap image) {
        super();
        this.image = image;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
