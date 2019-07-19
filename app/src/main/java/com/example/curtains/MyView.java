package com.example.curtains;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

public class MyView extends View {

    //Variables
    private DisplayMetrics Metrics = getResources().getDisplayMetrics();
    private Rect MainRect;
    private RectF MainRoundRect;
    private Path MainPath = new Path();
    private Paint paint = new Paint(), greenPaint = new Paint(), blackPaint = new Paint();

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //My methods
    private void newRect (double width, double height, double left, double top, double right, double bottom) {
        MainRect = new Rect((int) (width * left), (int) (height * top), (int) (width * right), (int) (height * bottom));
    }

    private void newRoundRect (double width, double height, double left, double top, double right, double bottom) {
        MainRoundRect = new RectF((int) (width * left), (int) (height * top), (int) (width * right), (int) (height * bottom));
    }

    @Override
    public void onDraw(Canvas canvas) {

        //Variables
        double MainWidth, MainHeight;
        int BlackColor = Color.BLACK, GreenColor = Color.argb(255,5,170,55);
        float FrameLineWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                (float) 0.5, Metrics);

        //Paint Stroke Black
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(FrameLineWidth);
        paint.setColor(BlackColor);

        //Paint Fill Green
        greenPaint.setStyle(Paint.Style.FILL);
        greenPaint.setColor(GreenColor);

        //Paint Fill Green
        blackPaint.setStyle(Paint.Style.FILL);
        blackPaint.setColor(BlackColor);

        //Size of the screen
        MainWidth = getWidth();
        MainHeight = getHeight();

        //Window Picture Elements

        //Window frame
        newRect(MainWidth, MainHeight, 0.30, 0.12, 0.70, 0.42);
        canvas.drawRect(MainRect, paint);

        //Window frame L
        newRect(MainWidth, MainHeight, 0.28, 0.12, 0.30, 0.42);
        canvas.drawRect(MainRect, paint);

        //Window frame R
        newRect(MainWidth, MainHeight, 0.70, 0.12, 0.72, 0.42);
        canvas.drawRect(MainRect, paint);

        //Glass T L
        newRect(MainWidth, MainHeight, 0.30, 0.12, 0.47, 0.245);
        canvas.drawRect(MainRect, paint);

        //Glass T R
        newRect(MainWidth, MainHeight, 0.53, 0.12, 0.70, 0.245);
        canvas.drawRect(MainRect, paint);

        //Glass B L
        newRect(MainWidth, MainHeight, 0.30, 0.26, 0.47, 0.42);
        canvas.drawRect(MainRect, paint);

        //Glass B R
        newRect(MainWidth, MainHeight, 0.53, 0.26, 0.70, 0.42);
        canvas.drawRect(MainRect, paint);

        //Windowsill
        newRect(MainWidth, MainHeight, 0.25, 0.42, 0.75, 0.445);
        canvas.drawRect(MainRect, paint);

        //Windowsill L
        newRoundRect(MainWidth, MainHeight, 0.24, 0.415, 0.25, 0.45);
        canvas.drawRoundRect(MainRoundRect,3,3, paint);

        //Windowsill R
        newRoundRect(MainWidth, MainHeight, 0.75, 0.415, 0.76, 0.45);
        canvas.drawRoundRect(MainRoundRect,3,3, paint);

        //Cornice T
        newRoundRect(MainWidth, MainHeight, 0.245, 0.095, 0.755, 0.10);
        canvas.drawRoundRect(MainRoundRect,8,8, paint);

        //Cornice B
        newRoundRect(MainWidth, MainHeight, 0.27, 0.1, 0.73, 0.12);
        canvas.drawRoundRect(MainRoundRect,3,3, paint);

        //Window line
        canvas.drawLine((int) (MainWidth * 0.50), (int) (MainHeight * 0.12), (int) (MainWidth * 0.50), (int) (MainHeight * 0.42), paint);

        //Handle L
        newRect(MainWidth, MainHeight, 0.481, 0.29, 0.489, 0.315);
        canvas.drawRect(MainRect, blackPaint);

        //Handle R
        newRect(MainWidth, MainHeight, 0.511, 0.29, 0.519, 0.315);
        canvas.drawRect(MainRect, blackPaint);

        //Handle base L
        canvas.drawCircle((int) (MainWidth * 0.486), (int) (MainHeight * 0.29), (int) (MainWidth * 0.007), paint);

        //Handle base R
        canvas.drawCircle((int) (MainWidth * 0.514), (int) (MainHeight * 0.29), (int) (MainWidth * 0.007), paint);

        //Curtain rod
        newRoundRect(MainWidth, MainHeight, 0.06, 0.051, 0.94, 0.063);
        canvas.drawRoundRect(MainRoundRect,7,7, paint);

        //Curtain L 1
        newRoundRect(MainWidth, MainHeight, 0.08, 0.063, 0.12, 0.49);
        canvas.drawRoundRect(MainRoundRect,7,5, paint);

