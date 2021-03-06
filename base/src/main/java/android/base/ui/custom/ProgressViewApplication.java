package android.base.ui.custom;

import android.base.R;
import android.base.dialog.BaseProgressDialog;
import android.base.util.ApplicationUtils;
import android.base.ui.widget.BaseProgressBar;
import android.base.ui.widget.BaseTextView;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

/**
 * Created by clickapps on 3/7/15.
 */
public class ProgressViewApplication extends RelativeLayout {
    private BaseTextView textView;
    private BaseProgressBar progressBar;
    private View view;

    /**
     * Instantiates a new Progress mView application.
     *
     * @param context the context
     */
    public ProgressViewApplication(Context context) {
        super(context);
        init(null);
    }

    /**
     * Instantiates a new Progress mView application.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public ProgressViewApplication(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    /**
     * Instantiates a new Progress mView application.
     *
     * @param context      the context
     * @param attrs        the attrs
     * @param defStyleAttr the def style attr
     */
    public ProgressViewApplication(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);

    }

    private void init(AttributeSet attrs) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_progress_base, null);
        textView = (BaseTextView) view.findViewById(R.id.dialog_progress_baseTextView);
        progressBar = (BaseProgressBar) view.findViewById(R.id.dialog_progress_baseProgressBar);
        if (attrs != null) {
            TypedArray ta = getContext().obtainStyledAttributes(attrs,
                    R.styleable.ProgressViewApplication);
            // intermediateDrawable
            int intermediateDrawable = ta.getResourceId(R.styleable.ProgressViewApplication_indeterminateDrawable, -1);
            if (intermediateDrawable != -1)
                progressBar.setIndeterminateDrawable(ContextCompat.getDrawable(getContext(), intermediateDrawable));
            // intermediateDrawable color
            int tint = ta.getResourceId(R.styleable.ProgressViewApplication_android_tint, -1);
            if (tint != -1)
                progressBar.setIndeterminateTint(tint);
            // ProgressText
            String progressText = ta.getString(R.styleable.ProgressViewApplication_android_text);
            if (!ApplicationUtils.Validator.isEmptyOrNull(progressText)) {
                textView.setVisibility(VISIBLE);
                textView.setText(progressText);
            } else {
                textView.setText(" ");
                textView.setVisibility(GONE);
            }
            // textColor
            int textColor = ta.getResourceId(R.styleable.ProgressViewApplication_android_textColor, -1);
            if (textColor != -1)
                textView.setTextColor(ContextCompat.getColor(getContext(), textColor));
            ta.recycle();
            addView(view);
        }
    }


    /**
     * Gets text mView.
     *
     * @return the text mView
     */
    public BaseTextView getTextView() {
        return textView;
    }

    /**
     * Gets progress bar.
     *
     * @return the progress bar
     */
    public BaseProgressBar getProgressBar() {
        return progressBar;
    }

    /**
     * Show.
     */
    public void show() {
        CustomDialog dialog = new CustomDialog(getContext(), R.style.Widget_ProgressDialog);
        dialog.setIndeterminateDrawable(ContextCompat.getDrawable(getContext(), R.drawable.dialog_progress_indeterminate));
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);
        dialog.show();
    }

    /**
     * Gets progress dialog.
     *
     * @return the progress dialog
     */
    public BaseProgressDialog getProgressDialog() {
        CustomDialog dialog = new CustomDialog(getContext(), R.style.Widget_ProgressDialog);
        dialog.setIndeterminateDrawable(ContextCompat.getDrawable(getContext(), R.drawable.dialog_progress_indeterminate));
        return dialog;
    }

    /**
     * Enable white color progress bar.
     */
    public void enableWhiteColorProgressBar() {
        if (getProgressBar() != null) {
            getProgressBar().getIndeterminateDrawable().setColorFilter(new LightingColorFilter(0xFF000000, 0xFFFFFF));
        }
    }

    /**
     * Sets progress text.
     *
     * @param resId the res id
     */
    public void setProgressText(@StringRes int resId) {
        getTextView().setVisibility(VISIBLE);
        getTextView().setText(resId);
    }

    /**
     * Sets progress text.
     *
     * @param text the text
     */
    public void setProgressText(String text) {
        if (!ApplicationUtils.Validator.isEmptyOrNull(text)) {
            getTextView().setVisibility(VISIBLE);
            getTextView().setText(text);
        }
    }

    /**
     * The type Custom dialog.
     */
    public class CustomDialog extends BaseProgressDialog {

        /**
         * Instantiates a new Custom dialog.
         *
         * @param context the context
         */
        public CustomDialog(Context context) {
            super(context);
        }

        /**
         * Instantiates a new Custom dialog.
         *
         * @param context the context
         * @param theme   the theme
         */
        public CustomDialog(Context context, int theme) {
            super(context, theme);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            super.onCreate(savedInstanceState);
            setContentView(R.layout.dialog_progress_base);
            textView = (BaseTextView) findViewById(R.id.dialog_progress_baseTextView);
            progressBar = (BaseProgressBar) findViewById(R.id.dialog_progress_baseProgressBar);
        }
    }
}
