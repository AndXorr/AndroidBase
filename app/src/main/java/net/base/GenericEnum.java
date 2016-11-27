package net.base;

import android.base.http.Builder;
import android.base.http.RetrofitManager;

import java.util.Map;

/**
 * Created by sahni on 24/8/16.
 */
public enum GenericEnum {
    LOGIN() {
        @Override
        public void execute(Builder client) {
            client.connect(Test.class).login(client.getWebParam().getRequestParam())
                    .enqueue(new RetrofitManager.CallBack<>(client.getWebParam()));
        }
    };

    public abstract void execute(Builder client);
}
