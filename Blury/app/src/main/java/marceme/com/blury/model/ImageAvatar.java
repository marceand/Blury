package marceme.com.blury.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * @author Marcelino Yax-marce7j@gmail.com-Android Developer
 *         Created on 9/24/2017.
 */
@AutoValue
public abstract class ImageAvatar {
    public abstract String name();
    public abstract String url();

    public static TypeAdapter<ImageAvatar> typeAdapter(Gson gson) {
        return new AutoValue_ImageAvatar.GsonTypeAdapter(gson);
    }

    public static ImageAvatar create(String name, String url) {
        return new AutoValue_ImageAvatar(name, url);
    }
}
