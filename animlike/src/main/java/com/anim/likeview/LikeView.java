package com.anim.likeview;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;



public class LikeView extends FrameLayout {

    private static final String TAG = LikeView.class.getName();

    Context context;
    View mainView;

    ImageView mainImage;
    SampleCircleView dotsView;

    int dotsColor;
    Drawable startDrawable,endDrawable;

    ObjectAnimator v;

    int widthParams,heightParams;

    RelativeLayout.LayoutParams dotParams;





    private boolean isChecked;

    public LikeView(@NonNull Context context) {
        super(context);

        this.context = context;
        init();
    }

    public LikeView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        this.context = context;
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.LikeView,
                0, 0);

        dotsColor = a.getColor(R.styleable.LikeView_dotsColor , getResources().getColor(R.color.red));
        startDrawable = a.getDrawable(R.styleable.LikeView_startDrawable);
        endDrawable = a.getDrawable(R.styleable.LikeView_endDrawable);

        String width = a.getString(R.styleable.LikeView_android_layout_width);
        String height = a.getString(R.styleable.LikeView_android_layout_height);

        Log.d(TAG, "LikeView: " + width+" " + height);

        if (width.equals("-1")) {

            widthParams = ViewGroup.LayoutParams.MATCH_PARENT;

        } else if (width.equals("-2")) {

            widthParams = 150;

        } else {

            widthParams = (int) Float.parseFloat(width.replace("dip",""));
        }


        if (height.equals("-1")) {

            heightParams = ViewGroup.LayoutParams.MATCH_PARENT;

        } else if (height.equals("-2")) {

            heightParams = 150;

        } else {

            heightParams = (int) Float.parseFloat(height.replace("dip",""));
        }

        Log.d(TAG, "LikeView: " + widthParams + " " + heightParams);

        //this.setLayoutParams(new FrameLayout.LayoutParams(widthParams,heightParams));


        init();
    }

    public LikeView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.context = context;
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.LikeView,
                0, 0);

        dotsColor = a.getColor(R.styleable.LikeView_dotsColor , getResources().getColor(R.color.red));
        startDrawable = a.getDrawable(R.styleable.LikeView_startDrawable);
        endDrawable = a.getDrawable(R.styleable.LikeView_endDrawable);

        String width = a.getString(R.styleable.LikeView_android_layout_width);
        String height = a.getString(R.styleable.LikeView_android_layout_height);

        Log.d(TAG, "LikeView: " + width+" " + height);

        if (width.equals("-1")) {

            widthParams = ViewGroup.LayoutParams.MATCH_PARENT;

        } else if (width.equals("-2")) {

            widthParams = 150;

        } else {

            widthParams = (int) Float.parseFloat(width.replace("dip",""));
        }


        if (height.equals("-1")) {

            heightParams = ViewGroup.LayoutParams.MATCH_PARENT;

        } else if (height.equals("-2")) {

            heightParams = 150;

        } else {

            heightParams = (int) Float.parseFloat(height.replace("dip",""));
        }

        Log.d(TAG, "LikeView: " + widthParams + " " + heightParams);

        //this.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));


        init();
    }



    private void init() {

        dotParams = new RelativeLayout.LayoutParams(widthParams,heightParams);
        dotParams.addRule(RelativeLayout.CENTER_IN_PARENT);



        mainView = LayoutInflater.from(context).inflate(R.layout.layout_mainview, this , true);
        //mainView.setLayoutParams(new RelativeLayout.LayoutParams(widthParams, heightParams));



        mainImage = (ImageView) mainView.findViewById(R.id.imgView);
        mainImage.setImageDrawable(null);
        mainImage.setImageDrawable(startDrawable);
        dotsView = (SampleCircleView) mainView.findViewById(R.id.dotsView);
        dotsView.setLayoutParams(dotParams);

        v = ObjectAnimator.ofFloat(dotsView,SampleCircleView.DOTS_PROGRESS,0.1f,1.0f);
        v.setDuration(500);


        v.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(@NonNull Animator animation) {

                LikeView.this.setClickable(false);
                mainImage.setScaleX(0);
                mainImage.setScaleY(0);

            }

            @Override
            public void onAnimationEnd(@NonNull Animator animation) {

                mainImage.setScaleX(1);
                mainImage.setScaleY(1);
                mainImage.setImageDrawable(endDrawable);
                LikeView.this.setClickable(true);
                dotsView.setReset(true);
                dotsView.reset();

            }

            @Override
            public void onAnimationCancel(@NonNull Animator animation) {

            }

            @Override
            public void onAnimationRepeat(@NonNull Animator animation) {

            }
        });

        v.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator animation) {

                Log.d(LikeView.class.getName(), "onAnimationUpdate: "+(Float) animation.getAnimatedValue());

            }
        });



    }

    public void startAnim() {

        if (!isChecked) {

            isChecked = true;
            dotsView.setReset(false);
            this.setClickable(false);
            v.start();

        } else {

            mainImage.setImageDrawable(startDrawable);
            isChecked = false;
            dotsView.setReset(true);
        }




    }
}
