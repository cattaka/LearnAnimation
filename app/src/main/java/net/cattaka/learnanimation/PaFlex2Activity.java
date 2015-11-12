package net.cattaka.learnanimation;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import net.cattaka.learnanimation.utils.RelativeLayoutAnimatorHelper;

/**
 * Created by takao on 2015/11/11.
 */
public class PaFlex2Activity extends Activity implements View.OnClickListener {
    TextView mLabelText;
    RelativeLayoutAnimatorHelper mLabelTextHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pa_flex_2);

        // Find views
        mLabelText = (TextView) findViewById(R.id.text_label);

        // Bind event handlers
        findViewById(R.id.button_hide).setOnClickListener(this);
        findViewById(R.id.button_show).setOnClickListener(this);

        mLabelTextHelper = new RelativeLayoutAnimatorHelper(mLabelText);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_hide) {
            int from = 0;
            int to = -mLabelText.getWidth();
            ObjectAnimator.ofInt(mLabelTextHelper, "rightMargin", from, to).start();
        } else if (view.getId() == R.id.button_show) {
            int from = mLabelTextHelper.getRightMargin();
            int to = 0;
            ObjectAnimator.ofInt(mLabelTextHelper, "rightMargin", from, to).start();
        }
    }
}
