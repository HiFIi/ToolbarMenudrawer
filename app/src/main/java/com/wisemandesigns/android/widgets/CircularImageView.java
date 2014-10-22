package com.wisemandesigns.android.widgets;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;

public class CircularImageView extends ImageView {
    int color = R.color.background_light;
    private Bitmap bitmap;
    private float borderWidth = 1.0F;
    private float center;
    private boolean drawBorder = true;
    private int height;
    private Paint paint;
    private Paint paintBorder;
    private BitmapShader shader;
    private int width;

    public CircularImageView(Context paramContext) {
        super(paramContext);
        setup();
    }

    public CircularImageView(Context paramContext,
                             AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        setup();
    }

    public CircularImageView(Context paramContext,
                             AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
        setup();
    }

    private void setShader() {
        if ((this.bitmap != null) && (this.width > 0) && (this.height > 0)) {
            this.shader = new BitmapShader(Bitmap.createScaledBitmap(
                    this.bitmap, this.width, this.height, false),
                    BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
            this.paint.setShader(this.shader);
        }
    }

    private void setup() {
        Resources localResources = getResources();
        this.borderWidth = TypedValue.applyDimension(1, this.borderWidth,
                localResources.getDisplayMetrics());
        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.paintBorder = new Paint();
        this.paintBorder.setColor(color);
        this.paintBorder.setStyle(Paint.Style.STROKE);
        this.paintBorder.setStrokeWidth(this.borderWidth);
        this.paintBorder.setAntiAlias(true);
    }

    @Override
    public void onDraw(Canvas paramCanvas) {
        if ((this.bitmap != null) && (this.shader != null)) {
            float f1 = this.center - 2 * (int) this.borderWidth;
            float f2 = this.center - ((int) this.borderWidth >> 1);
            paramCanvas.drawCircle(this.center, this.center, f1, this.paint);
            if (this.drawBorder)
                paramCanvas.drawCircle(this.center, this.center, f2
                        - this.borderWidth, this.paintBorder);
        }
    }

    @Override
    protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3,
                                 int paramInt4) {
        super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
        this.width = paramInt1;
        this.height = paramInt2;
        this.center = (this.width >> 1);
        setShader();
    }

    public void setDrawBorder(boolean paramBoolean) {
        this.drawBorder = paramBoolean;
        invalidate();
    }

    @Override
    public void setImageDrawable(Drawable paramDrawable) {
        super.setImageDrawable(paramDrawable);
        if ((paramDrawable instanceof BitmapDrawable)) {
            this.bitmap = ((BitmapDrawable) paramDrawable).getBitmap();
            setShader();
            return;
        }
        this.shader = null;
        invalidate();
    }
}