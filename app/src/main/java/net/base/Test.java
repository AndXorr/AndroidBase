package net.base;

import android.base.http.Builder;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by sahni on 24/8/16.
 */
public interface Test {

    @GET("buyer/auth/validate")
    Call<Object> login(@Body Map<String, ?> request);


    public enum Testo {
        LOGIN() {
            @Override
            public void execute(Builder client) {
            }
        };

        public abstract void execute(Builder client);
    }

}
