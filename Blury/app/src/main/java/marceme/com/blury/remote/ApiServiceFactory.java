package marceme.com.blury.remote;

import com.google.gson.GsonBuilder;

import java.io.IOException;

import marceme.com.blury.model.AutoValueGsonFactory;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Marcelino Yax-marce7j@gmail.com-Android Developer
 *         Created on 9/23/2017.
 */

public class ApiServiceFactory {

    private static final String PARSE_APPLICATION_ID_NAME = "X-Parse-Application-Id";
    private static final String PARSE_CLIENT_KEY_NAME = "X-Parse-Client-Key";
    private static final String PARSE_APPLICATION_ID = "";
    private static final String PARSE_CLIENT_KEY ="";

    public static BluryApiService makeBluryApiService(){
        return new Retrofit.Builder()
                .client(makeAClient(PARSE_APPLICATION_ID, PARSE_CLIENT_KEY))
                .baseUrl(BluryApiService.PARSE_BASE_URL)
                .addConverterFactory(gsonConverterFactory())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(BluryApiService.class);
    }

    private static OkHttpClient makeAClient(final String parseApplicationId, final String parseClientKey) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        .header(PARSE_APPLICATION_ID_NAME, parseApplicationId)
                        .header(PARSE_CLIENT_KEY_NAME, parseClientKey);

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        return httpClient.build();
    }


    private static GsonConverterFactory gsonConverterFactory() {
        return GsonConverterFactory.create(
                new GsonBuilder().registerTypeAdapterFactory(AutoValueGsonFactory.create())
                        .create());

    }
}
