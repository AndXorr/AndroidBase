package android.base.widget;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;

/**
 * Created by clickapps on 27/11/15.
 */
public class BaseNestedScrollView extends NestedScrollView {
    public BaseNestedScrollView(Context context) {
        super(context);
    }

    public BaseNestedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseNestedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
