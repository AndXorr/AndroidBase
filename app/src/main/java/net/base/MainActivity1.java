package net.base;

import android.base.activity.BaseAppCompatActivity;
import android.base.fragment.FragParam;
import android.base.http.WebHandler;
import android.os.Bundle;
import android.view.View;

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
