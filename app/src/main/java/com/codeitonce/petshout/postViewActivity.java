package com.codeitonce.petshout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

public class postViewActivity extends AppCompatActivity
{

    private ViewPager mViewPager;
    private List<Post> mPosts;
    private String postId;



    public static Intent newIntent(Context c, String postId)
    {
        Intent intent = new Intent(c, postViewActivity.class);
        intent.putExtra(Constents.EXTRA_POST_ID, postId);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        DBHandler db = new DBHandler(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_view);

        postId = (String)getIntent().getSerializableExtra(Constents.POSTS_ID);
        Log.i("postView", "postId = " + postId);
        mViewPager = (ViewPager) findViewById(R.id.activity_post_view_pager);

        mPosts = db.getPostsArray();
        Log.i("mPost Size", mPosts.toString());
        FragmentManager fm = getSupportFragmentManager();

        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm)
        {
            @Override
            public Fragment getItem(int position)
            {

                Post post = mPosts.get(position);
                return ClaimLostPetFragment.newInstance(post.getPostId());
            }

            @Override
            public int getCount()
            {
                return mPosts.size();
            }
        });

        for(int x = 0; x < mPosts.size(); x++)
        {
            if(mPosts.get(x).getPostId().equals(postId))
            {
                mViewPager.setCurrentItem(x);
                break;
            }
        }
    }
}
