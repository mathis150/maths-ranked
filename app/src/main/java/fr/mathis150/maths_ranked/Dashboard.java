package fr.mathis150.maths_ranked;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.google.android.material.navigation.NavigationView;

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        drawerLayout = findViewById(R.id.drawer_layout);

        toolBar = findViewById(R.id.toolBar);
        setSupportActionBar(toolBar);

        NavigationView navigationView = findViewById(R.id.contentNavbar);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolBar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayout, new Home()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.nav_home){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayout, new Home()).commit();
        }
        if(item.getItemId() == R.id.nav_about){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayout, new AboutUs()).commit();
        }
        if(item.getItemId() == R.id.nav_logout){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayout, new Logout()).commit();
        }
        if(item.getItemId() == R.id.nav_staff){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayout, new StaffList()).commit();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            getOnBackPressedDispatcher().onBackPressed();
        }
    }
}