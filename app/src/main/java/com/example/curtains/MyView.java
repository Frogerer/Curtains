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
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

public class MyView extends View {
    
    //Variables
    private DisplayMetrics Metrics = getResources().getDisplayMetrics();
    private Rect MainRect = new Rect();
    private RectF MainRoundRect = new RectF();
    private Path MainPath = new Path();
    private Paint paint = new Paint(), greenPaint = new Paint(), blackPaint = new Paint();
    private  static double szerokoscDPI, wysokoscDPI;
    double MainWidth = getWidth(), MainHeight = getHeight();
    double thickness = 1;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public static float dpFromPx(final Context context, final float px) {
        return px / context.getResources().getDisplayMetrics().density;
    }

    public static void getWindowWidth (String temp) {
        Log.i("TAG","Wartosc szerokosci: " + temp);
        szerokoscDPI =  (Double.parseDouble(temp) / 100);
        Log.i("TAG","szerokoscDPI: " + szerokoscDPI);
    }

    public static void getWindowHeight (String temp) {
        Log.i("TAG","Wartosc wysokosci: " + temp);
        wysokoscDPI =  (Double.parseDouble(temp) / 100);
        Log.i("TAG","wysokoscDPI: " + wysokoscDPI);
    }

    @Override
    public void onDraw(Canvas canvas) {

        MainWidth = getWidth() * szerokoscDPI;
        MainHeight = getHeight() * wysokoscDPI;

        if (0.8 < (szerokoscDPI * wysokoscDPI) && (szerokoscDPI * wysokoscDPI) <= 1.0) {
            thickness = 2.0;
        } else if (0.6 < (szerokoscDPI * wysokoscDPI) && (szerokoscDPI * wysokoscDPI) <= 0.8) {
            thickness = 1.6;
        } else if (0.4 < (szerokoscDPI * wysokoscDPI) && (szerokoscDPI * wysokoscDPI) <= 0.6) {
            thickness = 1.2;
        } else if (0.2 < (szerokoscDPI * wysokoscDPI) && (szerokoscDPI * wysokoscDPI) <= 0.4) {
            thickness = 0.8;
        } else if (0.0 < (szerokoscDPI * wysokoscDPI) && (szerokoscDPI * wysokoscDPI) <= 0.2) {
            thickness = 0.5;
        }

        float FrameLineWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                (float) thickness, Metrics);
        invalidate();

        //Variables
        int BlackColor = Color.BLACK, GreenColor = Color.argb(255,5,170,55);

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

        Log.i("TAG","Wartość pikseli szerokości: " + MainWidth);
        Log.i("TAG", "Wartość pikseli wysokości: " + MainHeight);

        float dpWeight = dpFromPx(getContext(),getWidth());
        float dpHeight = dpFromPx(getContext(),getHeight());

        Log.i("TAG","Wartość szerokości w DIP: " + dpWeight);
        Log.i("TAG","Wartość wysokości w DIP: " + dpHeight);

        //Window Picture Elements

        //Window frame
        MainRect.set((int) (MainWidth * 0.30), (int)(MainHeight * 0.12), (int)(MainWidth * 0.70), (int)(MainHeight * 0.42));
        canvas.drawRect(MainRect, paint);

        //Window frame L
        MainRect.set((int) (MainWidth * 0.28), (int)(MainHeight * 0.12), (int)(MainWidth * 0.30), (int)(MainHeight * 0.42));
        canvas.drawRect(MainRect, paint);

        //Window frame R
        MainRect.set((int) (MainWidth * 0.70), (int)(MainHeight * 0.12), (int)(MainWidth * 0.72), (int)(MainHeight * 0.42));
        canvas.drawRect(MainRect, paint);

        //Glass T L
        MainRect.set((int) (MainWidth * 0.30), (int)(MainHeight * 0.12), (int)(MainWidth * 0.47), (int)(MainHeight * 0.245));
        canvas.drawRect(MainRect, paint);

        //Glass T R
        MainRect.set((int) (MainWidth * 0.53), (int)(MainHeight * 0.12), (int)(MainWidth * 0.70), (int)(MainHeight * 0.245));
        canvas.drawRect(MainRect, paint);

        //Glass B L
        MainRect.set((int) (MainWidth * 0.30), (int)(MainHeight * 0.26), (int)(MainWidth * 0.47), (int)(MainHeight * 0.42));
        canvas.drawRect(MainRect, paint);

        //Glass B R
        MainRect.set((int) (MainWidth * 0.53), (int)(MainHeight * 0.26), (int)(MainWidth * 0.70), (int)(MainHeight * 0.42));
        canvas.drawRect(MainRect, paint);

        //Windowsill
        MainRect.set((int) (MainWidth * 0.25), (int)(MainHeight * 0.42), (int)(MainWidth * 0.75), (int)(MainHeight * 0.445));
        canvas.drawRect(MainRect, paint);

        //Windowsill L
        MainRoundRect.set((int) (MainWidth * 0.24), (int)(MainHeight * 0.415), (int)(MainWidth * 0.25), (int)(MainHeight * 0.45));
        canvas.drawRoundRect(MainRoundRect,3,3, paint);

        //Windowsill R
        MainRoundRect.set((int) (MainWidth * 0.75), (int)(MainHeight * 0.415), (int)(MainWidth * 0.76), (int)(MainHeight * 0.45));
        canvas.drawRoundRect(MainRoundRect,3,3, paint);

