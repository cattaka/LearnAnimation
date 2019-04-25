package net.cattaka.learnanimation;

import android.os.Bundle;
import android.support.transition.ChangeBounds;
import android.support.transition.Fade;
import android.support.transition.TransitionSet;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import net.cattaka.learnanimation.fragment.Ft1Fragment;
import net.cattaka.learnanimation.fragment.Ft2Fragment;
import net.cattaka.learnanimation.transition.SourceChangeBounds;
import net.cattaka.learnanimation.transition.SourceFade;

/**
 * Created by takao on 2015/10/28.
 */
public class FtActivity extends AppCompatActivity implements View.OnClickListener {
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
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.layout_fragment);
            if (currentFragment == null || currentFragment.getView() == null) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.layout_fragment, fragment);
                ft.addToBackStack(null);
                ft.commit();
                return;
            }
            View image = currentFragment.getView().findViewById(R.id.image_logo);
            View sourceFadeButton = currentFragment.getView().findViewById(R.id.button_source_fade);
            View aButton = currentFragment.getView().findViewById(R.id.button_a);

            fragment.setEnterTransition(new Fade(Fade.IN));
            fragment.setExitTransition(new Fade(Fade.OUT));
            fragment.setSharedElementEnterTransition(new TransitionSet()
                    .addTransition(new ChangeBounds().addTarget(image.getTransitionName()).addTarget(aButton.getTransitionName()))
                    .addTransition(new Fade().addTarget(sourceFadeButton.getTransitionName()))
            );
            fragment.setSharedElementReturnTransition(new TransitionSet()
                    .addTransition(new SourceChangeBounds(true, true, true, true).addTarget(image.getTransitionName()).addTarget(aButton.getTransitionName()))
                    .addTransition(new SourceFade().addTarget(sourceFadeButton.getTransitionName()))
            );

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.addSharedElement(image, image.getTransitionName());
            ft.addSharedElement(aButton, aButton.getTransitionName());
            ft.addSharedElement(sourceFadeButton, sourceFadeButton.getTransitionName());

            ft.setCustomAnimations(R.anim.no_anim, R.anim.no_anim, R.anim.no_anim, R.anim.no_anim);
            ft.replace(R.id.layout_fragment, fragment);
            ft.addToBackStack(null);
            ft.commit();
        } else if (view.getId() == R.id.button_item_2) {
            Fragment fragment = Ft2Fragment.newInstance();
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.layout_fragment);
            if (currentFragment == null || currentFragment.getView() == null) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.layout_fragment, fragment);
                ft.addToBackStack(null);
                ft.commit();
                return;
            }
            View image = currentFragment.getView().findViewById(R.id.image_logo);
            View sourceFadeButton = currentFragment.getView().findViewById(R.id.button_source_fade);
            View aButton = currentFragment.getView().findViewById(R.id.button_a);

            fragment.setEnterTransition(new Fade(Fade.IN));
            fragment.setExitTransition(new Fade(Fade.OUT));
            fragment.setSharedElementEnterTransition(new TransitionSet()
                    .addTransition(new ChangeBounds().addTarget(image.getTransitionName()).addTarget(aButton.getTransitionName()))
                    .addTransition(new Fade().addTarget(sourceFadeButton.getTransitionName()))
            );
            fragment.setSharedElementReturnTransition(new TransitionSet()
                    .addTransition(new SourceChangeBounds(true, true, true, true).addTarget(image.getTransitionName()).addTarget(aButton.getTransitionName()))
                    .addTransition(new SourceFade().addTarget(sourceFadeButton.getTransitionName()))
            );

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            ft.addSharedElement(image, image.getTransitionName());
            ft.addSharedElement(aButton, aButton.getTransitionName());
            ft.addSharedElement(sourceFadeButton, sourceFadeButton.getTransitionName());

            ft.setCustomAnimations(R.anim.no_anim, R.anim.no_anim, R.anim.no_anim, R.anim.no_anim);
            ft.replace(R.id.layout_fragment, fragment);
            ft.addToBackStack(null);
            ft.commit();
        }
    }
}
