package com.example.a02guanggaoxiaoguo;

import android.content.Context;
import android.media.Image;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    TextView tv_title;
    LinearLayout ll_points_group;
    private ArrayList<ImageView> imageViews;
    private int [] imageID ={
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e,

    };
    private int pressposition=0;
    private boolean isScroll=true;
    private boolean isDragging=true;
    private String [] str={
      "aaaa",
      "bbbb",
      "cccc",
      "dddd"   ,
      "eeee"
    };
    private Handler handler=new Handler(  ){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage( msg );
            int item=(viewPager.getCurrentItem()+1);
            viewPager.setCurrentItem( item );
            handler.sendEmptyMessageDelayed( 0,4000 );
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        viewPager=(ViewPager)findViewById( R.id.viewpager);
        tv_title=findViewById( R.id.tv_title );
        ll_points_group=(LinearLayout)findViewById( R.id.points_group );
        tv_title.setText( str[0] );
        handler.sendEmptyMessageDelayed( 0,4000 );
        final LinearLayout.LayoutParams params=new LinearLayout.LayoutParams( 8,8 );
        imageViews=new ArrayList<>(  );
        for(int i=0;i<imageID.length;i++)
        {

            ImageView imageView=new ImageView( this );
            imageView.setBackgroundResource(imageID[i]  );
            imageViews.add( imageView );
            ImageView point=new ImageView( this );
            point.setBackgroundResource( R.drawable.point_select);
            if(i==0)
            {
                point.setEnabled( true );
            }
            else
            {
                point.setEnabled( false );
                params.leftMargin=8;

            }
            point.setLayoutParams( params );
            ll_points_group.addView( point );
        }
        viewPager.setAdapter( new MyViewPagerAdapter(  ) );
        viewPager.setOnPageChangeListener( new ViewPager.OnPageChangeListener() {
            /**
             * 页面滚动回调
             * @param position 当前页面位置
             * @param positionOffset 页面的滑动的百分比
             * @param positionOffsetPixels 滑动了多少了像素/距离
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


            }

            /**
             * 当某个页面被选中的时候回调
             * @param position 被选中页面的位置
             */
            @Override
            public void onPageSelected(int position) {

                //把上一个高亮的设置为默认，当前的设置为高亮并设置文本信息
                int realpositing=position%imageViews.size();
               tv_title.setText( str[realpositing] );
               ll_points_group.getChildAt(pressposition).setEnabled( false );
               ll_points_group.getChildAt(realpositing).setEnabled( true );
               pressposition=realpositing;

            }

            /**
             * 当页面滚动变化的时候回调这个方法
             * 从静止到滑动的变化
             * 从滑动到静止
             * 静止到拖拽
             * @param state
             */
            @Override
            public void onPageScrollStateChanged(int state) {

                if(state==ViewPager.SCROLL_STATE_DRAGGING)
                {

                    isDragging=true;
                    handler.removeCallbacksAndMessages( null );

                }
                else if(state==ViewPager.SCROLL_STATE_SETTLING)
                {

                }
                else if(state==ViewPager.SCROLL_STATE_IDLE)
                {

                    isDragging=false;
                    handler.removeCallbacksAndMessages( null );
                    handler.sendEmptyMessageDelayed( 0,4000 );
                }
            }
        } );

        //设置中间位置
        int item=Integer.MAX_VALUE/2-Integer.MAX_VALUE/2%imageViews.size();   //保证imageviews的整数倍
        viewPager.setCurrentItem( item );
    }
    class MyViewPagerAdapter extends PagerAdapter{


        /**
         * 得到图片总数
         * @return
         */
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        /**
         * 比较刚创建的试图和view是否是同一个实例
         * @param view
         * @param object
         * @return
         */
        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {


            return view==object;
        }

        /**
         * 相当于getView方法
         * @param container viewpager自身
         * @param position 当前实例化位置
         * @return
         */
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            int realposition=position%imageViews.size();
            Log.e( "instantiateItem", "instantiateItem= "+position+"imageView="+imageViews );
         final ImageView imageView=imageViews.get( realposition );
         container.addView( imageView );
//         imageView.setOnTouchListener( new View.OnTouchListener() {
//             @Override
//             public boolean onTouch(View v, MotionEvent event) {
//                 switch (event.getAction())
//                 {
//                     case MotionEvent.ACTION_DOWN: //按下
//                         handler.removeCallbacksAndMessages( null );
//                         break;
//                     case MotionEvent.ACTION_UP:  //手指离开
//                         handler.removeCallbacksAndMessages( null );
//
//                         handler.sendEmptyMessageDelayed( 0,4000 );
//                         break;
//                     case MotionEvent.ACTION_MOVE:  //手指在控件移动
//
//                         break;
//                     case MotionEvent.ACTION_CANCEL:  //手指在控件移动
//
////                         handler.removeCallbacksAndMessages( null );
////                         handler.sendEmptyMessageDelayed( 0,4000 );
//                         break;
//                 }
//                 return false;
//             }
//         } );
         imageView.setTag( position );//标记位置信息
         imageView.setOnClickListener( new View.OnClickListener() {
             @Override
             public void onClick(View v) {


                int position=(int) v.getTag()%imageViews.size();
                String text= (String) tv_title.getText();
                ShowToast.toastShow( MainActivity.this,"你点击了"+position );
             }
         } );
            return imageView;
        }

        /**
         * 释放资源
         * 最多3个资源
         * @param container ViewPager
         * @param position 释放页面的位置
         * @param object
         */
        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            Log.e( "destroyItem", "destroyItem= "+position+"object="+object );

            container.removeView( (View) object );

        }
    }
}
