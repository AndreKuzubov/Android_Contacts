package contacts.andre.myapplication;

import android.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

public class MainActivity extends ActionBarActivity implements MenuItem.OnMenuItemClickListener {

    TabHost tabHost =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buildTagHost();
    }

    private void buildTagHost(){
        tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();
        TabHost.TabSpec tabSpec;
        tabSpec = tabHost.newTabSpec("tag1");
        tabSpec.setIndicator("Вкладка 1");
        tabSpec.setContent(R.id.sys_contacts);
        tabHost.addTab(tabSpec);
        tabSpec = tabHost.newTabSpec("tag2");
        tabSpec.setContent(R.id.realm_contacts);
        tabSpec.setIndicator("Вкладка контакты");
        tabHost.addTab(tabSpec);
        tabHost.setCurrentTab(0);

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            public void onTabChanged(String tabId) {
                invalidateOptionsMenu();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tool_bar_main, menu);
        menu.getItem(0).setOnMenuItemClickListener(MainActivity.this);
        menu.getItem(0).setVisible(tabHost.getCurrentTab() != 0);
        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        FragmentManager f=getFragmentManager();
        RealmContactsFragment realmContactsFragment=(RealmContactsFragment)f.findFragmentById(R.id.realm_contacts);
        realmContactsFragment.updateRealm();
        return true;
    }
}
