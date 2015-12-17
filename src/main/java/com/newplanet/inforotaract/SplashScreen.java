package com.newplanet.inforotaract;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.newplanet.inforotaract.Utils.App;

/**
 * Created on 12/17/2015
 * By : $(USER)<suchan211@gmail.com>
 */
public class SplashScreen extends AppCompatActivity
{
    private static int pauseDuration = 2400;
    private ImageView imgIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        imgIcon = (ImageView) findViewById(R.id.imgIcon);
        //getSupportActionBar().hide();

        alphaAnimation();

        App.Intialize(this);
//        new Handler().postDelayed(
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        onClick(null);
//                    }
//                }
//                , pauseDuration);

    }

    private void alphaAnimation()
    {
        Animator anim = AnimatorInflater.loadAnimator(this, R.animator.alpha);
        anim.setTarget(imgIcon);
        anim.start();
    }
}
