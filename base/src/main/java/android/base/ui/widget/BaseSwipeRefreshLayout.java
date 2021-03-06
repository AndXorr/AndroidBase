package android.base.ui.widget;

import android.base.R;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by clickapps on 4/8/15.
 */
public class BaseSwipeRefreshLayout extends SwipeRefreshLayout implements SwipeRefreshLayout.OnRefreshListener {
    private OnRefreshListener onRefreshListener;
    private ViewGroup container;

    @Override
    public void onRefresh() {
        if (onRefreshListener == null)
            return;
//        Force fully delay to show refresh icon for i sec
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onRefreshListener.onRefresh();
            }
        }, 1000);

    }

    /**
     * The interface On refresh listener.
     */
    public interface OnRefreshListener {
        /**
         * On refresh.
         */
        void onRefresh();
    }

    /**
     * Instantiates a new Base swipe refresh layout.
     *
     * @param context the context
     */
    public BaseSwipeRefreshLayout(Context context) {
        super(context);
        setColorTheme();
    }

    /**
     * Instantiates a new Base swipe refresh layout.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public BaseSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setColorTheme();
    }

    private void setColorTheme() {
        setColorSchemeColors(Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE);
        setProgressBackgroundColorSchemeColor(ContextCompat.getColor(getContext(), R.color.app_theme_base));
    }

    /**
     * Sets on refresh listener.
     *
     * @param listener the listener
     */
    public void setOnRefreshListener(OnRefreshListener listener) {
        onRefreshListener = listener;
        if (onRefreshListener != null)
            setOnRefreshListener(this);

    }

    @Override
    public boolean canChildScrollUp() {
        // The swipe refresh layout has 2 children; the circle refresh indicator
        // and the mView container. The container is needed here
        ViewGroup container = getContainer();
        if (container == null) {
            return false;
        }

        // The container has 2 children; the empty mView and the scrollable mView.
        // Use whichever one is visible and test that it can scroll
        View view = container.getChildAt(0);
        if (view.getVisibility() != View.VISIBLE) {
            view = container.getChildAt(1);
        }

        return ViewCompat.canScrollVertically(view, -1);
    }

    private ViewGroup getContainer() {
        // Cache this mView
        if (container != null) {
            return container;
        }

        // The container may not be the first mView. Need to iterate to find it
        for (int i = 0; i < getChildCount(); i++) {
            if (getChildAt(i) instanceof ViewGroup) {
                container = (ViewGroup) getChildAt(i);

                if (container.getChildCount() != 2) {
                    throw new RuntimeException("Container must have an empty mView and content mView");
                }

                break;
            }
        }

        if (container == null) {
            throw new RuntimeException("Container mView not found");
        }

        return container;
    }
}
