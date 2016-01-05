package android.base.anim;

/**
 * Created by clickapps on 9/12/15.
 */
public class AnimUtil {

    public static void getPos(AnimParam animParam) {
        int x = 0, y = 0;
        switch (animParam.posX) {
            case BOTTOM:
                x = animParam.view.getBottom();
                break;
            case CENTER:
                x = animParam.view.getWidth() / 2;
                break;
            case LEFT:
                x = animParam.view.getLeft();
                break;
            case RIGHT:
                x = animParam.view.getRight();
                break;
            case TOP:
                x = animParam.view.getTop();
                break;
        }
        switch (animParam.posY) {
            case BOTTOM:
                y = animParam.view.getBottom();
                break;
            case CENTER:
                y = animParam.view.getWidth() / 2;
                break;
            case LEFT:
                y = animParam.view.getLeft();
                break;
            case RIGHT:
                y = animParam.view.getRight();
                break;
            case TOP:
                y = animParam.view.getTop();
                break;
        }
        animParam.setX = x;
        animParam.setY = y;
        animParam.startRadius = 0;
        animParam.endRadius = Math.max(animParam.view.getWidth(), animParam.view.getHeight());
    }
}
