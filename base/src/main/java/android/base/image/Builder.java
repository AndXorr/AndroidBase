package android.base.image;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.Map;


/**
 * The type Builder.
 */
public class Builder {
    private ImageParam imageParam;
    /**
     * The Image loader async.
     */
    ImageLoaderAsync imageLoaderAsync = null;

    /**
     * Instantiates a new Builder.
     *
     * @param context the context
     * @param url     the url
     */
    public Builder(@NonNull Context context, @NonNull String url) {
        imageParam = new ImageParam();
        imageParam.context = context;
        imageParam.url = url;
        imageLoaderAsync = ImageLoaderAsync.getInstance(imageParam.context);
        imageParam.displayImageOptions = ImageLoaderAsync.mDisplayImageOptions;
    }

    /**
     * Instantiates a new Builder.
     *
     * @param context the context
     * @param url     the url
     */
    public Builder(@NonNull Activity context, @NonNull String url) {
        imageParam = new ImageParam();
        imageParam.activityContext = context;
        imageParam.context = context;
        imageParam.url = url;
        imageLoaderAsync = ImageLoaderAsync.getInstance(imageParam.context);
        imageParam.displayImageOptions = ImageLoaderAsync.mDisplayImageOptions;
    }

    /**
     * Image type builder.
     *
     * @param imageType the image type
     * @return the builder
     */
    public Builder imageType(@NonNull ImageParam.ImageType imageType) {
        imageParam.imageType = imageType;
        return this;
    }

    /**
     * Thumbnail builder.
     *
     * @param loadingThumb the loading thumb
     * @param errorThumb   the error thumb
     * @return the builder
     */
    public Builder thumbnail(int loadingThumb, int errorThumb) {
        imageParam.loadingThumbnail = loadingThumb;
        imageParam.errorThumbnail = errorThumb;
        return this;
    }

    /**
     * Task id builder.
     *
     * @param taskId the task id
     * @return the builder
     */
    public Builder taskId(int taskId) {
        imageParam.taskId = taskId;
        return this;
    }

    /**
     * Headers builder.
     *
     * @param headers the headers
     * @return the builder
     */
    public Builder headers(@NonNull Map<?, ?> headers) {
        imageParam.header = headers;
        return this;
    }

    /**
     * Need bitmap builder.
     *
     * @param needBitmap the need bitmap
     * @param taskId     the task id
     * @return the builder
     */
    public Builder needBitmap(boolean needBitmap, int taskId) {
        imageParam.needBitmap = needBitmap;
        imageParam.taskId = taskId;
        return this;
    }

    /**
     * Into builder.
     *
     * @param imageView the image mView
     * @return the builder
     */
    public Builder into(@NonNull ImageView imageView) {
        imageParam.imageView = imageView;
        return this;
    }

    /**
     * Into builder.
     *
     * @param imageView   the image mView
     * @param progressBar the progress bar
     * @return the builder
     */
    public Builder into(@NonNull ImageView imageView, @NonNull ProgressBar progressBar) {
        imageParam.imageView = imageView;
        imageParam.progressBar = progressBar;
        return this;
    }

    /**
     * Callback builder.
     *
     * @param callback the callback
     * @return the builder
     */
    public Builder callback(@NonNull ImageParam.onCallback callback) {
        imageParam.callback = callback;
        return this;
    }

    /**
     * Resize builder.
     *
     * @param height the height
     * @param width  the width
     * @return the builder
     */
    public Builder resize(int height, int width) {
        imageParam.height = height;
        imageParam.width = width;
        return this;
    }

    /**
     * Config builder.
     *
     * @param config the config
     * @return the builder
     */
    public Builder config(@NonNull Bitmap.Config config) {
        imageParam.config = config;
        return this;
    }

    /**
     * Disable cache builder.
     *
     * @return the builder
     */
    public Builder disableCache() {
        imageParam.disableCache = true;
        return this;
    }

    /**
     * Disable cache builder.
     *
     * @param disableCache the disable cache
     * @return the builder
     */
    public Builder disableCache(boolean disableCache) {
        imageParam.disableCache = disableCache;
        return this;
    }

    /**
     * Disable cache builder.
     *
     * @param key the key
     * @return the builder
     */
    public Builder disableCache(String key) {
        imageParam.disableCache = true;
        imageParam.disableCacheKey = key;
        return this;
    }

    /**
     * Clear whole cache builder.
     *
     * @return the builder
     */
    public Builder clearWholeCache() {
        imageParam.clearCache = true;
        return this;
    }

    /**
     * Build.
     */
    public void build() {
        if (!TextUtils.isEmpty(imageParam.url) && imageLoaderAsync != null) {
            if (imageParam.clearCache)
                imageLoaderAsync.clearWholeCache();
            String url = imageParam.url;
            if (imageParam.imageType == ImageParam.ImageType.FILE && !url.startsWith(ImageLoaderAsync.SDCARD)) {
                imageParam.url = ImageLoaderAsync.SDCARD + url;
            } else if (imageParam.imageType == ImageParam.ImageType.URI) {
                imageParam.url = ImageLoaderAsync.CONTENT + url;
            } else {
                imageParam.url = url;
            }
            if (imageParam.disableCache && imageParam.disableCacheKey != null) {
                imageLoaderAsync.clearCache(imageParam.disableCacheKey);
            } else if (imageParam.disableCache) {
                imageLoaderAsync.clearCache(imageParam.url);
            } else {
                // nothing
            }
            imageLoaderAsync.setImage(imageParam);
        } else {
            Log.e(getClass().getSimpleName(), "Url is missing");
        }
    }
}
