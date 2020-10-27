package com.biz.lrlist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    public PagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
//            case 0:
//                return Loginfragment.newInstance();
            case 0:
                return Registrationfragment.newInstance();
            case 1:
                return Listfragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public String getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Register";
            case 1:
                return "List";
            default:
                return null;
        }
    }
}
