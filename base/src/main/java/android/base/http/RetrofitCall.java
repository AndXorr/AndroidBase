package android.base.http;

import android.base.log.Log;

/**
 * Created by clickapps on 10/12/15.
 */
public class RetrofitCall {
    private OnRetrofitAPI onRetrofitAPI;

    private RetrofitCall() {

    }

    public RetrofitCall(WebParam param, OnRetrofitAPI onRetrofitAPI) {
        this.onRetrofitAPI = onRetrofitAPI;
        switch (param.webApi) {
        }
    }

}
