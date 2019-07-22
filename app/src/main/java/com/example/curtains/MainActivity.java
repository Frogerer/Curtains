package com.example.curtains;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tView, tView2;
    public String tempString;

    public static void getSendValue(String sendValue) {
        MyView.getWindowWidth(sendValue);
    }

    public static void getSendValue2(String sendValue) {
        MyView.getWindowHeight(sendValue);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SeekBar sBar = (SeekBar) findViewById(R.id.seekBar);
        SeekBar sBar2 = (SeekBar) findViewById(R.id.seekBar5);
        tView = (TextView) findViewById(R.id.textView);
        tView2 = (TextView) findViewById(R.id.textView10);
        tempString = sBar.getProgress() + "/" + sBar.getMax();
        tView.setText(tempString);
        sBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int pval = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pval = progress;
        }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tempString = pval + "/" +seekBar.getMax();
                tView.setText(tempString);
                Log.i("TAG","Wartość z bla1 MA: " + Integer.toString(pval));
                getSendValue(Integer.toString(pval));
            }
        });

        tempString = sBar2.getProgress() + "/" + sBar2.getMax();
        tView2.setText(tempString);
        sBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int pval2 = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pval2 = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tempString = pval2 + "/" +seekBar.getMax();
                tView2.setText(tempString);
                Log.i("TAG","Wartość z bla2 MA: " + Integer.toString(pval2));
                getSendValue2(Integer.toString(pval2));
            }
        });
    }
}
