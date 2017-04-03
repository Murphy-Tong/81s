package com.example.tong.a81s;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import util.GlobalResource;
import util.PageNav;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private int restExit = -2; ;
    private FloatingActionButton fab;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int w = msg.what;
            if (w == GlobalResource.LOADING) {
                Snackbar.make(fab, "loading...", Snackbar.LENGTH_LONG).show();
            }
            if (w == GlobalResource.GET) {
                int num = (int) msg.obj;
                Snackbar.make(fab, num + " new items", Snackbar.LENGTH_LONG).show();

            }
            if (w == GlobalResource.NOMORE) {
                Snackbar.make(fab, "NOMORE", Snackbar.LENGTH_LONG).show();
            }
            if(w==restExit){
                exit = 0;
            }
        }
    };
    private SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three

        GlobalResource.HANDLER = handler;
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "loading...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragments[PageNav.MOVIE] = new PlaceHolderFragment(PageNav.MOVIE);


        getSupportActionBar().setTitle("电影");

        getSupportFragmentManager().beginTransaction().add(R.id.main_container, fragments[PageNav.MOVIE], fragments[PageNav.MOVIE].getClass().getName()).commit();
        currentFragment = fragments[PageNav.MOVIE];
    }

    private PlaceHolderFragment currentFragment;
    private PlaceHolderFragment[] fragments = new PlaceHolderFragment[8];

    private int exit = 0;
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
//            super.onBackPressed();
            exit++;
            if(exit>=2){
//                super.onBackPressed();
                this.finish();

            }else {
                Toast.makeText(getApplicationContext(),"再按一次退出",Toast.LENGTH_SHORT).show();
                handler.sendEmptyMessageDelayed(restExit,1000);

            }

        }




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        final MenuItem item = menu.findItem(R.id.search);
        mSearchView = (SearchView) MenuItemCompat.getActionView(item);
//        mSearchView.setIconified(false);
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


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        int id = item.getItemId();
        int section = 0;
        if (id == R.id.nav_comic) {
            section = PageNav.COMIC;
        } else if (id == R.id.nav_media) {
            section = PageNav.MEDIA;
        } else if (id == R.id.nav_movie) {
            section = PageNav.MOVIE;
        }
        /*
        else if (id == R.id.nav_music) {
            section = PageNav.MV;
        } else if (id == R.id.nav_short_movie) {
            section = PageNav.SHORT;
        }
        */
        else if (id == R.id.nav_open_class) {
            section = PageNav.COURSE;
        } else if (id == R.id.nav_TV) {
            section = PageNav.TV;
        }

        PlaceHolderFragment f = fragments[section];
        if (f == null) {
            f = new PlaceHolderFragment(section);
            fragments[section] = f;

        }

        if (!f.isAdded()) {
            fragmentTransaction.add(R.id.main_container, f, f.getClass().getName());
        }

        GlobalResource.isLoading = false;

        fragmentTransaction.hide(currentFragment);
        fragmentTransaction.show(f);
        currentFragment = f;
        fragmentTransaction.commit();

        getSupportActionBar().setTitle(item.getTitle());
//        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, f).commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
