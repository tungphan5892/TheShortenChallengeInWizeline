package tungpt.wizelineremotechallenge.views.iviewlistener;

import android.content.Intent;

/**
 * Created by tungphan on 3/16/17.
 */

public interface ISingleTweetViewModel {

    void onActivityResult(int requestCode, int resultCode, Intent data);

    void retreivedIntentFromOtherActivity(Intent intent);

}
