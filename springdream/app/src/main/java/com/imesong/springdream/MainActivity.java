package com.imesong.springdream;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;

import com.imesong.springdream.utils.UpdateUtil;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;

public class MainActivity extends BaseActivity {

    private ViewPager viewPager;
    private static String[] categoryDreams;
    private com.astuetz.PagerSlidingTabStrip tabStrip;
    private FragmentManager fragmentManager;


    private PrimaryDrawerItem category;
    private SecondaryDrawerItem mPersionCategory;
    private SecondaryDrawerItem mActiveCategory;
    private SecondaryDrawerItem mPlantCategoy;
    private SecondaryDrawerItem mWoodCategory;
    private SecondaryDrawerItem mAnimalCategory;
    private SecondaryDrawerItem mLifeCategoy;
    private SecondaryDrawerItem mNatureCategory;
    private SecondaryDrawerItem mGhostCategory;
    private SecondaryDrawerItem mStructureCategory;
    private SecondaryDrawerItem mOtherCategory;
    private SecondaryDrawerItem mPregnantCategory;
    private DividerDrawerItem mDividerDrawerItem;


    private Drawer mLeftDrawer;
    private Drawer mRightDrawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        initDrawer(toolbar);
        UpdateUtil.update(this);

        initViewPager();


    }


    private void initDrawer(Toolbar toolbar) {

        category = new PrimaryDrawerItem().withName(R.string.category_dreams);

        mPersionCategory = new SecondaryDrawerItem().withName(R.string.category_persons);
        mAnimalCategory = new SecondaryDrawerItem().withName(R.string.category_animal);
        mPlantCategoy = new SecondaryDrawerItem().withName(R.string.category_plant);
        mWoodCategory = new SecondaryDrawerItem().withName(R.string.category_woods);
        mActiveCategory = new SecondaryDrawerItem().withName(R.string.category_active);
        mLifeCategoy = new SecondaryDrawerItem().withName(R.string.category_life);
        mNatureCategory = new SecondaryDrawerItem().withName(R.string.category_nature);
        mGhostCategory = new SecondaryDrawerItem().withName(R.string.category_ghost);
        mStructureCategory = new SecondaryDrawerItem().withName(R.string.category_structure);
        mOtherCategory = new SecondaryDrawerItem().withName(R.string.category_others);
        mPregnantCategory = new SecondaryDrawerItem().withName(R.string.category_pregnant);

        mDividerDrawerItem = new DividerDrawerItem();


        mLeftDrawer = new DrawerBuilder().withActivity(this).withToolbar(toolbar).
            addDrawerItems(category,
                mDividerDrawerItem,
                mPersionCategory,
                mDividerDrawerItem,
                mAnimalCategory,
                mDividerDrawerItem,
                mPlantCategoy,
                mDividerDrawerItem,
                mWoodCategory,
                mDividerDrawerItem,
                mActiveCategory,
                mDividerDrawerItem,
                mLifeCategoy,
                mDividerDrawerItem,
                mNatureCategory,
                mDividerDrawerItem,
                mGhostCategory,
                mDividerDrawerItem,
                mStructureCategory,
                mDividerDrawerItem,
                mOtherCategory,
                mDividerDrawerItem,
                mPregnantCategory
            ).build();


        //now we add the second drawer on the other site.
        //use the .append method to add this drawer to the first one
        mRightDrawer = new DrawerBuilder()
            .withActivity(this)
            .withDisplayBelowStatusBar(true)
            .addDrawerItems(
                new PrimaryDrawerItem().withName(R.string.right_item1).withIcon(FontAwesome.Icon.faw_eye),
                new PrimaryDrawerItem().withName(R.string.right_item2).withIcon(FontAwesome.Icon.faw_home),
                new PrimaryDrawerItem().withName(R.string.right_item3).withIcon(FontAwesome.Icon.faw_gamepad),
                new SectionDrawerItem().withName(R.string.right_item4),
                new SecondaryDrawerItem().withName(R.string.right_item5).withIcon(FontAwesome.Icon.faw_cog),
                new DividerDrawerItem(),
                new SecondaryDrawerItem().withName(R.string.right_item6).withIcon(FontAwesome.Icon.faw_github),
                new SecondaryDrawerItem().withName(R.string.right_item7).withIcon(FontAwesome.Icon.faw_question).withEnabled(false),
                new SectionDrawerItem().withName(R.string.right_item8)
            )
            .withDrawerGravity(Gravity.END)
            .append(mLeftDrawer);


        //set the back arrow in the toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
            return categoryDreams.length-1;
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
