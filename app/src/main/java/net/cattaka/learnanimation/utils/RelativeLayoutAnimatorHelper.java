package net.cattaka.learnanimation.utils;

import android.view.View;
import android.widget.RelativeLayout;

/**
 * Save all methods from proguard!!
 * <p/>
 * Created by cattaka on 2015/11/10.
 */
public class RelativeLayoutAnimatorHelper {
    View mView;

    public RelativeLayoutAnimatorHelper(View view) {
        mView = view;
    }

    public int getTopMargin() {
        return ((RelativeLayout.LayoutParams) mView.getLayoutParams()).topMargin;
    }

    public void setTopMargin(int v) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mView.getLayoutParams();
        params.topMargin = v;
        mView.setLayoutParams(params);
    }

    public int getLeftMargin() {
        return ((RelativeLayout.LayoutParams) mView.getLayoutParams()).leftMargin;
    }

    public void setLeftMargin(int v) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mView.getLayoutParams();
        params.leftMargin = v;
        mView.setLayoutParams(params);
    }

    public int getRightMargin() {
        return ((RelativeLayout.LayoutParams) mView.getLayoutParams()).rightMargin;
    }

    public void setRightMargin(int v) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mView.getLayoutParams();
        params.rightMargin = v;
        mView.setLayoutParams(params);
    }

    public int getBottomMargin() {
        return ((RelativeLayout.LayoutParams) mView.getLayoutParams()).bottomMargin;
    }

    public void setBottomMargin(int v) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mView.getLayoutParams();
        params.bottomMargin = v;
        mView.setLayoutParams(params);
    }
}