package com.anim.mylikeview;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.anim.likeview.LikeView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       LikeView c = (LikeView) findViewById(R.id.like);

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                c.startAnim();
            }
        });


    }
}