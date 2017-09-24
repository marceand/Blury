package marceme.com.blury.profile;

import java.util.List;

import marceme.com.blury.model.Feed;
import marceme.com.blury.model.Profile;

/**
 * @author Marcelino Yax-marce7j@gmail.com-Android Developer
 *         Created on 9/23/2017.
 */

public interface HomeViewController {
    void showProfile(Profile profile);

    void showProfileEmptyMessage();

    void showProfileProgressBar(boolean show);

    void profileErrorMessage();

    void showFeedProgressBar(boolean show);

    void showFeeds(List<Feed> feeds);

    void showFeedEmptyMessage();

    void feedErrorMessage();
}
