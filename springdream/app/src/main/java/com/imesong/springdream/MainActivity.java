package com.imesong.springdream;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class MainActivity extends BaseActivity {

    private PrimaryDrawerItem item1 ;
    private SecondaryDrawerItem item2;
    private Drawer mLeftDrawer ;
    private Drawer mRightDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        initDrawer(toolbar);


    }


    private void initDrawer(Toolbar toolbar){

        item1 = new PrimaryDrawerItem().withName(R.string.item1);
        item2 = new SecondaryDrawerItem().withName(R.string.item2);
        mLeftDrawer = new DrawerBuilder().withActivity(this).withToolbar(toolbar).
            addDrawerItems(item1,new DividerDrawerItem(),item2,new SecondaryDrawerItem().withName(R.string.item3)).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                return false;
            }
        }).build();



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
        getSupportActionBar().setHomeButtonEnabled(false);

    }























    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
