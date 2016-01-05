package android.base.widget;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;

/**
 * Created by clickapps on 27/11/15.
 */
public class BaseDrawerLayout extends DrawerLayout {
    public BaseDrawerLayout(Context context) {
        super(context);
        setup();
    }

    public BaseDrawerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    public BaseDrawerLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setup();
    }

    public void setup() {
        this.setDrawerListener(new DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    public void toggle() {
        if (isDrawerOpen(Gravity.LEFT)) {
            closeDrawer();
        } else {
            openDrawer();
        }
    }

    public void toggle(View view) {
        if (isDrawerOpen(view)) {
            this.closeDrawer(view);
        } else {
            openDrawer(view);
        }
    }

    public void openDrawer() {
        openDrawer(Gravity.LEFT);
    }

    public void closeDrawer() {
        openDrawer(Gravity.LEFT);
    }

}
