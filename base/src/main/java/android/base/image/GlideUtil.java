package android.base.image;

import android.base.log.Log;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.view.View;

import com.bumptech.glide.BitmapTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.io.File;

/**
 * Created by clickapps on 15/4/16.
 */
public class GlideUtil {

    public void setImage(final ImageParam imageParam) {
        if (imageParam.clearCache) {
            Glide.get(imageParam.context).clearDiskCache();
            Glide.get(imageParam.context).clearMemory();
        }
        BitmapTypeRequest<String> glideManager = Glide.with(imageParam.context)
                .load(imageParam.url).asBitmap();
        if (imageParam.loadingThumbnail != -1)
            glideManager.placeholder(imageParam.loadingThumbnail);
        if (imageParam.errorThumbnail != -1)
            glideManager.error(imageParam.errorThumbnail);
        if (imageParam.disableCache) {
            glideManager.skipMemoryCache(true);
            glideManager.diskCacheStrategy(DiskCacheStrategy.NONE);
        }
        if (imageParam.height > 0 && imageParam.width > 0) {
            glideManager.override(imageParam.width, imageParam.height);
        }
        glideManager.into(new Target(imageParam));
    }


    private class Target extends SimpleTarget<Bitmap> {
        private ImageParam imageParam;

        private Target(ImageParam imageParam) {
            this.imageParam = imageParam;
        }

        @Override
        public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
            if (imageParam.imageView != null && imageParam.progressBar != null) {
                setBitmap(imageParam, bitmap);
                imageParam.progressBar.setVisibility(View.INVISIBLE);
            } else if (imageParam.imageView != null) {
                setBitmap(imageParam, bitmap);
            } else {
                Log.e(getClass().getSimpleName(), "ImageView is null");
            }

            if (imageParam.needBitmap && imageParam.callback != null) {
                if (imageParam.context != null) {
                    imageParam.callback.onBitmapReceived(bitmap, new File(Environment.getExternalStorageDirectory(), "temp"), imageParam.taskId);
                } else if (imageParam.activityContext != null) {
                    imageParam.callback.onBitmapReceived(bitmap, new File(Environment.getExternalStorageDirectory(), "temp"), imageParam.taskId);
                }
            }
        }

        @Override
        public void onLoadCleared(Drawable placeholder) {

        }

        @Override
        public void onLoadStarted(Drawable placeholder) {
            if (imageParam.imageView != null && imageParam.progressBar != null) {
                setLoadingThumbnail(imageParam);
                imageParam.progressBar.setVisibility(View.VISIBLE);
            } else if (imageParam.imageView != null) {
                setLoadingThumbnail(imageParam);
            } else {
                Log.e(getClass().getSimpleName(), "ImageView is null");
            }
        }

        @Override
        public void onLoadFailed(Exception e, Drawable errorDrawable) {
            if (imageParam.imageView != null && imageParam.progressBar != null) {
                setErrorThumbnail(imageParam);
                imageParam.progressBar.setVisibility(View.INVISIBLE);
            } else if (imageParam.imageView != null) {
                setErrorThumbnail(imageParam);
            } else {
                Log.e(getClass().getSimpleName(), "ImageView is null");
            }
        }

        void setLoadingThumbnail(ImageParam imageParam) {
            if (imageParam.loadingThumbnail != -1) {
                imageParam.imageView.setImageResource(imageParam.loadingThumbnail);
            }
        }

        void setErrorThumbnail(ImageParam imageParam) {
            if (imageParam.errorThumbnail != 0) {
                imageParam.imageView.setImageResource(imageParam.errorThumbnail);
            } else if (imageParam.loadingThumbnail != 0) {
                imageParam.imageView.setImageResource(imageParam.loadingThumbnail);
            }
        }

        void setBitmap(ImageParam imageParam, Bitmap bitmap) {
            if (bitmap != null) {
                imageParam.imageView.setImageBitmap(bitmap);
            }
        }
    }

}
