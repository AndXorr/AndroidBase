package android.base.http;


import android.base.log.Log;

import com.google.common.base.Optional;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by Amit Sahni on 25-06-2015.
 */
public class OKHTTPConnect {
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    private static final MediaType MEDIA_TYPE_IMAGE = MediaType.parse("image/*");
    private OkHttpClient client = new OkHttpClient();

    public OKHTTPConnect(WebParam param) {
        OkHttpClient.Builder builder = client.newBuilder();
        builder.connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS);
        if (param.cacheResponse) {
            Cache cache = new Cache(param.cacheDir, param.cacheSize);
            builder.cache(cache);
        }
        client = builder.build();
        if (param.isJson) {
            json(param);
        } else if (param.isMultipart) {
            multipartParam(param);
        } else {
            bodyParam(param);
        }
    }

    /**
     * This method is used to sending data in json
     *
     * @param webParam set WebParam
     */
    public void json(WebParam webParam) {
        String json = new JSONObject(webParam.requestParam).toString();
        RequestBody body = RequestBody.create(JSON, json);
        Request request = null;
        Request.Builder builder = new Request.Builder();
        switch (webParam.httpType) {
            case POST:
                builder.url(webParam.url).post(body);
                if (webParam.headerParam.size() > 0) {
                    Headers headers = Headers.of(webParam.headerParam);
                    builder.headers(headers);
                }
                request = builder.build();
                break;
            case PUT:
                builder.url(webParam.url).put(body);
                if (webParam.headerParam.size() > 0) {
                    Headers headers = Headers.of(webParam.headerParam);
                    builder.headers(headers);
                }
                request = builder.build();
                break;
            case DELETE:
                builder.url(webParam.url).delete(body);
                if (webParam.headerParam.size() > 0) {
                    Headers headers = Headers.of(webParam.headerParam);
                    builder.headers(headers);
                }
                request = builder.build();
                break;
        }
        if (request == null)
            return;
        client.newCall(request).enqueue(new Callback(webParam));
    }

    /**
     * This method is used to sending data in body parameters
     *
     * @param webParam set WebParam
     */
    public void bodyParam(WebParam webParam) {
        Request request = null;
        FormBody.Builder formBuilder = new FormBody.Builder();
        Request.Builder builder = new Request.Builder();
        switch (webParam.httpType) {
            case GET:
                builder.url(webParam.url);
                if (webParam.headerParam.size() > 0) {
                    Headers headers = Headers.of(webParam.headerParam);
                    builder.headers(headers);
                }
                request = builder.build();
                break;
            case POST:
                for (String key : webParam.requestParam.keySet()) {
                    formBuilder.add(key, (String) webParam.requestParam.get(key));
                }
                RequestBody body = formBuilder.build();
                builder.url(webParam.url).post(body);
                if (webParam.headerParam.size() > 0) {
                    Headers headers = Headers.of(webParam.headerParam);
                    builder.headers(headers);
                }
                request = builder.build();
                break;
            case PUT:
                for (String key : webParam.requestParam.keySet()) {
                    formBuilder.add(key, (String) webParam.requestParam.get(key));
                }
                body = formBuilder.build();
                builder.url(webParam.url).put(body);
                if (webParam.headerParam.size() > 0) {
                    Headers headers = Headers.of(webParam.headerParam);
                    builder.headers(headers);
                }
                request = builder.build();
                break;
            case DELETE:
                for (String key : webParam.requestParam.keySet()) {
                    formBuilder.add(key, (String) webParam.requestParam.get(key));
                }
                body = formBuilder.build();
                builder.url(webParam.url).delete(body);
                if (webParam.headerParam.size() > 0) {
                    Headers headers = Headers.of(webParam.headerParam);
                    builder.headers(headers);
                }
                break;
        }
        if (request == null) return;
        client.newCall(request).enqueue(new Callback(webParam));
    }

    /**
     * This method is used to sending data in body parameters
     *
     * @param webParam set WebParam
     */
    public void multipartParam(WebParam webParam) {
        Request request = null;
        Request.Builder requestBuilder = new Request.Builder();
        MultipartBody.Builder builder = new MultipartBody.Builder();
        switch (webParam.httpType) {
            case GET:
                break;
            case POST:
                builder.setType(MultipartBody.FORM);
                for (String key : webParam.requestParam.keySet()) {
                    if (webParam.requestParam.get(key) instanceof String) {
                        builder.addFormDataPart(key, (String) webParam.requestParam.get(key));
                    } else if (webParam.requestParam.get(key) instanceof File) {
                        File file = (File) webParam.requestParam.get(key);
                        builder.addFormDataPart(key, file.getName(), RequestBody.create(MEDIA_TYPE_IMAGE, file));
                    }
                }
                if (webParam.headerParam.size() > 0) {
                    Headers headers = Headers.of(webParam.headerParam);
                    requestBuilder.headers(headers);
                }
                requestBuilder.url(webParam.url).post(builder.build());
                request = requestBuilder.build();
                break;
            case PUT:
                builder.setType(MultipartBody.FORM);
                for (String key : webParam.requestParam.keySet()) {
                    if (webParam.requestParam.get(key) instanceof String) {
                        builder.addFormDataPart(key, (String) webParam.requestParam.get(key));
                    } else if (webParam.requestParam.get(key) instanceof File) {
                        File file = (File) webParam.requestParam.get(key);
                        builder.addFormDataPart(key, file.getName(), RequestBody.create(MEDIA_TYPE_IMAGE, file));
                    }
                }
                if (webParam.headerParam.size() > 0) {
                    Headers headers = Headers.of(webParam.headerParam);
                    requestBuilder.headers(headers);
                }
                requestBuilder.url(webParam.url).put(builder.build());
                request = requestBuilder.build();
                break;
            case DELETE:
                builder.setType(MultipartBody.FORM);
                for (String key : webParam.requestParam.keySet()) {
                    if (webParam.requestParam.get(key) instanceof String) {
                        builder.addFormDataPart(key, (String) webParam.requestParam.get(key));
                    } else if (webParam.requestParam.get(key) instanceof File) {
                        File file = (File) webParam.requestParam.get(key);
                        builder.addFormDataPart(key, file.getName(), RequestBody.create(MEDIA_TYPE_IMAGE, file));
                    }
                }
                if (webParam.headerParam.size() > 0) {
                    Headers headers = Headers.of(webParam.headerParam);
                    requestBuilder.headers(headers);
                }
                requestBuilder.url(webParam.url).delete(builder.build());
                request = requestBuilder.build();
                break;
        }
        if (request == null) {
            Log.e(getClass().getName(), "Request is null");
            return;
        }
        client.newCall(request).enqueue(new Callback(webParam));
    }

    /**
     * This class is used to get the response from the server and return back to {@link WebConnect}
     */
    private class Callback implements okhttp3.Callback {
        private WebParam webParam;

        public Callback(WebParam webParam) {
            this.webParam = webParam;
        }

        @Override
        public void onFailure(Call call, IOException e) {
            try {
                if (call != null
                        && call.execute() != null
                        && webParam.callback != null) {
                    Response response = call.execute();
                    Optional<Response> optional = Optional.fromNullable(response);
                    if (optional.isPresent()) {
                        Optional<ResponseBody> opt = Optional.fromNullable(optional.get().body());
                        if (opt.isPresent())
                            webParam.callback.onError(null, opt.get().toString(), webParam.taskId, response.code());
                    }
                } else if (e != null && webParam.callback != null) {
                    webParam.callback.onError(null, e.getMessage(), webParam.taskId, -1);
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            if (response.body() != null) {
                String res = response.body().string();
                Object object;
                if (webParam.model != null) {
                    object = new Gson().fromJson(res, webParam.model.getClass());
                } else {
                    object = new Gson().fromJson(res, Object.class);
                }
                if (response.code() == 200 || response.code() == 201) {
                    if (webParam.callback != null)
                        webParam.callback.onSuccess(object,
                                res, webParam.taskId, response.code());
                } else {
                    if (webParam.callback != null)
                        webParam.callback.onError(object, res, webParam.taskId, response.code());
                }
            }
        }
    }


}
