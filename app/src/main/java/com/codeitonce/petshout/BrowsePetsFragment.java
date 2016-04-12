package com.codeitonce.petshout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.List;
import java.util.UUID;

import it.sephiroth.android.library.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class BrowsePetsFragment extends Fragment
{

    private RecyclerView mRecyclerView;
    private PostAdapter mPostAdapter;
    private static File petShoutPictures;
    private static String path;
    private List<Post> posts;



//    public BrowsePetsFragment() {
//        // Required empty public constructor
//    }

    private void updateUI()
    {
        DBHandler dbHandler = new DBHandler(getActivity());
        posts = dbHandler.getPostsArray();
        Log.i("PostsList", posts.toString());
        if(mPostAdapter == null)
        {
            mPostAdapter = new PostAdapter(posts);
            mRecyclerView.setAdapter(mPostAdapter);
        }
        else
        {
            mPostAdapter.notifyDataSetChanged();  // if you are changing only one item, you usually use notifyItemChanged
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_browse_pets, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.pet_post_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();


        return view;
    }

    private class PostHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private Post mPost;
        private ImageView mImageView;
        private TextView mDescriptionTextView;

        public PostHolder(View itemView)
        {
            super(itemView);
            itemView.setOnClickListener(this);

            mImageView = (ImageView)itemView.findViewById(R.id.pet_image);
            mDescriptionTextView = (TextView)itemView.findViewById(R.id.pet_description);
        }

        public void bindPost (Post post)
        {
            mPost = post;
            String imageID = UUID.randomUUID().toString();
            String imagePath = mPost.getPostImagePath();
            //Log.d("Imagepath", imagePath);
            //String destinationFile = imageID + ".jpg";

//
//            try
//            {
//                saveImage(imagePath, destinationFile);
//
//            } catch (IOException e)
//            {
//                e.printStackTrace();
//            }
            Picasso.with(getActivity()).load(imagePath).placeholder(R.drawable.catanddog).resize(200, 200).centerCrop().into(mImageView);
            mDescriptionTextView.setText(post.getPostDescription());
            //mImageView.setImageBitmap();


        }

        @Override
        public void onClick(View v)
        {

            Bundle bundle = new Bundle();
            bundle.putSerializable("objectId", mPost.getObjectId().toString());
            ClaimLostPetFragment fragment;
            fragment = new ClaimLostPetFragment();
            fragment.setArguments(bundle);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame, fragment);
            ft.commit();
        }
    }

    private class PostAdapter extends RecyclerView.Adapter<PostHolder>
    {
        private List<Post> mPosts;



        public PostAdapter(List<Post> posts)
        {

            mPosts = posts;
        }


        @Override
        public PostHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.fragment_list_item_pet, parent, false);
            return new PostHolder(view);
        }

        @Override
        public void onBindViewHolder(PostHolder holder, int position)
        {
            Post post = mPosts.get(position);

            holder.bindPost(post);

        }

        @Override
        public int getItemCount()
        {
            Log.d("mPosts Size", mPosts.toString());
            return mPosts.size();
        }
    }



}
