package com.kyler.toolbarmenudrawer;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class ToolbarMenudrawerTextView extends TextView {

    public ToolbarMenudrawerTextView(Context context, AttributeSet attrs,
                                     int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    public ToolbarMenudrawerTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);

    }

    public ToolbarMenudrawerTextView(Context context) {
        super(context);
        init(null);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs,
                    R.styleable.ToolbarMenuDrawer);
            String fontName = a
                    .getString(R.styleable.ToolbarMenuDrawer_fontName);
            if (fontName != null) {
                Typeface myTypeface = Typeface.createFromAsset(getContext()
                        .getAssets(), "fonts/" + fontName);
                setTypeface(myTypeface);
            }
            a.recycle();
        }
    }

}