package marceme.com.blury.model;

import android.support.annotation.NonNull;
import android.text.format.DateUtils;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.Date;

import marceme.com.blury.Util.Helper;

/**
 * @author Marcelino Yax-marce7j@gmail.com-Android Developer
 *         Created on 9/23/2017.
 */
@AutoValue
public abstract class Feed {
    public abstract ImageAvatar imageAvatar();
    public abstract String name();
    public abstract String message();
    public abstract Date createdAt();
    public abstract Date updatedAt();


    public static TypeAdapter<Feed> typeAdapter(Gson gson) {
        return new AutoValue_Feed.GsonTypeAdapter(gson);
    }

    public static Builder builder() {
        return new AutoValue_Feed.Builder();
    }

    public String date(){
        return Helper.transformToSpanTime(createdAt().getTime());
    }

    public String getUrl() {
        return imageAvatar().url();
    }

    public String messageFormatted() {
        return String.format("%s \"%s\"",name(), message());
    }


    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder imageAvatar(@NonNull ImageAvatar value);
        public abstract Builder name(@NonNull String value);
        public abstract Builder message(@NonNull String value);
        public abstract Builder createdAt(@NonNull Date value);
        public abstract Builder updatedAt(@NonNull Date value);
        public abstract Feed build();
    }
}
