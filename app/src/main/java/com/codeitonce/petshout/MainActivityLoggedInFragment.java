package com.codeitonce.petshout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainActivityLoggedInFragment extends Fragment {

    private ImageButton mLostAPet;
    private ImageButton mFoundAPet;


    public MainActivityLoggedInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_activity_logged_in, container, false);

        mLostAPet = (ImageButton) view.findViewById(R.id.image_button);
        mFoundAPet = (ImageButton) view.findViewById(R.id.found_pet_button);

        mLostAPet.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                CreateLostPetPostFragment fragment;
                fragment = new CreateLostPetPostFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.mainFrame, fragment);
                ft.commit();
            }
        });

        mFoundAPet.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ReportFoundPetFragment fragment;
                fragment = new ReportFoundPetFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.mainFrame, fragment);
                ft.commit();
            }
        });

        return view;
    }

}
