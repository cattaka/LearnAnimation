package net.cattaka.learnanimation;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by takao on 2015/10/28.
 */
public class DaActivity extends Activity implements View.OnClickListener {
    ImageView mLogoImage;
    AnimationDrawable mRollCatDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_da);

        mLogoImage = (ImageView) findViewById(R.id.image_logo);
        mLogoImage.setOnClickListener(this);

        mRollCatDrawable = (AnimationDrawable) mLogoImage.getDrawable();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.image_logo) {
            if (mRollCatDrawable.isRunning()) {
                mRollCatDrawable.stop();
            } else {
                mRollCatDrawable.start();
            }
        }
    }
}
