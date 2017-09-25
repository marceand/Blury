package marceme.com.blury.model;

/**
 * @author Marcelino Yax-marce7j@gmail.com-Android Developer
 *         Created on 9/24/2017.
 */

public class Chat {
    private Notifier notifier;
    private String message;

    public Chat(Notifier notifier, String message) {
        this.notifier = notifier;
        this.message = message;
    }

    public Notifier getNotifier() {
        return notifier;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
