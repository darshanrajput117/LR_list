package com.biz.lrlist;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashSet;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("NonConstantResourceId")
public class Listfragment extends Fragment {

    public static final String Fname = String.valueOf(R.string.preference_fname_key);
    public static final String Lname = String.valueOf(R.string.preference_lname_key);
    public static final String Mobileeno = String.valueOf(R.string.preference_mobileno_key);
    public static final String Email = String.valueOf(R.string.preference_email_key);

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    //    @BindView(R.id.cardview)
//    CardView cv;
    UserListAdapter adapter;
    User[] users;

    SharedPreferences sharedPreferences;

    public Listfragment() {
    }

    public static Listfragment newInstance() {
        return new Listfragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setLayout();
        //setUserList();
        //setAdapter();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listfragment, container, false);
    }

    private void setLayout() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setUserList() {
        HashSet<String> stringsfn = (HashSet<String>) sharedPreferences.getStringSet(Fname, new HashSet<>());
        HashSet<String> stringsln = (HashSet<String>) sharedPreferences.getStringSet(Lname, new HashSet<>());
        HashSet<String> stringsmno = (HashSet<String>) sharedPreferences.getStringSet(Mobileeno, new HashSet<>());
        HashSet<String> stringsEid = (HashSet<String>) sharedPreferences.getStringSet(Email, new HashSet<>());
        Object[] arrayfn = Objects.requireNonNull(stringsfn).toArray();
        Object[] arrayln = Objects.requireNonNull(stringsln).toArray();
        Object[] arraymno = Objects.requireNonNull(stringsmno).toArray();
        Object[] arrayeid = Objects.requireNonNull(stringsEid).toArray();
        users = new User[arrayfn.length];
        for (int i = 0; i < arrayfn.length; i++) {
            users[i] = new User(arrayfn[i].toString(), arraymno[i].toString(), arrayln[i].toString(), arrayeid[i].toString());
        }
    }

    private void setAdapter() {
        recyclerView.setAdapter(adapter);
    }
}