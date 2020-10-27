package com.biz.lrlist;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("NonConstantResourceId")
public class BottomNavigation extends AppCompatActivity {

    private static final String BACK_STACK_ROOT_TAG = "root_fragment";
    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
        ButterKnife.bind(this);

        SetBottomNavigation();
    }

    private void SetBottomNavigation() {
        openFragment(new Registrationfragment().newInstance());

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
//                case R.id.navigation_login:
//                    openFragment(Loginfragment.newInstance());
                case R.id.navigation_register:
                    openFragment(Registrationfragment.newInstance());
                    return true;
                case R.id.navigation_list:
                    openFragment(Listfragment.newInstance());
                    return true;
            }
            return false;
        });
    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(BACK_STACK_ROOT_TAG);
        transaction.commit();
    }
}