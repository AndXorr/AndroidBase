package android.base.http;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;

/**
 * Created by clickapps on 19/11/15.
 */
public interface OnRetrofitAPI {

    @GET("config_file")
    @Streaming
    Call<Object> getConfigFileStream();
}
