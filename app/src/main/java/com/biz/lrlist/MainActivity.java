package com.biz.lrlist;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;
@SuppressLint("NonConstantResourceId")
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar1)
    Toolbar toolbar;
    @BindView(R.id.drawer)
    DrawerLayout drawerlayout;
    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        butterknife();
        setToolbar();
        setDrawerlayout();
        setNavigationview();
        openFirstFragment();
    }


    public void butterknife() {
        ButterKnife.bind(this);
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
    }

    public void onBackPressed() {
        if (this.drawerlayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerlayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void setDrawerlayout() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerlayout, toolbar, R.string.open, R.string.close);
        drawerlayout.addDrawerListener(toggle);
        toggle.syncState();

    }

    public void setNavigationview() {
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void openFirstFragment () {
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new Loginfragment()).addToBackStack(null).commit();
        toolbar.setTitle(R.string.action_login);
        drawerlayout.closeDrawer(GravityCompat.START);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment temp=null;
        String title;
        drawerlayout.closeDrawer(GravityCompat.START);
        switch (item.getItemId()) {
            case R.id.nav_menu_login:
                temp = new Loginfragment();
                title = getText(R.string.action_login).toString();
                break;
            case R.id.nav_menu_registration:
                temp = new Registrationfragment();
                title = getText(R.string.nav_item_register).toString();
                break;
            case R.id.nav_menu_list:
                temp = new Listfragment();
                title = getText(R.string.title_list).toString();
                break;
            case R.id.nav_easy_image:
                startActivity(new Intent(this, EasyImageActivity.class));
                title = "Easy Image";
                break;
            case R.id.nav_view_pager:
                startActivity(new Intent(this, View_Pager.class));
                title = "View Pager";
                break;
            case R.id.nav_button_navigation:
                startActivity(new Intent(this, BottomNavigation.class));
                title = "Bottom Navigation";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + item.getItemId());
        }
        if (temp != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, temp).commit();
            toolbar.setTitle(title);
            this.drawerlayout.closeDrawer(GravityCompat.START);
        }
        return true;
    }
}