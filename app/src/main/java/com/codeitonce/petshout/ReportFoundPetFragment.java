package com.codeitonce.petshout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReportFoundPetFragment extends Fragment
{

    private EditText mEmail;
    private EditText mPhoneNumber;
    private EditText mBreed;
    private EditText mLocation;
    private Spinner mSpecies;
    private RadioGroup mGender;
    private Button mAddImage;
    private Button mSubmit;


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
        mSubmit = (Button) view.findViewById(R.id.submitButton);



        ArrayAdapter<CharSequence> mArrayAdapter;
        mArrayAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_data, android.R.layout.simple_spinner_item);

        mSpecies.setAdapter(mArrayAdapter);

        mArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        mSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                DBHandler db = new DBHandler(getActivity());
                //db.addPet(new Pet(mBreed.getText().toString(), mLocation.getText().toString()));
            }
        });

        mSpecies.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        return  view;
    }

}
