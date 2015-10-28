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
        ACTIVITY_TRANSITION("Activity Transition", At1Activity.class),;
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
