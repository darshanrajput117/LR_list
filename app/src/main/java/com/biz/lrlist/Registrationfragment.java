package com.biz.lrlist;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.io.IOException;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;
@SuppressLint("NonConstantResourceId")
public class Registrationfragment extends Fragment {

    Uri image_uri;
    @BindView(R.id.et_emailid)
    AppCompatEditText emailid;
    @BindView(R.id.et_password)
    AppCompatEditText password;
    @BindView(R.id.et_cpassword)
    AppCompatEditText cpassword;
    @BindView(R.id.et_fname)
    AppCompatEditText fname;
    @BindView(R.id.et_lname)
    AppCompatEditText lname;
    @BindView(R.id.et_mobileno)
    AppCompatEditText mno;
    @BindView(R.id.iv_userimage)
    AppCompatImageView iv_uimage;
    @BindView(R.id.ib_uimage)
    AppCompatImageButton ib_uploadimage;

    public static Registrationfragment newInstance() {
        return new Registrationfragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_registrationfragment, container, false);
    }

    @OnClick({R.id.btn_register, R.id.ib_uimage})
    public void OnClickAction(View view) {
        switch (view.getId()) {
            case (R.id.ib_uimage): {
                selectImage();
                break;
            }
            case (R.id.btn_register): {
                RegisterFormValidation();
                break;
            }
        }
    }

    private void selectImage() {
        CharSequence[] options = {getString(R.string.option_capture_image), getString(R.string.option_choose_image)};
        new AlertDialog.Builder(getContext()).setTitle(getString(R.string.option_choose_image))
                .setItems(options, (dialog, which) -> {
                    if (options[which].equals(getString(R.string.option_capture_image))) {
                        captureImage();
                    } else if (options[which].equals(getString(R.string.option_choose_image))) {
                        chooseImage();
                    }
                }).show();
    }

    private void captureImage() {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        int CAPTURE_IMAGE_REQUEST = 0;
        startActivityForResult(Intent.createChooser(intent, getString(R.string.option_capture_image)), CAPTURE_IMAGE_REQUEST);
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        int PICK_IMAGE_REQUEST = 1;
        startActivityForResult(Intent.createChooser(intent, getString(R.string.option_choose_image)), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK && data != null) {
                    Bitmap bitmap = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
                    iv_uimage.setImageBitmap(bitmap);
                }
                break;
            case 1:
                if (resultCode == RESULT_OK && data != null && data.getData() != null) {
                    image_uri = data.getData();

                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(Objects.requireNonNull(getActivity()).getContentResolver(), image_uri);
                        iv_uimage.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    private void RegisterFormValidation() {
        if (!new FormValidation().checkEmptyET(fname)) {
            if (!new FormValidation().checkEmptyET(lname)) {
                if (!new FormValidation().checkEmptyET(emailid)) {
                    if (!new FormValidation().checkEmptyET(password)) {
                        if (!new FormValidation().checkEmptyET(cpassword)) {
                            if (!new FormValidation().checkEmptyET(mno)) {
                                if (new FormValidation().checkname(fname)) {
                                    if (new FormValidation().checkname(lname)) {
                                        if (new FormValidation().checkemailid(emailid)) {
                                            if (new FormValidation().checkpassword(password)) {
                                                if (new FormValidation().checkconfirmpassword(cpassword, password)) {
                                                    if (new FormValidation().checkMobileNumber(mno)) {
                                                        Fragment fragment = new Listfragment();
                                                        FragmentManager fm = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                                                        FragmentTransaction ft = fm.beginTransaction();
                                                        ft.replace(R.id.content_frame, fragment);
                                                        ft.addToBackStack(null);
                                                        ft.commit();
                                                    } else {
                                                        Toast.makeText(getContext(), "Mobile no must be 10 digits.", Toast.LENGTH_LONG).show();
                                                        mno.requestFocus();
                                                    }
                                                } else {
                                                    Toast.makeText(getContext(), "Password and Confirm Password are not matched!!.", Toast.LENGTH_LONG).show();
                                                    cpassword.requestFocus();
                                                }
                                            } else {
                                                Toast.makeText(getContext(), "Password must be 6 characters,1 Uppercase,1 Lowercase,1 Number.", Toast.LENGTH_LONG).show();
                                                password.requestFocus();
                                            }
                                        } else {
                                            Toast.makeText(getContext(), "Emailid must be xyz@xyz.xyz", Toast.LENGTH_LONG).show();
                                            emailid.requestFocus();
                                        }
                                    } else {
                                        Toast.makeText(getContext(), "Last Name must be xyz not any inputed digit or speacial characters.", Toast.LENGTH_LONG).show();
                                        lname.requestFocus();
                                    }
                                } else {
                                    Toast.makeText(getContext(), "First Name must be xyz not any inputed digit or speacial characters.", Toast.LENGTH_LONG).show();
                                    fname.requestFocus();
                                }
                            } else {
                                Toast.makeText(getContext(), "Mobile No is empty.", Toast.LENGTH_LONG).show();
                                mno.requestFocus();
                            }
                        } else {
                            Toast.makeText(getContext(), "Confirm Password is empty.", Toast.LENGTH_LONG).show();
                            cpassword.requestFocus();
                        }
                    } else {
                        Toast.makeText(getContext(), "Password is empty.", Toast.LENGTH_LONG).show();
                        password.requestFocus();
                    }
                } else {
                    Toast.makeText(getContext(), "EmailId is empty.", Toast.LENGTH_LONG).show();
                    emailid.requestFocus();
                }
            } else {
                Toast.makeText(getContext(), "Last Name is empty.", Toast.LENGTH_LONG).show();
                lname.requestFocus();
            }
        } else {
            Toast.makeText(getContext(), "First Name is empty.", Toast.LENGTH_LONG).show();
            fname.requestFocus();
        }
    }
}