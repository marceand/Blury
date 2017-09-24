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
public abstract class ProfileResult {

    public abstract List<Profile> results();

    public static ProfileResult create(List<Profile> profiles) {
        return new AutoValue_ProfileResult(profiles);
    }

    public static TypeAdapter<ProfileResult> typeAdapter(Gson gson) {
        return new AutoValue_ProfileResult.GsonTypeAdapter(gson);
    }
}
