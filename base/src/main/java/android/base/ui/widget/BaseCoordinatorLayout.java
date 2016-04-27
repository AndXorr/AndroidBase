package android.base.ui.widget;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;

/**
 * Created by clickapps on 27/11/15.
 */
public class BaseCoordinatorLayout extends CoordinatorLayout {
    public BaseCoordinatorLayout(Context context) {
        super(context);
    }

    public BaseCoordinatorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseCoordinatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
