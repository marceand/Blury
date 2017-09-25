package marceme.com.blury.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.List;



@AutoValue
public abstract class ScoreResult {
    public abstract List<Score> results();

    public static ScoreResult create(List<Score> scores) {
        return new AutoValue_ScoreResult(scores);
    }

    public static TypeAdapter<ScoreResult> typeAdapter(Gson gson) {
        return new AutoValue_ScoreResult.GsonTypeAdapter(gson);
    }
}
