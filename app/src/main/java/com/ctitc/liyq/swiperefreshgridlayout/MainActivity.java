package com.ctitc.liyq.swiperefreshgridlayout;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout mHeader;
    private CustomHorizontalScrollView mHorizontalScrollView;
    private ListView mListView;
    private SimpleLineAdapter mAdapter;
    private LayoutInflater mInflater;
    private MyTouchListener mTouchListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInflater = LayoutInflater.from(this);
        mTouchListener = new MyTouchListener();
        mHeader = (RelativeLayout) findViewById(R.id.header);
        //使得mHorizontalScrollView可以滚动
        mHeader.setClickable(true);
        mHorizontalScrollView = (CustomHorizontalScrollView) mHeader.findViewById(R.id.horizontalScrollView);
        mHeader.setBackgroundColor(Color.parseColor("#d7d7d7"));
        //将touch事件传递给HorizontalScrollView
        mHeader.setOnTouchListener(mTouchListener);
        mListView = (ListView) findViewById(R.id.listview);
        mListView.setOnTouchListener(mTouchListener);
        mAdapter = new SimpleLineAdapter();
        mListView.setAdapter(mAdapter);
    }

    private class MyTouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            mHorizontalScrollView.dispatchTouchEvent(event);
            return false;
        }
    }

    public class SimpleLineAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 18;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (null == convertView) {
                convertView = mInflater.inflate(R.layout.scroll_line_item, parent, false);
                CustomHorizontalScrollView hScrollView = (CustomHorizontalScrollView) convertView.findViewById(R.id.horizontalScrollView);
                mHorizontalScrollView.AddOnScrollChangedListener(new OnScrollChangedListenerImpl(hScrollView));
            }
            return convertView;
        }
    }

    private class OnScrollChangedListenerImpl implements CustomHorizontalScrollView.OnScrollChangedListener {
        CustomHorizontalScrollView customScrollView;

        public OnScrollChangedListenerImpl(CustomHorizontalScrollView scrollViewar) {
            customScrollView = scrollViewar;
        }

        @Override
        public void onScrollChanged(int l, int t, int oldl, int oldt) {
            customScrollView.smoothScrollTo(l, t);
        }
    }
}
