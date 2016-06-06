package android.base.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;

/**
 * Created by Sahni on 10-11-2015.
 */
public class FragParam {

    protected FragmentActivity context;
    protected int replaceId, enter = 0, exit = 0, popEnter = 0, popExit = 0;
    protected Fragment fragment;
    protected String tag;
    protected FragType fragType = FragType.REPLACE;

    protected boolean enableAnimation = false, isBackStack = false;

    public enum FragType {
        REPLACE {
            @Override
            public void execute(FragParam fragParam, FragmentTransaction ft) {
                FragmentManagerUtil.replace(fragParam, ft);
            }
        }, POP {
            @Override
            public void execute(FragParam fragParam, FragmentTransaction ft) {
                FragmentManagerUtil.pop(fragParam, ft);
            }
        }, POP_TAG {
            @Override
            public void execute(FragParam fragParam, FragmentTransaction ft) {
                FragmentManagerUtil.popTag(fragParam, ft);
            }
        }, RESTART {
            @Override
            public void execute(FragParam fragParam, FragmentTransaction ft) {
                FragmentManagerUtil.restart(fragParam, ft);
            }
        }, CLEAR {
            @Override
            public void execute(FragParam fragParam, FragmentTransaction ft) {
                FragmentManagerUtil.clear(fragParam);
            }
        };

        public abstract void execute(FragParam fragParam, FragmentTransaction ft);
    }
}
