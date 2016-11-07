package android.base.http;

import android.support.annotation.Nullable;


/**
 * The type Web handler.
 */
public class WebHandler {

    /**
     * The interface On web callback.
     */
    public interface OnWebCallback {
        /**
         * On success.
         *
         * @param <T>        the type parameter
         * @param object     the object
         * @param response   the response
         * @param taskId     the task id
         * @param statusCode the status code
         */
        <T> void onSuccess(@Nullable T object, String response, int taskId, int statusCode);

        /**
         * On error.
         *
         * @param <T>        the type parameter
         * @param object     the object
         * @param error      the error
         * @param taskId     the task id
         * @param statusCode the status code
         */
        <T> void onError(@Nullable T object, String error, int taskId, int statusCode);
    }

    /**
     * The type Web callback.
     */
    public abstract class WebCallback implements OnWebCallback {

        @Override
        public <T> void onSuccess(@Nullable T object, String response, int taskId, int statusCode) {

        }

        @Override
        public <T> void onError(@Nullable T object, String error, int taskId, int statusCode) {

        }
    }
}
