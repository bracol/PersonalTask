package com.example.waniltonfilho.personaltasks.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.waniltonfilho.personaltasks.R;

/**
 * Created by wanilton.filho on 21/01/2016.
 */
public class CustomViews extends View {

    Paint mPaintColor;
    String circleText;
    Integer circleColor;
    Integer circleTextColor;
    Float circleTextSize;


    public CustomViews(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaintColor = new Paint();
        TypedArray attributesValuesArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomViews, 0, 0);
        try{
            circleText = attributesValuesArray.getString(R.styleable.CustomViews_circleText);
            circleColor = attributesValuesArray.getInteger(R.styleable.CustomViews_circleColor, 0);
            circleTextColor = attributesValuesArray.getInteger(R.styleable.CustomViews_circleTextColor, 0);
            circleTextSize = attributesValuesArray.getFloat(R.styleable.CustomViews_circleTextSize, 0);
        }
        finally {
            attributesValuesArray.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaintColor.setStyle(Paint.Style.FILL);
        mPaintColor.setAntiAlias(true);
        mPaintColor.setColor(circleColor);

        int centerX = this.getMeasuredWidth() / 2;
        int centerY = this.getMeasuredHeight() / 2;
        int radius = 150;
        canvas.drawCircle(centerX, centerY, radius, mPaintColor);

        mPaintColor.setColor(circleTextColor);
        mPaintColor.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(circleText, centerX, centerY, mPaintColor);
    }

    public void setCircleColor(int newCircleColor){
        circleColor = newCircleColor;
        invalidate();
        requestLayout();
    }

    public void setCircleTextColor(int newCircleTextColor){
        circleTextColor = newCircleTextColor;
        invalidate();
        requestLayout();
    }

    public void setCircleText(String newCircleText){
        circleText = newCircleText;
        invalidate();
        requestLayout();
    }

    public void setCircleTextSize(float newCircleTextSize){
        mPaintColor.setTextSize(newCircleTextSize);
        invalidate();
        requestLayout();
    }
}
