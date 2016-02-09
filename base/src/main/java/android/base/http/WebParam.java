package android.base.http;

import android.app.Activity;
import android.base.dialog.BaseProgressDialog;
import android.content.Context;
import android.os.Environment;
import android.support.annotation.Nullable;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by clickapps on 16/11/15.
 */
public class WebParam {
    public Activity activityContext;
    public Context context;
    public String url, progressDialogMessage;
    public HttpType httpType = HttpType.GET;
    public Map<String, ?> requestParam = new LinkedHashMap<>();
    public Map<String, String> headerParam = new LinkedHashMap<>();
    public OnWebCallback callback;
    public Class<?> model;
    public int taskId, retryCount;
    public BaseProgressDialog progressDialog;
    public boolean showDialog = true, isJson = false, isMultipart = false, cacheResponse = false;
    public int cacheSize = 10 * 1024 * 1024; // 10 MiB
    public File cacheDir = Environment.getDownloadCacheDirectory();
    public WebApi webApi;

    public enum HttpType {
        GET, POST, PUT, DELETE
    }


    public interface OnWebCallback {
        <T> void onSuccess(@Nullable T object, String response, int taskId, int statusCode);

        <T> void onError(@Nullable T object, String error, int taskId, int statusCode);
    }

    public abstract class WebCallback implements OnWebCallback {

        @Override
        public <T> void onSuccess(@Nullable T object, String response, int taskId, int statusCode) {

        }

        @Override
        public <T> void onError(@Nullable T object, String error, int taskId, int statusCode) {

        }
    }
}
