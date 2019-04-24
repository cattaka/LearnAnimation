package net.cattaka.learnanimation.transition;


import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.util.Property;

class PathProperty<T> extends Property<T, Float> {
    private final Property<T, PointF> mProperty;
    private final PathMeasure mPathMeasure;
    private final float mPathLength;
    private final float[] mPosition = new float[2];
    private final PointF mPointF = new PointF();
    private float mCurrentFraction;

    PathProperty(Property<T, PointF> property, Path path) {
        super(Float.class, property.getName());
        this.mProperty = property;
        this.mPathMeasure = new PathMeasure(path, false);
        this.mPathLength = this.mPathMeasure.getLength();
    }

    public Float get(T object) {
        return this.mCurrentFraction;
    }

    public void set(T target, Float fraction) {
        this.mCurrentFraction = fraction;
        this.mPathMeasure.getPosTan(this.mPathLength * fraction, this.mPosition, (float[]) null);
        this.mPointF.x = this.mPosition[0];
        this.mPointF.y = this.mPosition[1];
        this.mProperty.set(target, this.mPointF);
    }
}
