package com.example.truanggg.lazada.CustomView;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.example.truanggg.lazada.R;

/**
 * Created by Truang on 12/9/2017.
 */

@SuppressLint("AppCompatCustomView")
public class ClearEditText extends EditText {
    Drawable crossX, noneCrossX,drawable ;
    Boolean visible = false;
    public ClearEditText(Context context) {
        super(context);
        khoitao();
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        khoitao();
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        khoitao();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        khoitao();
    }
    private void khoitao(){
        crossX = ContextCompat.getDrawable(getContext(), R.drawable.ic_clear_black_24dp).mutate();
        // khởi tao clear mờ
        noneCrossX = ContextCompat.getDrawable(getContext(),android.R.drawable.screen_background_light_transparent).mutate();
        //screen_background_light_transparent lấy background trong suốt
        cauhinh();
    }
    private void cauhinh(){
        setInputType(InputType.TYPE_CLASS_TEXT);
        Drawable[] drawables = getCompoundDrawables();
        drawable = visible? crossX : noneCrossX;
        setCompoundDrawablesWithIntrinsicBounds(drawables[0],drawables[1],drawable,drawables[3]);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (MotionEvent.ACTION_DOWN == event.getAction() && event.getX() >= getRight() - drawable.getBounds().width())
            setText("");
        return super.onTouchEvent(event);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if (lengthAfter == 0 && start ==0) {
             visible = false;
             cauhinh();
        }else {
            visible = true;
            cauhinh();
        }

    }
}
