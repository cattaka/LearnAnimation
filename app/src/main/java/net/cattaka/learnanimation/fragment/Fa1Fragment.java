package net.cattaka.learnanimation.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.cattaka.learnanimation.R;

/**
 * Created by takao on 2015/11/18.
 */
public class Fa1Fragment extends Fragment {
    public static Fa1Fragment newInstance() {
        Bundle args = new Bundle();

        Fa1Fragment fragment = new Fa1Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fa_1, container, false);
    }
}
