package net.base;

import android.app.Activity;
import android.base.activity.ActivityManager;
import android.base.activity.ActivityParam;
import android.base.activity.BaseAppCompatActivity;
import android.base.alert.Alert;
import android.base.alert.AlertParam;
import android.base.anim.AnimManager;
import android.base.anim.AnimParam;
import android.base.constant.Constant;
import android.base.fragment.FragmentManager;
import android.base.http.Builder;
import android.base.http.WebConnect;
import android.base.http.WebHandler;
import android.base.image.ImageUtil;
import android.base.util.ApplicationUtils;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.util.LinkedHashMap;

import id.zelory.compressor.Compressor;

/**
 * Created by sahni on 25/3/16.
 */
public class MainActivity extends BaseAppCompatActivity implements View.OnClickListener, WebHandler.OnWebCallback {

    @Override
    protected void initUI() {
        setContentView(R.layout.activity_main);
        findViewById(android.R.id.button1).setOnClickListener(this);
        findViewById(android.R.id.background).setOnClickListener(this);
        String value = Constant.ACTION_BROADCAST_LANGUAGE_CHANGED;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case android.R.id.background:
                showHideKeyboard(this, false);
                break;
            case android.R.id.button1:
                showHideKeyboard(this, false);
                Toast.makeText(this, "Click", Toast.LENGTH_SHORT).show();
                ActivityManager.with(this, ActivityParam.ActivityType.START)
                        .klass(MainActivity1.class).build();
                break;
        }
    }

    /**
     * This method is used to show or hide keyboard.
     *
     * @param context      set context of calling class
     * @param isShowOrHide if pass true then show keyboard else hide keyboard
     */
    public static void showHideKeyboard(Activity context, boolean isShowOrHide) {
        try {
            InputMethodManager imm = (InputMethodManager) context
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            if (isShowOrHide) {
                imm.showSoftInput(context.getCurrentFocus(),
                        InputMethodManager.SHOW_IMPLICIT);
            } else {
                if (imm.isAcceptingText()) {
                    imm.hideSoftInputFromWindow(context
                            .getCurrentFocus().getWindowToken(), 0);
                }
            }
        } catch (Exception e) {
            ApplicationUtils.Log.e(e.getMessage());
        }
    }

    @Override
    public <T> void onSuccess(@Nullable T object, String response, int taskId, int statusCode) {
    }

    @Override
    public <T> void onError(@Nullable T object, String error, int taskId, int statusCode) {

    }
}
