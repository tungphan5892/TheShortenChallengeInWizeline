package tungpt.wizelineremotechallenge.listeners;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import tungpt.wizelineremotechallenge.networks.models.Tweet;
import tungpt.wizelineremotechallenge.networks.models.User;

/**
 * Created by Tung Phan on 2/17/2017.
 */

public interface ApiServices {

    @GET("/api/statuses/user_timeline")
    Call<List<Tweet>> getUserTimelineJson();

    @GET("/api/user")
    Call<User> getUserJson();

    @FormUrlEncoded
    @POST("/api/statuses/update")
    Call<ResponseBody> postNewTweet(@Field("status") String status);
}
