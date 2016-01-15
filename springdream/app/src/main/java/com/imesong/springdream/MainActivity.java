package com.imesong.springdream;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.SearchSuggestionsAdapter;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.arlib.floatingsearchview.util.view.BodyTextView;
import com.arlib.floatingsearchview.util.view.IconImageView;
import com.flyco.tablayout.SlidingTabLayout;
import com.imesong.springdream.database.DBHelper;
import com.imesong.springdream.database.Dream;
import com.imesong.springdream.database.DreamDao;
import com.imesong.springdream.utils.UpdateUtil;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    private static final int SEARCH_RESULT_LIMIT = 5;
    public static int PROFILE_SETTING = 1;
    private static String[] categoryDreams;
    private ViewPager viewPager;
    private SlidingTabLayout tabStrip;
    private FragmentManager fragmentManager;
    private AccountHeader headerResult = null;
    private Drawer mLeftDrawer;
    private FloatingSearchView mSearchView;
    private List<Dream> searchResult = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDrawer(savedInstanceState);
        UpdateUtil.update(this);

        initViewPager();

        initSearchBox();
    }

    private void initDrawer(Bundle savedInstanceState) {
        final IProfile profile3 = new ProfileDrawerItem().withName("imesong").withEmail("imesong@126.com")
                .withIcon(R.drawable.profile2).withIdentifier(PROFILE_SETTING);
        // Create the AccountHeader
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(profile3)
                .withSavedInstance(savedInstanceState)
                .build();

        mLeftDrawer = new DrawerBuilder()
                .withActivity(this)
                .withDisplayBelowStatusBar(true)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.right_item1).withIcon(FontAwesome.Icon
                                .faw_eye),
                        new PrimaryDrawerItem().withName(R.string.right_item2).withIcon(FontAwesome.Icon
                                .faw_home),
                        new PrimaryDrawerItem().withName(R.string.right_item3).withIcon(FontAwesome.Icon
                                .faw_gamepad),
                        new SectionDrawerItem().withName(R.string.right_item4),
                        new SecondaryDrawerItem().withName(R.string.right_item5).withIcon(FontAwesome.Icon
                                .faw_cog),
                        new SecondaryDrawerItem().withName(R.string.right_item6).withIcon(FontAwesome.Icon
                                .faw_github),
                        new SecondaryDrawerItem().withName(R.string.right_item7).withIcon(FontAwesome.Icon
                                .faw_question)
                )
                .withSavedInstance(savedInstanceState)
                .withOnDrawerListener(new Drawer.OnDrawerListener() {
                    @Override
                    public void onDrawerOpened(View drawerView) {
                        mSearchView.openMenu(true);
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        mSearchView.closeMenu(true);

                    }

                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        //TODO 添加 左侧icon 动画
                    }
                })
                .build();


    }

    private void initViewPager() {
        categoryDreams = getResources().getStringArray(R.array.category_dreams);
        fragmentManager = getSupportFragmentManager();

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new CategoryAdapter(this, fragmentManager));
        viewPager.setOffscreenPageLimit(categoryDreams.length);

        tabStrip = (SlidingTabLayout) findViewById(R.id.tab_strip);
        tabStrip.setViewPager(viewPager);
    }

    private void initSearchBox() {
        mSearchView = (FloatingSearchView) findViewById(R.id.floating_search_view);

        mSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String newQuery) {
                if (!TextUtils.isEmpty(oldQuery) && TextUtils.isEmpty(newQuery)) {
                    mSearchView.clearSuggestions();
                } else {
                    mSearchView.showProgress();
                    QueryBuilder queryBuilder = DBHelper.getInstance(MainActivity.this).getDreamDao().queryBuilder().limit(SEARCH_RESULT_LIMIT);
                    queryBuilder.where(DreamDao.Properties.Name.like("%" + newQuery + "%"));
                    searchResult = queryBuilder.list();
                    mSearchView.swapSuggestions(searchResult);
                    mSearchView.hideProgress();
                }
            }
        });

        mSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {
                Log.d(TAG, "onSuggestionClicked：" + searchSuggestion.getBody());
            }

            @Override
            public void onSearchAction() {
                Log.d(TAG, "onSearchAction()");
            }
        });


        mSearchView.setOnFocusChangeListener(new FloatingSearchView.OnFocusChangeListener() {
            @Override
            public void onFocus() {
                Log.d(TAG, "onFocus()");
            }

            @Override
            public void onFocusCleared() {
                Log.d(TAG, "onFocusCleared()");
            }
        });

        mSearchView.setOnMenuItemClickListener(new FloatingSearchView.OnMenuItemClickListener() {
            @Override
            public void onActionMenuItemSelected(MenuItem item) {
                //just print action
                Toast.makeText(getApplicationContext(), item.getTitle(),
                        Toast.LENGTH_SHORT).show();
            }
        });
        //use this listener to listen to menu clicks when app:floatingSearch_leftAction="showHamburger"
        mSearchView.setOnLeftMenuClickListener(new FloatingSearchView.OnLeftMenuClickListener() {
            @Override
            public void onMenuOpened() {
                mLeftDrawer.openDrawer();
                mSearchView.openMenu(true);
                Log.d(TAG, "onMenuOpened()");

            }

            @Override
            public void onMenuClosed() {
                mLeftDrawer.closeDrawer();
                Log.d(TAG, "onMenuClosed()");
                mSearchView.closeMenu(true);
            }
        });


        mSearchView.setOnHomeActionClickListener(new FloatingSearchView.OnHomeActionClickListener() {
            @Override
            public void onHomeClicked() {
                Log.d(TAG, "onHomeClicked()");
            }
        });

        mSearchView.setOnBindSuggestionCallback(new SearchSuggestionsAdapter.OnBindSuggestionCallback() {
            @Override
            public void onBindSuggestion(IconImageView leftIcon, BodyTextView bodyText, SearchSuggestion item, int itemPosition) {
                Log.d(TAG, "onBindSuggestion()  item =" + item.getBody() + "\t itemPosition:" + itemPosition + "\t bodyText:" + bodyText.getText());
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
