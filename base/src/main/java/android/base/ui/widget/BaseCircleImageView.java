package android.base.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Sahni on 13-12-2015.
 */
public class BaseCircleImageView extends CircleImageView {
    private boolean isNeedStrech = false;

    public BaseCircleImageView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        init();
    }

    public BaseCircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseCircleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        init();
    }

    public boolean isNeedStrech() {
        return isNeedStrech;
    }

    public void setIsNeedStrech(boolean isNeedStrech) {
        this.isNeedStrech = isNeedStrech;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!isNeedStrech()) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        } else {
            super.onMeasure(getMeasuredWidth(), getMeasuredWidth());
        }
    }

}
