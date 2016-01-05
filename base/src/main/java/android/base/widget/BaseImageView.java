package android.base.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * This class is used as widget instead to using FrameLayout with ProgressBar
 * and ImageView. This is used like a ImageView. This is used when images are
 * fetched from server and show on ImageView. Till the images are loader from
 * server progress bar is visible on image view. After that Images are loaded
 * and set on imageView. This is also done with the help of Universal Image
 * Loader.
 *
 * @author amit.singh
 */
public class BaseImageView extends AppCompatImageView {

    public BaseImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public BaseImageView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public BaseImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


}
