package com.billion;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;
import com.billion.service.BillionGoService;

public class ScrollingActivity extends AppCompatActivity implements OnClickListener {
    Button mCollectButton;
    TextView mShowPointView;
    NestedScrollView mScrollContent;
    PointCollectManager mPointCollectManager;
    boolean mCollectOpen = false;
    private FloatingActionButton mBillionGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mPointCollectManager = new PointCollectManager();
        mScrollContent = findViewById(R.id.contentId);
        mShowPointView = findViewById(R.id.showPointView);
        mScrollContent.setBackgroundColor(Color.WHITE);
        mCollectButton = findViewById(R.id.collectionButton);
        mCollectButton.setOnClickListener(this);
        mBillionGo = findViewById(R.id.fab);
        mBillionGo.setOnClickListener(this);
        updateBillionGoButton();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private OnTouchListener collectPointListener() {
        return new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (mPointCollectManager.collectPoint(event)) {
                    Point point = mPointCollectManager.getCollectPoint();
                    mShowPointView.setText(point.x + "," + point.y);
                    updateBillionGoButton();
                }
                return false;
            }
        };
    }

    private void startService() {
        Intent intent = new Intent();
        intent.setClass(this, BillionGoService.class);
        startService(intent);
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
        if (mCollectButton == v) {
            handleCollectPoint();
        } else if (mBillionGo == v) {
            startService();
        }
    }

    private void handleCollectPoint() {
        if (!mCollectOpen) {
            mScrollContent.setBackgroundColor(Color.TRANSPARENT);
            mCollectButton.setText(R.string.stop_collect);
            mScrollContent.setOnTouchListener(collectPointListener());
            mCollectOpen = true;
        } else {
            mCollectOpen = false;
            mScrollContent.setBackgroundColor(Color.WHITE);
            mScrollContent.setOnTouchListener(null);
            mCollectButton.setText(R.string.start_collect);
        }
    }

    private void updateBillionGoButton() {
        Point point = mPointCollectManager.getCollectPoint();
        if (point == null) {
            mBillionGo.setEnabled(false);
        } else {
            mBillionGo.setEnabled(true);
            mBillionGo.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_menu_upload_you_tube));
        }
        //FloatWidgetManager.show();
        //Snackbar.make(mCollectButton, "Replace with your own action", Snackbar.LENGTH_LONG)
        //    .setAction("Action", null).show();
    }
}
