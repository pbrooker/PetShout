package com.codeitonce.petshout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReportFoundPetFragment extends Fragment {

    private EditText mEmail;
    private EditText mPhoneNumber;
    private EditText mBreed;
    private EditText mLocation;
    private Spinner mSpecies;
    private RadioGroup mGender;
    private Button mAddImage;


    public ReportFoundPetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_report_found_pet, container, false);

        mEmail = (EditText) view.findViewById(R.id.emailAddressInput);
        mPhoneNumber = (EditText) view.findViewById(R.id.phoneNumberInput);
        mBreed = (EditText) view.findViewById(R.id.breedInput);
        mLocation = (EditText) view.findViewById(R.id.locationInput);
        mSpecies = (Spinner) view.findViewById(R.id.speciesSpinner);
        mGender = (RadioGroup) view.findViewById(R.id.gender_group);
        mAddImage = (Button) view.findViewById(R.id.imageButton);

        return  view;
    }

}
