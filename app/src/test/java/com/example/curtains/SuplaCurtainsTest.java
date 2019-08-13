package com.example.curtains;

import android.app.Instrumentation;

import org.junit.Test;

import static org.junit.Assert.*;

public class SuplaCurtainsTest extends Instrumentation{

    private SuplaCurtains curtains = new SuplaCurtains(getContext());

    @Test
    public void testLineColorDefaultValue()  {
        assertEquals(0x000000, curtains.getLineColor());
    }

    @Test
    public void testLineColorGetterSetter() {
        curtains.setLineColor(0x123123);
        assertEquals(0x123123, curtains.getLineColor());
    }

    @Test
    public void testFillColor1DefaultValue() {
        assertEquals(0x05AA37, curtains.getFillColor1());
    }

    @Test
    public void testFillColor1GetterSetter() {
        curtains.setFillColor1(0x123123);
        assertEquals(0x123123, curtains.getFillColor1());
    }

    @Test
    public void testFillColor2DefaultValue() {
        assertEquals(0x049629, curtains.getFillColor2());
    }

    @Test
    public void testFillColor2GetterSetter() {
        curtains.setFillColor2(0x123123);
        assertEquals(0x123123, curtains.getFillColor2());
    }

    @Test
    public void getPercent() {
        assertEquals(0, curtains.getPercent(), 0);
    }

    @Test
    public void testPercentGetterSetter() {
        curtains.setPercent(75f);
        assertEquals(75f, curtains.getPercent(), 0);
    }

    @Test
    public void testPercentMinValue() {
        for (int i  = 0; i>=-1000; i--) {
            curtains.setPercent(i);
            assertEquals(0, curtains.getPercent(), 0);
        }
    }

    @Test
    public void testPercentMaxValue() {
        for (int i = 100; i <= 1000; i++) {
            curtains.setPercent(i);
            assertEquals(100, curtains.getPercent(), 0);
        }
    }
}