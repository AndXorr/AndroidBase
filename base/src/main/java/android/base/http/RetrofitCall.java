package android.base.http;

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
