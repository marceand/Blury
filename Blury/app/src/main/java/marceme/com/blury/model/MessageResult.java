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
public abstract class MessageResult {
    public abstract List<Message> results();

    public static MessageResult create(List<Message> messages) {
        return new AutoValue_MessageResult(messages);
    }

    public static TypeAdapter<MessageResult> typeAdapter(Gson gson) {
        return new AutoValue_MessageResult.GsonTypeAdapter(gson);
    }
}
