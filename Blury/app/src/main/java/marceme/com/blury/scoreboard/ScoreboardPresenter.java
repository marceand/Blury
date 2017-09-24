package marceme.com.blury.scoreboard;

import java.util.List;

import marceme.com.blury.model.Score;
import marceme.com.blury.remote.DataManager;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * @author Marcelino Yax-marce7j@gmail.com-Android Developer
 *         Created on 9/24/2017.
 */

public class ScoreboardPresenter {

    private DataManager dataManager;
    private ScoreboardViewController scoreboardViewController;
    private Subscription scoreSubscription;

    public ScoreboardPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void attachView(ScoreboardViewController scoreboardViewController) {
        this.scoreboardViewController = scoreboardViewController;
    }

    public void detachView() {
        this.scoreboardViewController = null;
    }

    public void loadScore() {
        scoreboardViewController.showProgressBar(true);

        unsubscribeScore();

        scoreSubscription = dataManager.getScore()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<Score>>() {
                    @Override
                    public void onCompleted() {
                        scoreboardViewController.showProgressBar(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        scoreboardViewController.showProgressBar(false);
                        scoreboardViewController.errorMessage();
                    }

                    @Override
                    public void onNext(List<Score> scores) {
                        if(scores.size() > 0) {
                            scoreboardViewController.showScore(scores);
                        }else {
                            scoreboardViewController.showEmptyMessage();
                        }
                    }
                });

    }

    private void unsubscribeScore() {
        if(scoreSubscription != null){
            scoreSubscription.unsubscribe();
        }
    }


    public void stopLoadScore() {
        if(scoreSubscription != null){
            scoreSubscription.unsubscribe();
        }
    }
}
