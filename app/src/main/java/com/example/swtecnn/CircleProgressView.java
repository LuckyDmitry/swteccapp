package com.example.swtecnn;

import android.app.ActionBar;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class CircleProgressView extends View implements CircleProgress
{

    private static final int DEFAULT_PERCENT = 100;
    private static final int DEFAULT_STROKE_WIDTH = 20;
    private static final int DEFAULT_COLOR = R.color.black_yellow;
    private static final int DEFAULT_RADIUS = 0;

    Paint paint = new Paint();

    RectF rectF = new RectF(0,0,0,0);
    private int strokeWidth;
    private int percent;
    private int color;
    private int radius;
    public CircleProgressView(Context context) {
        this(context, null);
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        if(attrs != null){
            TypedArray typedArray = getContext()
                    .obtainStyledAttributes(attrs, R.styleable.CircleProgressView);
            percent = typedArray.getInteger(R.styleable.CircleProgressView_piv_fill_percent, DEFAULT_PERCENT);
            strokeWidth = typedArray.getInteger(R.styleable.CircleProgressView_piv_percent_width, DEFAULT_STROKE_WIDTH);
            color = typedArray.getInteger(R.styleable.CircleProgressView_piv_color, DEFAULT_COLOR);
            radius = typedArray.getInteger(R.styleable.CircleProgressView_radius, DEFAULT_RADIUS);
            typedArray.recycle();
        }

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(color);
        paint.setStrokeWidth(strokeWidth);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int desiredWidth = calculateWidth() + getPaddingStart() + getPaddingEnd();
        int desiredHeight = calculateHeight() + getPaddingTop() + getPaddingBottom();
        int width = resolveSize(desiredWidth, widthMeasureSpec);
        int height = resolveSize(desiredHeight, heightMeasureSpec);

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight() / 2;
        int width = getWidth() / 2;
        radius = getWidth() / 4;
        rectF.set(width - radius,
                height - radius,
                width + radius,
                height + radius);
        canvas.drawArc(rectF, 270, (float)(percent * 3.6), false, paint);
    }

    private int calculateWidth(){
        return (int)(rectF.right - rectF.left);
    }

    private int calculateHeight(){
        return (int)(rectF.bottom - rectF.top);
    }

    @Override
    public void increment() {
        percent++;
        if(percent < 100){
            invalidate();
        }
        else{
            percent = 100;
        }
    }

    @Override
    public int getValue() {
        return percent;
    }

    @Override
    public void reset() {
        percent = 0;
        invalidate();
    }
}
