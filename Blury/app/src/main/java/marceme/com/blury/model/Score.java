package marceme.com.blury.model;

import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;


@AutoValue
public abstract class Score {
    public abstract ImageAvatar avatarUrl();
    public abstract String name();
    public abstract long points();

    public static TypeAdapter<Score> typeAdapter(Gson gson) {
        return new AutoValue_Score.GsonTypeAdapter(gson);
    }

    public static Builder builder() {
        return new AutoValue_Score.Builder();
    }

    public String getUrl() {
        return avatarUrl().url();
    }

    public String getScore() {
        return String.format("%s pts", points());
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder avatarUrl(@NonNull ImageAvatar value);
        public abstract Builder name(@NonNull String value);
        public abstract Builder points(@NonNull long value);
        public abstract Score build();
    }

}
