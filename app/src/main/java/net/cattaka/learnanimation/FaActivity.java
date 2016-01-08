package net.cattaka.learnanimation;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import net.cattaka.learnanimation.fragment.Fa1Fragment;
import net.cattaka.learnanimation.fragment.Fa2Fragment;

/**
 * Created by takao on 2015/10/28.
 */
public class FaActivity extends AppCompatActivity implements View.OnClickListener {
    ViewGroup mContainerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fa);

        mContainerLayout = (ViewGroup) findViewById(R.id.layout_container);

        findViewById(R.id.button_item_1).setOnClickListener(this);
        findViewById(R.id.button_item_2).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_item_1) {
            Fragment fragment = Fa1Fragment.newInstance();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.anim.aa_slide_in, R.anim.aa_slide_out);
            ft.replace(R.id.layout_fragment, fragment);
            ft.addToBackStack(null);
            ft.commit();
        } else if (view.getId() == R.id.button_item_2) {
            Fragment fragment = Fa2Fragment.newInstance();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.anim.aa_slide_in, R.anim.aa_slide_out);
            ft.replace(R.id.layout_fragment, fragment);
            ft.addToBackStack(null);
            ft.commit();
        }
    }
}
