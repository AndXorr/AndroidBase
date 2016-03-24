package android.base.widget;

import android.base.R;
import android.base.util.ApplicationUtils;
import android.base.util.LetterSpacingUtils;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.text.Html;
import android.text.TextUtils;
import android.util.AttributeSet;


/**
 * This class is used as Widget instead of TextView.This class has also a custom
 * attribute which is used in xml file.
 * <p/>
 * This attribute is customtypeface support string value pass name of typeface
 * of using in asses folder here. It will automatically set on TextView text.
 * </P>
 *
 * @author amit.singh
 */
public class BaseTextView extends AppCompatTextView {

    public BaseTextView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public BaseTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        setAttributes(attrs);
    }

    void setAttributes(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray ta = getContext().obtainStyledAttributes(attrs,
                    R.styleable.BaseTextView);
            String typeface = ApplicationUtils.getFontName(getContext(), ta
                    .getInt(R.styleable.BaseTextView_typefaces, -1), ta.getResourceId(R.styleable.BaseTextView_customTypeface, -1));
            if (ta.getBoolean(R.styleable.BaseTextView_enableHtml, false)) {
                setText(Html.fromHtml(getText().toString()));
            }
            ta.recycle();
            if (!TextUtils.isEmpty(typeface)) {
                Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                        typeface);
                setTypeface(tf);
            } else {
                // Nothing
            }
        }
    }

    /**
     * This method is used to setTextSize. In this float value is changed to
     * scaledDenisty and then set to the editText
     *
     * @param px float size of editText
     */
    public void setTextViewSize(float px) {
        float scaledDensity = getResources().getDisplayMetrics().scaledDensity;
        int sp = (int) (px / scaledDensity);
        setTextSize(sp);
    }


    /**
     * ***************
     * Spacing between characters of text view
     * *******************
     * <ahref http://stackoverflow.com/questions/5133548/how-to-change-letter-spacing-in-a-textview></ahref>
     * <ahref http://stackoverflow.com/questions/5133548/how-to-change-letter-spacing-in-a-textview></ahref>
     */

    private float letterSpacing = LetterSpacingUtils.BIGGEST;
    private CharSequence originalText = "";

    public float setTextSpacing() {
        return letterSpacing;
    }

    public void setTextSpacing(float letterSpacing) {
        this.letterSpacing = letterSpacing;
        originalText = getText();
        applyLetterSpacing();
    }

    private void applyLetterSpacing() {
        if (this == null || this.originalText == null) return;
        super.setText(LetterSpacingUtils.applyLetterSpacing(originalText.toString(), letterSpacing), BufferType.SPANNABLE);
    }


}
