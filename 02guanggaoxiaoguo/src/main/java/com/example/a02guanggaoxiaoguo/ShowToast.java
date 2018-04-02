package com.example.a02guanggaoxiaoguo;

import android.content.Context;
import android.widget.Toast;

public class ShowToast {
    static Toast toast=null;
    public static void toastShow(Context context,String string)
    {
        if(toast==null)
        {
            toast=Toast.makeText( context,string,Toast.LENGTH_LONG );

        }
        {
            toast.setText( string );
        }
        toast.show();
    }

}
