package com.example.youku;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout; /**
 * 显示和隐藏指定控件
 */
class Tools {
    public static void hideView(ViewGroup view) {
       hideView( view,0 );
    }

    public static void showView(ViewGroup view) {
//        RotateAnimation rotateAnimation=new RotateAnimation( 180,360,
//        view.getWidth()/2,view.getHeight());
//        rotateAnimation.setDuration( 500 );
//        rotateAnimation.setFillAfter( true );//动画在播放完成后的状态
//        view.startAnimation( rotateAnimation );
//        for(int i=0;i<view.getChildCount();i++)
//        {
//            View v=view.getChildAt( i );
//            v.setEnabled( true );
//        }
//        view.setRotation(  );
        view.setPivotX( view.getWidth()/2 );
        view.setPivotY( view.getHeight() );
        ObjectAnimator animator=ObjectAnimator.ofFloat(view,"rotation",
                180,360);
        animator.setDuration( 500 );
        animator.start();


    }

    public static void hideView(ViewGroup view, int startOffset) {
//        RotateAnimation rotateAnimation=new RotateAnimation( 0,180,
//                view.getWidth()/2,view.getHeight()
//        );
//        rotateAnimation.setDuration( 500 );//动画播放持续时间
//        rotateAnimation.setFillAfter( true );//动画在播放完成后的状态
//        rotateAnimation.setStartOffset( startOffset );
//        view.startAnimation( rotateAnimation );
//        for(int i=0;i<view.getChildCount();i++)
//        {
//            View v=view.getChildAt( i );
//            v.setEnabled( false );
//        }
        view.setPivotX( view.getWidth()/2 );
        view.setPivotY( view.getHeight() );
        ObjectAnimator animator=ObjectAnimator.ofFloat(view,"rotation",
                0,180);
        animator.setDuration( 500 );
        animator.setStartDelay( startOffset );
        animator.start();
    }
}
