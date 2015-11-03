package net.cattaka.learnanimation;

import android.app.Activity;
import android.os.Bundle;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by takao on 2015/10/28.
 */
public class TaActivity extends Activity implements View.OnClickListener {
    ViewGroup mContainerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ta);

        mContainerLayout = (ViewGroup) findViewById(R.id.layout_container);

        findViewById(R.id.button_item_1).setOnClickListener(this);
        findViewById(R.id.button_item_2).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_item_1) {
            View childView = LayoutInflater.from(this).inflate(R.layout.activity_ta_child_logo, null);
            Scene scene = new Scene(mContainerLayout, childView);
            Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.ta_logo);
            TransitionManager.go(scene, transition);
        } else if (view.getId() == R.id.button_item_2) {
            View childView = LayoutInflater.from(this).inflate(R.layout.activity_ta_child_rb, null);
            Scene scene = new Scene(mContainerLayout, childView);
            Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.ta_rb);
            TransitionManager.go(scene, transition);
        }
    }
}
