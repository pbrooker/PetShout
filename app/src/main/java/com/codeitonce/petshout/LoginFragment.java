package com.codeitonce.petshout;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment
{

    private EditText mEmailAddress;
    private EditText mPassword;
    private Button mSubmit;
    private Button mRegister;
    private String currentUserId;
    private TextView mRestoreLogin;
    private Users user;
    private String userBackendlessID;


    public LoginFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putString(Constents.SAVED_CURRENT_USER, Backendless.UserService.loggedInUser());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        mEmailAddress = (EditText) view.findViewById(R.id.email_address_input);
        mPassword = (EditText) view.findViewById(R.id.password_input);
        mSubmit = (Button) view.findViewById(R.id.submit_button);
        mRegister = (Button) view.findViewById(R.id.register_button);
        mRestoreLogin = (TextView) view.findViewById(R.id.restore_password_button);




        mSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                String identity = mEmailAddress.getText().toString();
                String password = mPassword.getText().toString();
                //boolean rememberLogin = rememberLoginBox.isChecked();

                Backendless.UserService.isValidLogin(new DefaultCallback<Boolean>(getActivity())
                {
                    @Override
                    public void handleResponse(Boolean isValidLogin)
                    {
                        if (isValidLogin && Backendless.UserService.CurrentUser() == null)
                        {
                            currentUserId = Backendless.UserService.loggedInUser();

                            if (!currentUserId.equals(""))
                            {
                                Backendless.UserService.findById(currentUserId, new DefaultCallback<BackendlessUser>(getActivity(), "Logging in...")
                                {
                                    @Override
                                    public void handleResponse(BackendlessUser currentUser)
                                    {
                                        super.handleResponse(currentUser);
                                        Backendless.UserService.setCurrentUser(currentUser);
                                        userBackendlessID = currentUser.getObjectId();

                                        MainActivityLoggedInFragment fragment;
                                        fragment = new MainActivityLoggedInFragment();
                                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                                        ft.replace(R.id.mainFrame, fragment);
                                        ft.commit();

                                    }
                                });
                            }
                        }

                        super.handleResponse(isValidLogin);
                    }
                });

                Backendless.UserService.login(identity, password, new DefaultCallback<BackendlessUser>(getActivity()) {

                    public void handleResponse(BackendlessUser backendlessUser) {
                        super.handleResponse(backendlessUser);


                        user = new Users(backendlessUser.getObjectId(), backendlessUser.getProperty(Constents.USERS_FNAME).toString(),
                                backendlessUser.getProperty(Constents.USERS_LNAME).toString(), backendlessUser.getProperty(Constents.USERS_CITY).toString(),
                                backendlessUser.getProperty(Constents.USERS_POSTAL_CODE).toString(),
                                backendlessUser.getEmail(), backendlessUser.getProperty(Constents.USERS_PHONE).toString(), backendlessUser.getPassword());

//
//                        SharedPreferences myPreferences = getActivity().getSharedPreferences(Constents.PREFS_LOGGED_IN, 0);
//                        SharedPreferences.Editor editor = myPreferences.edit();
//                        editor.putBoolean("isLoggedIn", true);
//                        editor.putString(UserID, "userID");
//                        editor.commit();

                        //Bundle args = new Bundle();
                        Intent args = new Intent(getActivity(), MainActivity.class);
                        args.putExtra("user", new Gson().toJson(user));
                        startActivity(args);


                        MainActivityLoggedInFragment fragment;
                        fragment = new MainActivityLoggedInFragment();
                        //fragment.setArguments(args);
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.mainFrame, fragment);
                        ft.commit();
                    }
                } //rememberLogin
                );

                mRegister.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RegistrationFragment fragment;
                        fragment = new RegistrationFragment();
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.mainFrame, fragment);
                        ft.commit();
                    }
                });

            }

        });


        mRestoreLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RestorePasswordFragment fragment;
                fragment = new RestorePasswordFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.mainFrame, fragment);
                ft.commit();
            }
        });



        return view;
    }
}


