package android.base.interfaces;

import android.support.annotation.Nullable;

/**
 * Created by sahni on 25/3/16.
 */
public class WebHandler {

    public interface OnWebCallback {
        <T> void onSuccess(@Nullable T object, String response, int taskId, int statusCode);

        <T> void onError(@Nullable T object, String error, int taskId, int statusCode);
    }

    public abstract class WebCallback implements OnWebCallback {

        @Override
        public <T> void onSuccess(@Nullable T object, String response, int taskId, int statusCode) {

        }

        @Override
        public <T> void onError(@Nullable T object, String error, int taskId, int statusCode) {

        }
    }
}
