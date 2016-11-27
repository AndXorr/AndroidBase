package net.base;

import android.app.Activity;
import android.base.activity.BaseAppCompatActivity;
import android.base.constant.Constant;
import android.base.fragment.FragParam;
import android.base.http.WebHandler;
import android.base.util.ApplicationUtils;
import android.base.util.categories.LocaleUtils;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

/**
 * Created by sahni on 25/3/16.
 */
public class MainActivity1 extends BaseAppCompatActivity implements View.OnClickListener, WebHandler.OnWebCallback {

    @Override
    protected void initUI() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replace);
        if (savedInstanceState == null) {
            android.base.fragment.FragmentManager.with(this, android.R.id.replaceText)
                    .type(FragParam.FragType.REPLACE)
                    .fragment(MainFragment.init(MainFragment.class, new Bundle()))
                    .backStack(false)
                    .build();
        }
    }
}
