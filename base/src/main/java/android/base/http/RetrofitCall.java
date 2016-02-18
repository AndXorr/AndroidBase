package android.base.http;

/**
 * Created by clickapps on 10/12/15.
 */
public class RetrofitCall<T> {
    private T retroInterface;

    private RetrofitCall() {

    }

    public RetrofitCall(WebParam param, T retroInterface) {
        this.retroInterface = retroInterface;
        switch (param.webApi) {
        }
    }

}
