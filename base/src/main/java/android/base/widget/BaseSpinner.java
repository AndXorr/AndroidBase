package android.base.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatSpinner;
import android.util.AttributeSet;

/**
 * Created by clickapps on 3/12/15.
 */
public class BaseSpinner extends AppCompatSpinner {

    public BaseSpinner(Context context) {
        super(context);
    }

    public BaseSpinner(Context context, int mode) {
        super(context, mode);
    }

    public BaseSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
