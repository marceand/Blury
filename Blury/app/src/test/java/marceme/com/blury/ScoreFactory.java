package marceme.com.blury;

import java.util.ArrayList;
import java.util.List;

import marceme.com.blury.model.ImageAvatar;
import marceme.com.blury.model.Score;
import marceme.com.blury.model.ScoreResult;

/**
 * @author Marcelino Yax-marce7j@gmail.com-Android Developer
 *         Created on 9/24/2017.
 */

public class ScoreFactory {
    public static List<Score> makeScore(int size) {
        List<Score> scores = new ArrayList<>();

        for(int position = 0; position < size; position++){
            scores.add(makeAScore());
        }

        return scores;
    }

    public static Score makeAScore() {
        return Score.builder().avatarUrl(ImageAvatar.create("www.marcem.com/marce.png","Marcelino"))
                 .name("Marcelino")
                .points(100)
                .build();
    }

    public static ScoreResult makeScoreResult() {
        return ScoreResult.create(makeScore(10));
    }
}
