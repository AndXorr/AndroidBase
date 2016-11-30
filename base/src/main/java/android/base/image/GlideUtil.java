package android.base.image;

import android.base.util.ApplicationUtils;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.view.View;

import com.bumptech.glide.BitmapTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.io.File;


/**
 * The type Glide util.
 */
public class GlideUtil {

    /**
     * Sets image.
     *
     * @param imageParam the image param
     */
    public void setImage(final ImageParam imageParam) {
        if (imageParam.clearCache) {
            Glide.get(imageParam.context).clearDiskCache();
            Glide.get(imageParam.context).clearMemory();
        }
        RequestManager manager = Glide.with(imageParam.context);
        BitmapTypeRequest<?> glideManager;
        if (imageParam.imageType == ImageParam.ImageType.URI) {
            glideManager = manager.load(Uri.parse(imageParam.url)).asBitmap();
        } else if (imageParam.imageType == ImageParam.ImageType.FILE) {
            glideManager = manager.load(new File(Uri.parse(imageParam.url).getPath())).asBitmap();
        } else {
            glideManager = manager.load(imageParam.url).asBitmap();
        }
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


    private static class Target extends SimpleTarget<Bitmap> {
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
                throw new RuntimeException("ImageView can't be null");
            }

            if (imageParam.needBitmap && imageParam.callback != null) {
                imageParam.callback.onBitmapReceived(bitmap, new File(Environment.getExternalStorageDirectory(), "temp"), imageParam.taskId);
            }
        }

        @Override
        public void onLoadCleared(Drawable placeholder) {
            // TODO
        }

        @Override
        public void onLoadStarted(Drawable placeholder) {
            if (imageParam.imageView != null && imageParam.progressBar != null) {
                setLoadingThumbnail(imageParam);
                imageParam.progressBar.setVisibility(View.VISIBLE);
            } else if (imageParam.imageView != null) {
                setLoadingThumbnail(imageParam);
            } else {
                ApplicationUtils.Log.e("ImageView is null");
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
                ApplicationUtils.Log.e("ImageView is null");
            }
        }

        /**
         * Sets loading thumbnail.
         *
         * @param imageParam the image param
         */
        void setLoadingThumbnail(ImageParam imageParam) {
            if (imageParam.loadingThumbnail != -1) {
                imageParam.imageView.setImageResource(imageParam.loadingThumbnail);
            }
        }

        /**
         * Sets error thumbnail.
         *
         * @param imageParam the image param
         */
        void setErrorThumbnail(ImageParam imageParam) {
            if (imageParam.errorThumbnail != 0) {
                imageParam.imageView.setImageResource(imageParam.errorThumbnail);
            } else if (imageParam.loadingThumbnail != 0) {
                imageParam.imageView.setImageResource(imageParam.loadingThumbnail);
            }
        }

        /**
         * Sets bitmap.
         *
         * @param imageParam the image param
         * @param bitmap     the bitmap
         */
        void setBitmap(ImageParam imageParam, Bitmap bitmap) {
            if (bitmap != null) {
                imageParam.imageView.setImageBitmap(bitmap);
            }
        }
    }

}
