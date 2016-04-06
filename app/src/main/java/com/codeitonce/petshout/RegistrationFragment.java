package com.codeitonce.petshout;


import android.graphics.Color;
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
        mFirstName = (EditText) v.findViewById(R.id.first_name_input);
        mLastName = (EditText) v.findViewById(R.id.last_name_input);
        mCity = (EditText) v.findViewById(R.id.city_input);
        mPostalCode = (EditText) v.findViewById(R.id.postal_code);
        mEmail = (EditText) v.findViewById(R.id.email_address_input);
        mPhoneNumber = (EditText) v.findViewById(R.id.phone_number_input);
        mPassword = (EditText) v.findViewById(R.id.password_input);
        mConfirmPassword = (EditText) v.findViewById(R.id.confirm_password_input);
        mRegister = (Button) v.findViewById(R.id.register_button);
        mPasswordMessage = (TextView) v.findViewById(R.id.password_confirmation_text_view);


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
                    mPasswordMessage.setVisibility(View.VISIBLE);
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

                try
                {

                    DBHandler db = new DBHandler(getActivity());
                    db.addUser(new User(mFirstName.getText().toString(), mLastName.getText().toString(),
                            mCity.getText().toString(), mPostalCode.getText().toString(), mEmail.getText().toString(),
                            mPhoneNumber.getText().toString(), mPassword.getText().toString()));

                    Toast.makeText(getActivity(), R.string.reg_successful, Toast.LENGTH_SHORT).show();
                    LoginFragment fragment;
                    fragment = new LoginFragment();
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.mainFrame, fragment);
                    ft.commit();
                }
                catch (NullPointerException n)
                {
                    Toast.makeText(getActivity(), R.string.complete_all_fields, Toast.LENGTH_SHORT).show();
                }






            }

        });

        return v;
    }



}
