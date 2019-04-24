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
public class Ft1Fragment extends Fragment {
    public static Ft1Fragment newInstance() {
        Bundle args = new Bundle();

        Ft1Fragment fragment = new Ft1Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ft_1, container, false);
    }
}
