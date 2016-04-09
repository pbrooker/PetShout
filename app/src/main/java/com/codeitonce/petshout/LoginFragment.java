package com.codeitonce.petshout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;

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
    private ArrayList<Users> mUserArrayList;
    private Users mCurrentUser;


    public LoginFragment()
    {
        // Required empty public constructor
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
        DBHandler dbHandler = new DBHandler(getActivity());
        mUserArrayList = dbHandler.getUsersArray();


        Backendless.UserService.isValidLogin(new DefaultCallback<Boolean>(getActivity())
        {
            @Override
            public void handleResponse(Boolean isValidLogin)
            {
                if (isValidLogin && Backendless.UserService.CurrentUser() == null)
                {
                    String currentUserId = Backendless.UserService.loggedInUser();

                    if (!currentUserId.equals(""))
                    {
                        Backendless.UserService.findById(currentUserId, new DefaultCallback<BackendlessUser>(getActivity(), "Logging in...")
                        {
                            @Override
                            public void handleResponse(BackendlessUser currentUser)
                            {
                                super.handleResponse(currentUser);
                                Backendless.UserService.setCurrentUser(currentUser);
                                MainActivityLoggedInFragment fragment;
                                fragment = new MainActivityLoggedInFragment();
                                FragmentTransaction ft = getFragmentManager().beginTransaction();
                                ft.replace(R.id.mainFrame, fragment);
                                ft.commit();
//                                        startActivity( new Intent( getBaseContext(), LoginSuccessActivity.class ) );
//                                        finish();
                            }
                        });
                    }
                }

                super.handleResponse(isValidLogin);
            }
        });



        mSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                String identity = mEmailAddress.getText().toString();
                String password = mPassword.getText().toString();
                //boolean rememberLogin = rememberLoginBox.isChecked();

                Backendless.UserService.login(identity, password, new DefaultCallback<BackendlessUser>(getActivity())
                {
                    public void handleResponse(BackendlessUser backendlessUser)
                    {
                        super.handleResponse(backendlessUser);
                        //startActivity( new Intent(getActivity(), LoginSuccessActivity.class ) );
                        // finish();
                        MainActivityLoggedInFragment fragment;
                        fragment = new MainActivityLoggedInFragment();
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.mainFrame, fragment);
                        ft.commit();
                    }
                } //rememberLogin
                );

                mRegister.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        RegistrationFragment fragment;
                        fragment = new RegistrationFragment();
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.mainFrame, fragment);
                        ft.commit();
                    }
                });


            }


        });
        return view;
    }
}


