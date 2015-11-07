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

import net.cattaka.learnanimation.view.CustomPropertyView;

import java.util.ArrayList;

/**
 * Created by takao on 2015/10/28.
 */
public class Pa2Activity extends Activity implements View.OnClickListener {
    CustomPropertyView mTargetView;
    AnimationDrawable mRollCatDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pa_2);

        mTargetView = (CustomPropertyView)findViewById(R.id.text_target);

        findViewById(R.id.button_custom_property_code).setOnClickListener(this);
        findViewById(R.id.button_custom_property_xml).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_custom_property_code) {
            ObjectAnimator animator = ObjectAnimator.ofInt(mTargetView, "customProperty", 0, 1000);
            animator.setInterpolator(new BounceInterpolator());
            animator.setDuration(1000);
            animator.start();
        } else if (view.getId() == R.id.button_custom_property_xml) {
            Animator anim = AnimatorInflater.loadAnimator(this, R.animator.pa_custom_property);
            anim.setTarget(mTargetView);
            anim.start();
        }
    }
}
