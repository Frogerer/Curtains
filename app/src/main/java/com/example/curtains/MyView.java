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
    private Paint paint = new Paint(), greenPaint = new Paint(), blackPaint = new Paint();
    private Rect MainRect;
    private RectF MainRoundRect;
    private Path MainPath = new Path();

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onDraw(Canvas canvas) {

        //Variables
        int BlackColor = Color.BLACK, GreenColor = Color.argb(255,5,170,55);
        double MainWidth, MainHeight, percentWidth, percentHeight, marginLeft, marginTop;
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

        ///////good/////////

        //Ramka okna
        marginLeft = MainWidth * 0.30;
        marginTop = MainHeight * 0.12;
        percentWidth = MainWidth * 0.70;
        percentHeight = MainHeight * 0.42;
        newRect(marginLeft, marginTop, percentWidth, percentHeight);
        canvas.drawRect(MainRect, paint);

        //Ramka okna L
        marginLeft = MainWidth * 0.28;
        marginTop = MainHeight * 0.12;
        percentWidth = MainWidth * 0.30;
        percentHeight = MainHeight * 0.42;
        newRect(marginLeft, marginTop, percentWidth, percentHeight);
        canvas.drawRect(MainRect, paint);

        //Ramka okna P
        marginLeft = MainWidth * 0.70;
        marginTop = MainHeight * 0.12;
        percentWidth = MainWidth * 0.72;
        percentHeight = MainHeight * 0.42;
        newRect(marginLeft, marginTop, percentWidth, percentHeight);
        canvas.drawRect(MainRect, paint);

        //GL Szyba
        marginLeft = MainWidth * 0.30;
        marginTop = MainHeight * 0.12;
        percentWidth = MainWidth * 0.47;
        percentHeight = MainHeight * 0.245;
        newRect(marginLeft, marginTop, percentWidth, percentHeight);
        canvas.drawRect(MainRect, paint);

        //GP Szyba
        marginLeft = MainWidth * 0.53;
        marginTop = MainHeight * 0.12;
        percentWidth = MainWidth * 0.70;
        percentHeight = MainHeight * 0.245;
        newRect(marginLeft, marginTop, percentWidth, percentHeight);
        canvas.drawRect(MainRect, paint);

        //DL Szyba
        marginLeft = MainWidth * 0.30;
        marginTop = MainHeight * 0.26;
        percentWidth = MainWidth * 0.47;
        percentHeight = MainHeight * 0.42;
        newRect(marginLeft, marginTop, percentWidth, percentHeight);
        canvas.drawRect(MainRect, paint);

        //DP Szyba
        marginLeft = MainWidth * 0.53;
        marginTop = MainHeight * 0.26;
        percentWidth = MainWidth * 0.70;
        percentHeight = MainHeight * 0.42;
        newRect(marginLeft, marginTop, percentWidth, percentHeight);
        canvas.drawRect(MainRect, paint);

        //Parapet
        marginLeft = MainWidth * 0.25;
        marginTop = MainHeight * 0.42;
        percentWidth = MainWidth * 0.75;
        percentHeight = MainHeight * 0.445;
        newRect(marginLeft, marginTop, percentWidth, percentHeight);
        canvas.drawRect(MainRect, paint);

        //Parapet L
        marginLeft = MainWidth * 0.24;
        marginTop = MainHeight * 0.415;
        percentWidth = MainWidth * 0.25;
        percentHeight = MainHeight * 0.45;
        newRoundRect(marginLeft, marginTop, percentWidth, percentHeight);
        canvas.drawRoundRect(MainRoundRect,3,3, paint);

        //Parapet P
        marginLeft = MainWidth * 0.75;
        marginTop = MainHeight * 0.415;
        percentWidth = MainWidth * 0.76;
        percentHeight = MainHeight * 0.45;
        newRoundRect(marginLeft, marginTop, percentWidth, percentHeight);
        canvas.drawRoundRect(MainRoundRect,3,3, paint);

        //Gzyms D
        marginLeft = MainWidth * 0.27;
        marginTop = MainHeight * 0.1;
        percentWidth = MainWidth * 0.73;
        percentHeight = MainHeight * 0.12;
        newRoundRect(marginLeft, marginTop, percentWidth, percentHeight);
        canvas.drawRoundRect(MainRoundRect,3,3, paint);

        //Gzyms G
        marginLeft = MainWidth * 0.245;
        marginTop = MainHeight * 0.095;
        percentWidth = MainWidth * 0.755;
        percentHeight = MainHeight * 0.10;
        newRoundRect(marginLeft, marginTop, percentWidth, percentHeight);
        canvas.drawRoundRect(MainRoundRect,8,8, paint);

        //Linia okna
        marginLeft = MainWidth * 0.50;
        marginTop = MainHeight * 0.12;
        percentWidth = MainWidth * 0.50;
        percentHeight = MainHeight * 0.42;
        canvas.drawLine((int) marginLeft, (int) marginTop, (int) percentWidth, (int) percentHeight, paint);

        //Klamka podstawa L
        marginLeft = MainWidth * 0.486;
        marginTop = MainHeight * 0.29;
        percentWidth = MainWidth * 0.007;
        canvas.drawCircle((int) marginLeft, (int) marginTop, (int) percentWidth, paint);

        //Klamka podstawa R
        marginLeft = MainWidth * 0.514;
        marginTop = MainHeight * 0.29;
        percentWidth = MainWidth * 0.007;
        canvas.drawCircle((int) marginLeft, (int) marginTop, (int) percentWidth, paint);

        //Klamka uchwyt L
        marginLeft = MainWidth * 0.483;
        marginTop = MainHeight * 0.29;
        percentWidth = MainWidth * 0.488;
        percentHeight = MainHeight * 0.315;
        newRect(marginLeft, marginTop, percentWidth, percentHeight);
        canvas.drawRect(MainRect, blackPaint);

        //Klamka uchwyt R
        marginLeft = MainWidth * 0.512;
        marginTop = MainHeight * 0.29;
        percentWidth = MainWidth * 0.517;
        percentHeight = MainHeight * 0.315;
        newRect(marginLeft, marginTop, percentWidth, percentHeight);
        canvas.drawRect(MainRect, blackPaint);

        //Karnisz
        marginLeft = MainWidth * 0.06;
        marginTop = MainHeight * 0.051;
        percentWidth = MainWidth * 0.94;
        percentHeight = MainHeight * 0.063;
        newRoundRect(marginLeft, marginTop, percentWidth, percentHeight);
        canvas.drawRoundRect(MainRoundRect,7,7, paint);

        //Zasłona Lewa 1
        marginLeft = MainWidth * 0.08;
        marginTop = MainHeight * 0.063;
        percentWidth = MainWidth * 0.12;
        percentHeight = MainHeight * 0.47;
        newRoundRect(marginLeft, marginTop, percentWidth, percentHeight);
        canvas.drawRoundRect(MainRoundRect,7,5, paint);

        //Zasłona Lewa 2
        marginLeft = MainWidth * 0.12;
        marginTop = MainHeight * 0.063;
        percentWidth = MainWidth * 0.16;
        percentHeight = MainHeight * 0.465;
        newRoundRect(marginLeft, marginTop, percentWidth, percentHeight);
        canvas.drawRoundRect(MainRoundRect,1,5, paint);

        //Zasłona Lewa 3
        marginLeft = MainWidth * 0.16;
        marginTop = MainHeight * 0.063;
        percentWidth = MainWidth * 0.20;
        percentHeight = MainHeight * 0.47;
        newRoundRect(marginLeft, marginTop, percentWidth, percentHeight);
        canvas.drawRoundRect(MainRoundRect,7,5, paint);

        //Zasłona Prawa 1
        marginLeft = MainWidth * 0.88;
        marginTop = MainHeight * 0.063;
        percentWidth = MainWidth * 0.92;
        percentHeight = MainHeight * 0.47;
        newRoundRect(marginLeft, marginTop, percentWidth, percentHeight);
        canvas.drawRoundRect(MainRoundRect,7,5, paint);

        //Zasłona Prawa 2
        marginLeft = MainWidth * 0.84;
        marginTop = MainHeight * 0.063;
        percentWidth = MainWidth * 0.88;
        percentHeight = MainHeight * 0.465;
        newRoundRect(marginLeft, marginTop, percentWidth, percentHeight);
        canvas.drawRoundRect(MainRoundRect,1,5, paint);

        //Zasłona Prawa 3
        marginLeft = MainWidth * 0.80;
        marginTop = MainHeight * 0.063;
        percentWidth = MainWidth * 0.84;
        percentHeight = MainHeight * 0.47;
        newRoundRect(marginLeft, marginTop, percentWidth, percentHeight);
        canvas.drawRoundRect(MainRoundRect,7,5, paint);

        //Doniczka
        marginLeft = MainWidth * 0.58;
        marginTop = MainHeight * 0.38;
        percentWidth = MainWidth * 0.65;
        percentHeight = MainHeight * 0.415;
        newRect(marginLeft, marginTop, percentWidth, percentHeight);
        canvas.drawRect(MainRect, paint);

        //Podstawka
        marginLeft = MainWidth * 0.57;
        marginTop = MainHeight * 0.415;
        percentWidth = MainWidth * 0.66;
        percentHeight = MainHeight * 0.42;
        newRoundRect(marginLeft, marginTop, percentWidth, percentHeight);
        canvas.drawRoundRect(MainRoundRect,3,3, paint);

        //Obramowka
        marginLeft = MainWidth * 0.57;
        marginTop = MainHeight * 0.373;
        percentWidth = MainWidth * 0.66;
        percentHeight = MainHeight * 0.38;
        newRoundRect(marginLeft, marginTop, percentWidth, percentHeight);
        canvas.drawRoundRect(MainRoundRect,7,7, paint);

        //Kwiat LL
        marginLeft = MainWidth * 0.588;
        marginTop = MainHeight * 0.3729;
        MainPath.moveTo((float) marginLeft, (float) marginTop);
        MainPath.quadTo((float) (MainWidth * 0.575),(float) (MainHeight * 0.35),(float) (MainWidth * 0.555),(float) (MainHeight * 0.34));
        MainPath.quadTo((float) (MainWidth * 0.59),(float) (MainHeight * 0.35),(float) (MainWidth * 0.605), (float) marginTop);
        MainPath.close();
        canvas.drawPath(MainPath, greenPaint);
        canvas.drawPath(MainPath, paint);

        //Kwiat SL
        marginLeft = MainWidth * 0.605;
        marginTop = MainHeight * 0.3729;

        MainPath.moveTo((float) marginLeft, (float) marginTop);
        MainPath.quadTo((float) (MainWidth * 0.6),(float) (MainHeight * 0.365),(float) (MainWidth * 0.56),(float) (MainHeight * 0.325));
        MainPath.quadTo((float) (MainWidth * 0.59),(float) (MainHeight * 0.335),(float) (MainWidth * 0.62), (float) marginTop);
        MainPath.close();
        canvas.drawPath(MainPath, greenPaint);
        canvas.drawPath(MainPath, paint);

        //Kwiat SP
        marginLeft = MainWidth * 0.6095;
        marginTop = MainHeight * 0.36;
        MainPath.moveTo((float) marginLeft, (float) marginTop);
        MainPath.quadTo((float) (MainWidth * 0.62),(float) (MainHeight * 0.33),(float) (MainWidth * 0.65),(float) (MainHeight * 0.31));
        MainPath.quadTo((float) (MainWidth * 0.63),(float) (MainHeight * 0.35),(float) (MainWidth * 0.625), (float) (MainHeight * 0.3729));
        MainPath.lineTo((float) (MainWidth * 0.618), (float) (MainHeight * 0.3729));
        MainPath.close();
        canvas.drawPath(MainPath, greenPaint);
        canvas.drawPath(MainPath, paint);

        //Kwiat PP
        marginLeft = MainWidth * 0.622;
        marginTop = MainHeight * 0.3729;
        MainPath.moveTo((float) marginLeft, (float) marginTop);
        MainPath.quadTo((float) (MainWidth * 0.64),(float) (MainHeight * 0.35),(float) (MainWidth * 0.68),(float) (MainHeight * 0.34));
        MainPath.quadTo((float) (MainWidth * 0.64),(float) (MainHeight * 0.368),(float) (MainWidth * 0.645), (float) (MainHeight * 0.3729));
        MainPath.close();
        canvas.drawPath(MainPath, greenPaint);
        canvas.drawPath(MainPath, paint);

        //Kwadrat Wzor GL
        marginLeft = MainWidth * 0.59;
        marginTop = MainHeight * 0.385;
        percentWidth = MainWidth * 0.605;
        percentHeight = MainHeight * 0.4;
        newRect(marginLeft, marginTop, percentWidth, percentHeight);
        canvas.drawRect(MainRect, greenPaint);
        canvas.drawRect(MainRect, paint);

        //Kwadrat Wzor DL
        marginLeft = MainWidth * 0.6;
        marginTop = MainHeight * 0.3925;
        percentWidth = MainWidth * 0.615;
        percentHeight = MainHeight * 0.4075;
        newRect(marginLeft, marginTop, percentWidth, percentHeight);
        canvas.drawRect(MainRect, greenPaint);
        canvas.drawRect(MainRect, paint);

        //Kwadrat Wzor GP
        marginLeft = MainWidth * 0.615;
        marginTop = MainHeight * 0.385;
        percentWidth = MainWidth * 0.63;
        percentHeight = MainHeight * 0.4;
        newRect(marginLeft, marginTop, percentWidth, percentHeight);
        canvas.drawRect(MainRect, greenPaint);
        canvas.drawRect(MainRect, paint);

        //Kwadrat Wzor DL
        marginLeft = MainWidth * 0.625;
        marginTop = MainHeight * 0.3925;
        percentWidth = MainWidth * 0.64;
        percentHeight = MainHeight * 0.4075;
        newRect(marginLeft, marginTop, percentWidth, percentHeight);
        canvas.drawRect(MainRect, greenPaint);
        canvas.drawRect(MainRect, paint);
    }

    private void newRect (double left, double top, double right, double bottom) {
        MainRect = new Rect((int) left, (int) top, (int) right, (int) bottom);
    }

    private void newRoundRect (double left, double top, double right, double bottom) {
        MainRoundRect = new RectF((int) left, (int) top, (int) right, (int) bottom);
    }
}
