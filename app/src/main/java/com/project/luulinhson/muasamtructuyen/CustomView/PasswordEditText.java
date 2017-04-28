package com.project.luulinhson.muasamtructuyen.CustomView;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.project.luulinhson.muasamtructuyen.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Admin on 3/13/2017.
 */

public class PasswordEditText extends EditText {

    Drawable eye,eyeStrike;
    Boolean useStrile = false;
    Boolean useValidate = false;
    Boolean visible = false;
    Drawable drawable;
    final int ALPHA = (int) (255 * .60f); //độ đục cho drawable
//    String MATCHER_PATTERN = "((?=.*\\d)(?=.*[A-Z])(?=.*[a-z]).{6,20})";
    String MATCHER_PATTERN = "((?=.*[A-Z]).{6,20})";
    Pattern pattern;
    Matcher matcher;

    public PasswordEditText(Context context) {
        super(context);
        khoitao(null);
    }

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

    private void khoitao(AttributeSet attrs){
        pattern = Pattern.compile(MATCHER_PATTERN);
        if(attrs != null){
            // khởi tạo cho bên xml
            TypedArray array = getContext().getTheme().obtainStyledAttributes(attrs,R.styleable.PasswordEditText,0,0);
            this.useStrile = array.getBoolean(R.styleable.PasswordEditText_useStrike,false);
            this.useValidate = array.getBoolean(R.styleable.PasswordEditText_useValidate,false);
        }
        // Gán drawable với các hình ảnh có sẵn
        eye = ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_black_18dp).mutate();
        eyeStrike = ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_off_black_18dp).mutate();

        if(this.useValidate){
            setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(!hasFocus){
                        String chuoi = getText().toString();
                        TextInputLayout textInputLayout = (TextInputLayout) v.getParentForAccessibility();
                        matcher = pattern.matcher(chuoi);
                        if(!matcher.matches()){
                            textInputLayout.setErrorEnabled(true);
                            textInputLayout.setError("Mật khẩu không hợp lệ");
                        }else {
                            textInputLayout.setErrorEnabled(false);
                        }
                    }
                }
            });
        }
        caidat();
    }

    private void caidat(){
        // xét inputType là kiểu text.Nếu visible là true thì hiện kiều hiển thị pass còn nếu không thì hiện kiểu chữ
        setInputType(InputType.TYPE_CLASS_TEXT | (visible? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : InputType.TYPE_TEXT_VARIATION_PASSWORD));
        // Tạo ra một mảng drawable cho sự kiện setCompoundDrawablesWithIntrinsicBounds
        Drawable[] drawables = getCompoundDrawables();
        // drawable được hiện lên khi thuộc tính useStrike là true(xét bên file xml) && nếu visible = false thì hiện eyeStrike ngược lại thì hiện eye
        drawable = useStrile && !visible? eyeStrike : eye;
        //Xét độ đục cho drawable
        drawable.setAlpha(ALPHA);
        // Xét drawable cho từng vị trí top,left,right,bottom
        setCompoundDrawablesWithIntrinsicBounds(drawables[0],drawables[1],drawable,drawables[3]);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Xét sự kiện người dùng chạm vào màn hình && tọa độ x >= (tọa độ bên phải - chiều rộng của drawable) thì ăn sự kiện click
        if(event.getAction() == MotionEvent.ACTION_DOWN && event.getX() >= (getRight() - drawable.getBounds().width())){
            visible = !visible;
            caidat();
            invalidate();
        }
        return super.onTouchEvent(event); // phải giữ return ra super.onTouchEvent(event) để hiều là sự kiện vẫn đang tiếp tục
    }
}
