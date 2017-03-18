package com.tompee.utilities.photodisplay.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.tompee.utilities.photodisplay.R;
import com.tompee.utilities.photodisplay.view.adapter.ViewPagerAdapter;

public class MainActivity extends FragmentActivity {
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView toolbar = (TextView) findViewById(R.id.toolbar_text);
        toolbar.setText(R.string.app_name);

        mViewPager = (ViewPager) findViewById(R.id.pager_main);
        mViewPager.setAdapter(new ViewPagerAdapter(this, getSupportFragmentManager()));
        mViewPager.setOffscreenPageLimit(mViewPager.getAdapter().getCount());
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout_main);
        tabLayout.setupWithViewPager(mViewPager);
    }
}
