package marceme.com.blury;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import marceme.com.blury.model.Score;
import marceme.com.blury.remote.DataManager;
import marceme.com.blury.scoreboard.ScoreboardPresenter;
import marceme.com.blury.scoreboard.ScoreboardViewController;
import rx.Observable;

import static org.mockito.ArgumentMatchers.anyListOf;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author Marcelino Yax-marce7j@gmail.com-Android Developer
 *         Created on 9/24/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class ScoreboardPresenterTest {

    @Mock
    ScoreboardViewController scoreboardViewController;
    @Mock
    DataManager dataManager;

    @Rule
    public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();

    private ScoreboardPresenter scoreboardPresenter;

    @Before
    public void setUp() throws Exception {
         scoreboardPresenter = new ScoreboardPresenter(dataManager);
        scoreboardPresenter.attachView(scoreboardViewController);
    }

    @Test
    public void loadScoreSuccessfully() throws Exception{

        List<Score> scores = ScoreFactory.makeScore(10);
        stubDataManagerGetScores(Observable.just(scores));

        scoreboardPresenter.loadScore();

        verify(scoreboardViewController, times(1)).showScore(scores);
        verify(scoreboardViewController, times(1)).showProgressBar(false);
    }

    @Test
    public void loadScoreEmpty() throws Exception{
        List<Score> scores = new ArrayList<>();
        stubDataManagerGetScores(Observable.just(scores));

        scoreboardPresenter.loadScore();

        verify(scoreboardViewController, never()).showScore(scores);
        verify(scoreboardViewController, times(1)).showEmptyMessage();
        verify(scoreboardViewController, times(1)).showProgressBar(false);

    }

    @Test
    public void loadScoreFail() throws Exception{
        stubDataManagerGetScores(Observable.error(new RuntimeException()));

        scoreboardPresenter.loadScore();

        verify(scoreboardViewController, never()).showScore(anyListOf(Score.class));
        verify(scoreboardViewController, times(1)).errorMessage();
        verify(scoreboardViewController, times(1)).showProgressBar(false);
    }

    private void stubDataManagerGetScores(Observable observable) {
        doReturn(observable)
                .when(dataManager)
                .getScore();
    }
}
