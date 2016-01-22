package com.example.waniltonfilho.personaltasks.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.example.waniltonfilho.personaltasks.R;

/**
 * Created by wanilton.filho on 21/01/2016.
 */
public class OurView extends View {

    private View mView;

    public OurView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        Rect rect = new Rect(0, 0, getMeasuredWidth(), 1);
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color.divisor));
        canvas.drawRect(rect, paint);
    }


}
