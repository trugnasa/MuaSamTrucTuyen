package com.project.luulinhson.muasamtructuyen.CustomView;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.project.luulinhson.muasamtructuyen.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Admin on 3/14/2017.
 */

public class ClearEditText extends EditText {

    Drawable crossx,nonecrossx,drawable;
    Boolean visible = false;
    final int ALPHA2 = (int) (255 * .60f);
    String MATHCH_PATTERN = "((?=.*\\d)(?=.*[A-Z])(?=.*[a-z]).{6,20})";
    Matcher matcher;
    Pattern pattern;
    Boolean useValidatetwo = false;

    public ClearEditText(Context context) {
        super(context);
        khoitao(null);

    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        khoitao(attrs);
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        khoitao(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        khoitao(attrs);
    }

    private void khoitao(AttributeSet attrs){
        pattern = Pattern.compile(MATHCH_PATTERN);
        if(attrs != null){
            // khởi tạo cho bên xml
            TypedArray array = getContext().getTheme().obtainStyledAttributes(attrs,R.styleable.ClearEditText,0,0);
            this.useValidatetwo = array.getBoolean(R.styleable.ClearEditText_useValidatetwo,false);
        }
        crossx = ContextCompat.getDrawable(getContext(), R.drawable.ic_clear_black_18dp);
        crossx.setAlpha(ALPHA2);
        nonecrossx = ContextCompat.getDrawable(getContext(),android.R.drawable.screen_background_light_transparent);
        if(this.useValidatetwo){
            setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(!hasFocus){
                        String chuoi = getText().toString();
                        TextInputLayout textInputLayout = (TextInputLayout) v.getParentForAccessibility();
                        matcher = pattern.matcher(chuoi);
                        if((chuoi.trim().equals("") || chuoi.equals(null))){
                            textInputLayout.setErrorEnabled(true);
                            textInputLayout.setError("Trường này không được để trống");

                        }else {
                            int count = chuoi.length();
                            for (int i = 0; i < count ; i++) {
                                String c = String.valueOf(chuoi.charAt(i));
                                if (c.equals("?") || c.equals("!") || c.equals("@") || c.equals("#") || c.equals("$") || c.equals("%")
                                        || c.equals("^") || c.equals("&") || c.equals("*") || c.equals("(") || c.equals(")")
                                        || c.equals(",") || c.equals(".") || c.equals("<") || c.equals(">") || c.equals(";")
                                        || c.equals(":") || c.equals("'") || c.equals("/") || c.equals("\"") || c.equals("{")
                                        || c.equals("}") || c.equals("[") || c.equals("]") || c.equals("\\") || c.equals("|")
                                        || c.equals("~") || c.equals("`")) {
                                    textInputLayout.setErrorEnabled(true);
                                    textInputLayout.setError("Họ tên không được có ký tự đặc biệt");
                                }else {
                                    textInputLayout.setErrorEnabled(false);
                                }
                            }
                        }
                    }
                }
            });
        }

        caidat();
    }

    private  void caidat(){
        setInputType(InputType.TYPE_CLASS_TEXT);
        Drawable[] drawables = getCompoundDrawables();
        drawable = visible? crossx:nonecrossx;
        setCompoundDrawablesWithIntrinsicBounds(drawables[0],drawables[1],drawable,drawables[3]);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if(lengthAfter == 0 && start == 0){
            visible = false;
            caidat();
        }else {
            visible = true;
            caidat();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getAction() == MotionEvent.ACTION_DOWN && event.getX() >= (getRight() - drawable.getBounds().width())){
            setText("");
            invalidate();
        }
        return super.onTouchEvent(event);
    }
}
