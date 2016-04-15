package com.codeitonce.petshout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import it.sephiroth.android.library.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class ClaimLostPetFragment extends Fragment {

    private String postObjectId;
    private TextView mEmail;
    private TextView mBreed;
    private TextView mGender;
    private TextView mDescription;
    private TextView mLocation;
    private Button mClaimButton;
    private ImageView mPetImageView;
    private Post mPost;
    private String mID;
    private String userEmail;


    public ClaimLostPetFragment() {
        // Required empty public constructor
    }

    public static ClaimLostPetFragment newInstance(String postObjectId)
    {
        Bundle args = new Bundle();
        args.putSerializable(Constents.EXTRA_POST_ID, postObjectId);
        Log.i("newInstance", "string is " + postObjectId);
        ClaimLostPetFragment fragment = new ClaimLostPetFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

            String postObjectID = (String) getArguments().getSerializable(Constents.EXTRA_POST_ID);
            Log.d("ClaimFrag", "post object id is " + postObjectID);
            DBHandler dbHandler = new DBHandler(getActivity());

            mPost = new Post();
            mPost = dbHandler.getPost(postObjectID);
            mID = postObjectID;
            //Log.i("Post email", "post email is " + mPost.getUserEmail().toString());

        dbHandler.close();



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_claim_lost_pet, container, false);


        mEmail = (TextView) view.findViewById(R.id.claim_pet_email);
        mBreed = (TextView) view.findViewById(R.id.claim_pet_breed);
        mGender = (TextView) view.findViewById(R.id.claim_pet_gender);
        mDescription = (TextView) view.findViewById(R.id.claim_pet_description);
        mLocation = (TextView) view.findViewById(R.id.claim_pet_location);
        mClaimButton = (Button) view.findViewById(R.id.claim_lost_pet);
        mPetImageView = (ImageView) view.findViewById(R.id.claim_pet_image);


        try
        {
            Picasso.with(getActivity()).load(mPost.getPostImagePath()).placeholder(R.drawable.catanddog).resize(500, 500).centerCrop().into(mPetImageView);
        }
        catch(NullPointerException n)
        {

        }
        mEmail.setText(mPost.getUserEmail().toString());
        mBreed.setText(mPost.getPostBreed().toString());
        mGender.setText(mPost.getPostGender().toString());
        mDescription.setText(mPost.getPostDescription().toString());
        mLocation.setText(mPost.getPostLocation().toString());

        mClaimButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mEmail.setVisibility(View.VISIBLE);
            }
        });

        return view;
    }



}
