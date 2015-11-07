package net.cattaka.learnanimation.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by cattaka on 15/11/07.
 */
public class CustomPropertyView extends TextView {
    public CustomPropertyView(Context context) {
        super(context);
    }

    public CustomPropertyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomPropertyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomPropertyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setCustomProperty(int number) {
        setText(String.valueOf(number));
    }

    public int getCustomProperty() {
        try {
            return Integer.valueOf(String.valueOf(getText()));
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
