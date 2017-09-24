package marceme.com.blury.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.List;

/**
 * @author Marcelino Yax-marce7j@gmail.com-Android Developer
 *         Created on 9/24/2017.
 */
@AutoValue
public abstract class FeedResult {

    public abstract List<Feed> results();

    public static TypeAdapter<FeedResult> typeAdapter(Gson gson) {
        return new AutoValue_FeedResult.GsonTypeAdapter(gson);
    }


    public static FeedResult create(List<Feed> feeds) {
        return new AutoValue_FeedResult(feeds);
    }
}
