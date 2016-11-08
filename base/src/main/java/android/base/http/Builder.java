package android.base.http;

import android.app.Activity;
import android.base.dialog.BaseProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.File;
import java.util.Map;


/**
 * The type Builder.
 */
public class Builder {


    private WebParam webParam;

    /**
     * Instantiates a new Builder.
     *
     * @param context the context
     * @param url     the url
     */
    public Builder(@NonNull Activity context, @NonNull String url) {
        webParam = new WebParam();
        webParam.activityContext = context;
        webParam.context = context;
        webParam.url = url;
    }

    /**
     * Instantiates a new Builder.
     *
     * @param context the context
     * @param url     the url
     */
    public Builder(@NonNull Context context, @NonNull String url) {
        webParam = new WebParam();
        webParam.context = context;
        webParam.url = url;
    }


    /**
     * Base url builder.
     *
     * @param url the url
     * @return the builder
     */
    public Builder baseUrl(@NonNull String url) {
        webParam.baseUrl = url;
        return this;
    }

    /**
     * Http type builder.
     *
     * @param httpType the http type
     * @return the builder
     */
    public Builder httpType(@NonNull WebParam.HttpType httpType) {
        webParam.httpType = httpType;
        return this;
    }

    /**
     * Request param builder.
     *
     * @param requestParam the request param
     * @return the builder
     */
    public Builder requestParam(@NonNull Map<String, ?> requestParam) {
        webParam.requestParam = requestParam;
        return this;
    }

    /**
     * Header param builder.
     *
     * @param headerParam the header param
     * @return the builder
     */
    public Builder headerParam(@NonNull Map<String, String> headerParam) {
        webParam.headerParam = headerParam;
        return this;
    }

    /**
     * Callback builder.
     *
     * @param callback the callback
     * @param success  the success
     * @param error    the error
     * @return the builder
     */
    public Builder callback(@NonNull WebHandler.OnWebCallback callback, @NonNull Class<?> success, @NonNull Class<?> error) {
        webParam.callback = callback;
        webParam.model = success;
        webParam.error = error;
        return this;
    }

    /**
     * Success model builder.
     *
     * @param success the success
     * @return the builder
     */
    public Builder successModel(@NonNull Class<?> success) {
        webParam.model = success;
        return this;
    }

    /**
     * Error model builder.
     *
     * @param error the error
     * @return the builder
     */
    public Builder errorModel(@NonNull Class<?> error) {
        webParam.error = error;
        return this;
    }

    /**
     * Task id builder.
     *
     * @param taskId the task id
     * @return the builder
     */
    public Builder taskId(int taskId) {
        webParam.taskId = taskId;
        return this;
    }

    /**
     * Retry count builder.
     *
     * @param retry the retry
     * @return the builder
     */
    public Builder retryCount(int retry) {
        webParam.retryCount = retry;
        return this;
    }

    /**
     * Progress dialog builder.
     *
     * @param progressDialog the progress dialog
     * @param message        the message
     * @return the builder
     */
    public Builder progressDialog(@Nullable BaseProgressDialog progressDialog, @Nullable String message) {
        webParam.progressDialog = progressDialog;
        webParam.progressDialogMessage = message;
        return this;
    }

    /**
     * Show dialog builder.
     *
     * @param showDialog the show dialog
     * @return the builder
     */
    public Builder showDialog(boolean showDialog) {
        webParam.showDialog = showDialog;
        return this;
    }

    /**
     * Is json builder.
     *
     * @param isJson the is json
     * @return the builder
     */
    public Builder isJson(boolean isJson) {
        webParam.isJson = isJson;
        return this;
    }

    /**
     * Is multipart builder.
     *
     * @param isMultipart the is multipart
     * @return the builder
     */
    public Builder isMultipart(boolean isMultipart) {
        webParam.isMultipart = isMultipart;
        return this;
    }

    /**
     * Cache builder.
     *
     * @param cache the cache
     * @return the builder
     */
    public Builder cache(File cache) {
        webParam.cacheDir = cache;
        return this;
    }

    /**
     * Cache builder.
     *
     * @param cache     the cache
     * @param cacheSize the cache size
     * @return the builder
     */
    public Builder cache(File cache, int cacheSize) {
        webParam.cacheDir = cache;
        webParam.cacheSize = cacheSize;
        return this;
    }

    private ApiClient connect() {
        return getApiClient();
    }

    /**
     * Gets api client.
     *
     * @return the api client
     */
    public ApiClient getApiClient() {
        return new ApiClient(webParam);
    }

    /**
     * The type Api client.
     */
    public static final class ApiClient {
        private WebParam webParam;

        private ApiClient() {
        }

        private ApiClient(WebParam webParam) {
            this.webParam = webParam;
        }

        /**
         * Gets web param.
         *
         * @return the web param
         */
        public WebParam getWebParam() {
            return webParam;
        }

    }
    /**
     * Connect t.
     *
     * @param <T> the type parameter
     * @param cls the cls
     * @return the t
     */
    public <T> T connect(Class<T> cls) {
        return new RetrofitUtil().createService(cls, webParam);
    }
}
