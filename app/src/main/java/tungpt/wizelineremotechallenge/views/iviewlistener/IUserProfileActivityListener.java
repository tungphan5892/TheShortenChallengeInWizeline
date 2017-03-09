package tungpt.wizelineremotechallenge.views.iviewlistener;

import java.util.List;

import tungpt.wizelineremotechallenge.networks.models.Tweet;

/**
 * Created by tungphan on 3/9/17.
 */

public interface IUserProfileActivityListener {
    void finishLoadingTimeline(List<Tweet> timeline);
    void errorLoadingData();
}
