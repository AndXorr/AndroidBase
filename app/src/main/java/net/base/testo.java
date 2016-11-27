package net.base;

import android.base.activity.BaseAppCompatActivity;
import android.base.alert.Alert;
import android.base.http.WebConnect;
import android.base.log.Log;
import android.base.util.ApplicationUtils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedHashMap;


/**
 * Created by clickapps on 17/2/16.
 */
public class testo extends BaseAppCompatActivity {
    @Override
    protected void initUI() {
        final String URL_CONFIG = "config_file";
        HashMap<String, String> internal = new LinkedHashMap<>();
        internal.put("email", "a@a.com");
        internal.put("password", "password");
        HashMap<String, HashMap> external = new LinkedHashMap<>();
        external.put("user", internal);

        String value = new JSONObject(external).toString();
        Log.d(getLocalClassName(), "Json = " + value);

        GenericEnum.LOGIN.execute(WebConnect.with(this, URL_CONFIG).showDialog(true));
    }


}
