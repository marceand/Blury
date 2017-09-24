package marceme.com.blury.dependencyinjection;

import marceme.com.blury.adapter.FeedAdapter;
import marceme.com.blury.profile.HomePresenter;
import marceme.com.blury.remote.ApiServiceFactory;
import marceme.com.blury.remote.DataManager;

/**
 * @author Marcelino Yax-marce7j@gmail.com-Android Developer
 *         Created on 9/23/2017.
 */

public class Injector {

    public static HomePresenter provideHomePresenter(){
        return new HomePresenter(provideDataManager());
    }

    private static DataManager provideDataManager() {
        return new DataManager(ApiServiceFactory.makeBluryApiService());
    }

    public static FeedAdapter provideFeedAdapter() {
        return new FeedAdapter();
    }
}
