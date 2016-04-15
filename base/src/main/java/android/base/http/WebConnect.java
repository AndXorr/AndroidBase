package android.base.http;

import android.app.Activity;
import android.base.dialog.BaseProgressDialog;
import android.base.interfaces.WebHandler;
import android.base.log.Log;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.File;
import java.util.Map;

/**
 * Created by clickapps on 16/11/15.
 */
public class WebConnect {

    public static class Builder {
        private WebParam webParam;

        public Builder(@NonNull Activity context, @NonNull String url) {
            webParam = new WebParam();
            webParam.activityContext = context;
            webParam.context = context;
            webParam.url = url;
        }

        public Builder(@NonNull Context context, @NonNull String url) {
            webParam = new WebParam();
            webParam.context = context;
            webParam.url = url;
        }

        public Builder(@NonNull Activity context, @NonNull String url, @NonNull WebApi webApi) {
            webParam = new WebParam();
            webParam.activityContext = context;
            webParam.context = context;
            webParam.url = url;
            webParam.webApi = webApi;
        }

        public Builder(@NonNull Context context, @NonNull String url, @NonNull WebApi webApi) {
            webParam = new WebParam();
            webParam.context = context;
            webParam.url = url;
            webParam.webApi = webApi;
        }

        public Builder baseUrl(@NonNull String url) {
            webParam.baseUrl = url;
            return this;
        }

        public Builder httpType(@NonNull WebParam.HttpType httpType) {
            webParam.httpType = httpType;
            return this;
        }

        public Builder requestParam(@NonNull Map<String, ?> requestParam) {
            webParam.requestParam = requestParam;
            return this;
        }

        public Builder headerParam(@NonNull Map<String, String> headerParam) {
            webParam.headerParam = headerParam;
            return this;
        }

        public Builder callback(@NonNull WebHandler.OnWebCallback callback, @NonNull Class<?> model) {
            webParam.callback = callback;
            webParam.model = model;
            return this;
        }

        public Builder taskId(int taskId) {
            webParam.taskId = taskId;
            return this;
        }

        public Builder retryCount(int retry) {
            webParam.retryCount = retry;
            return this;
        }

        public Builder setProgressDialog(@Nullable BaseProgressDialog progressDialog, @Nullable String message) {
            webParam.progressDialog = progressDialog;
            webParam.progressDialogMessage = message;
            return this;
        }

        public Builder showDialog(boolean showDialog) {
            webParam.showDialog = showDialog;
            return this;
        }

        public Builder isJson(boolean isJson) {
            webParam.isJson = isJson;
            return this;
        }

        public Builder isMultipart(boolean isMultipart) {
            webParam.isMultipart = isMultipart;
            return this;
        }

        public Builder cache(File cache) {
            webParam.cacheDir = cache;
            return this;
        }

        public Builder cache(File cache, int cacheSize) {
            webParam.cacheDir = cache;
            webParam.cacheSize = cacheSize;
            return this;
        }

        public void connect() {
            if (webParam.webApi != null) {
                new RetrofitUtil(webParam);
            } else {
                Log.e(getClass().getCanonicalName(), "can't find WebApi");
            }
        }

        public ApiClient getApiClient() {
            return new ApiClient(webParam);
        }

        public static final class ApiClient {
            private WebParam webParam;

            private ApiClient() {
            }

            private ApiClient(WebParam webParam) {
                this.webParam = webParam;
            }

            public WebParam getWebParam() {
                return webParam;
            }

            public <T> T connect(Class<T> cls) {
                return new RetrofitUtil().createService(cls, webParam);
            }
        }

    }
}
