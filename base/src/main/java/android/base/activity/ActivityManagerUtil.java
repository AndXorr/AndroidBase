package android.base.activity;

import android.base.util.ApplicationUtils;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;

/**
 * Created by clickapps on 24/11/15.
 */
public class ActivityManagerUtil {

    public static void performTask(ActivityParam activityParam) {
        ActivityParam.ActivityType activityType = activityParam.activityType;
        switch (activityType) {
            case START:
            case START_FINISH:
                Intent intent = new Intent(activityParam.context, activityParam.uri);
                if (activityParam.flag == 0) {
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                            | Intent.FLAG_ACTIVITY_NEW_TASK);
                } else {
                    intent.setFlags(activityParam.flag);
                }
                if (activityParam.bundle != null) {
                    intent.putExtras(activityParam.bundle);
                }
                if (activityParam.activityOptionsCompat != null && ApplicationUtils.isLollipop()) {
                    activityParam.context.startActivity(intent, activityParam.activityOptionsCompat.toBundle());
                } else {
                    activityParam.context.startActivity(intent);
                    if (activityParam.enableAnimation && activityParam.enter > 0 && activityParam.exit > 0)
                        activityParam.context.overridePendingTransition(activityParam.enter, activityParam.exit);
                }
                if (activityType == ActivityParam.ActivityType.START_FINISH) {
                    finish(activityParam);
                }
                break;
            case START_RESULT:
            case START_RESULT_FINISH:
                intent = new Intent(activityParam.context, activityParam.uri);
                if (activityParam.flag == 0) {
                } else {
                    intent.setFlags(activityParam.flag);
                }
                if (activityParam.bundle != null) {
                    intent.putExtras(activityParam.bundle);
                }
                if (activityParam.activityOptionsCompat != null && ApplicationUtils.isLollipop()) {
                    activityParam.context.startActivityForResult(intent, activityParam.requestCode, activityParam.activityOptionsCompat.toBundle());
                } else {
                    activityParam.context.startActivityForResult(intent, activityParam.requestCode);
                    if (activityParam.enableAnimation && activityParam.enter > 0 && activityParam.exit > 0)
                        activityParam.context.overridePendingTransition(activityParam.enter, activityParam.exit);
                }
                if (activityType == ActivityParam.ActivityType.START_RESULT_FINISH) {
                    finish(activityParam);
                }
                break;
            case FINISH:
                finish(activityParam);
                break;
            default:
                break;
        }
    }

    private static void finish(ActivityParam activityParam) {
        ActivityCompat.finishAfterTransition(activityParam.context);
        if (!ApplicationUtils.isLollipop()) {
            if (activityParam.enableAnimation && activityParam.enter > 0 && activityParam.exit > 0)
                activityParam.context.overridePendingTransition(activityParam.enter, activityParam.exit);
        }
    }

}
