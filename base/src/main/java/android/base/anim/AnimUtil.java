package android.base.anim;


/**
 * The type Anim util.
 */
public class AnimUtil {

    /**
     * Gets pos.
     *
     * @param animParam the anim param
     */
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
