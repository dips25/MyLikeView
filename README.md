implementation 'com.github.dips25:MyLikeView:likeview1.0'

<com.anim.likeview.LikeView
        android:layout_width="30dp"
        android:layout_height="30dp"
        app:startDrawable="@drawable/heart_off"
        app:endDrawable="@drawable/heart_on"
        app:dotsColor="@color/red"
        android:layout_centerInParent="true"
        android:id="@+id/like"/>


 LikeView c = (LikeView) findViewById(R.id.like);

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                c.startAnim();
            }
        });
