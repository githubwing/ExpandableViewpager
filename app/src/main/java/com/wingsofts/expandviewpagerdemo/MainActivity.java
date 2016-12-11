package com.wingsofts.expandviewpagerdemo;

import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wingsofts.expandviewpager.ExpandableViewpager;

public class MainActivity extends AppCompatActivity {

    private ExpandableViewpager mViewpager;

    private ImageView expandImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewpager = (ExpandableViewpager) findViewById(R.id.viewpager);
        expandImg = (ImageView) findViewById(R.id.iv_expand);
        mViewpager.setOffscreenPageLimit(5);


        //set the background view
//        mViewpager.setBackgroundView(R.layout.layout_background);
        View view = View.inflate(this,R.layout.layout_background,null);
        Glide.with(this).load(R.drawable.bg).into((ImageView) view.findViewById(R.id.iv_bg));
        mViewpager.setBackgroundView(view);


        //set the animation duration
        mViewpager.setDuration(500);

        //set the viewpager min height
        mViewpager.setMinHeight(1000);

        mViewpager.setAdapter(mAdapter);
        mViewpager.setCurrentItem(2);


        mViewpager.setOnStateChangedListener(new ExpandableViewpager.OnStateChangedListener() {
            @Override
            public void onCollaps() {
                expandImg.setVisibility(View.GONE);
            }

            @Override
            public void onExpand() {

                expandImg.setVisibility(View.VISIBLE);
            }
        });

        expandImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewpager.collaps();
            }
        });


    }






    private PagerAdapter mAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View v = View.inflate(MainActivity.this, R.layout.item_pager, null);
            Glide.with(MainActivity.this).load(R.drawable.w).into((ImageView) v.findViewById(R.id.iv_w));

            Glide.with(MainActivity.this).load(R.drawable.train).into((ImageView) v.findViewById(R.id.iv_train));
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mViewpager.expand();
                }
            });
            container.addView(v);
            return v;
        }


    };

}
