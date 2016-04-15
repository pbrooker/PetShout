package com.codeitonce.petshout;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class BrowsePetsFragment extends Fragment
{

    private RecyclerView mRecyclerView;
    private PostAdapter mPostAdapter;



    public BrowsePetsFragment() {
        // Required empty public constructor
    }

    private void updateUI()
    {
        DBHandler dbHandler = new DBHandler(getActivity());
        List<Post> posts = dbHandler.getPostsArray();
        dbHandler.close();
        //Log.i("PostsList", posts.toString());
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
    public void onResume()
    {
        super.onResume();
        updateUI();
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
        private String objectId;

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
            String imagePath = mPost.getPostImagePath();
            objectId = mPost.getObjectId();
           // Log.i("Bind post objectid", "bindpost object id is " + objectId);

            try
            {
                Picasso.with(getActivity()).load(imagePath).placeholder(R.drawable.catanddog).resize(300, 300).centerCrop().into(mImageView);
            }
            catch (NullPointerException n)
            {

            }
            mDescriptionTextView.setText(post.getPostDescription());




        }

        @Override
        public void onClick(View v)
        {

            Intent i = postViewActivity.newIntent(getActivity(),objectId );
            startActivity(i);

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
            //Log.d("mPosts Size", mPosts.toString());
            return mPosts.size();
        }
    }



}
