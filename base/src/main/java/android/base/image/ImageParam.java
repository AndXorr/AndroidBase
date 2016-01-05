package android.base.image;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Sahni on 17-11-2015.
 */
public class ImageParam {
    public Context context;
    public Activity activityContext;
    public String url, disableCacheKey;
    public ImageType imageType = ImageType.URL;
    public int loadingThumbnail, errorThumbnail, taskId, height, width;
    public boolean needBitmap = false, disableCache = false, clearCache = false;
    public Map<?, ?> header = new LinkedHashMap<>();
    public ImageView imageView;
    public ProgressBar progressBar;
    public onCallback callback;
    public Bitmap.Config config = Bitmap.Config.RGB_565;
    public DisplayImageOptions displayImageOptions = ImageLoaderAsync.mDisplayImageOptions;

    public enum ImageType {
        URL, URI, FILE
    }

    public interface onCallback {
        public void onBitmapReceived(Bitmap bitmap, File file, int taskId);
    }

    public abstract class Callback implements onCallback {

    }
}
