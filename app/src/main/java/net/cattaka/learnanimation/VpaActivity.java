package net.cattaka.learnanimation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;

import java.util.ArrayList;

/**
 * Created by takao on 2015/10/28.
 */
public class VpaActivity extends Activity implements View.OnClickListener {
    View mTargetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vpa);

        mTargetButton = findViewById(R.id.button_target);

        findViewById(R.id.button_move_code).setOnClickListener(this);
        findViewById(R.id.button_rotate_code).setOnClickListener(this);
        findViewById(R.id.button_scale_code).setOnClickListener(this);
        findViewById(R.id.button_alpha_code).setOnClickListener(this);
        findViewById(R.id.button_move_back).setOnClickListener(this);
        findViewById(R.id.button_rotate_back).setOnClickListener(this);
        findViewById(R.id.button_scale_back).setOnClickListener(this);
        findViewById(R.id.button_alpha_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_move_code) {
            mTargetButton.animate()
                    .translationX(mTargetButton.getWidth())
                    .setDuration(3000)
                    .start();
        } else if (view.getId() == R.id.button_rotate_code) {
            mTargetButton.animate()
                    .rotation(360f)
                    .setDuration(3000)
                    .start();
        } else if (view.getId() == R.id.button_scale_code) {
            mTargetButton.animate()
                    .scaleX(0.5f)
                    .scaleY(0.5f)
                    .setDuration(3000)
                    .start();
        } else if (view.getId() == R.id.button_alpha_code) {
            mTargetButton.animate()
                    .alpha(0f)
                    .setDuration(3000)
                    .start();
        } else if (view.getId() == R.id.button_move_back) {
            mTargetButton.animate()
                    .translationX(0)
                    .setDuration(3000)
                    .start();
        } else if (view.getId() == R.id.button_rotate_back) {
            mTargetButton.animate()
                    .rotation(0f)
                    .setDuration(3000)
                    .start();
        } else if (view.getId() == R.id.button_scale_back) {
            mTargetButton.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(3000)
                    .start();
        } else if (view.getId() == R.id.button_alpha_back) {
            mTargetButton.animate()
                    .alpha(1f)
                    .setDuration(3000)
                    .start();

        }
    }
}
