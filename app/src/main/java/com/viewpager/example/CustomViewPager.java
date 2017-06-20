package com.viewpager.example;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * 自定义ViewGroup实现类似ViewPager滑动效果
 * Scroller使用： http://blog.csdn.net/guolin_blog/article/details/48719871
 * 主要可以分为以下几个步骤：
 * 1. 创建Scroller实例
 * 2. 调用startScroll()方法来初始化滚动数据并刷新界面
 * 3. 重写computeScroll()方法，并在其内部完成平滑滚动的逻辑
 */

public class CustomViewPager extends ViewGroup {

    private GestureDetector mGestureDetector;
    private Scroller        mScroller;

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // 指定子View位置
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            childView.layout(i * getWidth(), 0, (i + 1) * getWidth(), getHeight());
        }
    }

    public CustomViewPager(Context context) {
        super(context);
        init(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mScroller = new Scroller(context);
        // 初始化手势识别器
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                // 屏幕滑动的距离
                scrollBy((int) distanceX, 0);
                return true;
            }
        });
    }

    // 记录手指按下时在x轴坐标
    private float startX      = 0;
    // 当前页index
    private int   currentPage = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        mGestureDetector.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                float endX = event.getX();
                int tempPage = currentPage;
                if (endX - startX > getWidth() / 2) {
                    // 显示上一个页面
                    tempPage--;
                } else if (startX - endX > getWidth() / 2) {
                    // 显示下一个页面
                    tempPage++;
                }

                moveToPage(tempPage);
                break;
        }
        return true;
    }

    /**
     * 移动到指定的页面
     *
     * @param page 页面Index
     */
    private void moveToPage(int page) {
        if (page < 0) {
            page = 0;
        }

        if (page > getChildCount() - 1) {
            page = getChildCount() - 1;
        }

        currentPage = page;
        // 移动的距离
        int scrollDistance = page * getWidth() - getScrollX();
        mScroller.startScroll(getScrollX(), 0, scrollDistance, 0);
        invalidate();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), 0);
            invalidate();
        }
    }
}
