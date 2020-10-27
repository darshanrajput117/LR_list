package com.biz.lrlist;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
@SuppressLint("NonConstantResourceId")
public class Loginfragment extends Fragment {

    public static final String TAG = "PermissionFragment";
    private static final int REQUEST_ALL = 3;
    private static String[] permissions;

    @BindView(R.id.et_emailid)
    AppCompatEditText emailid;
    @BindView(R.id.et_password)
    AppCompatEditText password;

    @BindView(R.id.btn_login)
    AppCompatButton login;
    @BindView(R.id.btn_register)
    AppCompatButton register;

    public static Loginfragment newInstance() {
        return new Loginfragment();
    }


    private void checkAllPermission() {
        if (ActivityCompat.checkSelfPermission(Objects.requireNonNull(getContext()), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "checkSelfPermission: ALL");
            requestAllPermissions();
        } else {
            Log.i(TAG, "else checkSelfPermission: ALL");
        }
    }

    private void requestAllPermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(Objects.requireNonNull(getActivity()), Manifest.permission.CAMERA)
                || ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) || ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                || ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_ALL);
        } else {
            ActivityCompat.requestPermissions(Objects.requireNonNull(getActivity()), permissions, REQUEST_ALL);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_ALL:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getActivity(), getString(R.string.msg_permission_granted), Toast.LENGTH_SHORT).show();
                } else {
                    ActivityCompat.requestPermissions(Objects.requireNonNull(getActivity()), new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_ALL);
                }
                break;
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        permissions = new String[]{
                Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION};
        checkAllPermission();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loginfragment, container, false);
        return view;
    }

    @OnClick({R.id.btn_login, R.id.btn_register})
    public void OnClickAction(View view) {
        switch (view.getId()) {
            case (R.id.btn_login):
                LoginFormValidation();
                break;
            case (R.id.btn_register):
                Fragment fragment = new Registrationfragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
        }
    }

    private boolean LoginFormValidation() {
        if (!new FormValidation().checkEmptyET(emailid)) {
            if (!new FormValidation().checkEmptyET(password)) {
                if (new FormValidation().checkemailid(emailid)) {
                    if (new FormValidation().checkpassword(password)) {
                        Toast.makeText(getContext(), "Successfully Login...", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Password must be 6 characters,1 Uppercase,1 Lowercase,1 Number.", Toast.LENGTH_SHORT).show();
                        password.requestFocus();
                    }
                } else {
                    Toast.makeText(getContext(), "Emailid must be xyz@xyz.xyz", Toast.LENGTH_SHORT).show();
                    emailid.requestFocus();
                }
            } else {
                Toast.makeText(getContext(), "Password is empty!!", Toast.LENGTH_SHORT).show();
                password.requestFocus();
            }
        } else {
            Toast.makeText(getContext(), "EmailId is emplty!!!", Toast.LENGTH_SHORT).show();
            emailid.requestFocus();
        }
        return false;
    }
}