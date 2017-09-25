package marceme.com.blury;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import marceme.com.blury.model.Feed;
import marceme.com.blury.model.Profile;
import marceme.com.blury.home.HomePresenter;
import marceme.com.blury.home.HomeViewController;
import marceme.com.blury.remote.DataManager;
import rx.Observable;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyListOf;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author Marcelino Yax-marce7j@gmail.com-Android Developer
 *         Created on 9/23/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class HomePresenterTest {

    @Mock
    HomeViewController homeViewController;
    @Mock
    DataManager dataManager;
    private HomePresenter homePresenter;

    @Rule
    public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();

    @Before
    public void setUp() throws Exception {
        homePresenter = new HomePresenter(dataManager);
        homePresenter.attachView(homeViewController);
    }

    @Test
    public void loadProfileSuccessfully() throws Exception{
        Profile profile = HomeFactory.makeAProfile();
        stubDataManagerGetProfile(Observable.just(profile));
        homePresenter.loadProfile();
        verify(homeViewController, times(1)).showProfile(profile);
        verify(homeViewController, times(1)).showProfileProgressBar(false);
    }

    @Test
    public void loadProfileEmpty() throws Exception{
        Profile profile = null;
        stubDataManagerGetProfile(Observable.just(profile));
        homePresenter.loadProfile();
        verify(homeViewController, never()).showProfile(profile);
        verify(homeViewController, times(1)).showProfileEmptyMessage();
        verify(homeViewController, times(1)).showProfileProgressBar(false);
    }

    @Test
        public void loadProfileFail() throws Exception{
        stubDataManagerGetProfile(Observable.error(new RuntimeException()));
        homePresenter.loadProfile();
        verify(homeViewController, never()).showProfile(any(Profile.class));
        verify(homeViewController, times(1)).profileErrorMessage();
        verify(homeViewController, times(1)).showProfileProgressBar(false);
    }

    @Test
    public void loadFeedSuccessfully() throws Exception{
        List<Feed> feeds = HomeFactory.makeFeeds(10);
        stubDataManagerGetFeeds(Observable.just(feeds));
        homePresenter.loadFeed();
        verify(homeViewController, times(1)).showFeeds(feeds);
        verify(homeViewController, times(1)).showFeedProgressBar(false);
    }

    @Test
    public void loadFeedEmpty() throws Exception{
        List<Feed> feeds = new ArrayList<>();
        stubDataManagerGetFeeds(Observable.just(feeds));
        homePresenter.loadFeed();
        verify(homeViewController, never()).showFeeds(feeds);
        verify(homeViewController, times(1)).showFeedEmptyMessage();
        verify(homeViewController, times(1)).showFeedProgressBar(false);
    }

    @Test
    public void loadFeedFail() throws Exception{
        stubDataManagerGetFeeds(Observable.error(new RuntimeException()));
        homePresenter.loadFeed();
        verify(homeViewController, never()).showFeeds(anyListOf(Feed.class));
        verify(homeViewController, times(1)).feedErrorMessage();
        verify(homeViewController, times(1)).showFeedProgressBar(false);
    }

    private void stubDataManagerGetFeeds(Observable observable) {
        doReturn(observable)
                .when(dataManager)
                .getFeeds();
    }

    private void stubDataManagerGetProfile(Observable observable) {
        doReturn(observable)
                .when(dataManager)
                .getProfile();
    }

}
