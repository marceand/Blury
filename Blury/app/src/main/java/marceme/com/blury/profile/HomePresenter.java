package marceme.com.blury.profile;

import java.util.List;

import marceme.com.blury.model.Feed;
import marceme.com.blury.model.Profile;
import marceme.com.blury.remote.DataManager;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * @author Marcelino Yax-marce7j@gmail.com-Android Developer
 *         Created on 9/23/2017.
 */

public class HomePresenter {

    private HomeViewController homeViewController;
    private DataManager dataManager;
    private Subscription profileSubscription;
    private Subscription feedSubscription;

    public HomePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void attachView(HomeViewController homeViewController){
        this.homeViewController = homeViewController;
    }

    public void detachView(){
        if (profileSubscription != null) profileSubscription.unsubscribe();
        homeViewController = null;
    }

    public void loadProfile() {

        homeViewController.showProfileProgressBar(true);
        if (profileSubscription != null) profileSubscription.unsubscribe();

        profileSubscription = dataManager.getProfile()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Profile>() {
                    @Override
                    public void onCompleted() {
                        homeViewController.showProfileProgressBar(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        homeViewController.showProfileProgressBar(false);
                        homeViewController.profileErrorMessage();
                    }

                    @Override
                    public void onNext(Profile profile) {
                        if(profile != null) {
                            homeViewController.showProfile(profile);
                        }else {
                            homeViewController.showProfileEmptyMessage();
                        }
                    }
                });
    }


    public void loadFeed() {
        homeViewController.showFeedProgressBar(true);
        if (feedSubscription != null) feedSubscription.unsubscribe();

         feedSubscription= dataManager.getFeeds()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                 .subscribe(new Subscriber<List<Feed>>() {
                     @Override
                     public void onCompleted() {
                        homeViewController.showFeedProgressBar(false);
                     }

                     @Override
                     public void onError(Throwable e) {
                         Timber.e(e.getMessage());
                         homeViewController.showFeedProgressBar(false);
                         homeViewController.feedErrorMessage();
                     }

                     @Override
                     public void onNext(List<Feed> feeds) {
                         if(feeds.size() > 0){
                             homeViewController.showFeeds(feeds);
                         }else {
                             homeViewController.showFeedEmptyMessage();
                         }
                     }
                 });
    }

    public void unsubscribeLoadFeed() {
        if (feedSubscription != null) feedSubscription.unsubscribe();
    }
}
