package com.imesong.springdream;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;

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
                .tab_strip_bg_normal));
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
        mSearchBox.setSearchListener(new SearchBoxListenerControl(this, mSearchBox));
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
