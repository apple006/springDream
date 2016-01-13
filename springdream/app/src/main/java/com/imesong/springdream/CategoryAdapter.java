package com.imesong.springdream;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 *
 */
public class CategoryAdapter extends FragmentStatePagerAdapter {

    private static String[] categoryDreams;


    public CategoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        categoryDreams = context.getResources().getStringArray(R.array.category_dreams);
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putString(CategoryListFragment.CATEGORY_NAME, categoryDreams[position]);
        bundle.putString(CategoryListFragment.CATEGORY_ID, position + "");

        return CategoryListFragment.getInstance(bundle);
    }

    @Override
    public int getCount() {
        return categoryDreams.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return categoryDreams[position];
    }
}
