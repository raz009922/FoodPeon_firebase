package com.example.dc_raz.foodpeon_again.Activity;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.dc_raz.foodpeon_again.Model.User;
import com.example.dc_raz.foodpeon_again.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class MainActivity extends AppCompatActivity {

    Button buttonlogin, signup;
    LinearLayout rootLayout;
    //MaterialEditText
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;
    //DatabaseReference kitchens;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath("fonts/Arkhip_font.ttf")
                .setFontAttrId(R.attr.fontPath).build());
        setContentView(R.layout.activity_main);


        //Firebase should be init here
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");
        //kitchens=db.getReference("Kitchens");

        //View
        buttonlogin = findViewById(R.id.login_btn);
        signup = findViewById(R.id.sgnup_btn);
        rootLayout = findViewById(R.id.rootLayout1);

        //Event
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegisterDialog();
            }
        });

        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoginDialog();
            }
        });
    }

    private void showLoginDialog() {

        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("SIGNIN ");
        dialog.setMessage("Please use email to signin");

        //now inflate the view

        LayoutInflater inflater = LayoutInflater.from(this);
        View layout_login = inflater.inflate(R.layout.login_layout, null);

        //init view now

        final MaterialEditText editEmail = layout_login.findViewById(R.id.editEmail);
        final MaterialEditText editPass = layout_login.findViewById(R.id.editPassword);


        dialog.setView(layout_login);

        //button setting
        dialog.setPositiveButton("SIGNIN", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();


                //validation checking

                if (TextUtils.isEmpty(editEmail.getText().toString())) {

                    Snackbar.make(rootLayout, "Please enter Email Adress", Snackbar.LENGTH_SHORT)
                            .show();
                    return;

                }

                if (TextUtils.isEmpty(editPass.getText().toString())) {

                    Snackbar.make(rootLayout, "Please enter Password", Snackbar.LENGTH_SHORT)
                            .show();
                    return;

                }
                if (editPass.getText().toString().length() < 6) {

                    Snackbar.make(rootLayout, "Password is too short !!!!", Snackbar.LENGTH_SHORT)
                            .show();
                    return;

                }

                //Login With Firebase gose here

                auth.signInWithEmailAndPassword(editEmail.getText().toString(), editPass.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {


                                startActivity(new Intent(MainActivity.this, MapsActivity.class));
                                finish();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Snackbar.make(rootLayout, "Failed!!!" + e.getMessage(), Snackbar.LENGTH_SHORT)
                                        .show();
                            }
                        });
            }
        });

        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


        dialog.show();


    }

    private void showRegisterDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("SIGNUP ");
        dialog.setMessage("Please use email to register");

        //now inflate the view

        LayoutInflater inflater = LayoutInflater.from(this);
        View layout_register = inflater.inflate(R.layout.register_alyout, null);

        //init view now

        final MaterialEditText editEmail = layout_register.findViewById(R.id.editEmail);
        final MaterialEditText editPass = layout_register.findViewById(R.id.editPassword);
        final MaterialEditText editName = layout_register.findViewById(R.id.editName);
        final MaterialEditText editPhone = layout_register.findViewById(R.id.editPhoneNumber);

        dialog.setView(layout_register);

        //button setting
        dialog.setPositiveButton("SIGNUP", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
                //validation checking

                if (TextUtils.isEmpty(editEmail.getText().toString())) {

                    Snackbar.make(rootLayout, "Please enter Email Adress", Snackbar.LENGTH_SHORT)
                            .show();
                    return;

                }

                if (TextUtils.isEmpty(editPass.getText().toString())) {

                    Snackbar.make(rootLayout, "Please enter Password", Snackbar.LENGTH_SHORT)
                            .show();
                    return;

                }
                if (editPass.getText().toString().length() < 6) {

                    Snackbar.make(rootLayout, "Password is too short !!!!", Snackbar.LENGTH_SHORT)
                            .show();
                    return;

                }

                if (TextUtils.isEmpty(editName.getText().toString())) {

                    Snackbar.make(rootLayout, "Please enter your Name", Snackbar.LENGTH_SHORT)
                            .show();
                    return;

                }
                if (TextUtils.isEmpty(editPhone.getText().toString())) {

                    Snackbar.make(rootLayout, "Please enter your Phone Nember", Snackbar.LENGTH_SHORT)
                            .show();
                    return;

                }

                //Registration For new Useer

                auth.createUserWithEmailAndPassword(editEmail.getText().toString(),
                        editPass.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {

                                //here we save the users in the firebase database

                                User user = new User();
                                user.setEmail(editEmail.getText().toString());
                                user.setPass(editPass.getText().toString());
                                user.setName(editName.getText().toString());
                                user.setPhone(editPhone.getText().toString());

                               /* this users is the DatabaseReference of the firebase
                                here we can use the UID of firebase Authentication
                                of current user after registration is okay, we have an uid.*/

                                users.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {

                                                Snackbar.make(rootLayout, "SignUp Successful", Snackbar.LENGTH_SHORT)
                                                        .show();

                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Snackbar.make(rootLayout, "Failed " + e.getMessage(), Snackbar.LENGTH_SHORT)
                                                        .show();

                                            }
                                        });


                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(rootLayout, "Failed " + e.getMessage(), Snackbar.LENGTH_SHORT)
                                .show();

                    }
                });


            }
        });

        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });
        dialog.show();


    }


}
