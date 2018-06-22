package com.vedas.weightloss.FONTS;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by vedas on 3/27/2015.
 */
public class EditTextFontStyle extends EditText {
    public EditTextFontStyle(Context context) {
        super(context);
        init();
    }

    public EditTextFontStyle(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditTextFontStyle(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                "fonts/FiraSans-Regular.ttf");
        setTypeface(tf);
    }
}
