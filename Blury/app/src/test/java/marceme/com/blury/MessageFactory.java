package marceme.com.blury;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import marceme.com.blury.model.ImageAvatar;
import marceme.com.blury.model.Message;
import marceme.com.blury.model.MessageResult;

/**
 * @author Marcelino Yax-marce7j@gmail.com-Android Developer
 *         Created on 9/24/2017.
 */

public class MessageFactory {
    public static List<Message> makeMessages(int size) {

        List<Message> profiles = new ArrayList<>();

        for(int position = 0; position < size; position++){
            profiles.add(makeAMessage());
        }

        return profiles;

    }

    private static Message makeAMessage() {
        return Message.builder()
                .imageAvatar(ImageAvatar.create("www.marceme.com/marce.png","marce.png"))
                .name("Marcelino")
                .message("hello there")
                .numberOfMessage(4)
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
    }

    public static MessageResult makeMessageResult() {
        return MessageResult.create(makeMessages(10));
    }
}
