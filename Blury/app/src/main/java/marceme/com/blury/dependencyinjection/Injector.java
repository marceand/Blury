package marceme.com.blury.dependencyinjection;

import marceme.com.blury.adapter.FeedAdapter;
import marceme.com.blury.adapter.ScoreAdapter;
import marceme.com.blury.home.HomePresenter;
import marceme.com.blury.remote.ApiServiceFactory;
import marceme.com.blury.remote.DataManager;
import marceme.com.blury.scoreboard.ScoreboardPresenter;

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

    public static ScoreboardPresenter provideScoreboardPresenter() {
        return new ScoreboardPresenter(provideDataManager());
    }

    public static ScoreAdapter provideScoreAdapter() {
        return new ScoreAdapter();
    }
}
