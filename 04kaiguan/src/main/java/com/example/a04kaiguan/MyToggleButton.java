package com.example.a04kaiguan;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MyToggleButton extends View implements View.OnClickListener {
    /**
     * 如果在布局文件使用该类
     * 将会用这个跟构造方法实例该类
     * @param context
     * @param attrs
     */
    private Bitmap backgroundBitmap;
    private Bitmap slidingBitmap;
    private int slidingLeftMax;
    private int leftmagin;
    private Paint paint;
    /**
     * 点击事件生效
     */
    private boolean isEnableClick=true;


    public MyToggleButton(Context context, @Nullable AttributeSet attrs) {
        super( context, attrs );
        initView();
    }

    private void initView() {
        paint=new Paint( );
        paint.setAntiAlias( true );
        backgroundBitmap= BitmapFactory.decodeResource(
                getResources(),R.drawable.switch_background );
        slidingBitmap=BitmapFactory.decodeResource(
                getResources(),R.drawable.slide_button );
        slidingLeftMax=backgroundBitmap.getWidth()-slidingBitmap.getWidth();
        setOnClickListener( this );
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap( backgroundBitmap,0,0,paint );
        canvas.drawBitmap( slidingBitmap,leftmagin,0,paint );

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(backgroundBitmap.getWidth(),backgroundBitmap.getHeight());
    }

    private boolean isOpen=false;
    @Override
    public void onClick(View v) {
        if(isEnableClick)
        {
            isOpen=!isOpen;
            finishView();
        }

    }

    private void finishView() {
        if(isOpen)
        {
            leftmagin=slidingLeftMax;
        }
        else {
            leftmagin=0;
        }
        invalidate();//强制绘制
    }

    float startX;
    float lastX;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent( event );
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                //记录按下的坐标：
                lastX =startX=event.getX();
//                isEnableClick=true;
                break;
            case MotionEvent.ACTION_MOVE:

                //计算结束值
                float endX=event.getX();
                float distanceX=endX-startX;
                leftmagin+=distanceX;
                //屏蔽非法值；
                if(leftmagin<0)
                {
                    leftmagin=0;
                }
                else if(leftmagin>slidingLeftMax)
                {
                    leftmagin=slidingLeftMax;
                }

                //刷新：
                invalidate();
                //数据初始化：
                startX=event.getX();
                if(Math.abs( endX-lastX )>5)
            {
                isEnableClick=false;
            }
                break;
            case MotionEvent.ACTION_UP:

                if(!isEnableClick)
                {
                    if(leftmagin>slidingLeftMax/2)
                    {
                        isOpen=true;
                    }
                    else
                    {
                        isOpen=false;
                    }
                    finishView();
                }


                break;

        }
        return true;
    }

}
