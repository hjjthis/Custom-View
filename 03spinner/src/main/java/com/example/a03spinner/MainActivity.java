package com.example.a03spinner;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText et_input;
    private ImageView iv_down_arrow;
    /**
     *
     */
    private PopupWindow popupWindow;
    private ListView listview;
    private ArrayList<String> msgs;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        et_input= findViewById( R.id.et_input );
        iv_down_arrow=findViewById( R.id.iv_down_arrow );
        iv_down_arrow.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(popupWindow==null)
                {
                    popupWindow=new PopupWindow( MainActivity.this );
                    popupWindow.setWidth( et_input.getWidth() );
                    popupWindow.setHeight( 1000 );
                    popupWindow.setContentView( listview );
                    popupWindow.setFocusable( true );//设置焦点
                    popupWindow.showAsDropDown( et_input,0,0 );
                }

                else
                {
                    popupWindow.showAsDropDown( et_input,0,0 );
                }

            }
        } );
        listview=new ListView( MainActivity.this );
        //准备数据
        msgs=new ArrayList<>(  );
        for(int i=0;i<50;i++)
        {
            msgs.add( i+"a" );
        }
        adapter=new MyAdapter();
        listview.setAdapter( adapter );
    }
    class MyAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return msgs.size();
        }

        @Override
        public Object getItem(int position) {
            return msgs.get( position );
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder=null;
            if(convertView==null)
            {
                convertView= View.inflate(MainActivity.this ,R.layout.item_main,null);
                holder=new ViewHolder();
                holder.tv=(TextView)convertView.findViewById( R.id.tv_mesg );
                holder.img_delete=(ImageView) convertView.findViewById( R.id.iv_delete );
                convertView.setTag( holder );
            }
            else
            {
               holder=(ViewHolder)convertView.getTag();
            }

            final String msg=msgs.get( position );
            holder.tv.setText( msg );
            holder.img_delete.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //从集合删除
                    msgs.remove( msg );
                    adapter.notifyDataSetChanged();
                }
            } );
            return convertView;
        }
        class ViewHolder
        {
            TextView tv;
            ImageView img_delete;
        }

    }
}
