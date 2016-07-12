package com.jjke.animatehw;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FrameLayout content;
    private Button bt1,bt2,bt3,bt4;
    private AnimationSet as;
    private AnimatorSet atorS;
    private ImageView iv,iv_back;
   // private int clickCount=0;

    private int centerX,centerY;
    private int depthZ = 300;
    private int duration = 600;
    private Rotate3dAnimation openAnimation,closeAnimation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        content =(FrameLayout)findViewById(R.id.content);
        bt1 = (Button) findViewById(R.id.bt1);
        bt2 = (Button) findViewById(R.id.bt2);
        bt3 = (Button) findViewById(R.id.bt3);
        bt4 = (Button) findViewById(R.id.bt4);
        iv = (ImageView) findViewById(R.id.iv);
        iv_back = (ImageView) findViewById(R.id.iv2);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        iv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt1:
                v.startAnimation(AnimationUtils.loadAnimation(MainActivity.this,R.anim.anim));
//                Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.anim_rigth);
//                v.startAnimation(animation);
//                animation.setAnimationListener(new Animation.AnimationListener() {
//                    @Override
//                    public void onAnimationStart(Animation animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationEnd(Animation animation) {
//                        Animation animation12 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.anim_down);
//                        bt1.startAnimation(animation12);
//                    }
//
//                    @Override
//                    public void onAnimationRepeat(Animation animation) {
//
//                    }
//                });
                break;
            case R.id.bt2:
                AnimationSet as = new AnimationSet(false);
                TranslateAnimation ta_x = new TranslateAnimation(0,100,0,0);
                ta_x.setDuration(500);
                TranslateAnimation ta_y = new TranslateAnimation(0,0,0,100);
                ta_y.setStartOffset(500);
                ta_y.setDuration(500);
                as.addAnimation(ta_x);
                as.addAnimation(ta_y);
                v.startAnimation(as);
//                as = new AnimationSet(true);
//                as.setDuration(1500);
//                TranslateAnimation ta1 = new TranslateAnimation(0,150,0,0);
//                ta1.setDuration(500);
////                as.addAnimation(ta1);
//                ta1.setAnimationListener(new Animation.AnimationListener() {
//                    @Override
//                    public void onAnimationStart(Animation animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationEnd(Animation animation) {
//                        TranslateAnimation ta2 = new TranslateAnimation(150,150,0,150);
//                        ta2.setDuration(500);
//                        bt2.startAnimation(ta2);
//                    }
//                    @Override
//                    public void onAnimationRepeat(Animation animation) {
//
//                    }
//                });
//                TranslateAnimation ta2 = new TranslateAnimation(0,0,0,150);
//                ta2.setStartOffset(1000);
//                ta2.setDuration(500);
//                as.addAnimation(ta2);
                //v.startAnimation(ta1);
                break;
            case R.id.bt3:
                atorS = (AnimatorSet) AnimatorInflater.loadAnimator(MainActivity.this,R.animator.animator_set);
                atorS.setTarget(v);
                atorS.start();
                break;
            case R.id.bt4:
                atorS = new AnimatorSet();
                atorS.setDuration(500);
                atorS.playSequentially(ObjectAnimator.ofFloat(v,"translationX",0,150),
                        ObjectAnimator.ofFloat(v,"translationY",0,150),ObjectAnimator.ofFloat(v,"translationY",150,0),
                        ObjectAnimator.ofFloat(v,"translationX",150,0));
                atorS.start();
                break;

            case R.id.iv:
                ObjectAnimator animator = ObjectAnimator.ofFloat(v,"rotationY",180);
                animator.setTarget(v);
                animator.setDuration(1000);
                animator.setRepeatCount(1);
                animator.setRepeatMode(ValueAnimator.REVERSE);
                animator.start();
//                System.out.print("hihi");
//               // flip();
//                centerX = content.getWidth()/2;
//                centerY = content.getHeight()/2;
//                if (openAnimation == null){
//                    initOpenAnim();
//                    initCloseAnim();
//                }
//                v.startAnimation(openAnimation);

                default: break;
        }
    }
//    private void flip(){
//        clickCount++;
//        AnimatorSet asOut = (AnimatorSet) AnimatorInflater.loadAnimator(this,R.animator.flipout);
//        final AnimatorSet asIn = (AnimatorSet) AnimatorInflater.loadAnimator(this,R.animator.flip_in);
//        asOut.setTarget(iv);
//        asIn.setTarget(iv);
//        asOut.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                if (clickCount%2==0){
//                    iv.setImageResource(R.drawable.img);
//                }else{
//                    iv.setImageResource(R.drawable.img2);
//                }
//                asIn.start();
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//
//            }
//        });
//        asOut.start();
//    }

    private void initOpenAnim(){
        openAnimation = new Rotate3dAnimation(0,90,centerX,centerY,depthZ,true);
        openAnimation.setDuration(duration);
        openAnimation.setFillAfter(true);
        openAnimation.setInterpolator(new AccelerateInterpolator());
        openAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                iv.setVisibility(View.GONE);
                iv_back.setVisibility(View.VISIBLE);
                Rotate3dAnimation rotate3dAnimation = new Rotate3dAnimation(270,360,centerX,centerY,depthZ,false);
                rotate3dAnimation.setDuration(duration);
                rotate3dAnimation.setFillAfter(true);
                rotate3dAnimation.setInterpolator(new DecelerateInterpolator());
                iv_back.startAnimation(rotate3dAnimation);
                iv_back.startAnimation(closeAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    private void initCloseAnim(){
        closeAnimation = new Rotate3dAnimation(360,270,centerX,centerY,depthZ,true);
        closeAnimation.setDuration(duration);
        closeAnimation.setFillAfter(true);
        closeAnimation.setInterpolator(new AccelerateInterpolator());
        closeAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                iv.setVisibility(View.VISIBLE);
                iv_back.setVisibility(View.GONE);

                Rotate3dAnimation rotate3dAnimation = new Rotate3dAnimation(90,0,centerX,centerY,depthZ,false);
                rotate3dAnimation.setDuration(duration);
                rotate3dAnimation.setFillAfter(true);
                rotate3dAnimation.setInterpolator(new DecelerateInterpolator());
                iv.startAnimation(rotate3dAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
