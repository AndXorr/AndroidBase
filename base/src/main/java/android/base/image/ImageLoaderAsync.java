package android.base.image;

import android.base.log.Log;
import android.base.util.ApplicationUtils;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.View;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.utils.DiskCacheUtils;
import com.nostra13.universalimageloader.utils.L;
import com.nostra13.universalimageloader.utils.MemoryCacheUtils;

import java.io.File;

/**
 * This is Singleton class. Instance of this class is not created.
 *
 * @author Amit
 */
public class ImageLoaderAsync {

    // ImageLoaderAsync
    private static ImageLoaderAsync instance;
    // DisplayImageOption
    protected static DisplayImageOptions mDisplayImageOptions;
    protected static String WEB = "http://"; // from Web
    protected static String SDCARD = "file:///"; // from SD card
    protected static String CONTENT = "content://"; // from content
    // provider
    protected static String ASSETS = "assets://"; // from assets
    protected static String DRAWABLE = "drawable://";// from drawables
    // (only images,
    // non-9patch)

    private ImageLoaderAsync() {

    }

    /**
     * This method is used to make instacne of this class if not created or
     * return already created
     *
     * @param context {@link Context}
     * @param
     * @return {@link ImageLoaderAsync}
     */
    public static synchronized ImageLoaderAsync getInstance(Context context) {
        if (instance == null) {
            instance = new ImageLoaderAsync();
            File cacheDir = ApplicationUtils.FileUtil.getDirectoryAppImages(context);
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            int width = displayMetrics.widthPixels;
            int height = displayMetrics.heightPixels;
            ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                    context)
                    .memoryCacheExtraOptions(width / 2,
                            height / 2)
                    // default = device screen dimensions
                    .diskCacheExtraOptions(width,
                            height, null)
                    .threadPoolSize(3)
                    // default
                    .threadPriority(Thread.NORM_PRIORITY - 1)
                    // default
                    .tasksProcessingOrder(QueueProcessingType.FIFO)
                    // default
                    .denyCacheImageMultipleSizesInMemory()
                    // .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                    .memoryCacheSize(10 * 1024 * 1024)
                    .diskCache(new UnlimitedDiskCache(cacheDir))
                    // default
                    .diskCacheSize(30 * 1024 * 1024)
                    .diskCacheFileCount(100)
                    .diskCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
                    // .imageDownloader(new BaseImageDownloader(context)) //
                    // default
                    // .imageDecoder(new BaseImageDecoder()) // default
                    .build();
            ImageLoader.getInstance().init(config);
            L.writeLogs(false);
            mDisplayImageOptions = new DisplayImageOptions.Builder()
                    .resetViewBeforeLoading(false)
                    .cacheInMemory(true).cacheOnDisk(true).considerExifParams(true)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .build();
        }
        return instance;
    }

    /**
     * This method is used to return ImageLoader
     *
     * @return {@link ImageLoader}
     */
    public ImageLoader getImageLoader() {
        return ImageLoader.getInstance();
    }

    /**
     * This method is used to return Option
     *
     * @return {@link DisplayImageOptions}
     */
    public DisplayImageOptions getOption() {
        return mDisplayImageOptions;
    }

    public File getLocalSavedPath(String url, Context context) {
        File file = DiskCacheUtils.findInCache(url, getImageLoader().getDiskCache());
        if (file == null) return null;
        return file;

    }


    /**
     * This method is used to load bitmap from the url
     *
     * @param imageUri Image URI (i.e. "http://site.com/image.png",
     * @param width    set width of image
     * @param height   set height of image
     * @return bitmap
     */
    public final Bitmap getBitmapSync(String imageUri, int width, int height) {
        ImageSize size = new ImageSize(width, height);

        return getImageLoader().loadImageSync(
                imageUri, size, getOption());

    }

    /**
     * This method is used to load bitmap from the url
     *
     * @param imageUri Image URI (i.e. "file:///mnt/sdcard/image.png")
     * @param width    set width of image
     * @param height   set height of image
     * @return bitmap
     */
    public final Bitmap getBitmapFileSync(String imageUri, int width,
                                          int height) {
        ImageSize size = new ImageSize(width, height);
        if (!imageUri.contains(ImageLoaderAsync.SDCARD)) {
            imageUri = ImageLoaderAsync.SDCARD + imageUri;
        }
        return getImageLoader().loadImageSync(
                imageUri, size, getOption());

    }

    /**
     * This method is used to clear the cache for specific key
     *
     * @param key set key for which cache to be cleared
     */
    public void clearCache(String key) {
        MemoryCacheUtils.removeFromCache(key, ImageLoader.getInstance().getMemoryCache());
        DiskCacheUtils.removeFromCache(key, ImageLoader.getInstance().getDiskCache());
    }

    public void clearWholeCache() {
        getImageLoader().clearDiskCache();
        getImageLoader().clearMemoryCache();
    }

    /**
     * This method is used to setImage on AsyncImageView
     */
    public void setImage(final ImageParam imageParam) {
        getImageLoader().displayImage(imageParam.url, imageParam.imageView,
                imageParam.displayImageOptions, new ImageLoadingListener() {

                    @Override
                    public void onLoadingStarted(String arg0, View arg1) {
                        // TODO Auto-generated method stub
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
                    public void onLoadingFailed(String arg0, View arg1,
                                                FailReason arg2) {
                        if (imageParam.imageView != null && imageParam.progressBar != null) {
                            setErrorThumbnail(imageParam);
                            imageParam.progressBar.setVisibility(View.INVISIBLE);
                        } else if (imageParam.imageView != null) {
                            setErrorThumbnail(imageParam);
                        } else {
                            Log.e(getClass().getSimpleName(), "ImageView is null");
                        }
                    }

                    @Override
                    public void onLoadingComplete(String arg0, View arg1,
                                                  Bitmap bitmap) {
                        // TODO Auto-generated method stub
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
                                imageParam.callback.onBitmapReceived(bitmap, getLocalSavedPath(imageParam.url, imageParam.context), imageParam.taskId);
                            } else if (imageParam.activityContext != null) {
                                imageParam.callback.onBitmapReceived(bitmap, getLocalSavedPath(imageParam.url, imageParam.activityContext), imageParam.taskId);
                            }
                        }
                    }

                    @Override
                    public void onLoadingCancelled(String arg0, View arg1) {
                        // TODO Auto-generated method stub

                    }
                });
    }

    private void setLoadingThumbnail(ImageParam imageParam) {
        if (imageParam.loadingThumbnail != 0) {
            imageParam.imageView.setImageResource(imageParam.loadingThumbnail);
        }
    }

    private void setErrorThumbnail(ImageParam imageParam) {
        if (imageParam.errorThumbnail != 0) {
            imageParam.imageView.setImageResource(imageParam.errorThumbnail);
        } else if (imageParam.loadingThumbnail != 0) {
            imageParam.imageView.setImageResource(imageParam.loadingThumbnail);
        }
    }

    private void setBitmap(ImageParam imageParam, Bitmap bitmap) {
        if (bitmap != null) {
            imageParam.imageView.setImageBitmap(bitmap);
        }
    }


}
