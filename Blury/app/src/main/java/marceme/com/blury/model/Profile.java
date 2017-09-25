package marceme.com.blury.model;

import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * @author Marcelino Yax-marce7j@gmail.com-Android Developer
 *         Created on 9/23/2017.
 */

@AutoValue
public abstract class Profile {
    public abstract ImageAvatar imageAvatar();
    public abstract String name();
    public abstract String status();
    public abstract long todayPoints();
    public abstract long weekPoints();
    public abstract long totalPoints();
    public abstract int rank();

    public static TypeAdapter<Profile> typeAdapter(Gson gson) {
        return new AutoValue_Profile.GsonTypeAdapter(gson);
    }

    public static Builder builder() {
        return new AutoValue_Profile.Builder();
    }

    public String todayPointsAsString(){
        return String.valueOf(todayPoints());
    }

    public String weekPointsAsString(){
        return String.valueOf(weekPoints());
    }

    public String totalPointsAsString(){
        return String.valueOf(todayPoints());
    }

    public String rankAsString(){
        return String.valueOf(rank());
    }

    public String statusFormatted() {
        return String.format("Status: %s", status());
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder imageAvatar(@NonNull ImageAvatar value);
        public abstract Builder name(@NonNull String value);
        public abstract Builder status(@NonNull String value);
        public abstract Builder todayPoints(@NonNull long value);
        public abstract Builder weekPoints(@NonNull long value);
        public abstract Builder totalPoints(@NonNull long value);
        public abstract Builder rank(@NonNull int value);
        public abstract Profile build();
    }
}
