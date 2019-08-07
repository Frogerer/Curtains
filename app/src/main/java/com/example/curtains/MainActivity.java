package com.example.curtains;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MyView.OnPercentChangeListener {

    private String tempString;
    private MyView cView;
    private TextView tView, tView2, tView3;
    private SeekBar sBar, sBar2, sBar3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cView = (MyView) findViewById(R.id.cView);
        cView.setOnPercentChangeListener(this);

        sBar = (SeekBar) findViewById(R.id.seekBar1);
        sBar2 = (SeekBar) findViewById(R.id.seekBar2);
        sBar3 = (SeekBar) findViewById(R.id.seekBar3);
        tView = (TextView) findViewById(R.id.textView1);
        tView2 = (TextView) findViewById(R.id.textView2);
        tView3 = (TextView) findViewById(R.id.textView3);

        cView.setLineColor(Color.BLACK);
        cView.setFillColor1(Color.argb(255,5,170,55));
        cView.setFillColor2(Color.argb(255,4,150,40));

        tempString = sBar.getProgress() + "/" + sBar.getMax();
        tView.setText(tempString);
        sBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ViewGroup.LayoutParams lp = cView.getLayoutParams();
                cView.setLayoutParams(new RelativeLayout.LayoutParams(progress,lp.height));
                tempString = sBar.getProgress() + "/" + sBar.getMax();
                tView.setText(tempString);
        }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        tempString = sBar2.getProgress() + "/" + sBar2.getMax();
        tView2.setText(tempString);
        sBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ViewGroup.LayoutParams lp = cView.getLayoutParams();
                cView.setLayoutParams(new RelativeLayout.LayoutParams(lp.width, progress));
                tempString = sBar2.getProgress() + "/" + sBar2.getMax();
                tView2.setText(tempString);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        tempString = sBar3.getProgress() + "/" + sBar3.getMax();
        tView3.setText(tempString);
        sBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                cView.setPercent(progress);
                tempString = sBar3.getProgress() + "/" + sBar3.getMax();
                tView3.setText(tempString);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
    }

    @Override
    public void onPercentChangeing(MyView rs, float percent) {
        cView.setPercent((int) percent);
        sBar3.setProgress((int)percent);
        tempString = sBar3.getProgress() + "/" + sBar3.getMax();
        tView3.setText(tempString);
    }
}
