package com.example.curtains;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View {

    //Variables
    private final DisplayMetrics Metrics = getResources().getDisplayMetrics();
    private final Rect MainRect = new Rect();
    private final RectF MainRoundRect = new RectF();
    private final Path MainPath = new Path();
    private final Paint paint = new Paint(), greenPaint = new Paint(), blackPaint = new Paint(), darkgreenPaint = new Paint();
    private float mFirstClick, mCurtainWidth, mCurtainMargin, mElWidth, mPercent, mLastTouchX, mLastTouchY;
    private double mThickness = 1, mLineSubtract;
    private boolean mTouchLeft;
    private int mLineColor, mFillColor1, mFillColor2;
    private RectF workSpace = new RectF();
    private OnPercentChangeListener mOnPercentChangeListener;
    private float tempP = 0;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) { super(context, attrs, defStyleAttr); }

    public interface OnPercentChangeListener {
        void onPercentChangeing(MyView rs, float percent);
    }

    public int getLineColor() {
        return mLineColor;
    }

    public void setLineColor(int lineColor) {
        mLineColor = lineColor;
        invalidate();
    }

    public int getFillColor1() { return mFillColor1; }

    public void setFillColor1(int fillColor1) {
        mFillColor1 = fillColor1;
        invalidate();
    }

    public int getFillColor2() { return mFillColor2; }

    public void setFillColor2(int fillColor2) {
        mFillColor2 = fillColor2;
        invalidate();
    }

    public void setPercent(float percent) {
        if (percent < 0) {
            percent = 0;
        } else if (percent > 100) {
            percent = 100;
        }
        mPercent = percent;
        calculateConstants();
        invalidate();
    }

    private float getmPercent() {
        return mPercent;
    }

    private void drawRect(Canvas canvas, float leftPercent, float topPercent, float rightPercent, float bottomPercent, Paint paint) {
        MainRect.set((int) (workSpace.width() * leftPercent + workSpace.left), (int)(workSpace.height() * topPercent + workSpace.top), (int)(workSpace.width() * rightPercent + workSpace.left), (int)(workSpace.height() * bottomPercent + workSpace.top));
        canvas.drawRect(MainRect, paint);
    }

    private void drawRoundRect(Canvas canvas, float leftPercent, float topPercent, float rightPercent, float bottomPercent, int rx, int ry, Paint paint) {
        MainRoundRect.set(workSpace.width() * leftPercent + workSpace.left,workSpace.height() * topPercent + workSpace.top,workSpace.width() * rightPercent + workSpace.left, workSpace.height() * bottomPercent + workSpace.top);
        canvas.drawRoundRect(MainRoundRect,rx,ry, paint);
    }

    private void drawCurtain(Canvas canvas, float left, float right, float bottomPercent, Paint colorPaint) {
        MainRoundRect.set(workSpace.left + left, workSpace.top + workSpace.height() * 0.05f, workSpace.left + right, workSpace.top + workSpace.height() * bottomPercent);
        canvas.drawRoundRect(MainRoundRect, 7, 5, colorPaint);
        canvas.drawRoundRect(MainRoundRect, 7, 5, paint);
    }

    private void drawCurtains(Canvas canvas, boolean right) {
        float width = mCurtainWidth + mElWidth;
        int elCount;
        float l, r;

        elCount = (int)(width / mElWidth);

        if (right) {
            width = workSpace.width() - width - mCurtainMargin;
        }
        for (int i = 0; i < elCount; i++) {
            if (right) {
                l = width + (mElWidth * i);
                r = width + (mElWidth *( i + 1 ));
            } else {
                l = mCurtainMargin + width - (mElWidth * (i + 1));
                r = mCurtainMargin + width - (mElWidth * i);
            }

            if(i%2 == 0) {
                drawCurtain(canvas,  l, r, (float) (1 - mLineSubtract), greenPaint);
            } else {
                drawCurtain(canvas, l, r, 0.97f, darkgreenPaint);
            }
        }

        if (right) {
            drawCurtain(canvas,workSpace.width()-mCurtainMargin - mElWidth,workSpace.width()-mCurtainMargin,(float) (1 - mLineSubtract), greenPaint);
        } else {
            drawCurtain(canvas, mCurtainMargin,mCurtainMargin + mElWidth,(float) (1 - mLineSubtract), greenPaint);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mFirstClick = event.getY();
                mLastTouchX = event.getX();
                mLastTouchY = event.getY();
                mTouchLeft = mLastTouchX < workSpace.width() / 2 + workSpace.left;
                tempP = getmPercent();
            case MotionEvent.ACTION_MOVE:
                float X = event.getX();
                float Y = event.getY();
                float dX = X - mLastTouchX;
                float dY = Y - mLastTouchY;

                if (((workSpace.height() * 1) + ((getHeight()-workSpace.height())/2) > mFirstClick) && ((workSpace.height() * 0.08 + ((getHeight()-workSpace.height())/2) < mFirstClick))) {
                    if (Math.abs(dX) > Math.abs(dY)) {
                        if (mTouchLeft) {
                            dX *= -1;
                        }
                        float p = dX * 100.f / workSpace.width() * 2f;
                        setPercent(getmPercent() - p);
                    }
                }
                mLastTouchX = X;
                mLastTouchY = Y;
                if (mOnPercentChangeListener != null) {
                    mOnPercentChangeListener.onPercentChangeing(this, getmPercent());
                }
                return true;
            case MotionEvent.ACTION_UP:
                setPercent(tempP);
                return true;
        }
        return false;
    }

    private void setCourtainWidth(float width) {
        if (width < 0) {
            width = 0;
        } else if (width > (workSpace.width() / 2) - mElWidth) {
            width = (workSpace.width() / 2) - mElWidth;
        }
        mCurtainWidth = width;
    }

    private void calculateConstants() {
        if (getWidth() > getHeight()) {
            workSpace = new RectF((float)(getWidth()-getHeight())/2,0,getHeight()+ (float) ((getWidth()-getHeight())/2), getHeight());
            mThickness = workSpace.height() * 0.003;
            if (mThickness < 0.5) mThickness = 0.5;
            mLineSubtract = mThickness * 2 / workSpace.height();
        } else if (getWidth() < getHeight()) {
            workSpace = new RectF(0,(float) (getHeight()-getWidth())/2,getWidth(), getWidth()+ (float) ((getHeight()-getWidth())/2));
            mThickness = workSpace.width() * 0.003;
            if (mThickness < 0.5) mThickness = 0.5;
            mLineSubtract = mThickness * 2 / workSpace.width();
        } else {
            workSpace = new RectF(0,0,getWidth(), getHeight());
            mThickness = workSpace.height() * 0.003;
            if (mThickness < 0.5) mThickness = 0.5;
            mLineSubtract = mThickness * 2 / workSpace.height();
        }
        mElWidth = workSpace.width() * 0.05f;
        mCurtainMargin = workSpace.width() * 0.05f;
        setCourtainWidth((((workSpace.width() - mCurtainMargin * 2) / 2f) - mElWidth) * mPercent / 100f);
    }

    @Override
    public void onDraw(Canvas canvas) {

        calculateConstants();

        //Variables
        float FrameLineWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float) mThickness, Metrics);

        //Paint Stroke Line
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(FrameLineWidth);
        paint.setColor(mLineColor);

        //Paint Fill
        blackPaint.setStyle(Paint.Style.FILL);
        blackPaint.setColor(mLineColor);

        //Paint Fill Color 1
        greenPaint.setStyle(Paint.Style.FILL);
        greenPaint.setColor(mFillColor1);

        //Paint Fill Color 2
        darkgreenPaint.setStyle(Paint.Style.FILL);
        darkgreenPaint.setColor(mFillColor2);

        //Window Picture Elements

        //Window frame L
        drawRect(canvas, 0.275f, 0.16f, 0.30f, 0.88f, paint);

        //Window frame R
        drawRect(canvas, 0.70f, 0.16f, 0.725f, 0.88f, paint);

        //Glass T L
        drawRect(canvas, 0.30f, 0.16f, 0.465f, 0.46f, paint);

        //Glass T R
        drawRect(canvas, 0.535f, 0.16f, 0.70f, 0.46f, paint);

        //Glass B L
        drawRect(canvas, 0.30f, 0.5f, 0.465f, 0.88f, paint);

        //Glass B R
        drawRect(canvas, 0.535f, 0.5f, 0.70f, 0.88f, paint);

        //Windowsill
        drawRect(canvas, 0.24f, 0.88f, 0.76f, 0.95f, paint);

        //Windowsill L
        drawRoundRect(canvas, 0.225f,0.87f, 0.24f, 0.96f, 3, 3, paint);

        //Windowsill R
        drawRoundRect(canvas, 0.76f, 0.87f, 0.775f, 0.96f, 3, 3, paint);

        //Cornice T
        drawRoundRect(canvas, 0.23f, 0.11f, 0.77f, 0.125f, 8, 8, paint);

        //Cornice B
        drawRoundRect(canvas, 0.26f, 0.125f, 0.74f, 0.16f, 3, 3, paint);

        //Window line
        canvas.drawLine((int) (workSpace.width() * 0.50 + workSpace.left), (int) (workSpace.height() * 0.16f + workSpace.top), (int) (workSpace.width() * 0.50 + workSpace.left), (int) (workSpace.height() * 0.88f + workSpace.top), paint);

        //Handle L
        drawRect(canvas, 0.476f, 0.56f, 0.489f, 0.63f, blackPaint);

        //Handle R
        drawRect(canvas, 0.511f, 0.56f, 0.524f, 0.63f, blackPaint);

        //Handle base L
        canvas.drawCircle((int) (workSpace.width() * 0.483 + workSpace.left), (int) (workSpace.height() * 0.56 + workSpace.top), (int) (workSpace.width() * 0.007), paint);

        //Handle base R
        canvas.drawCircle((int) (workSpace.width() * 0.517 + workSpace.left), (int) (workSpace.height() * 0.56 + workSpace.top), (int) (workSpace.width() * 0.007), paint);

        //Curtain rod
        drawRoundRect(canvas, (float) (0 + mLineSubtract), (float) (0 + mLineSubtract), (float) (1 - mLineSubtract), 0.05f, 7, 7, paint);

        //Flowerpot
        drawRect(canvas, 0.58f, 0.8f, 0.65f, 0.87f, paint);

        //Flowerpot stand
        drawRoundRect(canvas, 0.57f, 0.87f, 0.66f, 0.88f, 3, 3, paint);

        //Flowerpot frame
        drawRoundRect(canvas, 0.57f, 0.785f, 0.66f, 0.8f, 7, 7, paint);

        //Leaf L L
        MainPath.reset();
        MainPath.moveTo(workSpace.width() * 0.588f + workSpace.left,workSpace.height() * 0.785f + workSpace.top);
        MainPath.quadTo(workSpace.width() * 0.575f + workSpace.left,workSpace.height() * 0.7392f + workSpace.top,workSpace.width() * 0.555f  + workSpace.left,workSpace.height() * 0.7192f + workSpace.top);
        MainPath.quadTo(workSpace.width() * 0.59f + workSpace.left,workSpace.height() * 0.7392f + workSpace.top,workSpace.width() * 0.605f + workSpace.left,workSpace.height() * 0.785f + workSpace.top);
        MainPath.close();
        canvas.drawPath(MainPath, greenPaint);
        canvas.drawPath(MainPath, paint);

        //Leaf C L
        MainPath.moveTo(workSpace.width() * 0.605f + workSpace.left,workSpace.height() * 0.785f + workSpace.top);
        MainPath.quadTo(workSpace.width() * 0.6f + workSpace.left,workSpace.height() * 0.7692f + workSpace.top,workSpace.width() * 0.56f + workSpace.left,workSpace.height() * 0.6892f + workSpace.top);
        MainPath.quadTo(workSpace.width() * 0.59f + workSpace.left,workSpace.height() * 0.7092f + workSpace.top,workSpace.width() * 0.62f + workSpace.left,workSpace.height() * 0.785f + workSpace.top);
        MainPath.close();
        canvas.drawPath(MainPath, greenPaint);
        canvas.drawPath(MainPath, paint);

        //Leaf C R
        MainPath.moveTo(workSpace.width() * 0.6095f + workSpace.left,workSpace.height() * 0.7592f + workSpace.top);
        MainPath.quadTo(workSpace.width() * 0.62f + workSpace.left,workSpace.height() * 0.6992f + workSpace.top,workSpace.width() * 0.65f + workSpace.left,workSpace.height() * 0.6592f + workSpace.top);
        MainPath.quadTo(workSpace.width() * 0.63f + workSpace.left,workSpace.height() * 0.7392f + workSpace.top,workSpace.width() * 0.625f + workSpace.left,workSpace.height() * 0.785f + workSpace.top);
        MainPath.lineTo(workSpace.width() * 0.618f + workSpace.left,workSpace.height() * 0.785f + workSpace.top);
        MainPath.close();
        canvas.drawPath(MainPath, greenPaint);
        canvas.drawPath(MainPath, paint);

        //Leaf R R
        MainPath.moveTo(workSpace.width() * 0.622f + workSpace.left,workSpace.height() * 0.785f + workSpace.top);
        MainPath.quadTo(workSpace.width() * 0.64f + workSpace.left,workSpace.height() * 0.7392f + workSpace.top,workSpace.width() * 0.68f + workSpace.left,workSpace.height() * 0.7192f + workSpace.top);
        MainPath.quadTo(workSpace.width() * 0.64f + workSpace.left,workSpace.height() * 0.7752f + workSpace.top,workSpace.width() * 0.645f + workSpace.left, workSpace.height() * 0.785f + workSpace.top);
        MainPath.close();
        canvas.drawPath(MainPath, greenPaint);
        canvas.drawPath(MainPath, paint);

        //Square pattern T L
        drawRect(canvas, 0.59f, 0.813f, 0.605f, 0.843f, greenPaint);
        drawRect(canvas, 0.59f, 0.813f, 0.605f, 0.843f, paint);

        //Square pattern B L
        drawRect(canvas, 0.6f, 0.828f, 0.615f, 0.858f, greenPaint);
        drawRect(canvas, 0.6f, 0.828f, 0.615f, 0.858f, paint);

        //Square pattern T R
        drawRect(canvas, 0.615f, 0.813f, 0.63f, 0.843f, greenPaint);
        drawRect(canvas, 0.615f, 0.813f, 0.63f, 0.843f, paint);

        //Square pattern B R
        drawRect(canvas, 0.625f, 0.828f, 0.64f, 0.858f, greenPaint);
        drawRect(canvas, 0.625f, 0.828f, 0.64f, 0.858f, paint);

        //Curtain L Creator
        drawCurtains(canvas, false);

        //Curtain R Creator
        drawCurtains(canvas, true);
    }

    public void setOnPercentChangeListener(OnPercentChangeListener percentChangeListener) {
       mOnPercentChangeListener = percentChangeListener;
    }
}