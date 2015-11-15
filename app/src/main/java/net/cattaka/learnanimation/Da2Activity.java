package net.cattaka.learnanimation;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by takao on 2015/10/28.
 */
public class Da2Activity extends Activity implements View.OnClickListener {
    ImageView mLogoImage;
    AnimationDrawable mRollCatDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_da_2);

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
