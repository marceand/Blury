package marceme.com.blury.model;

import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.Date;

import marceme.com.blury.util.Helper;

/**
 * @author Marcelino Yax-marce7j@gmail.com-Android Developer
 *         Created on 9/24/2017.
 */

@AutoValue
public abstract class Message {
    public abstract ImageAvatar imageAvatar();
    public abstract String name();
    public abstract String message();
    public abstract int numberOfMessage();
    public abstract Date createdAt();
    public abstract Date updatedAt();


    public static TypeAdapter<Message> typeAdapter(Gson gson) {
        return new AutoValue_Message.GsonTypeAdapter(gson);
    }
    public static Builder builder() {
        return new AutoValue_Message.Builder();
    }

    public String getUrl() {
        return imageAvatar().url();
    }

    public String getDate() {
        return Helper.transformToSpanTime(createdAt().getTime());
    }

    public String getCountAsString() {
        return String.valueOf(numberOfMessage());
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder imageAvatar(@NonNull ImageAvatar value);
        public abstract Builder name(@NonNull String value);
        public abstract Builder message(@NonNull String value);
        public abstract Builder numberOfMessage(int value);
        public abstract Builder createdAt(@NonNull Date value);
        public abstract Builder updatedAt(@NonNull Date value);
        public abstract Message build();
    }
}
