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
 * Created by Sahni on 17-11-2015.
 */
public class ImageUtil {

    public static class Builder {
        private ImageParam imageParam;
        ImageLoaderAsync imageLoaderAsync = null;

        public Builder(@NonNull Context context, @NonNull String url) {
            imageParam = new ImageParam();
            imageParam.context = context;
            imageParam.url = url;
            imageLoaderAsync = ImageLoaderAsync.getInstance(imageParam.context);
            imageParam.displayImageOptions = ImageLoaderAsync.mDisplayImageOptions;
        }

        public Builder(@NonNull Activity context, @NonNull String url) {
            imageParam = new ImageParam();
            imageParam.activityContext = context;
            imageParam.context = context;
            imageParam.url = url;
            imageLoaderAsync = ImageLoaderAsync.getInstance(imageParam.context);
            imageParam.displayImageOptions = ImageLoaderAsync.mDisplayImageOptions;
        }

        public Builder setImageType(@NonNull ImageParam.ImageType imageType) {
            imageParam.imageType = imageType;
            return this;
        }

        public Builder thumbnail(int loadingThumb, int errorThumb) {
            imageParam.loadingThumbnail = loadingThumb;
            imageParam.errorThumbnail = errorThumb;
            return this;
        }

        public Builder taskId(int taskId) {
            imageParam.taskId = taskId;
            return this;
        }

        public Builder setHeaders(@NonNull Map<?, ?> headers) {
            imageParam.header = headers;
            return this;
        }

        public Builder needBitmap(boolean needBitmap, int taskId) {
            imageParam.needBitmap = needBitmap;
            imageParam.taskId = taskId;
            return this;
        }

        public Builder into(@NonNull ImageView imageView) {
            imageParam.imageView = imageView;
            return this;
        }

        public Builder into(@NonNull ImageView imageView, @NonNull ProgressBar progressBar) {
            imageParam.imageView = imageView;
            imageParam.progressBar = progressBar;
            return this;
        }

        public Builder callback(@NonNull ImageParam.onCallback callback) {
            imageParam.callback = callback;
            return this;
        }

        public Builder resize(int height, int width) {
            imageParam.height = height;
            imageParam.width = width;
            return this;
        }

        public Builder config(@NonNull Bitmap.Config config) {
            imageParam.config = config;
            return this;
        }

        public Builder disableCache() {
            imageParam.disableCache = true;
            return this;
        }

        public Builder disableCache(boolean disableCache) {
            imageParam.disableCache = disableCache;
            return this;
        }

        public Builder disableCache(String key) {
            imageParam.disableCache = true;
            imageParam.disableCacheKey = key;
            return this;
        }

        public Builder clearWholeCache() {
            imageParam.clearCache = true;
            return this;
        }

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
}
