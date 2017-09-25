package marceme.com.blury.message;

import java.util.List;

import marceme.com.blury.model.Message;

/**
 * @author Marcelino Yax-marce7j@gmail.com-Android Developer
 *         Created on 9/24/2017.
 */

public interface MessageViewController {
    void showMessages(List<Message> messages);

    void showProgressBar(boolean show);

    void showEmptyMessage();

    void showErrorMessage();
}
