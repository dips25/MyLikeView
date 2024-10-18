package com.anim.likeview;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Property;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;



public class SampleCircleView extends View {

    private static final String TAG = SampleCircleView.class.getName();

    private static final int DOTS_COUNT = 5;

    Paint mainPaint,maskPaint;
    int size = 0;

    private static int MAX_SIZE = 18;

    float offsetDeviation = 0f;

    int centerX,centerY = 0;

    int dotsColor;
    Drawable startDrawable,endDrawable;

    View mainView;

    Context context;

    private boolean isReset = true;


    public SampleCircleView(Context context) {
        super(context);

        this.context = context;

        mainPaint = new Paint();
        mainPaint.setAntiAlias(true);
        mainPaint.setColor(Color.RED);

        maskPaint = new Paint();
        maskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

        init();


    }

    public SampleCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        this.context = context;

        mainPaint = new Paint();
        mainPaint.setAntiAlias(true);
        mainPaint.setColor(Color.RED);

        maskPaint = new Paint();
        maskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

        init();
    }

    public SampleCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.context = context;

        mainPaint = new Paint();
        mainPaint.setAntiAlias(true);
        mainPaint.setColor(Color.RED);

        maskPaint = new Paint();
        maskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

        init();
    }

    private void init() {

        //this.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        //mainView = LayoutInflater.from(context).inflate(R.layout.layout_samplecircleview , this , false)


//
//        ObjectAnimator v = ObjectAnimator.ofFloat(this,SampleCircleView.DOTS_PROGRESS,0.1f,1.0f);
//        v.setDuration(3000);
//
//
//        v.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(@NonNull Animator animation) {
//
//                SampleCircleView.this.setScaleX(0);
//                SampleCircleView.this.setScaleY(0);
//
//            }
//
//            @Override
//            public void onAnimationEnd(@NonNull Animator animation) {
//
//                SampleCircleView.this.setScaleX(1);
//                SampleCircleView.this.setScaleY(1);
//
//            }
//
//            @Override
//            public void onAnimationCancel(@NonNull Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(@NonNull Animator animation) {
//
//            }
//        });
//
//        v.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(@NonNull ValueAnimator animation) {
//
//                Log.d(SampleTestActivity.class.getName(), "onAnimationUpdate: "+(Float) animation.getAnimatedValue());
//
//            }
//        });
//
//        v.start();


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        Log.d(TAG, "onSizeChanged: " + w + h);

        centerX = w/2;
        centerY = h/2;

        //MAX_SIZE = (int) (w*0.27);
        //size = MAX_SIZE;
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        if (!isReset) {

            for (int i=0 ; i<7 ; i++) {


                int cX = (int) (centerX + offsetDeviation  * (Math.cos(i*51 * Math.PI / 180)));
                int cY = (int) (centerY + offsetDeviation   * (Math.sin(i*51 * Math.PI / 180)));

                Log.d(TAG, "onDraw: " + cX + " " + cY);

                canvas.drawCircle(cX, cY, size, mainPaint);

            }


        }


        //canvas.drawCircle(10 , 20 , size-10 , maskPaint);
    }

    public void setCurrentProgress(float value) {

//        offsetDeviation=value;
        updateOuterDotsPosition(value);

       invalidate();


    }

    public static final Property<SampleCircleView, Float> DOTS_PROGRESS = new Property<SampleCircleView, Float>(Float.class, "circleProgress") {
        @Override
        public Float get(SampleCircleView object) {
//            return object.getCurrentProgress();

            return object.getCurrentProgress();
        }

        @Override
        public void set(SampleCircleView object, Float value) {
//            object.setCurrentProgress(value);

            object.setCurrentProgress(value);
        }
    };

    private Float getCurrentProgress() {

        return offsetDeviation;
    }

    private void updateOuterDotsPosition(float value) {

        offsetDeviation+=value;
//        if (value < 0.3f) {
//            this.offsetDeviation = (float) mapValueFromRangeToRange(value, 0.0f, 0.3f, 0, 10 * 0.8f);
//        } else {
//            this.offsetDeviation = (float) mapValueFromRangeToRange(value, 0.3f, 1f, 0.8f * 10, 10);
//        }
        if (value == 0) {
            this.size = 0;
        } else if (value > 0.1 && value<=0.2) {

            this.size = (int) (this.MAX_SIZE*0.15);

        } else if (value > 0.2 && value<=0.3) {

            this.size = (int) (this.MAX_SIZE*0.25);

            //this.size = (int) mapValueFromRangeToRange(offsetDeviation, 0.7f, 1f, (int)size, 0);
        }else if (value > 0.3 && value<=0.4) {

            this.size = (int) (this.MAX_SIZE*0.35);

            //this.size = (int) mapValueFromRangeToRange(offsetDeviation, 0.7f, 1f, (int)size, 0);
        }else if (value > 0.4 && value<=0.5) {

            this.size = (int) (this.MAX_SIZE*0.45);

            //this.size = (int) mapValueFromRangeToRange(offsetDeviation, 0.7f, 1f, (int)size, 0);
        }else if (value > 0.5 && value<=0.6) {

            this.size = (int) (this.MAX_SIZE*0.55);

            //this.size = (int) mapValueFromRangeToRange(offsetDeviation, 0.7f, 1f, (int)size, 0);
        }else if (value > 0.6 && value<=0.7) {

            this.size = (int) (this.MAX_SIZE*0.40);

            //this.size = (int) mapValueFromRangeToRange(offsetDeviation, 0.7f, 1f, (int)size, 0);
        }else if (value > 0.7 && value<=0.8) {

            this.size = (int) (this.MAX_SIZE*0.30);

            //this.size = (int) mapValueFromRangeToRange(offsetDeviation, 0.7f, 1f, (int)size, 0);
        }else if (value > 0.8 && value<=0.9) {

            this.size = (int) (this.MAX_SIZE*0.20);

            //this.size = (int) mapValueFromRangeToRange(offsetDeviation, 0.7f, 1f, (int)size, 0);
        }else if (value > 0.9 && value<1.0) {

            this.size = (int) (this.MAX_SIZE*0.10);

            //this.size = (int) mapValueFromRangeToRange(offsetDeviation, 0.7f, 1f, (int)size, 0);
        }else  {

            this.size = 0;

            //this.size = (int) mapValueFromRangeToRange(offsetDeviation, 0.7f, 1f, (int)size, 0);
        }
    }

    public static double mapValueFromRangeToRange(double value, double fromLow, double fromHigh, double toLow, double toHigh) {
        return toLow + ((value - fromLow) / (fromHigh - fromLow) * (toHigh - toLow));
    }


    public void reset() {

        //size = MAX_SIZE;
        offsetDeviation = 0f;
    }

    public void setReset(boolean reset) {
        isReset = reset;
    }
}
