package android.base.dialog;

import android.os.Bundle;

/**
 * This interface is used as a call back for the dialogs
 */
public interface OnDialogProcess {
    public int POSITIVE = 1;
    public int NEGATIVE = 2;
    public int NEUTRAL = 3;
    public int VIEW = 4;


    /**
     * callback for the view that is clicked/selected to perform other tasks
     *
     * @param dialogId      to identify dialog
     * @param bundle        data that can required by activity/fragment
     * @param object        Dialog class object
     * @param selectionType type LEFT, RIGHT, MIDDLE,VIEW
     */
    void onDialog(int dialogId, Bundle bundle, Object object, int selectionType);

}
