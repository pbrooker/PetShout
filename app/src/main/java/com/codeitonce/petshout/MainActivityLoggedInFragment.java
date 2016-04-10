package com.codeitonce.petshout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainActivityLoggedInFragment extends Fragment {

    private Serializable userID = "";


    public MainActivityLoggedInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        return inflater.inflate(R.layout.fragment_main_activity_logged_in, container, false);
    }

}
