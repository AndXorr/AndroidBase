package android.base.fragment;

import android.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * Created by Sahni on 10-11-2015.
 */
public class FragParam {

    public FragmentActivity context;
    public int replaceId, enter = 0, exit = 0, popEnter = 0, popExit = 0;
    public Fragment fragment;
    public String tag;
    public FragType fragType = FragType.REPLACE;

    public boolean enableAnimation = false, isBackStack = false;

    public enum FragType {
        ADD, REPLACE, POP, POP_TAG, RESTART, CLEAR
    }
}
