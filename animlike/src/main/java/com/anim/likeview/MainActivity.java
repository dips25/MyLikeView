package com.anim.likeview;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    LikeView c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        c = (LikeView) findViewById(R.id.sample_circle);

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                c.startAnim();
            }
        });

    }
}