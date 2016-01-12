package com.imesong.springdream;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

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

public class MainActivity extends BaseActivity {

    public static int PROFILE_SETTING = 1;
    private static String[] categoryDreams;
    private ViewPager viewPager;
    private SlidingTabLayout tabStrip;
    private FragmentManager fragmentManager;
    private AccountHeader headerResult = null;
    private Drawer mLeftDrawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDrawer(savedInstanceState);
        UpdateUtil.update(this);

        initViewPager();


    }


    private void initDrawer(Bundle savedInstanceState) {
        final IProfile profile3 = new ProfileDrawerItem().withName("imesong").withEmail("imesong@126.com").withIcon(R.drawable.profile2).withIdentifier(PROFILE_SETTING);
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
                new PrimaryDrawerItem().withName(R.string.right_item1).withIcon(FontAwesome.Icon.faw_eye),
                new PrimaryDrawerItem().withName(R.string.right_item2).withIcon(FontAwesome.Icon.faw_home),
                new PrimaryDrawerItem().withName(R.string.right_item3).withIcon(FontAwesome.Icon.faw_gamepad),
                new SectionDrawerItem().withName(R.string.right_item4),
                new SecondaryDrawerItem().withName(R.string.right_item5).withIcon(FontAwesome.Icon.faw_cog),
                new SecondaryDrawerItem().withName(R.string.right_item6).withIcon(FontAwesome.Icon.faw_github),
                new SecondaryDrawerItem().withName(R.string.right_item7).withIcon(FontAwesome.Icon.faw_question)
            )
            .withSavedInstance(savedInstanceState)
            .build();
    }


    private void initViewPager() {
        categoryDreams = getResources().getStringArray(R.array.category_dreams);
        fragmentManager = getSupportFragmentManager();

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new CategoryAdapter(fragmentManager));
        viewPager.setOffscreenPageLimit(categoryDreams.length);

        tabStrip = (SlidingTabLayout) findViewById(R.id.tab_strip);
        tabStrip.setViewPager(viewPager);

    }


    class CategoryAdapter extends FragmentStatePagerAdapter {
        public CategoryAdapter(FragmentManager fm) {
            super(fm);
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


}
