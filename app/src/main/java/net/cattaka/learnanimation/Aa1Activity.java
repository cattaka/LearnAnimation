package net.cattaka.learnanimation;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.util.Pair;
import android.view.View;
import android.view.Window;

/**
 * Created by takao on 2015/10/28.
 */
public class Aa1Activity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_aa_1);

        findViewById(R.id.image_logo).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.image_logo) {
            if (true) {
                Intent intent = new Intent(this, At2Activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.aa_slide_in, R.anim.aa_slide_out);
            } else {
                Intent intent = new Intent(this, At2Activity.class);
                ActivityOptions options = ActivityOptions.makeCustomAnimation(this, R.anim.aa_slide_in, R.anim.aa_slide_out);
                startActivity(intent, options.toBundle());
            }
        }
    }
}