        //Cornice T
        MainRoundRect.set((int) (MainWidth * 0.245), (int)(MainHeight * 0.095), (int)(MainWidth * 0.755), (int)(MainHeight * 0.10));
        canvas.drawRoundRect(MainRoundRect,8,8, paint);

        //Cornice B
        MainRoundRect.set((int) (MainWidth * 0.27), (int)(MainHeight * 0.1), (int)(MainWidth * 0.73), (int)(MainHeight * 0.12));
        canvas.drawRoundRect(MainRoundRect,3,3, paint);

        //Window line
        canvas.drawLine((int) (MainWidth * 0.50), (int) (MainHeight * 0.12), (int) (MainWidth * 0.50), (int) (MainHeight * 0.42), paint);

        //Handle L
        MainRect.set((int) (MainWidth * 0.481), (int)(MainHeight * 0.29), (int)(MainWidth * 0.489), (int)(MainHeight * 0.315));
        canvas.drawRect(MainRect, blackPaint);

        //Handle R
        MainRect.set((int) (MainWidth * 0.511), (int)(MainHeight * 0.29), (int)(MainWidth * 0.519), (int)(MainHeight * 0.315));
        canvas.drawRect(MainRect, blackPaint);

        //Handle base L
        canvas.drawCircle((int) (MainWidth * 0.486), (int) (MainHeight * 0.29), (int) (MainWidth * 0.007), paint);

        //Handle base R
        canvas.drawCircle((int) (MainWidth * 0.514), (int) (MainHeight * 0.29), (int) (MainWidth * 0.007), paint);

        //Curtain rod
        MainRoundRect.set((int) (MainWidth * 0.06), (int)(MainHeight * 0.051), (int)(MainWidth * 0.94), (int)(MainHeight * 0.063));
        canvas.drawRoundRect(MainRoundRect,7,7, paint);

        //Curtain L 1
        MainRoundRect.set((int) (MainWidth * 0.08), (int)(MainHeight * 0.063), (int)(MainWidth * 0.12), (int)(MainHeight * 0.49));
        canvas.drawRoundRect(MainRoundRect,7,5, paint);

        //Curtain L 2
        MainRoundRect.set((int) (MainWidth * 0.12), (int)(MainHeight * 0.063), (int)(MainWidth * 0.16), (int)(MainHeight * 0.48));
        canvas.drawRoundRect(MainRoundRect,1,5, paint);

        //Curtain L 3
        MainRoundRect.set((int) (MainWidth * 0.16), (int)(MainHeight * 0.063), (int)(MainWidth * 0.20), (int)(MainHeight * 0.49));
        canvas.drawRoundRect(MainRoundRect,7,5, paint);

        //Curtain R 1
        MainRoundRect.set((int) (MainWidth * 0.88), (int)(MainHeight * 0.063), (int)(MainWidth * 0.92), (int)(MainHeight * 0.49));
        canvas.drawRoundRect(MainRoundRect,7,5, paint);

        //Curtain R 2
        MainRoundRect.set((int) (MainWidth * 0.84), (int)(MainHeight * 0.063), (int)(MainWidth * 0.88), (int)(MainHeight * 0.48));
        canvas.drawRoundRect(MainRoundRect,1,5, paint);

        //Curtain R 3
        MainRoundRect.set((int) (MainWidth * 0.80), (int)(MainHeight * 0.063), (int)(MainWidth * 0.84), (int)(MainHeight * 0.49));
        canvas.drawRoundRect(MainRoundRect,7,5, paint);

        //Flowerpot
        MainRect.set((int) (MainWidth * 0.58), (int)(MainHeight * 0.38), (int)(MainWidth * 0.65), (int)(MainHeight * 0.415));
        canvas.drawRect(MainRect, paint);

        //Flowerpot stand
        MainRoundRect.set((int) (MainWidth * 0.57), (int)(MainHeight * 0.415), (int)(MainWidth * 0.66), (int)(MainHeight * 0.42));
        canvas.drawRoundRect(MainRoundRect,3,3, paint);

        //Flowerpot frame
        MainRoundRect.set((int) (MainWidth * 0.57), (int)(MainHeight * 0.373), (int)(MainWidth * 0.66), (int)(MainHeight * 0.38));
        canvas.drawRoundRect(MainRoundRect,7,7, paint);

        //Leaf L L
        MainPath.reset();
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
        MainRect.set((int) (MainWidth * 0.59), (int)(MainHeight * 0.3865), (int)(MainWidth * 0.605), (int)(MainHeight * 0.4015));
        canvas.drawRect(MainRect, greenPaint);
        canvas.drawRect(MainRect, paint);

        //Square pattern B L
        MainRect.set((int) (MainWidth * 0.6), (int)(MainHeight * 0.394), (int)(MainWidth * 0.615), (int)(MainHeight * 0.409));
        canvas.drawRect(MainRect, greenPaint);
        canvas.drawRect(MainRect, paint);

        //Square pattern T R
        MainRect.set((int) (MainWidth * 0.615), (int)(MainHeight * 0.3865), (int)(MainWidth * 0.63), (int)(MainHeight * 0.4015));
        canvas.drawRect(MainRect, greenPaint);
        canvas.drawRect(MainRect, paint);

        //Square pattern B R
        MainRect.set((int) (MainWidth * 0.625), (int)(MainHeight * 0.394), (int)(MainWidth * 0.64), (int)(MainHeight * 0.409));
        canvas.drawRect(MainRect, greenPaint);
        canvas.drawRect(MainRect, paint);
    }
}
