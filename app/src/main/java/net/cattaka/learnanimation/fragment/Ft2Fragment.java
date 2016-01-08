package net.cattaka.learnanimation.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.cattaka.learnanimation.R;

/**
 * Created by takao on 2015/11/18.
 */
public class Ft2Fragment extends Fragment {
    public static Ft2Fragment newInstance() {
        Bundle args = new Bundle();

        Ft2Fragment fragment = new Ft2Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ft_2, container, false);
    }
}
