package com.codeitonce.petshout;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;

import com.backendless.BackendlessUser;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class EditAccountFragment extends Fragment {

    private EditText mFirstName;
    private EditText mLastName;
    private EditText mCity;
    private EditText mEmail;
    private EditText mPhoneNumber;
    private Spinner mPetSpinner;
    private Boolean isValidUser;
    private String userEmail;
    private String userObjectID;
    private BackendlessUser currentbkuser;
    private TableRow mTableRow;
    private List<Pets> pets;
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
            //Log.i("current user", userObjectID.toString());
            Log.i("user email", userEmail);
            currentbkuser = new BackendlessUser();
            currentbkuser.setProperty("objectID", userObjectID);

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
        mPetSpinner = (Spinner) view.findViewById(R.id.pet_spinner);
        mUpdate = (Button) view.findViewById(R.id.update_button);
        mTableRow = (TableRow) view.findViewById(R.id.email_verify_row);
        mImageView = (ImageView) view.findViewById(R.id.cat_dog_profile);
        mEmailVerify = (TextView) view.findViewById(R.id.email_verify_textview);


        DBHandler db = new DBHandler(getActivity());

        pets = new ArrayList<>();

        pets = db.getPets(userObjectID);


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
                    mTableRow.setVisibility(View.GONE);
                    mEmailVerify.setVisibility(View.GONE);
                    mImageView.setVisibility(View.VISIBLE);
                }else
                {
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
                if (mFirstName.getText().toString() != null)
                {
                    currentbkuser.setProperty(Constents.USERS_FNAME, mFirstName.getText().toString());
                }
                if (mLastName.getText().toString() != null)
                {
                    currentbkuser.setProperty(Constents.USERS_LNAME, mLastName.getText().toString());
                }
                if (mCity.getText().toString() != null)
                {
                    currentbkuser.setProperty(Constents.USERS_CITY, mCity.getText().toString());
                }
                if (mEmail.getText().toString() != null && isEmail(mEmail.getText().toString()))
                {
                    currentbkuser.setProperty(Constents.USERS_EMAIL, mEmail.getText().toString());
                }

            }
        });









        return view;
    }


    private boolean isEmail(CharSequence target)
    {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
