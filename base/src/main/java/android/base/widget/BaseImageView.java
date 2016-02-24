package android.base.widget;

import android.base.R;
import android.base.util.ApplicationUtils;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.util.AttributeSet;

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
        init(attrs);
    }

    public BaseImageView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public BaseImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs,
                    R.styleable.BaseTextView, 0, 0);
            int resId = a.getResourceId(R.styleable.BaseTextView_android_tint, -1);
            if (resId != -1) {
                setImageTint(resId);
            }
            a.recycle();
        }
    }

    private void setImageTint(@ColorRes int resId) {
        super.setColorFilter(ContextCompat.getColorStateList(getContext(), resId).getColorForState(getDrawableState(), 0));
    }

}
