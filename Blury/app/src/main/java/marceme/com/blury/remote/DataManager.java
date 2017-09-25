package marceme.com.blury.remote;

import java.util.List;

import marceme.com.blury.model.Feed;
import marceme.com.blury.model.FeedResult;
import marceme.com.blury.model.Message;
import marceme.com.blury.model.MessageResult;
import marceme.com.blury.model.Profile;
import marceme.com.blury.model.ProfileResult;
import marceme.com.blury.model.Score;
import marceme.com.blury.model.ScoreResult;
import rx.Observable;
import rx.functions.Func1;

/**
 * @author Marcelino Yax-marce7j@gmail.com-Android Developer
 *         Created on 9/23/2017.
 */

public class DataManager {

    private BluryApiService bluryApiService;


    public DataManager(BluryApiService bluryApiService) {
        this.bluryApiService = bluryApiService;
    }

    public Observable<Profile> getProfile() {
        return bluryApiService.getProfile().map(new Func1<ProfileResult, Profile>() {
            @Override
            public Profile call(ProfileResult profileResult) {
                return profileResult.results().get(0);
            }
        });
    }

    public Observable<List<Feed>> getFeeds() {
        return bluryApiService.getFeed().map(new Func1<FeedResult, List<Feed>>() {
            @Override
            public List<Feed> call(FeedResult feedResult) {
                return feedResult.results();
            }
        });
    }

    public Observable<List<Score>> getScore() {
        return bluryApiService.getScore().map(new Func1<ScoreResult, List<Score>>() {
            @Override
            public List<Score> call(ScoreResult scoreResult) {
                return scoreResult.results();
            }
        });
    }

    public Observable<List<Message>> getMessages() {
        return bluryApiService.getMessages().map(new Func1<MessageResult, List<Message>>() {
            @Override
            public List<Message> call(MessageResult messageResult) {
                return messageResult.results();
            }
        });
    }
}
