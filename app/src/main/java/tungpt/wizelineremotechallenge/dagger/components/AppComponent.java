package tungpt.wizelineremotechallenge.dagger.components;

import javax.inject.Singleton;

import dagger.Component;
import tungpt.wizelineremotechallenge.dagger.modules.AppModule;
import tungpt.wizelineremotechallenge.views.activities.featuresactivity.NewTweetActivity;
import tungpt.wizelineremotechallenge.views.activities.featuresactivity.SingleTweetActivity;
import tungpt.wizelineremotechallenge.views.activities.featuresactivity.TimelineActivity;

/**
 * Created by tungphan on 3/12/17.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(NewTweetActivity newTweetActivity);

    void inject(SingleTweetActivity singleTweetActivity);

    void inject(TimelineActivity timelineActivity);

}
