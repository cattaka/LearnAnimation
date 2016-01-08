package net.cattaka.learnanimation;

import android.app.Activity;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by takao on 2015/10/28.
 */
public class AvActivity extends Activity implements View.OnClickListener {
    ImageView mLogoImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_av);

        mLogoImage = (ImageView) findViewById(R.id.image_logo);
        mLogoImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.image_logo) {
            AnimatedVectorDrawable drawable = (AnimatedVectorDrawable) getDrawable(R.drawable.avd);
            mLogoImage.setImageDrawable(drawable);
            drawable.start();
        }
    }
}
