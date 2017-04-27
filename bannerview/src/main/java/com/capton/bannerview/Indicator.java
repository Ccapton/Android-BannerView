package com.capton.bannerview;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CAPTON on 2017/1/15.
 */

public class Indicator extends RelativeLayout {
    private int dotCount=3;
    private int dotColor= Color.WHITE;
    private final int GAP_SMALL=2;
    private final int GAP_NORMAL=3;
    private final int GAP_LARGE=4;
    private int dotGap= GAP_NORMAL;
    private  final int DOT_SMALL=6;
    private  final int DOT_NORMAL=8;
    private  final int DOT_LARGE=12;
    private int diameter=DOT_NORMAL;
    private List<Dot> dotList=new ArrayList<>();

    public void setDotColor(int dotColor){
        this.dotColor=dotColor;
        for (int i = 0; i <dotList.size(); i++) {
            dotList.get(i).setColor(dotColor);
            dotList.get(i).setAlpha(0.4f);
        }
        dotList.get(0).setAlpha(1f);
    }
    public void setDotSize(int dotSize){
        if(dotSize==DOT_SMALL||dotSize==DOT_NORMAL||dotSize==DOT_LARGE) {
            this.diameter =dotSize;
            for (int i = 0; i <dotList.size(); i++) {
               dotList.get(i).setSize(dotSize);
            }
            switch (dotSize){
                case DOT_SMALL:dotGap=GAP_SMALL;break;
                case DOT_NORMAL:dotGap=GAP_NORMAL;break;
                case DOT_LARGE:dotGap=GAP_LARGE;break;
            }
        }else {
            Log.e("setDotSize", " Wrong setting!Please input a correct dotSize:'Indicator." +
                    "DOT_SMALL','Indicator.DOT_NORMAL'or'Indicator.DOT_NORMAL'");
        }
    }

   public void setDotChecked(int position){
       for (int i = 0; i <dotList.size(); i++) {
           dotList.get(i).setAlpha(0.4f);
       }
       dotList.get(position).setAlpha(1f);
   }

    public Indicator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public Indicator(Context context, int dotCount,int dotColor) {
        super(context);
        this.dotColor=dotColor;
        this.dotCount=dotCount;
        for (int i = 0; i < this.dotCount; i++) {
            Dot dot = new Dot(context,this.dotColor, diameter);
            LayoutParams lp = new LayoutParams(diameter, diameter);
            dot.setLayoutParams(lp);
            dotList.add(dot);
            addView(dot);
        }
        setDotColor(this.dotColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,diameter*dotCount+2*dotGap*dotCount,getResources().getDisplayMetrics());
        int height=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,diameter,getResources().getDisplayMetrics());
        setMeasuredDimension(width,height);
    }

    private void setChildLayout(){
        for (int i = 0; i < dotList.size(); i++) {
            int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dotGap + (diameter + dotGap * 2) * i, getResources().getDisplayMetrics());
            int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dotGap + (diameter + dotGap * 2) * i + diameter, getResources().getDisplayMetrics());
            int top = 0;
            int bottom = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, diameter, getResources().getDisplayMetrics());
            dotList.get(i).layout(left, top, right, bottom);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        setChildLayout();
    }
}
