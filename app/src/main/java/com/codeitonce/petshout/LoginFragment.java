package com.codeitonce.petshout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private EditText mEmailAddress;
    private EditText mPassword;
    private Button mSubmit;
    private Button mRegister;
    private ArrayList<User> mUserArrayList;
    private User mCurrentUser;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        mEmailAddress =  (EditText) view.findViewById(R.id.email_address_input);
        mPassword = (EditText) view.findViewById(R.id.password_input);
        mSubmit = (Button) view.findViewById(R.id.submit_button);
        mRegister = (Button) view.findViewById(R.id.register_button);
        DBHandler dbHandler = new DBHandler(getActivity());
        mUserArrayList = dbHandler.getUsersArray();


        mEmailAddress.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                for(int x = 0; x < mUserArrayList.size(); x++)
                {
                    if(mEmailAddress.getText().toString().equals(mUserArrayList.get(x).getEmail()))
                    {
                        mCurrentUser = mUserArrayList.get(x);
                    }
                    else
                    {
                        mCurrentUser = new User();
                    }
                }
            }
        });

        mSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(mPassword.getText().toString().equals(mCurrentUser.getPassword()))
                {
                    MainActivityLoggedInFragment fragment;
                    fragment = new MainActivityLoggedInFragment();
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.mainFrame, fragment);
                    ft.commit();
                }
                else
                {
                    Toast.makeText(getActivity(), "Email or Password Incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });


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



        return view;
    }

}
