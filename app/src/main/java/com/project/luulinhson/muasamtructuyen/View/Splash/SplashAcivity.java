package com.project.luulinhson.muasamtructuyen.View.Splash;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.project.luulinhson.muasamtructuyen.R;
import com.project.luulinhson.muasamtructuyen.View.Main.MainActivity;

/**
 * Created by Admin on 2/26/2017.
 */

public class SplashAcivity extends AppCompatActivity {

    ImageView imLazada;
    Animation anim_fale_in;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imLazada = (ImageView) findViewById(R.id.imLazada);
        anim_fale_in = AnimationUtils.loadAnimation(this,R.anim.anim_fale_in);
        imLazada.setAnimation(anim_fale_in);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent iMain = new Intent(SplashAcivity.this, MainActivity.class);
                startActivity(iMain);
                finish();
            }
        },3000);
    }
}