        //Curtain L 2
        newRoundRect(MainWidth, MainHeight, 0.12, 0.063, 0.16, 0.48);
        canvas.drawRoundRect(MainRoundRect,1,5, paint);

        //Curtain L 3
        newRoundRect(MainWidth, MainHeight, 0.16, 0.063, 0.20, 0.49);
        canvas.drawRoundRect(MainRoundRect,7,5, paint);

        //Curtain R 1
        newRoundRect(MainWidth, MainHeight, 0.88, 0.063, 0.92, 0.49);
        canvas.drawRoundRect(MainRoundRect,7,5, paint);

        //Curtain R 2
        newRoundRect(MainWidth, MainHeight, 0.84, 0.063, 0.88, 0.48);
        canvas.drawRoundRect(MainRoundRect,1,5, paint);

        //Curtain R 3
        newRoundRect(MainWidth, MainHeight, 0.80, 0.063, 0.84, 0.49);
        canvas.drawRoundRect(MainRoundRect,7,5, paint);

        //Flowerpot
        newRect(MainWidth, MainHeight, 0.58, 0.38, 0.65, 0.415);
        canvas.drawRect(MainRect, paint);

        //Flowerpot stand
        newRoundRect(MainWidth, MainHeight, 0.57, 0.415, 0.66, 0.42);
        canvas.drawRoundRect(MainRoundRect,3,3, paint);

        //Flowerpot frame
        newRoundRect(MainWidth, MainHeight, 0.57, 0.373, 0.66, 0.38);
        canvas.drawRoundRect(MainRoundRect,7,7, paint);

        //Leaf L L
        MainPath.moveTo((float) (MainWidth * 0.588), (float) (MainHeight * 0.3729));
        MainPath.quadTo((float) (MainWidth * 0.575),(float) (MainHeight * 0.35),(float) (MainWidth * 0.555),(float) (MainHeight * 0.34));
        MainPath.quadTo((float) (MainWidth * 0.59),(float) (MainHeight * 0.35),(float) (MainWidth * 0.605), (float) (MainHeight * 0.3729));
        MainPath.close();
        canvas.drawPath(MainPath, greenPaint);
        canvas.drawPath(MainPath, paint);

        //Leaf C L
        MainPath.moveTo((float) (MainWidth * 0.605), (float) (MainHeight * 0.3729));
        MainPath.quadTo((float) (MainWidth * 0.6),(float) (MainHeight * 0.365),(float) (MainWidth * 0.56),(float) (MainHeight * 0.325));
        MainPath.quadTo((float) (MainWidth * 0.59),(float) (MainHeight * 0.335),(float) (MainWidth * 0.62), (float) (MainHeight * 0.3729));
        MainPath.close();
        canvas.drawPath(MainPath, greenPaint);
        canvas.drawPath(MainPath, paint);

        //Leaf C R
        MainPath.moveTo((float) (MainWidth * 0.6095), (float) (MainHeight * 0.36));
        MainPath.quadTo((float) (MainWidth * 0.62),(float) (MainHeight * 0.33),(float) (MainWidth * 0.65),(float) (MainHeight * 0.31));
        MainPath.quadTo((float) (MainWidth * 0.63),(float) (MainHeight * 0.35),(float) (MainWidth * 0.625), (float) (MainHeight * 0.3729));
        MainPath.lineTo((float) (MainWidth * 0.618), (float) (MainHeight * 0.3729));
        MainPath.close();
        canvas.drawPath(MainPath, greenPaint);
        canvas.drawPath(MainPath, paint);

        //Leaf R R
        MainPath.moveTo((float) (MainWidth * 0.622), (float) (MainHeight * 0.3729));
        MainPath.quadTo((float) (MainWidth * 0.64),(float) (MainHeight * 0.35),(float) (MainWidth * 0.68),(float) (MainHeight * 0.34));
        MainPath.quadTo((float) (MainWidth * 0.64),(float) (MainHeight * 0.368),(float) (MainWidth * 0.645), (float) (MainHeight * 0.3729));
        MainPath.close();
        canvas.drawPath(MainPath, greenPaint);
        canvas.drawPath(MainPath, paint);

        //Square pattern T L
        newRect(MainWidth, MainHeight, 0.59, 0.3865, 0.605, 0.4015);
        canvas.drawRect(MainRect, greenPaint);
        canvas.drawRect(MainRect, paint);

        //Square pattern B L
        newRect(MainWidth, MainHeight, 0.6, 0.394, 0.615, 0.409);
        canvas.drawRect(MainRect, greenPaint);
        canvas.drawRect(MainRect, paint);

        //Square pattern T R
        newRect(MainWidth, MainHeight, 0.615, 0.3865, 0.63, 0.4015);
        canvas.drawRect(MainRect, greenPaint);
        canvas.drawRect(MainRect, paint);

        //Square pattern B R
        newRect(MainWidth, MainHeight, 0.625, 0.394, 0.64, 0.409);
        canvas.drawRect(MainRect, greenPaint);
        canvas.drawRect(MainRect, paint);
    }
}
