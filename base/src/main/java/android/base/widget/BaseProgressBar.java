package android.base.widget;

import android.base.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/**
 * Created by clickapps on 3/7/15.
 */
public class BaseProgressBar extends ProgressBar {
    public BaseProgressBar(Context context) {
        super(context);
    }

    public BaseProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(@NonNull AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs,
                    R.styleable.BaseTextView, 0, 0);
            int resId = a.getResourceId(R.styleable.BaseTextView_android_tint, -1);
            setIndeterminateTint(resId);
            a.recycle();
        }
    }

    public void setIndeterminateTint(int resId) {
        Drawable d = getIndeterminateDrawable();
        if (d != null && resId != -1) {
            DrawableCompat.setTintList(d, ContextCompat.getColorStateList(getContext(), resId));
        }
    }
}
