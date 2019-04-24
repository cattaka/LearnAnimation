package net.cattaka.learnanimation.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeConverter;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.transition.ChangeBounds;
import android.support.transition.TransitionValues;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import net.cattaka.learnanimation.R;

public class SourceChangeBounds extends ChangeBounds {

    public SourceChangeBounds() {
    }

    public SourceChangeBounds(Context context, AttributeSet attrs) {
        super(context, attrs);
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
            Rect endBounds = (Rect) endValues.values.get("android:changeBounds:bounds");
            Point startOffset = (Point) startValues.values.get("android:changeBounds:offset");
            Point endOffset = (Point) endValues.values.get("android:changeBounds:offset");
            int ox = startOffset.x - endOffset.x;
            int oy = startOffset.y - endOffset.y;

            Bitmap bitmap = Bitmap.createBitmap(fromView.getWidth(), fromView.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            fromView.draw(canvas);
            final BitmapDrawable drawable = new BitmapDrawable(bitmap);
            DrawableWrapper drawableWrapper = new DrawableWrapper(drawable);
            final float transitionAlpha = getTransitionAlpha(toView);
            setTransitionAlpha(toView, 0.0F);
            sceneRoot.getOverlay().add(drawable);

            ObjectAnimator anim = ObjectAnimator.ofObject(drawable, "bounds", mRectTypeEvaluator,
                    new Rect(startBounds.left, startBounds.top, startBounds.right, startBounds.bottom),
                    new Rect(endBounds.left, endBounds.top, endBounds.right, endBounds.bottom)
            );
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

    public static void setLeftTopRightBottom(View v, int left, int top, int right, int bottom) {
        v.setLeft(left);
        v.setTop(top);
        v.setRight(right);
        v.setBottom(bottom);
    }

    private static class DrawableWrapper {
        private Drawable mDrawable;

        public DrawableWrapper(Drawable drawable) {
            mDrawable = drawable;
        }

        @Keep
        public void setBounds(int[] value) {
            mDrawable.setBounds(value[0], value[1], value[2], value[3]);
        }

        @Keep
        public int[] getBounds() {
            Rect b = mDrawable.getBounds();
            return new int[]{b.left, b.top, b.right, b.top};
        }
    }

    RectEvaluator mRectTypeEvaluator = new RectEvaluator();

    static PropertyValuesHolder ofPointF(Property<?, PointF> property, Path path) {
        return PropertyValuesHolder.ofObject(property, (TypeConverter) null, path);
    }
}

