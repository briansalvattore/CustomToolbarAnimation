package com.houston.titles.commons;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by bcast_000 on 23/06/2015.
 */
public class Methods {

    private static String TAG = "Methods";

    private static Context context;

    public Methods(){
        super();
    }

    public static Methods init(Context context){
        Methods.context = context;
        return new Methods();
    }
    public static int toPixels(int dpi){
        float d = context.getResources().getDisplayMetrics().density;
        int margin = (int)(dpi * d); // margin in pixels

        return margin;
    }

    public static void showSoftKeyboard(View view) {
        if (view.requestFocus()) {
            InputMethodManager imm = (InputMethodManager) context
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    public static void hideSoftKeyboard(View view) {
        view.clearFocus();
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static Drawable setTint(int mipmap, int color){

        Drawable drawable = context.getResources().getDrawable(mipmap);

        if(Build.VERSION.SDK_INT >= 21){
            drawable.setTint(context.getResources().getColor(color));
        }else{
            drawable.setColorFilter(context.getResources().getColor(color), PorterDuff.Mode.SRC_ATOP);
        }

        return drawable;
    }
}
