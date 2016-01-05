package android.base.http;

import android.base.R;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Map;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

/**
 * Created by clickapps on 18/11/15.
 */
public class RetrofitUtil {
    private final String BASE_URL = WebConstant.BASE_URL;
    private final long CONNECT_TIMEOUT_MILLIS = 10 * 1000, READ_TIMEOUT_MILLIS = 20 * 1000;
    private Gson gson = new GsonBuilder()
            .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
            .create();
    private RestAdapter.Builder builder = new RestAdapter.Builder()
            .setConverter(new GsonConverter(gson))
            .setConverter(new StringConverter())
            .setLogLevel(RestAdapter.LogLevel.FULL)
            .setEndpoint(BASE_URL);

    public RetrofitUtil(WebParam webParam) {
        OnRetrofitAPI onRetrofitAPI = createService(OnRetrofitAPI.class, webParam);
        if (!TextUtils.isEmpty(webParam.url)) {
            if (webParam.showDialog) {
                webParam.progressDialog = WebConnectUtils.resolveProgressDialog(webParam);
                webParam.progressDialog.show();
            } else {
                webParam.progressDialog = null;
            }
            new RetrofitCall(webParam, onRetrofitAPI);
        } else {
            Log.e(getClass().getSimpleName(), "Enter Valid url");
        }
    }

    // Retrofit Adapter
    public <T> T createService(Class<T> serviceClass, final WebParam webParam) {
        if (webParam.headerParam != null && webParam.headerParam.size() > 0) {
            builder.setRequestInterceptor(new RequestInterceptor() {
                @Override
                public void intercept(RequestFacade request) {
                    for (Map.Entry<String, String> entry : webParam.headerParam.entrySet()) {
                        request.addHeader(entry.getKey(), entry.getValue());
                    }
                }
            });
        }
        RestAdapter restAdapter = builder.build();
        return restAdapter.create(serviceClass);
    }

    public class StringConverter implements Converter {

        public Object fromBody(TypedInput typedInput, Type type)
                throws ConversionException {

            String text = "";
            try {
                text = fromStream(typedInput.in());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return text;
        }

        @Override
        public TypedOutput toBody(Object o) {
            return null;
        }

        // Custom method to convert stream from request to string
        public String fromStream(InputStream in) throws IOException {
            return IOUtils.toString(in);
        }
    }

    // Retrofit Call
    public static class CallBack<T> implements Callback<String> {
        private WebParam webParam;

        public CallBack(WebParam webParam) {
            this.webParam = webParam;
        }


        @Override
        public void success(String res, Response response) {
            try {
                Object object;
                if (webParam.model != null) {
                    object = new Gson().fromJson(res, webParam.model);
                } else {
                    object = new Gson().fromJson(res, Object.class);
                }
                if (webParam.callback != null) {
                    webParam.callback.onSuccess(object, res, webParam.taskId, response.getStatus());
                }
                if (webParam.showDialog && webParam.progressDialog != null && webParam.progressDialog.isShowing()) {
                    webParam.progressDialog.dismiss();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void failure(RetrofitError error) {
            Response response = error.getResponse();
            if (response != null && response.getBody() != null) {
                if (webParam.callback != null) {
                    String errorResponse = (String) error.getBodyAs(String.class);
                    webParam.callback.onError(null, errorResponse, webParam.taskId, response.getStatus());
                }
            } else {
                if (webParam.callback != null) {
                    String errors;
                    if (error.getKind().equals(RetrofitError.Kind.NETWORK)) {
                        errors = webParam.context.getString(R.string.error_internet_connection);
                    } else if (error.getKind().equals(RetrofitError.Kind.HTTP)) {
                        errors = webParam.context.getString(R.string.error_server_connection);
                    } else {
                        errors = error.getMessage();
                    }
                    webParam.callback.onError(null, errors, webParam.taskId, -1);
                }
            }
            if (webParam.showDialog && webParam.progressDialog != null && webParam.progressDialog.isShowing()) {
                webParam.progressDialog.dismiss();
            }
        }
    }

}
