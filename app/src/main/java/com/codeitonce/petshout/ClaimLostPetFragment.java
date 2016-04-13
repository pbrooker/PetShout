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
    private static final String ARG_POST_ID = "post_id";
    private String mID;
    private String userEmail;


    public ClaimLostPetFragment() {
        // Required empty public constructor
    }

    public static ClaimLostPetFragment newInstance(String postObjectId)
    {
        Bundle args = new Bundle();
        args.putSerializable(ARG_POST_ID, postObjectId);
        Log.i("newInstance", "string is " + postObjectId);
        ClaimLostPetFragment fragment = new ClaimLostPetFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

            String postID = (String) getArguments().getSerializable(ARG_POST_ID);
            Log.d("ClaimFrag", "post object id is " + postID);
            DBHandler dbHandler = new DBHandler(getActivity());

            mPost = new Post();
            mPost = dbHandler.getPost(postID);
            mID = postID;



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_claim_lost_pet, container, false);

        mEmail = (TextView) view.findViewById(R.id.found_pet_email_textview);
        mBreed = (TextView) view.findViewById(R.id.claim_pet_breed);
        mGender = (TextView) view.findViewById(R.id.claim_pet_gender);
        mDescription = (TextView) view.findViewById(R.id.claim_pet_description);
        mLocation = (TextView) view.findViewById(R.id.claim_pet_location);
        mClaimButton = (Button) view.findViewById(R.id.claim_lost_pet);
        mPetImageView = (ImageView) view.findViewById(R.id.claim_pet_image);

//        BackendlessDataQuery query = new BackendlessDataQuery();
//        Log.d("mID", "mID = " + mID);
//        query.setWhereClause("objectId = " + "'" + mID + "'");
//        BackendlessCollection<Users> user = Backendless.Data.of(Users.class ).find( query );
//
//        Iterator<Users> iterator = user.getCurrentPage().iterator();
//
//        while (iterator.hasNext())
//        {
//            Users u = iterator.next();
//            userEmail = u.getObjectId();
//
//        }
//        query.setWhereClause("User.objectId = " + mID);
//        BackendlessCollection<Users> user = Backendless.Data.of(Users.class ).find( query );
//
//        Iterator<Users> iterator2 = user.getCurrentPage().iterator();
//
//        while (iterator2.hasNext())
//        {
//            Users user2 = iterator2.next();
//            userEmail = user2.getEmail();
//
//        }



        Picasso.with(getActivity()).load(mPost.getPostImagePath()).placeholder(R.drawable.catanddog).resize(500, 500).centerCrop().into(mPetImageView);

        //mEmail.setText(userEmail);
        mBreed.setText(mPost.getPostBreed().toString());
        mGender.setText(mPost.getPostGender().toString());
        mDescription.setText(mPost.getPostDescription().toString());
        mLocation.setText(mPost.getPostLocation().toString());









        return view;
    }



}
