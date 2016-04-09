package com.codeitonce.petshout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditLostPetPostFragment extends Fragment
{
    private Spinner mSpecies;
    private String species;


    public EditLostPetPostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_lost_pet_post, container, false);

        ArrayAdapter<CharSequence> mArrayAdapter;
        mArrayAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_data, R.layout.spinner_item);

        mSpecies.setAdapter(mArrayAdapter);

        mSpecies = (Spinner) view.findViewById(R.id.species_spinner);


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

        return view;
    }

}
