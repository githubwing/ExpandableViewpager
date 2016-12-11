package com.wingsofts.expandviewpager;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * 可以展开和缩小的Viewpager
 * <p>
 * Created by wing on 2016/12/11.
 */

public class ExpandableViewpager extends RelativeLayout {
    private ViewPager mViewpager;
    private float mMinHeight = 1000;
    private int mDuration = 500;
    private boolean isExpand = true;
    private OnStateChangedListener mListener;

    public ExpandableViewpager(Context context) {
        this(context, null);
    }

    public ExpandableViewpager(Context context, AttributeSet attrs) {
        super(context, attrs);

        setClipChildren(false);

        mViewpager = new ViewPager(context);
        mViewpager.setClipChildren(false);


        addView(mViewpager);

    }


    /**
     * set the view in background
     *
     * @param resourceId
     */
    public void setBackgroundView(int resourceId) {
        View view = View.inflate(getContext(), resourceId, null);
        setBackgroundView(view);
    }

    public void setBackgroundView(View view) {
        RelativeLayout.LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , ViewGroup.LayoutParams.MATCH_PARENT);
        removeAllViews();
        view.setLayoutParams(layoutParams);
        addView(view);
        addView(mViewpager);
        requestLayout();
    }

    public void setAdapter(PagerAdapter adapter) {
        mViewpager.setAdapter(adapter);
    }


    public void setOffscreenPageLimit(int limit) {
        mViewpager.setOffscreenPageLimit(limit);
    }


    public void collaps() {
        if (isExpand) {
            isExpand = false;
            ValueAnimator collapsVa = getCollposValueAnimator();
            collapsVa.start();
            if (mListener != null) {
                mListener.onCollaps();
            }
        }
    }

    public void expand() {
        if (!isExpand) {
            isExpand = true;
            ValueAnimator expandXVa = getExpandValueAnimator();
            expandXVa.start();
            if (mListener != null) {
                mListener.onExpand();
            }
        }
    }

    @NonNull
    private ValueAnimator getCollposValueAnimator() {
        ValueAnimator collapsVa = ValueAnimator.ofFloat(1, mMinHeight / mViewpager.getHeight());

        collapsVa.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                float percent = (Float) valueAnimator.getAnimatedValue();
                mViewpager.setScaleX(percent);
                mViewpager.setScaleY(percent);
                mViewpager.setY((1 - percent) / 2 * mViewpager.getHeight());
            }
        });
        collapsVa.setDuration(mDuration);
        return collapsVa;
    }


    @NonNull
    private ValueAnimator getExpandValueAnimator() {
        ValueAnimator expandXVa = ValueAnimator.ofFloat(mMinHeight / mViewpager.getHeight(), 1);
        expandXVa.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float percent = (Float) valueAnimator.getAnimatedValue();
                mViewpager.setScaleX(percent);
                mViewpager.setScaleY(percent);
                mViewpager.setY((1 - percent) / 2 * mViewpager.getHeight());
            }
        });
        expandXVa.setDuration(mDuration);
        return expandXVa;
    }


    public boolean isExpand() {
        return isExpand;
    }

    public int getCurrentItem() {
        return mViewpager.getCurrentItem();
    }


    public View getPagerChildAt(int pos) {
        return mViewpager.getChildAt(pos);
    }

    public void setPageTransformer(boolean reverseDrawingOrder, ViewPager.PageTransformer transformer) {
        mViewpager.setPageTransformer(reverseDrawingOrder, transformer);
    }

    public void addOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
        mViewpager.addOnPageChangeListener(listener);
    }

    public void setCurrentItem(int pos) {
        mViewpager.setCurrentItem(pos);
    }

    public void setCurrentItem(int pos, boolean smoothScroll) {
        mViewpager.setCurrentItem(pos, smoothScroll);
    }

    public void setDuration(int duration) {
        mDuration = duration;
    }

    public void setMinHeight(float minHeight) {
        mMinHeight = minHeight;
    }





    public void setOnStateChangedListener(OnStateChangedListener listener){
        mListener = listener;
    }



    public interface OnStateChangedListener {
        void onCollaps();

        void onExpand();
    }
}
