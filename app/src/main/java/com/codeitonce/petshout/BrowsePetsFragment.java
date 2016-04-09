package com.codeitonce.petshout;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class BrowsePetsFragment extends Fragment
{

    private ArrayList<Post> mPostArrayList;
    private RecyclerView mRecyclerView;
    private PostAdapter mPostAdapter;
    private static File petShoutPictures;
    private static String path;
    //private Bitmap bitmap;





    public BrowsePetsFragment() {
        // Required empty public constructor
    }

    private void updateUI()
    {
        DBHandler dbHandler = new DBHandler(getActivity());
        List<Post> posts = dbHandler.getPosts();
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
            String imageID = UUID.randomUUID().toString();
            String imagePath = post.getPostImagePath();
            String destinationFile = imageID + ".jpg";

            try
            {
                saveImage(imagePath, destinationFile);
            } catch (IOException e)
            {
                e.printStackTrace();
            }


            mPost = post;
            mDescriptionTextView.setText(mPost.getPostDescription());
            mImageView.setImageBitmap(getBitmap(path));

        }

        @Override
        public void onClick(View v)
        {
            ClaimLostPetFragment fragment;
            fragment = new ClaimLostPetFragment();
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
            return mPosts.size();
        }
    }

    public static void saveImage(String imagePath, String destinationFile) throws IOException
    {

        URL url = new URL (imagePath);
        InputStream is = url.openStream();

        File img;
        String MEDIA_MOUNTED = "mounted";
        String diskState = Environment.getExternalStorageState();

        byte[]b = new byte[2048];
        int length;

        if (diskState.equals(MEDIA_MOUNTED) )
        {
            petShoutPictures = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        }

        img = new File(petShoutPictures, destinationFile);
        OutputStream os = new FileOutputStream(img);
        while ((length = is.read(b)) != -1)
        {
            os.write(b, 0, length);
        }


        is.close();
        os.close();

        path = img.getPath() + destinationFile;

    }

    private Bitmap getBitmap(String path)
    {
        Bitmap bitmap = BitmapFactory.decodeFile(path);

        return bitmap;
    }



}
