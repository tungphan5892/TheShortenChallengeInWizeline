package tungpt.wizelineremotechallenge.views.iviewlistener;

import tungpt.wizelineremotechallenge.networks.models.User;

/**
 * Created by tungphan on 3/9/17.
 */

public interface ILeftDrawerMenuListener {

    void errorLoadingData();
    void successLoadingData(User user);
}
