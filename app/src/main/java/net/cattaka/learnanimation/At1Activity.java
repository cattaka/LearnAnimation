package net.cattaka.learnanimation;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;

/**
 * Created by takao on 2015/10/28.
 */
public class At1Activity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_at_1);

        findViewById(R.id.image_logo).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.image_logo) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                    this,
                    new Pair<>(view.findViewById(R.id.image_logo), getString(R.string.transition_name_image_logo))
            );

            Intent intent = new Intent(this, At2Activity.class);
            startActivity(intent, options.toBundle());
        }
    }
}
