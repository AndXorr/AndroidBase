package android.base.http;

import android.app.Activity;
import android.base.dialog.BaseProgressDialog;
import android.base.interfaces.WebHandler;
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
    public String url, baseUrl, progressDialogMessage;
    public HttpType httpType = HttpType.GET;
    public Map<String, ?> requestParam = new LinkedHashMap<>();
    public Map<String, String> headerParam = new LinkedHashMap<>();
    public WebHandler.OnWebCallback callback;
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
}
