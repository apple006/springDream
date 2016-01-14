package com.imesong.springdream;

import android.content.Context;
import android.util.Log;

import com.imesong.springdream.database.DBHelper;
import com.imesong.springdream.database.Dream;
import com.imesong.springdream.database.DreamDao;
import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchResult;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

/**
 *
 */
public class SearchBoxListenerControl implements SearchBox.SearchListener {
    private static final String TAG = "SearchBoxListener";
    private static final int SEARCH_RESULT_LIMIT = 10;

    private Context mContext;
    private List<Dream> list;
    private QueryBuilder queryBuilder;
    private SearchBox mSearchBox;

    public SearchBoxListenerControl(Context context, SearchBox searchBox) {
        mContext = context;
        mSearchBox = searchBox;
    }

    @Override
    public void onSearchOpened() {

    }

    @Override
    public void onSearchCleared() {

    }

    @Override
    public void onSearchClosed() {

    }

    @Override
    public void onSearchTermChanged(String term) {
        Log.d(TAG, "term" + term);
//        if (queryBuilder == null){
//            queryBuilder = DBHelper.getInstance(mContext).getDreamDao().queryBuilder();
//        }
        QueryBuilder queryBuilder = DBHelper.getInstance(mContext).getDreamDao().queryBuilder().limit(SEARCH_RESULT_LIMIT);
        queryBuilder.where(DreamDao.Properties.Name.like("%" + term + "%"));
        list = queryBuilder.list();
        mSearchBox.addAllSearchables((ArrayList<Dream>) list);
        Log.d(TAG, "list:" + list.toString());
    }

    @Override
    public void onSearch(String result) {
        Log.d(TAG, "result:" + result);
    }

    @Override
    public void onResultClick(SearchResult result) {

    }
}
