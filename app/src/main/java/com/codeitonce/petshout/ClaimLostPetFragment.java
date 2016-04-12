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
    private TextView mAddinfo;
    private Button mClaimButton;
    private ImageView mPetImageView;
    private Post mPost;


    public ClaimLostPetFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Bundle extras = getActivity().getIntent().getExtras();

        if(extras != null)
        {
            postObjectId = extras.getString("objectId");
        }
        else
        {
            Log.d("PostIdString", "post id string is null");
        }

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
        mAddinfo = (TextView) view.findViewById(R.id.claim_pet_addinfo);
        mClaimButton = (Button) view.findViewById(R.id.claim_lost_pet);
        mPetImageView = (ImageView) view.findViewById(R.id.claim_pet_image);

//        if(postObjectId != null)
//        {
//            AsyncCallback<BackendlessCollection<Post>> callback = new AsyncCallback<BackendlessCollection<Post>>()
//            {
//                @Override
//                public void handleResponse(BackendlessCollection<Post> response)
//                {
//                    Iterator
//                }
//
//                @Override
//                public void handleFault(BackendlessFault fault)
//                {
//
//                }
//            };
//        }
//
//        Picasso.with(getActivity()).load(imagePath).placeholder(R.drawable.catanddog).resize(300, 300).centerCrop().into(mPetImageView);








        return view;
    }

}
