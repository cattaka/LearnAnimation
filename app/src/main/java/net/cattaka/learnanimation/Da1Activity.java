package net.cattaka.learnanimation;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by takao on 2015/10/28.
 */
public class Da1Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_da_1);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            ImageView logoImage = (ImageView) findViewById(R.id.image_logo);
            AnimationDrawable rollCatDrawable = (AnimationDrawable) logoImage.getDrawable();
            rollCatDrawable.start();
        }
    }
}
