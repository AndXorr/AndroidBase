package android.base.pubnub;

import android.base.util.ApplicationUtils;

import com.pubnub.api.Callback;
import com.pubnub.api.Pubnub;
import com.pubnub.api.PubnubError;
import com.pubnub.api.PubnubException;

import java.text.MessageFormat;


/**
 * The type Pub nub.
 */
public class PubNub {

    private PubNub() {

    }

    private Pubnub pubnub;

    /**
     * Instantiates a new Pub nub.
     *
     * @param pubNubParam the pub nub param
     */
    public PubNub(PubNubParam pubNubParam) {
        pubnub = new Pubnub(pubNubParam.publish_key, pubNubParam.subscribe_key,
                pubNubParam.secret_key, pubNubParam.cipher_key, pubNubParam.ssl_on);
        pubnub.setCacheBusting(false);
        pubnub.setResumeOnReconnect(true);
        pubnub.setOrigin(PubNubConstant.ORIGIN);
        pubnub.setAuthKey(pubNubParam.secret_key);
    }

    /**
     * Gets pub nub.
     *
     * @return the pub nub
     */
    public Pubnub getPubNub() {
        return pubnub;
    }

    /**
     * Handle event.
     *
     * @param pubNubParam the pub nub param
     */
    public void handleEvent(PubNubParam pubNubParam) {
        switch (pubNubParam.event) {
            case SUB:
                try {
                    pubnub.subscribe(pubNubParam.channels, new PubNubCallback(pubNubParam));
                } catch (PubnubException e) {
                    ApplicationUtils.Log.e(e.getMessage());
                }
                break;
            case UNSUB:
                pubnub.unsubscribe(pubNubParam.channels);
                break;
            case UNSUBALL:
                pubnub.unsubscribeAll();
                break;
            default:
                break;
        }
    }

    private class PubNubCallback extends Callback {
        private PubNubParam pubNubParam;
        private static final String FORMAT = "Channel : {0}\nMessage = {1}";

        private PubNubCallback(PubNubParam param) {
            this.pubNubParam = param;
        }

        @Override
        public void successCallback(String channel, Object message) {
            ApplicationUtils.Log.i(MessageFormat.format(FORMAT, channel, message));
            if (pubNubParam.listener != null
                    && message != null)
                pubNubParam.listener.onSuccess(channel, message);
        }

        @Override
        public void errorCallback(String channel, PubnubError error) {
            ApplicationUtils.Log.e(MessageFormat.format(FORMAT, channel, error.getErrorString()));
            if (pubNubParam.listener != null)
                pubNubParam.listener.onFailure(channel, error.getErrorString());
        }

        @Override
        public void connectCallback(String channel, Object message) {
            ApplicationUtils.Log.d(MessageFormat.format(FORMAT, channel, message));
        }

        @Override
        public void reconnectCallback(String channel, Object message) {
            ApplicationUtils.Log.d(MessageFormat.format(FORMAT, channel, message));
        }

        @Override
        public void disconnectCallback(String channel, Object message) {
            ApplicationUtils.Log.d(MessageFormat.format(FORMAT, channel, message));
        }
    }
}
