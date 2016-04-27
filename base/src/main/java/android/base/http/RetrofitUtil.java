package android.base.http;


import android.base.util.ApplicationUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by clickapps on 20/4/16.
 */
public class RetrofitUtil {
    private String BASE_URL = WebConstant.BASE_URL;
    private final long CONNECT_TIMEOUT_MILLIS = 10 * 1000, READ_TIMEOUT_MILLIS = 20 * 1000;
    private static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
            .create();
    private OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
    private HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    private Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(StringConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson));


    public RetrofitUtil() {

    }

    public <T> T createService(Class<T> interfaceFile, final WebParam webParam) {
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClientBuilder.connectTimeout(CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
        okHttpClientBuilder.readTimeout(READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
        okHttpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (webParam.headerParam != null && webParam.headerParam.size() > 0) {
                    for (Map.Entry<String, String> entry : webParam.headerParam.entrySet()) {
                        request = request.newBuilder().addHeader(entry.getKey(), entry.getValue()).build();
                    }
                }
                return chain.proceed(request);
            }
        });
        okHttpClientBuilder.addInterceptor(interceptor);
        builder.client(okHttpClientBuilder.build());
        if (!ApplicationUtils.Validator.isEmptyOrNull(webParam.baseUrl)) {
            builder.baseUrl(webParam.baseUrl);
        }
        Retrofit retrofit = builder.build();
        if (webParam.showDialog) {
            webParam.progressDialog = WebConnectUtils.resolveProgressDialog(webParam);
            webParam.progressDialog.show();
        } else {
            webParam.progressDialog = null;
        }
        return retrofit.create(interfaceFile);
    }

    public static class CallBack<T> implements Callback<T> {
        private WebParam webParam;

        public CallBack(WebParam webParam) {
            this.webParam = webParam;
        }

        @Override
        public void onResponse(Call<T> call, retrofit2.Response<T> response) {
            dismissDialog(webParam);
            Object object;
            String res;
            if (response.isSuccessful()) {
                res = response.body().toString();
                if (webParam.callback != null) {
                    if (webParam.model != null) {
                        object = gson.fromJson(res, webParam.model);
                    } else {
                        object = gson.fromJson(res, Object.class);
                    }
                    webParam.callback.onSuccess(object, res, webParam.taskId, response.code());
                }
            } else {
                try {
                    res = response.errorBody().string();
                    if (webParam.callback != null) {
                        if (webParam.error != null) {
                            object = gson.fromJson(res, webParam.error);
                        } else {
                            object = gson.fromJson(res, Object.class);
                        }
                        webParam.callback.onError(object, res, webParam.taskId, response.code());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onFailure(Call<T> call, Throwable t) {
            dismissDialog(webParam);
            if (webParam.callback != null) {
                webParam.callback.onError(t.getMessage(), t.getMessage(), webParam.taskId, 0);
            }
        }
    }

    public static void dismissDialog(WebParam webParam) {
        if (webParam.showDialog &&
                webParam.progressDialog != null &&
                webParam.progressDialog.isShowing()) {
            webParam.progressDialog.dismiss();
        }
    }

    public static final class StringConverterFactory extends Converter.Factory {

        public static StringConverterFactory create() {
            return new StringConverterFactory();
        }

        @Override
        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
            return new ConfigurationServiceConverter();
        }

        final class ConfigurationServiceConverter implements Converter<ResponseBody, String> {

            @Override
            public String convert(ResponseBody value) throws IOException {
                BufferedReader r = new BufferedReader(new InputStreamReader(value.byteStream()));
                StringBuilder total = new StringBuilder();
                String line;
                while ((line = r.readLine()) != null) {
                    total.append(line);
                }
                return total.toString();
            }
        }
    }
}
