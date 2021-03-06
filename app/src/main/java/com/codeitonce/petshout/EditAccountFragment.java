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
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.exceptions.BackendlessException;
import com.google.gson.Gson;


public class EditAccountFragment extends Fragment {

    private EditText mFirstName;
    private EditText mLastName;
    private EditText mCity;
    private EditText mEmail;
    private EditText mPhoneNumber;
    private Boolean isValidUser;
    private String userEmail;
    private String userObjectID;
    private BackendlessUser currentbkuser;
    private TableRow mTableRow;
    private Button mUpdate;
    private ImageView mImageView;
    private TextView mEmailVerify;



    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        String jsonMyObject = "";
        Bundle extras = getActivity().getIntent().getExtras();
        if(extras != null)
        {
            jsonMyObject = extras.getString("user");
            isValidUser = extras.getBoolean("validUser");
            Users user = new Gson().fromJson(jsonMyObject, Users.class);
            userEmail = user.getEmail();
            userObjectID = user.getObjectId();
            Log.i("edit account user", userObjectID.toString());
            //Log.i("user email", userEmail);
            currentbkuser = new BackendlessUser();
            currentbkuser.setProperty("objectId", userObjectID);

        }
        else
        {
            isValidUser = false;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_edit_account, container, false);



        mFirstName = (EditText) view.findViewById(R.id.first_name_input);
        mLastName = (EditText) view.findViewById(R.id.last_name_input);
        mCity = (EditText) view.findViewById(R.id.city_input);
        mEmail = (EditText) view.findViewById(R.id.email_address_input);
        mPhoneNumber =(EditText) view.findViewById(R.id.phone_number_input);
        mUpdate = (Button) view.findViewById(R.id.update_button);
        mTableRow = (TableRow) view.findViewById(R.id.email_verify_row);
        mImageView = (ImageView) view.findViewById(R.id.cat_dog_profile);
        mEmailVerify = (TextView) view.findViewById(R.id.email_verify_textview);




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

                if (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches())
                {
                    mTableRow.setVisibility(View.GONE);
                    mEmailVerify.setVisibility(View.GONE);
                    mImageView.setVisibility(View.VISIBLE);
                } else
                {
                    mTableRow.setVisibility(View.VISIBLE);
                    mEmailVerify.setVisibility(View.VISIBLE);
                    mEmailVerify.setTextColor(Color.RED);
                    mEmailVerify.setText(R.string.email_verify);
                    mImageView.setVisibility(View.GONE);
                }
            }
        });

        mUpdate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (!(isEmpty(mFirstName)))
                {
                    currentbkuser.setProperty(Constents.USERS_FNAME, mFirstName.getText().toString());
                }
                if (!(isEmpty(mLastName)))
                {
                    currentbkuser.setProperty(Constents.USERS_LNAME, mLastName.getText().toString());
                }
                if ((!(isEmpty(mCity))))
                {
                    currentbkuser.setProperty(Constents.USERS_CITY, mCity.getText().toString());
                }
                if (!(isEmpty(mEmail)) && isEmail(mEmail.getText().toString()))
                {
                    currentbkuser.setProperty(Constents.USERS_EMAIL, mEmail.getText().toString());
                }



                try
                {

                    Backendless.UserService.update(currentbkuser, new DefaultCallback<BackendlessUser>(getActivity())
                    {
                        @Override
                        public void handleResponse(BackendlessUser backendlessUser)
                        {
                            super.handleResponse(backendlessUser);

                            MainActivityLoggedInFragment fragment;
                            fragment = new MainActivityLoggedInFragment();
                            FragmentTransaction ft = getFragmentManager().beginTransaction();
                            ft.replace(R.id.mainFrame, fragment);
                            ft.commit();

                        }

                    });
                } catch (BackendlessException e)
                {
                    Toast.makeText(getActivity(), "Error - record not added, please try again", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;
    }


    private boolean isEmail(CharSequence target)
    {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0) {
            return false;
        } else {
            return true;
        }
    }
}
