package marceme.com.blury;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import marceme.com.blury.model.Feed;
import marceme.com.blury.model.FeedResult;
import marceme.com.blury.model.Profile;
import marceme.com.blury.model.ProfileResult;
import marceme.com.blury.model.Score;
import marceme.com.blury.model.ScoreResult;
import marceme.com.blury.remote.BluryApiService;
import marceme.com.blury.remote.DataManager;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Mockito.doReturn;

/**
 * @author Marcelino Yax-marce7j@gmail.com-Android Developer
 *         Created on 9/23/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class DataManagerTest {

    @Mock
    BluryApiService bluryApiService;
    private DataManager dataManager;

    @Before
    public void setUp() throws Exception {
        dataManager = new DataManager(bluryApiService);
    }

    @Test
    public void getProfile() throws Exception{

        ProfileResult profileResult = HomeFactory.makeAProfileResult();

        doReturn(Observable.just(profileResult))
                .when(bluryApiService)
                .geProfile();

        TestSubscriber<Profile> testSubscriber = new TestSubscriber<>();

        dataManager.getProfile().subscribe(testSubscriber);

        testSubscriber.assertCompleted();
        testSubscriber.assertValueCount(1);
        testSubscriber.assertValue(profileResult.results().get(0));
    }

    @Test
    public void getFeed() throws Exception{

        FeedResult feedResult= HomeFactory.makeFeedResult();

        doReturn(Observable.just(feedResult))
                .when(bluryApiService)
                .geFeed();

        TestSubscriber<List<Feed>> testSubscriber = new TestSubscriber<>();

        dataManager.getFeeds().subscribe(testSubscriber);

        testSubscriber.assertCompleted();
        testSubscriber.assertValueCount(1);
        testSubscriber.assertValue(feedResult.results());
    }

    @Test
    public void getScore() throws Exception{
        ScoreResult scoreResult= ScoreFactory.makeScoreResult();

        doReturn(Observable.just(scoreResult))
                .when(bluryApiService)
                .geScore();

        TestSubscriber<List<Score>> testSubscriber = new TestSubscriber<>();
        dataManager.getScore().subscribe(testSubscriber);

        testSubscriber.assertCompleted();
        testSubscriber.assertValueCount(1);
        testSubscriber.assertValue(scoreResult.results());
    }
}
