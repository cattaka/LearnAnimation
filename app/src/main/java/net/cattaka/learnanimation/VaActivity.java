package net.cattaka.learnanimation;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

/**
 * Created by takao on 2015/10/28.
 */
public class VaActivity extends Activity implements View.OnClickListener {
    View mTargetButton;
    AnimationDrawable mRollCatDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_va);

        mTargetButton = findViewById(R.id.button_target);

        findViewById(R.id.button_move_code).setOnClickListener(this);
        findViewById(R.id.button_rotate_code).setOnClickListener(this);
        findViewById(R.id.button_scale_code).setOnClickListener(this);
        findViewById(R.id.button_alpha_code).setOnClickListener(this);
        findViewById(R.id.button_all_code).setOnClickListener(this);
        findViewById(R.id.button_all_share_interpolator_code).setOnClickListener(this);
        findViewById(R.id.button_move_xml).setOnClickListener(this);
        findViewById(R.id.button_rotate_xml).setOnClickListener(this);
        findViewById(R.id.button_scale_xml).setOnClickListener(this);
        findViewById(R.id.button_alpha_xml).setOnClickListener(this);
        findViewById(R.id.button_all_xml).setOnClickListener(this);
        findViewById(R.id.button_all_share_interpolator_xml).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_move_code) {
            TranslateAnimation anim = new TranslateAnimation(0, mTargetButton.getWidth(), 0, mTargetButton.getHeight());
            anim.setInterpolator(new BounceInterpolator());
            anim.setDuration(3000);
            mTargetButton.startAnimation(anim);
        } else if (view.getId() == R.id.button_rotate_code) {
            RotateAnimation anim = new RotateAnimation(0, 360, mTargetButton.getWidth() / 2, mTargetButton.getHeight() / 2);
            anim.setInterpolator(new BounceInterpolator());
            anim.setDuration(3000);
            mTargetButton.startAnimation(anim);
        } else if (view.getId() == R.id.button_scale_code) {
            ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, mTargetButton.getWidth() / 2, mTargetButton.getHeight() / 2);
            anim.setInterpolator(new BounceInterpolator());
            anim.setDuration(3000);
            mTargetButton.startAnimation(anim);
        } else if (view.getId() == R.id.button_alpha_code) {
            AlphaAnimation anim = new AlphaAnimation(1.0f, 0.0f);
            anim.setInterpolator(new BounceInterpolator());
            anim.setDuration(3000);
            mTargetButton.startAnimation(anim);
        } else if (view.getId() == R.id.button_all_code) {
            TranslateAnimation translate = new TranslateAnimation(0, mTargetButton.getWidth(), 0, mTargetButton.getHeight());
            translate.setInterpolator(new BounceInterpolator());
            translate.setDuration(3000);

            RotateAnimation rotate = new RotateAnimation(0, 360, mTargetButton.getWidth() / 2, mTargetButton.getHeight() / 2);
            rotate.setInterpolator(new BounceInterpolator());
            rotate.setDuration(3000);

            ScaleAnimation scale = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, mTargetButton.getWidth() / 2, mTargetButton.getHeight() / 2);
            scale.setInterpolator(new BounceInterpolator());
            scale.setDuration(3000);

            AlphaAnimation alpha = new AlphaAnimation(1.0f, 0.0f);
            alpha.setInterpolator(new BounceInterpolator());
            alpha.setDuration(3000);

            boolean shareInterpolator = false;
            AnimationSet as = new AnimationSet(shareInterpolator);
            as.addAnimation(translate);
            as.addAnimation(rotate);
            as.addAnimation(scale);
            as.addAnimation(alpha);
            mTargetButton.startAnimation(as);
        } else if (view.getId() == R.id.button_all_share_interpolator_code) {
            TranslateAnimation translate = new TranslateAnimation(0, mTargetButton.getWidth(), 0, mTargetButton.getHeight());
            RotateAnimation rotate = new RotateAnimation(0, 360, mTargetButton.getWidth() / 2, mTargetButton.getHeight() / 2);
            ScaleAnimation scale = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, mTargetButton.getWidth() / 2, mTargetButton.getHeight() / 2);
            AlphaAnimation alpha = new AlphaAnimation(1.0f, 0.0f);

            boolean shareInterpolator = true;
            AnimationSet as = new AnimationSet(shareInterpolator);
            as.addAnimation(translate);
            as.addAnimation(rotate);
            as.addAnimation(scale);
            as.addAnimation(alpha);
            as.setInterpolator(new BounceInterpolator());
            as.setDuration(3000);
            mTargetButton.startAnimation(as);
        } else if (view.getId() == R.id.button_move_xml) {
            Animation anim = AnimationUtils.loadAnimation(this, R.anim.va_move);
            mTargetButton.startAnimation(anim);
        } else if (view.getId() == R.id.button_rotate_xml) {
            Animation anim = AnimationUtils.loadAnimation(this, R.anim.va_rotate);
            mTargetButton.startAnimation(anim);
        } else if (view.getId() == R.id.button_scale_xml) {
            Animation anim = AnimationUtils.loadAnimation(this, R.anim.va_scale);
            mTargetButton.startAnimation(anim);
        } else if (view.getId() == R.id.button_alpha_xml) {
            Animation anim = AnimationUtils.loadAnimation(this, R.anim.va_alpha);
            mTargetButton.startAnimation(anim);
        } else if (view.getId() == R.id.button_all_xml) {
            Animation anim = AnimationUtils.loadAnimation(this, R.anim.va_all);
            mTargetButton.startAnimation(anim);
        } else if (view.getId() == R.id.button_all_share_interpolator_xml) {
            Animation anim = AnimationUtils.loadAnimation(this, R.anim.va_all_share_interpolator);
            mTargetButton.startAnimation(anim);
        }
    }
}
