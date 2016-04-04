package com.codeitonce.petshout;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationFragment extends Fragment {

    EditText mFirstName;
    EditText mLastName;
    EditText mCity;
    EditText mPostalCode;
    EditText mEmail;
    EditText mPhoneNumber;
    EditText mPassword;
    EditText mConfirmPassword;
    Button mRegister;
    TextView mPasswordMessage;


    public RegistrationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_registration, container, false);
        mFirstName = (EditText) v.findViewById(R.id.firstNameInput);
        mLastName = (EditText) v.findViewById(R.id.lastNameInput);
        mCity = (EditText) v.findViewById(R.id.cityInput);
        mPostalCode = (EditText) v.findViewById(R.id.postalCode);
        mEmail = (EditText) v.findViewById(R.id.emailAddressInput);
        mPhoneNumber = (EditText) v.findViewById(R.id.phoneNumberInput);
        mPassword = (EditText) v.findViewById(R.id.passwordInput);
        mConfirmPassword = (EditText) v.findViewById(R.id.confirmPasswordInput);
        mRegister = (Button) v.findViewById(R.id.registerButton);
        mPasswordMessage = (TextView) v.findViewById(R.id.password_confirmation_TextView);


        mConfirmPassword.addTextChangedListener(new TextWatcher()
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
                String pass1 = mPassword.getText().toString();
                String pass2 = mConfirmPassword.getText().toString();
                if(pass1.equals(pass2))
                {
                    mPasswordMessage.setVisibility(View.VISIBLE);
                    mPasswordMessage.setTextColor(Color.GREEN);
                    mPasswordMessage.setText(R.string.passwords_matched);
                }
                else
                {
                    mPasswordMessage.setVisibility(View.INVISIBLE);
                    mPasswordMessage.setTextColor(Color.RED);
                    mPasswordMessage.setText(R.string.passwords_not_matched);

                }
            }
        });


        mRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                DBHandler db = new DBHandler(getActivity());
                db.addUser(new User(mFirstName.getText().toString(), mLastName.getText().toString(),
                        mCity.getText().toString(), mPostalCode.getText().toString(), mEmail.getText().toString(),
                        mPhoneNumber.getText().toString(), mPassword.getText().toString() ));

                Toast.makeText(getActivity(), "Registration Successful", Toast.LENGTH_SHORT).show();

            }

        });

        return v;
    }



}
