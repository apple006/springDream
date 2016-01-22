package com.imesong.springdream;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

/**
 */
public class BaseFragment extends Fragment {
    private static final int NUM_OF_ITEMS = 100;


    protected void setDummyDataWithHeader(RecyclerView recyclerView, View headerView) {
        recyclerView.setAdapter(new SimpleHeaderRecyclerAdapter(getActivity(), getDummyData(), headerView));
    }

    public static ArrayList<String> getDummyData() {
        return getDummyData(NUM_OF_ITEMS);
    }

    public static ArrayList<String> getDummyData(int num) {
        ArrayList<String> items = new ArrayList<>();
        for (int i = 1; i <= num; i++) {
            items.add("Item " + i);
        }
        return items;
    }

}
