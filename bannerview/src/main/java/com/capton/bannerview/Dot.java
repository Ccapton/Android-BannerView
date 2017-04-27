package com.capton.bannerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;

/**
 * Created by CAPTON on 2017/1/15.
 */

public class Dot extends ImageView {
    private int dotColor= Color.WHITE;
    private final int RADIUS_SMALL=6;
    private final int RADIUS_NORMAL=8;
    private final int RADIUS_LARGE=12;
    private int diameter =RADIUS_NORMAL;
    private Paint paint;
    private int width,height;

    public Dot(Context context) {
        super(context);

    }

    public Dot(Context context, AttributeSet attrs) {
        super(context, attrs);
        width= (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,this.diameter,getResources().getDisplayMetrics());
        height=(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,this.diameter,getResources().getDisplayMetrics());
        paint=new Paint();
        paint.setColor(dotColor);
        paint.setAntiAlias(true);
    }

    public Dot(Context context, int dotColor, int diameter ) {
        super(context);
        this.dotColor=dotColor;
        this.diameter =diameter;

        width= (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,this.diameter,getResources().getDisplayMetrics());
        height=(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,this.diameter,getResources().getDisplayMetrics());

        paint=new Paint();
        paint.setColor(this.dotColor);
        paint.setAntiAlias(true);
    }


    public void setAlpha(float alpha){
        int tureAlpha= (int) (255*alpha);
        paint.setAlpha(tureAlpha);
        invalidate();
    }


    public void setColor(int color){
        paint.setColor(color);
        invalidate();
    }

    public void setSize(int size){
        switch (size){
            case RADIUS_SMALL:
            case RADIUS_NORMAL:
            case RADIUS_LARGE:
                diameter=size;
                width= (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,diameter,getResources().getDisplayMetrics());
                height=(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,diameter,getResources().getDisplayMetrics());
                measure(width,height);
                invalidate();
                break;
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.drawCircle(width/2,height/2,width/2,paint);
        canvas.restore();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);
        int heightSize=MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(width,height);
    }


}
