package net.base;

import android.app.Activity;
import android.base.fragment.BaseFragment;
import android.base.util.ApplicationUtils;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by sahni on 25/3/16.
 */
public class MainFragment2 extends BaseFragment {


    @Override
    protected View initUI(LayoutInflater inflater, ViewGroup container) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.activity_main, null);
            mView.findViewById(android.R.id.button1).setOnClickListener(this);
            mView.findViewById(android.R.id.background).setOnClickListener(this);
        }
        return mView;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case android.R.id.background:
                break;
            case android.R.id.button1:
                ApplicationUtils.Locale.updateLocale(getActivity(), "ar", true);
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
