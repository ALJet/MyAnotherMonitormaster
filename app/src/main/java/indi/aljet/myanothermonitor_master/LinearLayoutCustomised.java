package indi.aljet.myanothermonitor_master;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by PC-LJL on 2018/5/8.
 */

public class LinearLayoutCustomised extends LinearLayout {

    private boolean touchEventsDisabled = true;


    public LinearLayoutCustomised(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return touchEventsDisabled;
    }

    public void interceptChildTouchEvents(boolean b){
        touchEventsDisabled   = b;

    }
}
