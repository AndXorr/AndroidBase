package android.base.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff;
import android.util.AttributeSet;

/**
 * Created by clickapps on 3/7/15.
 */
public class BaseProgressBar extends android.widget.ProgressBar {
    public BaseProgressBar(Context context) {
        super(context);
    }

    public BaseProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void changeProgressDrawableColor(int color) {
        if (getProgressDrawable() != null) {
            getProgressDrawable().setColorFilter(new LightingColorFilter(color, 0xFFFFFF));
        }
    }
}
