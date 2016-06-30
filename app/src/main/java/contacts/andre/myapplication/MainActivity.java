package contacts.andre.myapplication;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity implements MenuItem.OnMenuItemClickListener {

    TabLayout tabLayout =null;
    ViewPager viewPager=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buildTagHost();
    }

    private void buildTagHost(){
        tabLayout = (TabLayout) findViewById(R.id.m_tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("tab22"));
        tabLayout.addTab(tabLayout.newTab().setText("tab23"));
        viewPager=(ViewPager)findViewById(R.id.viewPaper);
        viewPager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public Fragment getItem(int position) {
                Fragment f = null;
                switch (position) {
                    case 0:
                        f = new SystemFragmentContacts();
                        break;
                    case 1:
                        f = new RealmContactsFragment();
                        break;
                }
                return f;
            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                invalidateOptionsMenu();
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        } );
    }


   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        getMenuInflater().inflate(R.menu.tool_bar_main, menu);
        menu.getItem(0).setOnMenuItemClickListener(MainActivity.this);
        menu.getItem(0).setVisible(tabLayout.getSelectedTabPosition() != 0);
        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        FragmentManager f=getFragmentManager();
        RealmContactsFragment realmContactsFragment=(RealmContactsFragment)f.findFragmentById(R.id.viewPaper);
        realmContactsFragment.updateRealm();
        return true;
    }
}
