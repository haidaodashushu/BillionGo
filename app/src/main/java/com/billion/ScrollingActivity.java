package com.billion;

import android.app.Instrumentation;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ScrollingActivity extends AppCompatActivity implements OnClickListener {
    Button mTestBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mTestBtn = findViewById(R.id.testBtn);
        mTestBtn.setOnClickListener(this);
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tryClickScreenByInst();
            }
        });
    }

    private void tryClickScreenByInst() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int[] location = new int[2];
                mTestBtn.getLocationInWindow(location);
                int x = location[0];
                int y = location[1];
                Instrumentation inst = new Instrumentation();
                inst.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, x,y,0));
                inst.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, x,y,0));
            }
        });
        thread.start();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        FloatWidgetManager.show();
        Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show();
    }
}
