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
    private static volatile ImageUtil sImageUtil;

    private ImageUtil() {

    }

    public static ImageUtil get() {
        if (sImageUtil == null) {
            synchronized (ImageUtil.class) {
                if (sImageUtil == null) {
                    sImageUtil = new ImageUtil();
                }
            }
        }
        return sImageUtil;
    }

    public static Builder with(@NonNull Context context, @NonNull String url) {
        return new Builder(context, url);
    }

    public static Builder with(@NonNull Activity context, @NonNull String url) {
        return new Builder(context, url);
    }
}
