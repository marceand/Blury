package marceme.com.blury.scoreboard;

import java.util.List;

import marceme.com.blury.model.Score;

/**
 * @author Marcelino Yax-marce7j@gmail.com-Android Developer
 *         Created on 9/24/2017.
 */

public interface ScoreboardViewController {

    void showProgressBar(boolean show);

    void showScore(List<Score> scores);

    void showEmptyMessage();

    void errorMessage();
}
