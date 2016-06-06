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
    protected Context context;
    protected Activity activityContext;
    protected String url, disableCacheKey;
    protected ImageType imageType = ImageType.URL;
    protected int loadingThumbnail = -1, errorThumbnail = -1, taskId, height, width;
    protected boolean needBitmap = false, disableCache = false, clearCache = false;
    protected Map<?, ?> header = new LinkedHashMap<>();
    protected ImageView imageView;
    protected ProgressBar progressBar;
    protected onCallback callback;
    protected Bitmap.Config config = Bitmap.Config.RGB_565;
    protected DisplayImageOptions displayImageOptions = ImageLoaderAsync.mDisplayImageOptions;

    public enum ImageType {
        URL, URI, FILE
    }

    public interface onCallback {
        void onBitmapReceived(Bitmap bitmap, File file, int taskId);
    }

    public abstract class Callback implements onCallback {

    }
}
