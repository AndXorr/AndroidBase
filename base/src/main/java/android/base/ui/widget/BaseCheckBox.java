package android.base.ui.widget;

import android.base.R;
import android.base.util.ApplicationUtils;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatCheckBox;
import android.text.TextUtils;
import android.util.AttributeSet;

/**
 * Created by Sahni on 24-11-2015.
 */
public class BaseCheckBox extends AppCompatCheckBox {
    public BaseCheckBox(Context context) {
        super(context);
        setAttributes(null);
    }

    public BaseCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAttributes(attrs);
    }

    public BaseCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setAttributes(attrs);
    }

    private void setAttributes(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray ta = getContext().obtainStyledAttributes(attrs,
                    R.styleable.BaseTextView);
            String typeface = ApplicationUtils.System.getFontName(getContext(), ta
                    .getInt(R.styleable.BaseTextView_typefaces, -1), ta.getResourceId(R.styleable.BaseTextView_customTypeface, -1));
            ta.recycle();
            if (!TextUtils.isEmpty(typeface)) {
                Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                        typeface);
                setTypeface(tf);
            }
        }
    }
}
