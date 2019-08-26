package com.example.googlemap;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.googlemap.adapter.TabFragmentAdapter;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ViewPager viewPager;
    TabLayout tabLayout;
    TabItem googleMap,lvLocations;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bind();

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this,drawerLayout,toolbar,
                R.string.nav_open,R.string.nav_close);

        drawerLayout.addDrawerListener(toggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        TabFragmentAdapter tabFragmentAdapter = new TabFragmentAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(tabFragmentAdapter);

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("Google Map");
        tabLayout.getTabAt(1).setText("All School");
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_item_one:
                Toast.makeText(MainActivity.this,"One",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_item_two:
                Toast.makeText(MainActivity.this,"Two",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_item_three:
                Toast.makeText(MainActivity.this,"Three",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_item_four:
                Toast.makeText(MainActivity.this,"Four",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_item_five:
                Toast.makeText(MainActivity.this,"Five",Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }

    public void bind(){
        tabLayout = findViewById(R.id.tablayout);
        googleMap = findViewById(R.id.google_map);
        lvLocations = findViewById(R.id.lv_locations);
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        viewPager = findViewById(R.id.view_pager);
    }

}
