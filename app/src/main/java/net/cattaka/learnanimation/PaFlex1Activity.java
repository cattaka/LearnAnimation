package net.cattaka.learnanimation;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by takao on 2015/11/11.
 */
public class PaFlex1Activity extends Activity implements View.OnClickListener {
    TextView mLabelText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pa_flex_1);

        // Find views
        mLabelText = (TextView) findViewById(R.id.text_label);

        // Bind event handlers
        findViewById(R.id.button_hide).setOnClickListener(this);
        findViewById(R.id.button_show).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_hide) {
            int from = mLabelText.getWidth();
            int to = 0;
            ObjectAnimator.ofInt(mLabelText, "width", from, to).start();
        } else if (view.getId() == R.id.button_show) {
            int from = mLabelText.getWidth();
            mLabelText.setMaxWidth(Integer.MAX_VALUE);
            mLabelText.setMaxHeight(Integer.MAX_VALUE);
            mLabelText.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            int to = mLabelText.getMeasuredWidth();
            ObjectAnimator.ofInt(mLabelText, "width", from, to).start();
        }
    }
}
