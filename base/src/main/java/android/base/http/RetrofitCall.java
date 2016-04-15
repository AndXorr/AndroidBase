package android.base.http;

import android.base.log.Log;

import retrofit2.Call;

/**
 * Created by clickapps on 10/12/15.
 */
public class RetrofitCall<T> {
    private OnRetrofitAPI retroInterface;

    private RetrofitCall() {

    }

    public RetrofitCall(WebParam param, OnRetrofitAPI retroInterface) {
        this.retroInterface = retroInterface;
        if (param.webApi != null) {

        } else {
            Log.e(getClass().getCanonicalName(), "Set WebApi");
        }
    }

}
