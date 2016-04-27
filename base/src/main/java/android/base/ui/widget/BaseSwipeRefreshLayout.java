package android.base.ui.widget;

import android.base.R;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;


/**
 * Created by clickapps on 4/8/15.
 */
public class BaseSwipeRefreshLayout extends SwipeRefreshLayout implements SwipeRefreshLayout.OnRefreshListener {
    private OnRefreshListener onRefreshListener;

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

    public interface OnRefreshListener {
        void onRefresh();
    }

    public BaseSwipeRefreshLayout(Context context) {
        super(context);
        setColorTheme();
    }

    public BaseSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setColorTheme();
    }

    private void setColorTheme() {
        setColorSchemeColors(Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE);
        setProgressBackgroundColorSchemeColor(ContextCompat.getColor(getContext(), R.color.app_theme_base));
    }

    public void setOnRefreshListener(OnRefreshListener listener) {
        onRefreshListener = listener;
        if (onRefreshListener != null)
            setOnRefreshListener(this);

    }

}
