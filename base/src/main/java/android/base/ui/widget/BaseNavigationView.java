package android.base.ui.widget;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.util.AttributeSet;

/**
 * Created by clickapps on 27/11/15.
 */
public class BaseNavigationView extends NavigationView {
    public BaseNavigationView(Context context) {
        super(context);
    }

    public BaseNavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseNavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
