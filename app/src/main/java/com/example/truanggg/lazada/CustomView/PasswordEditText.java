package com.example.truanggg.lazada.CustomView;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.example.truanggg.lazada.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Truang on 12/8/2017.
 */

@SuppressLint("AppCompatCustomView")
public class PasswordEditText extends EditText {

    Drawable eye, eyeStrike;
    // visible mặc định là password
    Boolean visible =false;
    Boolean useStrike = false;
    Boolean useValiDate = false;
    Drawable drawable;
    String MATCHER_PATTERN = "((?=.*\\d)(?=.*[A-Z])(?=.*[a-z]).{6,20})";
    Pattern pattern;
    Matcher matcher;
    public PasswordEditText(Context context) {
        super(context);
        khoitao(null);
    }
    // khi dùng userStrike ở file xml thì nó gọi thằng này
    public PasswordEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        khoitao(attrs);
    }

    public PasswordEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        khoitao(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PasswordEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        khoitao(attrs);
    }
    // tạo phương thức khởi tạo phải truyền vào AttributeSet để truyền khoitao vào các hàm có  AttributeSet attrs
    private void khoitao(AttributeSet attrs){

        this.pattern = Pattern.compile(MATCHER_PATTERN);

        if(attrs != null){
            TypedArray array = getContext().getTheme().obtainStyledAttributes(attrs,R.styleable.PasswordEditText,0,0);
            this.useStrike  = array.getBoolean(R.styleable.PasswordEditText_useStrike,false);
            this.useValiDate = array.getBoolean(R.styleable.PasswordEditText_useValidate,false);
        }
        eye = ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_black_24dp).mutate(); // mutate vẽ drawable
        eyeStrike = ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_off_black_24dp).mutate();
        if (this.useValiDate){
            setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus){
                        String chuoi = getText().toString();
                        TextInputLayout textInputLayout = (TextInputLayout) v.getParent();
                        matcher = pattern.matcher(chuoi);

                        if(!matcher.matches()) {
                            Log.d("kiemtra mat khau", matcher.matches() + "-" +hasFocus);
                            textInputLayout.setErrorEnabled(true);
                            textInputLayout.setError("Mật khẩu phải bao gồm 6 kí tự và 1 chũ hoa");
                        }else {
                            textInputLayout.setErrorEnabled(true);
                            setError("");
                        }
                    }
                }
            });
        }
        caidat();
    }
    private void caidat(){
        setInputType(InputType.TYPE_CLASS_TEXT | (visible ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                                                        : InputType.TYPE_TEXT_VARIATION_PASSWORD));
                                                    // nếu visible là true thì hiển thị password ngược lại thì không hiển thị password
        // lấy drawable trong edittext
        Drawable[] drawables = getCompoundDrawables();
        // nếu useStrike bằng true và phủ định của visible là true thì không hiển thị password và ngược lại sẽ hiển thị password
        drawable = useStrike && !visible? eyeStrike : eye;
        // xét con mắt nằm bên phải thì nó sẽ ở ví trí thứ 2 và truyền vào drawable
        setCompoundDrawablesWithIntrinsicBounds(drawables[0],drawables[1],drawable,drawables[3]);
    }
    // muốn bắt sự kiện cho con mắt thì phải dùng onTouchEvent
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // xét tọa độ của con mắt khi mình di chuyển con trỏ vào
        //xét tọa độ con mắt chúng ta lấy chiều dài bên phải của edt - chiều rộng của con mắt thì sẽ ra kích thước của drawable con mắt
        if(event.getAction() == MotionEvent.ACTION_UP && event.getX() >=  (getRight() - drawable.getBounds().width()) ){
            visible = !visible;
            caidat();
            // phương thức kiểm tra lại màn hình
            invalidate();
        }
        return super.onTouchEvent(event);
    }
}
