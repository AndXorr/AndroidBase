package android.base.util;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ScaleXSpan;

/**
 * Created by clickapps on 21/7/15.
 */
public class LetterSpacingUtils {
    public final static float NORMAL = 0;
    public final static float NORMALBIG = (float) 0.025;
    public final static float BIG = (float) 0.05;
    public final static float BIGGEST = (float) 0.2;

    public static SpannableString applyLetterSpacing(String originalText, float letterSpacing) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < originalText.length(); i++) {
            String c = "" + originalText.charAt(i);
            builder.append(c);
            if (i + 1 < originalText.length()) {
                builder.append("\u00A0");
            }
        }
        SpannableString finalText = new SpannableString(builder.toString());
        if (builder.toString().length() > 1) {
            for (int i = 1; i < builder.toString().length(); i += 2) {
                finalText.setSpan(new ScaleXSpan((letterSpacing + 1) / 10), i, i + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        return finalText;
    }
}
