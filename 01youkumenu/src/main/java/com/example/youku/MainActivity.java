package com.example.youku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView icon_home;
    private ImageView icon_menu;
    private RelativeLayout level1;
    private RelativeLayout level2;
    private RelativeLayout level3;
    /**
     * 是否显示我们的level3
     * ture显示
     * false隐藏
     */
    private boolean isShowLevel3=true;
    /**
     * 是否显示我们的level2
     * ture显示
     * false隐藏
     */
    private boolean isShowLevel2=true;
    /**
     * 是否显示我们的level1
     * ture显示
     * false隐藏
     */
    private boolean isShowLevel1=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        icon_home=(ImageView) findViewById( R.id. icon_home);
        icon_menu=(ImageView) findViewById( R.id.icon_menu );
        level1=(RelativeLayout) findViewById( R.id.level1 );
        level2=(RelativeLayout) findViewById( R.id.level2 );
        level3=(RelativeLayout) findViewById( R.id.level3 );
        icon_menu.setOnClickListener( this );
        icon_home.setOnClickListener( this );
        level1.setOnClickListener( this );
        level2.setOnClickListener( this );
        level3.setOnClickListener( this );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.icon_home:
                if(isShowLevel3)
                {
                    isShowLevel3=false;
                    Tools.hideView( level3 );
                }
                if(isShowLevel2)
                {
                    isShowLevel2=false;
                    Tools.hideView( level2,200 );
                }
                else {
                    isShowLevel2=true;
                    Tools.showView( level2 );

                }
                break;
            case R.id.icon_menu:
                if(isShowLevel3==true)
                {
                    //隐藏
                    Tools.hideView(level3);
                    isShowLevel3=false;
                }
                else
                {
                    //显示
                    Tools.showView(level3);
                    isShowLevel3=true;

                }
                break;
            case R.id.level1:
                Toast.makeText(MainActivity.this,"你点击了level1",
                        Toast.LENGTH_LONG).show();
                break;
            case R.id.level2:
                Toast.makeText(MainActivity.this,"你点击了level2",
                        Toast.LENGTH_LONG).show();
                break;
            case R.id.level3:
                Toast.makeText(MainActivity.this,"你点击了level3",
                        Toast.LENGTH_LONG).show();
                break;
        }
    }
}
