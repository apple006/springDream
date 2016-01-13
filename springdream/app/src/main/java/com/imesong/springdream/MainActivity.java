package com.imesong.springdream;

import com.flyco.tablayout.SlidingTabLayout;
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
import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchResult;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MenuItem;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    public static int PROFILE_SETTING = 1;
    private static String[] categoryDreams;
    private ViewPager viewPager;
    private SlidingTabLayout tabStrip;
    private FragmentManager fragmentManager;
    private AccountHeader headerResult = null;
    private Drawer mLeftDrawer;
    private SearchBox mSearchBox;

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
        mSearchBox = (SearchBox) findViewById(R.id.searchbox);

        mSearchBox.enableVoiceRecognition(this);

        mSearchBox.findViewById(R.id.search_root).setBackgroundColor(getResources().getColor(R.color
                .tab_strip_bg_normal, getTheme()));
        mSearchBox.setMenuListener(new SearchBox.MenuListener() {
            @Override
            public void onMenuClick() {
                if (mLeftDrawer.isDrawerOpen()) {
                    mLeftDrawer.closeDrawer();
                } else {
                    mLeftDrawer.openDrawer();
                }
            }
        });
        mSearchBox.setSearchListener(new SearchBox.SearchListener() {

            @Override
            public void onSearchOpened() {
                //Use this to tint the screen
                Log.d(TAG, "onSearchOpened");
            }

            @Override
            public void onSearchClosed() {
                //Use this to un-tint the screen
                Log.d(TAG, "onSearchClosed");
            }

            @Override
            public void onSearchTermChanged(String term) {
                //React to the search term changing
                //Called after it has updated results
                Log.d(TAG, "onSearchTermChanged");
            }

            @Override
            public void onSearch(String searchTerm) {
                Toast.makeText(MainActivity.this, searchTerm + " Searched", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResultClick(SearchResult result) {
                //React to a result being clicked
                Log.d(TAG, "onResultClick");
            }

            @Override
            public void onSearchCleared() {
                //Called when the clear button is clicked
                Log.d(TAG, "onSearchCleared");
            }
        });
        mSearchBox.setOverflowMenu(R.menu.menu_main);
        mSearchBox.setOverflowMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_settings:
                        Toast.makeText(MainActivity.this, "Clicked!", Toast.LENGTH_SHORT).show();
                        return true;
                }
                return false;
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "requestCode==" + requestCode + "\t resultCode==" + resultCode);
        if (requestCode == 1234 && resultCode == RESULT_OK) {
            ArrayList<String> matches = data
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            mSearchBox.populateEditText(matches.get(0));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mSearchBox.isShown()) {
            mSearchBox.clearAnimation();
            return;
        }
    }
}
