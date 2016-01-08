package net.cattaka.learnanimation;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.view.View;
import android.view.ViewGroup;

import net.cattaka.learnanimation.fragment.Ft1Fragment;
import net.cattaka.learnanimation.fragment.Ft2Fragment;

/**
 * Created by takao on 2015/10/28.
 */
public class FtActivity extends Activity implements View.OnClickListener {
    ViewGroup mContainerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ft);

        mContainerLayout = (ViewGroup) findViewById(R.id.layout_container);

        findViewById(R.id.button_item_1).setOnClickListener(this);
        findViewById(R.id.button_item_2).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_item_1) {
            Fragment fragment = Ft1Fragment.newInstance();
            fragment.setEnterTransition(new Fade(Fade.IN));
            fragment.setExitTransition(new Fade(Fade.OUT));
            fragment.setSharedElementEnterTransition(new ChangeBounds());
            fragment.setSharedElementReturnTransition(new ChangeBounds());

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.layout_fragment, fragment);

            Fragment currentFragment = getFragmentManager().findFragmentById(R.id.layout_fragment);
            if (currentFragment != null && currentFragment.getView() != null) {
                View image = currentFragment.getView().findViewById(R.id.image_logo);
                ft.addSharedElement(image, "transition:image_logo");
                View aButton = currentFragment.getView().findViewById(R.id.button_a);
                ft.addSharedElement(aButton, "transition:button_a");
            }
            ft.addToBackStack(null);
            ft.commit();
        } else if (view.getId() == R.id.button_item_2) {
            Fragment fragment = Ft2Fragment.newInstance();
            fragment.setEnterTransition(new Fade(Fade.IN));
            fragment.setExitTransition(new Fade(Fade.OUT));
            fragment.setSharedElementEnterTransition(new ChangeBounds());
            fragment.setSharedElementReturnTransition(new ChangeBounds());

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.layout_fragment, fragment);

            Fragment currentFragment = getFragmentManager().findFragmentById(R.id.layout_fragment);
            if (currentFragment != null && currentFragment.getView() != null) {
                View image = currentFragment.getView().findViewById(R.id.image_logo);
                ft.addSharedElement(image, "transition:image_logo");
                View aButton = currentFragment.getView().findViewById(R.id.button_a);
                ft.addSharedElement(aButton, "transition:button_a");
            }
            ft.addToBackStack(null);
            ft.commit();
        }
    }
}
