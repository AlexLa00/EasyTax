package app.cyberzen.easytax;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class Menu extends AppCompatActivity {

    public boolean onCreateOptionsMenu(Menu menu) {
              getMenuInflater().inflate(R.menu.drawer_view, (android.view.Menu) menu);
        return true;
    }
            private DrawerLayout mDrawerLayout;

            @Override

            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_home_screen);

                Toolbar toolbar = findViewById(R.id.toolbar);
                //setSupportActionBar(toolbar);

                ActionBar actionbar = getSupportActionBar();
                actionbar.setDisplayHomeAsUpEnabled(true);
                actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

                mDrawerLayout = findViewById(R.id.drawer_layout);

                NavigationView navigationView = findViewById(R.id.nav_view);
                navigationView.setNavigationItemSelectedListener(
                        new NavigationView.OnNavigationItemSelectedListener() {
                            @Override
                            public boolean onNavigationItemSelected(MenuItem menuItem) {

                                menuItem.setChecked(true);

                                mDrawerLayout.closeDrawers();


                                // Add code here to update the UI based on the item selected
                                // For example, swap UI fragments here
                                //display in short period of time
                                Toast.makeText(getApplicationContext(), menuItem.getTitle(),
                                        Toast.LENGTH_LONG).show();

                                return true;
                            }
                        });


                mDrawerLayout.addDrawerListener(
                        new DrawerLayout.DrawerListener() {
                            @Override
                            public void onDrawerSlide(View drawerView, float slideOffset) {

                            }

                            @Override
                            public void onDrawerOpened(View drawerView) {

                            }

                            @Override
                            public void onDrawerClosed(View drawerView) {

                            }

                            @Override
                            public void onDrawerStateChanged(int newState) {

                            }
                        }
                );

            }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}