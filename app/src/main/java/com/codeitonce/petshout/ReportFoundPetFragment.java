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
import android.widget.RadioButton;
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
    private EditText mPetDescription;
    private Spinner mSpecies;
    private RadioGroup mGender;
    private Button mAddImage;
    private Button mSubmit;
    private String species;
    private String gender = "";
    private static final int SELECT_PHOTO = 0;



    public ReportFoundPetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_report_found_pet, container, false);

        mEmail = (EditText) view.findViewById(R.id.email_address_input);
        mPhoneNumber = (EditText) view.findViewById(R.id.phone_number_input);
        mBreed = (EditText) view.findViewById(R.id.breed_input);
        mLocation = (EditText) view.findViewById(R.id.location_input);
        mPetDescription = (EditText) view.findViewById(R.id.pet_description);
        mSpecies = (Spinner) view.findViewById(R.id.species_spinner);
        mGender = (RadioGroup) view.findViewById(R.id.gender_group);
        mAddImage = (Button) view.findViewById(R.id.image_button);
        mSubmit = (Button) view.findViewById(R.id.submit_button);

        final RadioButton selection = (RadioButton) view.findViewById(mGender.getCheckedRadioButtonId());



        ArrayAdapter<CharSequence> mArrayAdapter;
        mArrayAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_data, android.R.layout.simple_spinner_item);

        mSpecies.setAdapter(mArrayAdapter);

        mArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        mSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    switch (selection.getId())
                    {
                        case R.id.male_radio:
                            gender = "M";
                            break;
                        case R.id.female_radio:
                            gender = "F";
                            break;
                    }
                } catch (NullPointerException n)
                {

                }

                DBHandler db = new DBHandler(getActivity());
                db.addPost(new Post(mLocation.getText().toString(), "F", mBreed.getText().toString(),
                        gender, species, mEmail.getText().toString(), mBreed.getText().toString(),
                        mPetDescription.getText().toString(), mPhoneNumber.getText().toString()));
            }
        });

        mSpecies.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                int num = position;
                species = "";

                switch (num)
                {
                    case 0:
                        species = getString(R.string.dog);
                        break;
                    case 1:
                        species = getString(R.string.cat);
                        break;
                    case 2:
                        species = getString(R.string.bird);
                        break;
                    case 3:
                        species = getString(R.string.rat);
                        break;
                    case 4:
                        species = getString(R.string.hedgehog);
                        break;
                    case 5:
                        species = getString(R.string.pig);
                        break;
                    case 6:
                        species = getString(R.string.horse);
                        break;
                    case 7:
                        species = getString(R.string.reptile);
                        break;
                    case 8:
                        species = getString(R.string.guinea_pig);
                        break;
                    case 9:
                        species = getString(R.string.rabbit);
                        break;
                    case 10:
                        species = getString(R.string.other);
                        break;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        mAddImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               // ImageLoaderDialog imageLoaderDialog = new ImageLoaderDialog(imageLoader);
                //imageLoaderDialog.show(getFragmentManager(), "imageLoaderDialog");

            }
        });


        return  view;
    }





}
