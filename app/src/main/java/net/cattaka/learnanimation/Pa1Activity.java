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
import android.widget.AdapterView;

import java.util.ArrayList;

/**
 * Created by takao on 2015/10/28.
 */
public class Pa1Activity extends Activity implements View.OnClickListener {
    View mTargetButton;
    AnimationDrawable mRollCatDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pa);

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
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_move_code) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(mTargetButton, "translationX", 0f, mTargetButton.getWidth());
            animator.setInterpolator(new BounceInterpolator());
            animator.setDuration(3000);
            animator.start();
        } else if (view.getId() == R.id.button_rotate_code) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(mTargetButton, "rotation", 0f, 360f);
            animator.setInterpolator(new BounceInterpolator());
            animator.setDuration(3000);
            animator.start();
        } else if (view.getId() == R.id.button_scale_code) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(mTargetButton, "scaleX", 1f, 0f, 1f);
            animator.setInterpolator(new BounceInterpolator());
            animator.setDuration(3000);
            animator.start();
        } else if (view.getId() == R.id.button_alpha_code) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(mTargetButton, "alpha", 1f, 0f, 1f);
            animator.setInterpolator(new BounceInterpolator());
            animator.setDuration(3000);
            animator.start();
        } else if (view.getId() == R.id.button_all_code) {
            ObjectAnimator translationX = ObjectAnimator.ofFloat(mTargetButton, "translationX", 0f, mTargetButton.getWidth());
            translationX.setInterpolator(new BounceInterpolator());
            translationX.setDuration(3000);

            ObjectAnimator rotation = ObjectAnimator.ofFloat(mTargetButton, "rotation", 0f, 360f);
            rotation.setInterpolator(new BounceInterpolator());
            rotation.setDuration(3000);

            ObjectAnimator scaleX = ObjectAnimator.ofFloat(mTargetButton, "scaleX", 1f, 0f, 1f);
            scaleX.setInterpolator(new BounceInterpolator());
            scaleX.setDuration(3000);

            ObjectAnimator alpha = ObjectAnimator.ofFloat(mTargetButton, "alpha", 1f, 0f, 1f);
            alpha.setInterpolator(new BounceInterpolator());
            alpha.setDuration(3000);

            ArrayList<Animator> animators = new ArrayList<>();
            animators.add(translationX);
            animators.add(rotation);
            animators.add(scaleX);
            animators.add(alpha);

            AnimatorSet as = new AnimatorSet();
            as.playTogether(animators);
            as.start();
        } else if (view.getId() == R.id.button_all_share_interpolator_code) {
            ArrayList<Animator> animators = new ArrayList<>();

            ObjectAnimator translationX = ObjectAnimator.ofFloat(mTargetButton, "translationX", 0f, mTargetButton.getWidth());
            animators.add(translationX);

            ObjectAnimator rotation = ObjectAnimator.ofFloat(mTargetButton, "rotation", 0f, 360f);
            animators.add(rotation);

            ObjectAnimator scaleX = ObjectAnimator.ofFloat(mTargetButton, "scaleX", 1f, 0f, 1f);
            animators.add(scaleX);

            ObjectAnimator alpha = ObjectAnimator.ofFloat(mTargetButton, "alpha", 1f, 0f, 1f);
            animators.add(alpha);

            AnimatorSet as = new AnimatorSet();
            as.setInterpolator(new BounceInterpolator());
            as.setDuration(3000);
            as.playTogether(animators);
            as.start();
        } else if (view.getId() == R.id.button_move_xml) {
            Animator anim = AnimatorInflater.loadAnimator(this, R.animator.pa_move);
            anim.setTarget(mTargetButton);
            anim.start();
        } else if (view.getId() == R.id.button_rotate_xml) {
            Animator anim = AnimatorInflater.loadAnimator(this, R.animator.pa_rotate);
            anim.setTarget(mTargetButton);
            anim.start();
        } else if (view.getId() == R.id.button_scale_xml) {
            Animator anim = AnimatorInflater.loadAnimator(this, R.animator.pa_scale);
            anim.setTarget(mTargetButton);
            anim.start();
        } else if (view.getId() == R.id.button_alpha_xml) {
            Animator anim = AnimatorInflater.loadAnimator(this, R.animator.pa_alpha);
            anim.setTarget(mTargetButton);
            anim.start();
        } else if (view.getId() == R.id.button_all_xml) {
            Animator anim = AnimatorInflater.loadAnimator(this, R.animator.pa_all);
            anim.setTarget(mTargetButton);
            anim.start();
        }
    }
}
