package net.cattaka.learnanimation.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.transition.Transition;
import android.support.transition.TransitionValues;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import net.cattaka.learnanimation.R;

public class SourceFade extends Transition {

    public SourceFade() {
    }

    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        this.captureValues(transitionValues);
    }

    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        this.captureValues(transitionValues);
    }

    private void captureValues(TransitionValues values) {
        View view = values.view;
        if (ViewCompat.isLaidOut(view)) {
            Rect parentRect = new Rect();
            ViewParent parent = view.getParent();
            if (parent instanceof View) {
                ((View) parent).getGlobalVisibleRect(parentRect);

                ViewGroup.LayoutParams params = ((View) parent).getLayoutParams();
                if (params instanceof ViewGroup.MarginLayoutParams) {
                    parentRect.left -= ((ViewGroup.MarginLayoutParams) params).leftMargin;
                    parentRect.top -= ((ViewGroup.MarginLayoutParams) params).topMargin;
                    parentRect.right += ((ViewGroup.MarginLayoutParams) params).rightMargin;
                    parentRect.bottom += ((ViewGroup.MarginLayoutParams) params).bottomMargin;
                }
            }

            Rect rect = new Rect(
                    view.getLeft(),
                    view.getTop(),
                    view.getRight(),
                    view.getBottom()
            );
            if (parent instanceof View) {

                ViewGroup.LayoutParams params = ((View) parent).getLayoutParams();
                if (params instanceof ViewGroup.MarginLayoutParams) {
                    rect.left += ((ViewGroup.MarginLayoutParams) params).leftMargin;
                    rect.top += ((ViewGroup.MarginLayoutParams) params).topMargin;
                    rect.right += ((ViewGroup.MarginLayoutParams) params).leftMargin;
                    rect.bottom += ((ViewGroup.MarginLayoutParams) params).topMargin;
                }
            }

            values.values.put("android:changeBounds:bounds", rect);
            values.values.put("android:changeBounds:offset", new Point(parentRect.left, parentRect.top));
        }
    }

    @Nullable
    @Override
    public Animator createAnimator(@NonNull final ViewGroup sceneRoot, @Nullable TransitionValues startValues, @Nullable TransitionValues endValues) {
        if (startValues != null && endValues != null) {
            final View toView = endValues.view;
            final View fromView = startValues.view;
            Rect startBounds = (Rect) startValues.values.get("android:changeBounds:bounds");
            // Rect endBounds = (Rect) endValues.values.get("android:changeBounds:bounds");

            Bitmap bitmap = Bitmap.createBitmap(fromView.getWidth(), fromView.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            fromView.draw(canvas);
            final BitmapDrawable drawable = new BitmapDrawable(bitmap);
            final float transitionAlpha = getTransitionAlpha(toView);
            setTransitionAlpha(toView, 0.0F);
            sceneRoot.getOverlay().add(drawable);

            if (startBounds != null) {
                drawable.setBounds(startBounds);
            }
            ObjectAnimator anim = ObjectAnimator.ofInt(drawable, "alpha", 255, 0);
            anim.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animation) {
                    sceneRoot.getOverlay().remove(drawable);
                    setTransitionAlpha(toView, transitionAlpha);
                }
            });
            return anim;
        }
        return null;
    }

    public float getTransitionAlpha(@NonNull View view) {
        Float savedAlpha = (Float) view.getTag(R.id.save_non_transition_alpha);
        return savedAlpha != null ? view.getAlpha() / savedAlpha : view.getAlpha();
    }

    public void setTransitionAlpha(@NonNull View view, float alpha) {
        Float savedAlpha = (Float) view.getTag(R.id.save_non_transition_alpha);
        if (savedAlpha != null) {
            view.setAlpha(savedAlpha * alpha);
        } else {
            view.setAlpha(alpha);
        }
    }
}

