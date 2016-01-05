package com.imesong.springdream;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.imesong.springdream.utils.UpdateUtil;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

public class MainActivity extends BaseActivity {

    private ViewPager viewPager;
    private static String[] categoryDreams;
    private com.astuetz.PagerSlidingTabStrip tabStrip;
    private FragmentManager fragmentManager;
    private AccountHeader headerResult = null;
    public  static int PROFILE_SETTING = 1;
    private Drawer mLeftDrawer;
    private Drawer mRightDrawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initDrawer(savedInstanceState);
        UpdateUtil.update(this);

        initViewPager();


    }


    private void initDrawer(Bundle savedInstanceState) {
        final IProfile profile3 = new ProfileDrawerItem().withName("imesong").withEmail("imesong@126.com").withIcon(R.drawable.profile2).withIdentifier(102);
        // Create the AccountHeader
        headerResult = new AccountHeaderBuilder()
            .withActivity(this)
            .withHeaderBackground(R.drawable.header)
            .addProfiles(profile3)
//            .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
//                @Override
//                public boolean onProfileChanged(View view, IProfile profile, boolean current) {
//                    return true;
//                }
//            })
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
                new DividerDrawerItem(),
                new SecondaryDrawerItem().withName(R.string.right_item6).withIcon(FontAwesome.Icon.faw_github),
                new SecondaryDrawerItem().withName(R.string.right_item7).withIcon(FontAwesome.Icon.faw_question)
            )
            .withSavedInstance(savedInstanceState)
            .build();
    }


    private void initViewPager() {
        categoryDreams = getResources().getStringArray(R.array.category_dreams);
        fragmentManager = getSupportFragmentManager();

        viewPager = (ViewPager)findViewById(R.id.view_pager);
        viewPager.setAdapter(new CategoryAdapter(fragmentManager));
        viewPager.setOffscreenPageLimit(categoryDreams.length);

        tabStrip = (com.astuetz.PagerSlidingTabStrip)findViewById(R.id.tab_strip);
        tabStrip.setViewPager(viewPager);

    }


    class CategoryAdapter extends FragmentStatePagerAdapter {
        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            bundle.putString(CategoryListFragment.CATEGORY_NAME, categoryDreams[position]);
            bundle.putString(CategoryListFragment.CATEGORY_ID, position + "");

            return  CategoryListFragment.getInstance(bundle);
        }

        @Override
        public int getCount() {
            return categoryDreams.length;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return categoryDreams[position];
        }

        public CategoryAdapter(FragmentManager fm) {
            super(fm);
        }
    }


}
