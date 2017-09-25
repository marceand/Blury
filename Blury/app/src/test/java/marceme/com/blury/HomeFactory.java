package marceme.com.blury;

import java.util.ArrayList;
import java.util.List;

import marceme.com.blury.model.Feed;
import marceme.com.blury.model.FeedResult;
import marceme.com.blury.model.ImageAvatar;
import marceme.com.blury.model.Profile;
import marceme.com.blury.model.ProfileResult;

/**
 * @author Marcelino Yax-marce7j@gmail.com-Android Developer
 *         Created on 9/23/2017.
 */

public class HomeFactory {

    public static List<Profile> makeProfile(int size) {
        List<Profile> profiles = new ArrayList<>();

        for(int position = 0; position < size; position++){
            profiles.add(makeAProfile());
        }

        return profiles;
    }

    public static Profile makeAProfile() {
        return Profile.builder()
                .imageAvatar(makeAvatarUrl())
                .name("Marcelino Yax")
                .status("Online")
                .todayPoints(126)
                .weekPoints(543)
                .totalPoints(4300)
                . rank(3)
                .build();
    }

    private static ImageAvatar makeAvatarUrl() {
        return ImageAvatar.create("marcelino", "www.marceme.com/marce.png");
    }

    public static List<Feed> makeFeeds(int size) {
        List<Feed> feeds = new ArrayList<>();

        for (int position = 0; position < size; position++){
            feeds.add(makeAFeed());
        }
        return feeds;
    }

    private static Feed makeAFeed() {
        return Feed.builder().imageAvatar(makeAvatarUrl())
                .name("Pedro")
                .message("Is rainy?")
                .build();
    }

    public static ProfileResult makeAProfileResult() {
        return ProfileResult.create(makeProfile(1));
    }

    public static FeedResult makeFeedResult() {
        return FeedResult.create(makeFeeds(10));
    }
}
