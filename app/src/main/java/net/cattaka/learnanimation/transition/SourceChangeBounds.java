package net.cattaka.learnanimation.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeConverter;
import android.content.Context;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.transition.ChangeBounds;
import android.support.transition.Transition;
import android.support.transition.TransitionListenerAdapter;
import android.support.transition.TransitionValues;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;

import net.cattaka.learnanimation.R;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SourceChangeBounds extends ChangeBounds {

    private static final Property<Drawable, PointF> DRAWABLE_ORIGIN_PROPERTY = new Property<Drawable, PointF>(PointF.class, "boundsOrigin") {
        private Rect mBounds = new Rect();

        public void set(Drawable object, PointF value) {
            object.copyBounds(this.mBounds);
            this.mBounds.offsetTo(Math.round(value.x), Math.round(value.y));
            object.setBounds(this.mBounds);
        }

        public PointF get(Drawable object) {
            object.copyBounds(this.mBounds);
            return new PointF((float) this.mBounds.left, (float) this.mBounds.top);
        }
    };
    private static final Property<ViewBounds, PointF> TOP_LEFT_PROPERTY = new Property<ViewBounds, PointF>(PointF.class, "topLeft") {
        public void set(ViewBounds viewBounds, PointF topLeft) {
            viewBounds.setTopLeft(topLeft);
        }

        public PointF get(ViewBounds viewBounds) {
            return null;
        }
    };
    private static final Property<ViewBounds, PointF> BOTTOM_RIGHT_PROPERTY = new Property<ViewBounds, PointF>(PointF.class, "bottomRight") {
        public void set(ViewBounds viewBounds, PointF bottomRight) {
            viewBounds.setBottomRight(bottomRight);
        }

        public PointF get(ViewBounds viewBounds) {
            return null;
        }
    };
    private static final Property<View, PointF> BOTTOM_RIGHT_ONLY_PROPERTY = new Property<View, PointF>(PointF.class, "bottomRight") {
        public void set(View view, PointF bottomRight) {
            int left = view.getLeft();
            int top = view.getTop();
            int right = Math.round(bottomRight.x);
            int bottom = Math.round(bottomRight.y);
            setLeftTopRightBottom(view, left, top, right, bottom);
        }

        public PointF get(View view) {
            return null;
        }
    };
    private static final Property<View, PointF> TOP_LEFT_ONLY_PROPERTY = new Property<View, PointF>(PointF.class, "topLeft") {
        public void set(View view, PointF topLeft) {
            int left = Math.round(topLeft.x);
            int top = Math.round(topLeft.y);
            int right = view.getRight();
            int bottom = view.getBottom();
            setLeftTopRightBottom(view, left, top, right, bottom);
        }

        public PointF get(View view) {
            return null;
        }
    };
    private static final Property<View, PointF> POSITION_PROPERTY = new Property<View, PointF>(PointF.class, "position") {
        public void set(View view, PointF topLeft) {
            int left = Math.round(topLeft.x);
            int top = Math.round(topLeft.y);
            int right = left + view.getWidth();
            int bottom = top + view.getHeight();
            setLeftTopRightBottom(view, left, top, right, bottom);
        }

        public PointF get(View view) {
            return null;
        }
    };

    private int[] mTempLocation = new int[2];
    private boolean mResizeClip = false;
    private boolean mReparent = false;
    private static RectEvaluator sRectEvaluator = new RectEvaluator();

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
        if (ViewCompat.isLaidOut(view) || view.getWidth() != 0 || view.getHeight() != 0) {
            values.values.put("android:changeBounds:bounds", new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
            values.values.put("android:changeBounds:parent", values.view.getParent());
            if (this.mReparent) {
                values.view.getLocationInWindow(this.mTempLocation);
                values.values.put("android:changeBounds:windowX", this.mTempLocation[0]);
                values.values.put("android:changeBounds:windowY", this.mTempLocation[1]);
            }

            if (this.mResizeClip) {
                values.values.put("android:changeBounds:clip", ViewCompat.getClipBounds(view));
            }
        }

    }

    @Nullable
    @Override
    public Animator createAnimator(@NonNull final ViewGroup sceneRoot, @Nullable TransitionValues startValues, @Nullable TransitionValues endValues) {
        if (startValues != null && endValues != null) {
            final View view = endValues.view;
            Rect startBounds = (Rect) startValues.values.get("android:changeBounds:bounds");
            Rect endBounds = (Rect) endValues.values.get("android:changeBounds:bounds");
            int startLeft = startBounds.left;
            final int endLeft = endBounds.left;
            int startTop = startBounds.top;
            final int endTop = endBounds.top;
            int startRight = startBounds.right;
            final int endRight = endBounds.right;
            int startBottom = startBounds.bottom;
            final int endBottom = endBounds.bottom;
            int startWidth = startRight - startLeft;
            int startHeight = startBottom - startTop;
            int endWidth = endRight - endLeft;
            int endHeight = endBottom - endTop;
            Rect startClip = (Rect) startValues.values.get("android:changeBounds:clip");
            Rect endClip = (Rect) endValues.values.get("android:changeBounds:clip");
            int numChanges = 0;
            if (startWidth != 0 && startHeight != 0 || endWidth != 0 && endHeight != 0) {
                if (startLeft != endLeft || startTop != endTop) {
                    ++numChanges;
                }

                if (startRight != endRight || startBottom != endBottom) {
                    ++numChanges;
                }
            }

            if (startClip != null && !startClip.equals(endClip) || startClip == null && endClip != null) {
                ++numChanges;
            }

            if (numChanges > 0) {
                Object anim;
                ObjectAnimator positionAnimator;
                Path bottomRightPath;
                ObjectAnimator clipAnimator;
                if (!this.mResizeClip) {
                    setLeftTopRightBottom(view, startLeft, startTop, startRight, startBottom);
                    Path topLeftPath;
                    if (numChanges == 2) {
                        if (startWidth == endWidth && startHeight == endHeight) {
                            topLeftPath = this.getPathMotion().getPath((float) startLeft, (float) startTop, (float) endLeft, (float) endTop);
                            anim = ofPointF(view, POSITION_PROPERTY, topLeftPath);
                        } else {
                            final ViewBounds viewBounds = new ViewBounds(view);
                            topLeftPath = this.getPathMotion().getPath((float) startLeft, (float) startTop, (float) endLeft, (float) endTop);
                            positionAnimator = ofPointF(viewBounds, TOP_LEFT_PROPERTY, topLeftPath);
                            bottomRightPath = this.getPathMotion().getPath((float) startRight, (float) startBottom, (float) endRight, (float) endBottom);
                            clipAnimator = ofPointF(viewBounds, BOTTOM_RIGHT_PROPERTY, bottomRightPath);
                            AnimatorSet set = new AnimatorSet();
                            set.playTogether(new Animator[]{positionAnimator, clipAnimator});
                            anim = set;
                            set.addListener(new AnimatorListenerAdapter() {
                                private ViewBounds mViewBounds = viewBounds;
                            });
                        }
                    } else if (startLeft == endLeft && startTop == endTop) {
                        topLeftPath = this.getPathMotion().getPath((float) startRight, (float) startBottom, (float) endRight, (float) endBottom);
                        anim = ofPointF(view, BOTTOM_RIGHT_ONLY_PROPERTY, topLeftPath);
                    } else {
                        topLeftPath = this.getPathMotion().getPath((float) startLeft, (float) startTop, (float) endLeft, (float) endTop);
                        anim = ofPointF(view, TOP_LEFT_ONLY_PROPERTY, topLeftPath);
                    }
                } else {
                    int maxWidth = Math.max(startWidth, endWidth);
                    int maxHeight = Math.max(startHeight, endHeight);
                    setLeftTopRightBottom(view, startLeft, startTop, startLeft + maxWidth, startTop + maxHeight);
                    positionAnimator = null;
                    if (startLeft != endLeft || startTop != endTop) {
                        bottomRightPath = this.getPathMotion().getPath((float) startLeft, (float) startTop, (float) endLeft, (float) endTop);
                        positionAnimator = ofPointF(view, POSITION_PROPERTY, bottomRightPath);
                    }

                    final Rect finalClip = endClip;
                    if (startClip == null) {
                        startClip = new Rect(0, 0, startWidth, startHeight);
                    }

                    if (endClip == null) {
                        endClip = new Rect(0, 0, endWidth, endHeight);
                    }

                    clipAnimator = null;
                    if (!startClip.equals(endClip)) {
                        ViewCompat.setClipBounds(view, startClip);
                        clipAnimator = ObjectAnimator.ofObject(view, "clipBounds", sRectEvaluator, new Object[]{startClip, endClip});
                        clipAnimator.addListener(new AnimatorListenerAdapter() {
                            private boolean mIsCanceled;

                            public void onAnimationCancel(Animator animation) {
                                this.mIsCanceled = true;
                            }

                            public void onAnimationEnd(Animator animation) {
                                if (!this.mIsCanceled) {
                                    ViewCompat.setClipBounds(view, finalClip);
                                    setLeftTopRightBottom(view, endLeft, endTop, endRight, endBottom);
                                }

                            }
                        });
                    }

                    anim = mergeAnimators(positionAnimator, clipAnimator);
                }

                if (view.getParent() instanceof ViewGroup) {
                    final ViewGroup parent = (ViewGroup) view.getParent();
                    suppressLayout(parent, true);
                    TransitionListener transitionListener = new TransitionListenerAdapter() {
                        boolean mCanceled = false;

                        public void onTransitionCancel(@NonNull Transition transition) {
                            suppressLayout(parent, false);
                            this.mCanceled = true;
                        }

                        public void onTransitionEnd(@NonNull Transition transition) {
                            if (!this.mCanceled) {
                                suppressLayout(parent, false);
                            }

                            transition.removeListener(this);
                        }

                        public void onTransitionPause(@NonNull Transition transition) {
                            suppressLayout(parent, false);
                        }

                        public void onTransitionResume(@NonNull Transition transition) {
                            suppressLayout(parent, true);
                        }
                    };
                    this.addListener(transitionListener);
                }

                return (Animator) anim;
            }
        }
        return super.createAnimator(sceneRoot, startValues, endValues);
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

    static PropertyValuesHolder ofPointF(Property<?, PointF> property, Path path) {
        return PropertyValuesHolder.ofObject(property, (TypeConverter) null, path);
    }

    static <T> ObjectAnimator ofPointF(T target, Property<T, PointF> property, Path path) {
        return ObjectAnimator.ofObject(target, property, (TypeConverter) null, path);
    }

    static Animator mergeAnimators(Animator animator1, Animator animator2) {
        if (animator1 == null) {
            return animator2;
        } else if (animator2 == null) {
            return animator1;
        } else {
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(new Animator[]{animator1, animator2});
            return animatorSet;
        }
    }


    private static Method sSuppressLayoutMethod;
    private static boolean sSuppressLayoutMethodFetched;

    static void suppressLayout(@NonNull ViewGroup group, boolean suppress) {
        fetchSuppressLayoutMethod();
        if (sSuppressLayoutMethod != null) {
            try {
                sSuppressLayoutMethod.invoke(group, suppress);
            } catch (IllegalAccessException var3) {
                Log.i("ViewUtilsApi18", "Failed to invoke suppressLayout method", var3);
            } catch (InvocationTargetException var4) {
                Log.i("ViewUtilsApi18", "Error invoking suppressLayout method", var4);
            }
        }

    }

    private static void fetchSuppressLayoutMethod() {
        if (!sSuppressLayoutMethodFetched) {
            try {
                sSuppressLayoutMethod = ViewGroup.class.getDeclaredMethod("suppressLayout", Boolean.TYPE);
                sSuppressLayoutMethod.setAccessible(true);
            } catch (NoSuchMethodException var1) {
                Log.i("ViewUtilsApi18", "Failed to retrieve suppressLayout method", var1);
            }

            sSuppressLayoutMethodFetched = true;
        }

    }

    private static class ViewBounds {
        private int mLeft;
        private int mTop;
        private int mRight;
        private int mBottom;
        private View mView;
        private int mTopLeftCalls;
        private int mBottomRightCalls;

        ViewBounds(View view) {
            this.mView = view;
        }

        void setTopLeft(PointF topLeft) {
            this.mLeft = Math.round(topLeft.x);
            this.mTop = Math.round(topLeft.y);
            ++this.mTopLeftCalls;
            if (this.mTopLeftCalls == this.mBottomRightCalls) {
                this.setLeftTopRightBottom();
            }

        }

        void setBottomRight(PointF bottomRight) {
            this.mRight = Math.round(bottomRight.x);
            this.mBottom = Math.round(bottomRight.y);
            ++this.mBottomRightCalls;
            if (this.mTopLeftCalls == this.mBottomRightCalls) {
                this.setLeftTopRightBottom();
            }

        }

        private void setLeftTopRightBottom() {
            SourceChangeBounds.setLeftTopRightBottom(this.mView, this.mLeft, this.mTop, this.mRight, this.mBottom);
            this.mTopLeftCalls = 0;
            this.mBottomRightCalls = 0;
        }
    }
}

