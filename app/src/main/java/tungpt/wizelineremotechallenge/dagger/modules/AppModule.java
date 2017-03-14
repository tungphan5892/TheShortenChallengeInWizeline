package tungpt.wizelineremotechallenge.dagger.modules;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import tungpt.wizelineremotechallenge.listeners.ApiServices;
import tungpt.wizelineremotechallenge.networks.RetroClient;

/**
 * Created by tungphan on 3/12/17.
 */
@Module
public class AppModule {
    private static final String ROOT_URL = "https://wizetwitterproxy.herokuapp.com";
    Application mApplication;

    public AppModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    ApiServices provideApiServices(){
       return new RetroClient(ROOT_URL).getApiServices();
    }
}
