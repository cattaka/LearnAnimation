package net.cattaka.learnanimation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.list);
        mListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Arrays.asList(ActivityItem.values())));
        mListView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        if (adapterView.getId() == R.id.list) {
            ActivityItem item = (ActivityItem) adapterView.getItemAtPosition(position);
            startActivity(new Intent(this, item.mActivityClass));
        }
    }

    public enum ActivityItem {
        ACTIVITY_ANIMATION("Activity Animation", Aa1Activity.class),
        ACTIVITY_TRANSITION("Activity Transition", At1Activity.class),
        FRAGMENT_ANIMATION("Fragment Animation", FaActivity.class),
        FRAGMENT_TRANSITION("Fragment Transition", FtActivity.class),
        DRAWABLE_ANIMATION_1("Drawable Animation 1", Da1Activity.class),
        DRAWABLE_ANIMATION_2("Drawable Animation 2", Da2Activity.class),
        VIEW_ANIMATION("View Animation", VaActivity.class),
        PROPERTY_ANIMATION_1("Property Animation 1", Pa1Activity.class),
        PROPERTY_ANIMATION_2("Property Animation 2", Pa2Activity.class),
        VIEW_PROPERTY_ANIMATOR("View Property Animator", VpaActivity.class),
        TRANSITION_ANIMATION("Transition Animation", TaActivity.class),
        PROPERTY_ANIMATION_SAMPLE_FLEX_1("Property Animation - Sample Flex 1", PaFlex1Activity.class),
        PROPERTY_ANIMATION_SAMPLE_FLEX_2("Property Animation - Sample Flex 2", PaFlex2Activity.class),
        ANIMATION_VECTOR("Animation Vector", AvActivity.class),
        //
        ;
        final String mLabel;
        final Class<? extends Activity> mActivityClass;

        ActivityItem(String label, Class<? extends Activity> activityClass) {
            mLabel = label;
            mActivityClass = activityClass;
        }

        @Override
        public String toString() {
            return mLabel;
        }
    }
}
