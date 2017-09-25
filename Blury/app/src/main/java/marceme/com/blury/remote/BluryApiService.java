package marceme.com.blury.remote;


import java.util.List;

import marceme.com.blury.model.Feed;
import marceme.com.blury.model.FeedResult;
import marceme.com.blury.model.MessageResult;
import marceme.com.blury.model.Profile;
import marceme.com.blury.model.ProfileResult;
import marceme.com.blury.model.ScoreResult;
import retrofit2.http.GET;
import rx.Observable;

/**
 * @author Marcelino Yax-marce7j@gmail.com-Android Developer
 *         Created on 9/23/2017.
 */

public interface BluryApiService {

    String PARSE_BASE_URL = "https://parseapi.back4app.com/";

    @GET("classes/profile")
    Observable<ProfileResult> getProfile();

    @GET("classes/feed")
    Observable<FeedResult> getFeed();

    @GET("classes/score")
    Observable<ScoreResult> getScore();

    @GET("classes/messages")
    Observable<MessageResult> getMessages();
}
