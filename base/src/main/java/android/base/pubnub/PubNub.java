package android.base.pubnub;

import android.base.log.Log;

import com.google.common.base.Optional;
import com.pubnub.api.Callback;
import com.pubnub.api.Pubnub;
import com.pubnub.api.PubnubError;
import com.pubnub.api.PubnubException;

/**
 * Created by clickapps on 5/2/16.
 */
public class PubNub {
    private Pubnub pubnub;

    public Pubnub getPubnub() {
        return pubnub;
    }

    public PubNub(PubNubParam pubNubParam) {
        pubnub = new Pubnub(pubNubParam.publish_key, pubNubParam.subscribe_key,
                pubNubParam.secret_key, pubNubParam.cipher_key, pubNubParam.ssl_on);
        pubnub.setCacheBusting(false);
        pubnub.setResumeOnReconnect(true);
        pubnub.setOrigin(PubNubConstant.ORIGIN);
        pubnub.setAuthKey(pubNubParam.secret_key);
        handleEvent(pubNubParam);
    }

    private void handleEvent(PubNubParam pubNubParam) {
        switch (pubNubParam.event) {
            case SUB:
                try {
                    pubnub.subscribe(pubNubParam.channels, new PubNubCallback(pubNubParam));
                } catch (PubnubException e) {
                    e.printStackTrace();
                }
                break;
            case UNSUB:
                pubnub.unsubscribe(pubNubParam.channels);
                break;
            case UNSUBALL:
                pubnub.unsubscribeAll();
            default:
                break;
        }
    }

    private class PubNubCallback extends Callback {
        private PubNubParam pubNubParam;

        private PubNubCallback(PubNubParam param) {
            this.pubNubParam = param;
        }

        @Override
        public void successCallback(String channel, Object message) {
            Optional<Object> optional = Optional.fromNullable(message);
            if (optional.isPresent()) {
                message = optional.get();
                Log.i(getClass().getName(), "Channel : " + channel + "\nMessage = " + message);
                if (pubNubParam.listener != null)
                    pubNubParam.listener.onSuccess(channel, message);
            }
        }

        @Override
        public void errorCallback(String channel, PubnubError error) {
            Optional<PubnubError> optional = Optional.fromNullable(error);
            if (optional.isPresent()) {
                error = optional.get();
                Log.e(getClass().getName(), "Channel : " + channel + "\nMessage = " + error.getErrorString());
                if (pubNubParam.listener != null)
                    pubNubParam.listener.onFailure(channel, error.getErrorString());
            }
        }

        @Override
        public void connectCallback(String channel, Object message) {
            Optional<Object> optional = Optional.fromNullable(message);
            if (optional.isPresent()) {
                message = optional.get();
                Log.d(getClass().getName(), "Channel : " + channel + "\nMessage = " + message);
            }
        }

        @Override
        public void reconnectCallback(String channel, Object message) {
            Optional<Object> optional = Optional.fromNullable(message);
            if (optional.isPresent()) {
                message = optional.get();
                Log.d(getClass().getName(), "Channel : " + channel + "\nMessage = " + message);
            }
        }

        @Override
        public void disconnectCallback(String channel, Object message) {
            Optional<Object> optional = Optional.fromNullable(message);
            if (optional.isPresent()) {
                message = optional.get();
                Log.d(getClass().getName(), "Channel : " + channel + "\nMessage = " + message);
            }
        }
    }
}
