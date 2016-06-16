package android.base.http;

import android.app.Activity;
import android.base.dialog.BaseProgressDialog;
import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * The type Web param.
 */
public class WebParam {
    /**
     * The Activity context.
     */
    protected Activity activityContext;
    /**
     * The Context.
     */
    protected Context context;
    /**
     * The Url.
     */
    protected String url, /**
     * The Base url.
     */
    baseUrl, /**
     * The Progress dialog message.
     */
    progressDialogMessage;
    /**
     * The Http type.
     */
    protected HttpType httpType = HttpType.GET;
    /**
     * The Request param.
     */
    protected Map<String, ?> requestParam = new LinkedHashMap<>();
    /**
     * The Header param.
     */
    protected Map<String, String> headerParam = new LinkedHashMap<>();
    /**
     * The Callback.
     */
    protected WebHandler.OnWebCallback callback;
    /**
     * The Model.
     */
    protected Class<?> model;
    /**
     * The Error.
     */
    protected Class<?> error;
    /**
     * The Task id.
     */
    protected int taskId, /**
     * The Retry count.
     */
    retryCount;
    /**
     * The Progress dialog.
     */
    protected BaseProgressDialog progressDialog;
    /**
     * The Show dialog.
     */
    protected boolean showDialog = true, /**
     * The Is json.
     */
    isJson = false, /**
     * The Is multipart.
     */
    isMultipart = false, /**
     * The Cache response.
     */
    cacheResponse = false;
    /**
     * The Cache size.
     */
    protected int cacheSize = 10 * 1024 * 1024; // 10 MiB
    /**
     * The Cache dir.
     */
    protected File cacheDir = Environment.getDownloadCacheDirectory();

    /**
     * The enum Http type.
     */
    public enum HttpType {
        /**
         * Get http type.
         */
        GET, /**
         * Post http type.
         */
        POST, /**
         * Put http type.
         */
        PUT, /**
         * Delete http type.
         */
        DELETE
    }
}
