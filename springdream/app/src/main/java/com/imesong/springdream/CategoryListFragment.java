package com.imesong.springdream;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 *
 */
public class CategoryListFragment extends BaseFragment {

    private static CategoryListFragment instance ;
    private String categoryName;
    private String categoryId;
    public static final String CATEGORY_NAME = "category_name";
    public static final String CATEGORY_ID = "categoryId";

    public CategoryListFragment() {
        Bundle bundle = getArguments();
        if (bundle != null){
            categoryName = bundle.getString(CATEGORY_NAME,"");
            categoryId = bundle.getString(CATEGORY_ID,"");
        }


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return LayoutInflater.from(getActivity()).inflate(R.layout.category_list_fragment,null);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }


    public static CategoryListFragment getInstance(Bundle bundle){
        CategoryListFragment categoryListFragment = new CategoryListFragment();
        categoryListFragment.setArguments(bundle);
        return categoryListFragment;
    }


}
