package com.codeitonce.petshout;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.BackendlessCallback;

import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationFragment extends Fragment {

    private EditText mFirstName;
    private EditText mLastName;
    private EditText mCity;
    private EditText mEmail;
    private EditText mPhoneNumber;
    private EditText mPassword;
    private TextView mEmailVerify;
    private TextView mPasswordVerify;
    private EditText mConfirmPassword;
    private Button mRegister;
    private TextView mPasswordMessage;
    private ScrollView mScrollView;
    private TableRow mTableRow;
    private ImageView mLogo;
    private String mID;
//    private String counterName = "UserIdCounter";
//    Long counterValue = Backendless.Counters.incrementAndGet( counterName );
//
//
//    IAtomic<Integer>  userCounter = Backendless.Counters.of(counterName, Integer.class)




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
        mEmail = (EditText) v.findViewById(R.id.email_address_input);
        mPhoneNumber = (EditText) v.findViewById(R.id.phone_number_input);
        mPassword = (EditText) v.findViewById(R.id.password_input);
        mConfirmPassword = (EditText) v.findViewById(R.id.confirm_password_input);
        mRegister = (Button) v.findViewById(R.id.register_button);
        mPasswordMessage = (TextView) v.findViewById(R.id.password_confirmation_text_view);
        mEmailVerify = (TextView) v.findViewById(R.id.email_verify_textview);
        mPasswordVerify = (TextView) v.findViewById(R.id.password_verify_textview);
        mScrollView = (ScrollView) v.findViewById(R.id.scroll_view);
        mTableRow = (TableRow) v.findViewById(R.id.bottom_of_table);
        mLogo = (ImageView) v.findViewById(R.id.cat_dog_profile);

        mEmail.addTextChangedListener(new TextWatcher()
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
                String target = mEmail.getText().toString();

                if(!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches())
                {
                    mEmailVerify.setVisibility(View.GONE);
                    mLogo.setVisibility(View.VISIBLE);
                }else
                {
                    mEmailVerify.setVisibility(View.VISIBLE);
                    mEmailVerify.setTextColor(Color.RED);
                    mEmailVerify.setText(R.string.email_verify);
                    mScrollView.scrollTo(0, mTableRow.getScrollY());
                    mLogo.setVisibility(View.GONE);
                }
            }
        });


        mPassword.addTextChangedListener(new TextWatcher()
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
                String pass = mPassword.getText().toString();
                if(!(pass.matches(Constents.PASSWORD_PATTERN)))
                {
                    mLogo.setVisibility(View.GONE);
                    mPasswordVerify.setTextColor(Color.RED);
                    mPasswordVerify.setVisibility(View.VISIBLE);
                    mPasswordVerify.setText(R.string.pass_length);
                }else
                {
                    mLogo.setVisibility(View.VISIBLE);
                    mPasswordVerify.setVisibility(View.GONE);
                }
            }
        });


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


                if (!(isEmpty(mFirstName)) && !(isEmpty(mLastName)) && !(isEmpty(mCity))  &&
                        (isEmail(mEmail.getText().toString())) && !(isEmpty(mPhoneNumber)) && (mPassword.getText().toString()
                        .matches(Constents.PASSWORD_PATTERN)))
                {
                    mID = UUID.randomUUID().toString();
                    DBHandler db = new DBHandler(getActivity());
                    db.addUser(new Users(mID ,mFirstName.getText().toString(), mLastName.getText().toString(),
                            mCity.getText().toString(), mEmail.getText().toString(),
                            mPhoneNumber.getText().toString(), mPassword.getText().toString()));

                    Toast.makeText(getActivity(), R.string.reg_successful, Toast.LENGTH_SHORT).show();

                    BackendlessUser bkUser = new BackendlessUser();
                    bkUser.setEmail(mEmail.getText().toString());
                    bkUser.setPassword(mPassword.getText().toString());
                    bkUser.setProperty(Constents.USERS_FNAME, mFirstName.getText().toString());
                    bkUser.setProperty(Constents.USERS_LNAME, mLastName.getText().toString());
                    bkUser.setProperty(Constents.USERS_ID, mID.toString());
                    bkUser.setProperty(Constents.USERS_CITY, mCity.getText().toString());
                    bkUser.setProperty(Constents.USERS_PHONE, mPhoneNumber.getText().toString());


                    Backendless.UserService.register(bkUser, new BackendlessCallback<BackendlessUser>()
                    {
                        @Override
                        public void handleResponse(BackendlessUser backendlessUser)
                        {
                            Log.i("Registration", backendlessUser.getEmail() + " successfully registered");
                            LoginFragment fragment;
                            fragment = new LoginFragment();
                            FragmentTransaction ft = getFragmentManager().beginTransaction();
                            ft.replace(R.id.mainFrame, fragment);
                            ft.commit();
                        }

                    });

                } else
                {
                    Toast.makeText(getActivity(), R.string.complete_all_fields, Toast.LENGTH_SHORT).show();
                }
            }

        });



        return v;
    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    private boolean isEmail(CharSequence target)
    {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }



}
