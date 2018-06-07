package indi.aljet.myanothermonitor_master;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.util.Linkify;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by PC-LJL on 2018/5/9.
 */

public class ActivityHelp extends AppCompatActivity {

    private BroadcastReceiver receiverFinish = new
            BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    finish();
                }
            };


    @SuppressLint("InlinedApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        final Resources res = getResources();

        if(Build.VERSION.SDK_INT >= 19){
            getWindow().getDecorView()
                    .setSystemUiVisibility(View
                    .SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            float sSw = res.getConfiguration()
                    .smallestScreenWidthDp,
                    sD = res.getDisplayMetrics()
                            .density;
            LinearLayout l = (LinearLayout)findViewById(R
            .id.LParent);
            int statusBarHeight = res.getDimensionPixelSize
                    (res.getIdentifier(C.sbh,C.dimen,
                            C.android));
            int navigationBarHeight = 0;
            if(!ViewConfiguration.get(this).hasPermanentMenuKey()
                    && !KeyCharacterMap.deviceHasKey(KeyEvent
            .KEYCODE_HOME) && (res.getConfiguration()
            .orientation == Configuration.ORIENTATION_PORTRAIT
            || sSw > 560)){
                getWindow().getDecorView()
                        .setSystemUiVisibility(View
                        .SYSTEM_UI_FLAG_HIDE_NAVIGATION);
                navigationBarHeight = res.getDimensionPixelSize
                        (res.getIdentifier(C.nbh,
                                C.dimen,C.android));
                if(navigationBarHeight == 0){
                    navigationBarHeight =  (int) (48 * sD);
                }
                FrameLayout nb = (FrameLayout)findViewById(R
                .id.LNavigationBar);
                nb.setVisibility(View.VISIBLE);
                ((FrameLayout.LayoutParams)nb
                .getLayoutParams()).height =
                        navigationBarHeight;
            }
            l.setPadding(0,statusBarHeight,0
            ,navigationBarHeight);
        }

        Linkify.addLinks((TextView)findViewById(R.id.TVHelpText),
                Linkify.WEB_URLS);
    }


    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(receiverFinish,new IntentFilter(
                C.actionFinishActivity));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiverFinish);
    }
}
