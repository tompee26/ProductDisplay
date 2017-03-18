package com.tompee.utilities.mercarisample.view.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tompee.utilities.mercarisample.R;
import com.tompee.utilities.mercarisample.view.fragment.ProductFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private static final int PAGE_COUNT = 3;
    private final ProductFragment mMenProductFragment;
    private final ProductFragment mWomenProductFragment;
    private final ProductFragment mAllProductFragment;
    private final Context mContext;

    public ViewPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
        mMenProductFragment = ProductFragment.newInstance(ProductFragment.MEN);
        mWomenProductFragment = ProductFragment.newInstance(ProductFragment.WOMEN);
        mAllProductFragment = ProductFragment.newInstance(ProductFragment.ALL);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return mMenProductFragment;
            case 1:
                return mAllProductFragment;
            case 2:
                return mWomenProductFragment;
        }
        return mMenProductFragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String name;
        switch (position) {
            case 1:
                name = mContext.getString(R.string.fragment_title_all);
                break;
            case 2:
                name = mContext.getString(R.string.fragment_title_women);
                break;
            default:
                name = mContext.getString(R.string.fragment_title_men);
                break;
        }
        return name;
    }
}
