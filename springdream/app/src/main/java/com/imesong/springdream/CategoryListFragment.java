package com.imesong.springdream;

import com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 *
 */
public class CategoryListFragment extends BaseFragment {
    public static final String ARG_INITIAL_POSITION = "ARG_INITIAL_POSITION";
    public static final String CATEGORY_NAME = "category_name";
    public static final String CATEGORY_ID = "categoryId";
    private static CategoryListFragment instance;
    private String categoryName;
    private String categoryId;

    public CategoryListFragment() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            categoryName = bundle.getString(CATEGORY_NAME, "");
            categoryId = bundle.getString(CATEGORY_ID, "");
        }
    }

    public static CategoryListFragment getInstance(Bundle bundle) {
        CategoryListFragment categoryListFragment = new CategoryListFragment();
        categoryListFragment.setArguments(bundle);
        return categoryListFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_list_fragment, container, false);

        Activity parentActivity = getActivity();
        final ObservableRecyclerView recyclerView = (ObservableRecyclerView) view.findViewById(R.id.scroll);
        recyclerView.setLayoutManager(new LinearLayoutManager(parentActivity));
        recyclerView.setHasFixedSize(false);
        View headerView = LayoutInflater.from(parentActivity).inflate(R.layout.padding, null);
        setDummyDataWithHeader(recyclerView, headerView);

        if (parentActivity instanceof ObservableScrollViewCallbacks) {
            // Scroll to the specified offset after layout
            Bundle args = getArguments();
            if (args != null && args.containsKey(ARG_INITIAL_POSITION)) {
                final int initialPosition = args.getInt(ARG_INITIAL_POSITION, 0);
                ScrollUtils.addOnGlobalLayoutListener(recyclerView, new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.scrollVerticallyToPosition(initialPosition);
                    }
                });
            }

            // TouchInterceptionViewGroup should be a parent view other than ViewPager.
            // This is a workaround for the issue #117:
            // https://github.com/ksoichiro/Android-ObservableScrollView/issues/117
            recyclerView.setTouchInterceptionViewGroup((ViewGroup) parentActivity.findViewById(R.id.root));

            recyclerView.setScrollViewCallbacks((ObservableScrollViewCallbacks) parentActivity);
        }
        return view;

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }
}
