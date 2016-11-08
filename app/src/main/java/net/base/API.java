package net.base;

import android.base.http.RetrofitUtil;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;


/**
 * Created by clickapps on 18/2/16.
 */
public interface API {

    @GET("config_file")
    @Streaming
    Call<ConfigModel> getConfigFileStream();
}
