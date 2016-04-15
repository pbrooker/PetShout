package com.codeitonce.petshout;

/**
 * Created by Paul on 4/4/2016.
 */
public class Post
{
    private String post_location;
    private String post_lostfound;
    private String postGender;
    private String postSpecies;
    private String postBreed;
    private String postDescription;
    private String postImagePath;
    private String postId;
    private String objectId;
    private String userEmail;
    private String userId;




    public Post()
    {
        //empty constructor for filling array
    }


    public Post(String postLocation, String post_lostfound, String postGender, String postSpecies,  String postBreed,
                String postDescription, String postImagePath, String userEmail, String userId)
    {
        this.post_location = postLocation;
        this.post_lostfound = post_lostfound;
        this.postGender = postGender;
        this.postSpecies = postSpecies;
        this.postBreed = postBreed;
        this.postDescription = postDescription;
        this.postImagePath = postImagePath;
        this.userEmail = userEmail;
        this.userId = userId;
    }

    public Post(String postLocation, String post_lostfound,String postGender, String postSpecies,  String postBreed,
                String postDescription, String postImagePath, String objectId, String userEmail, String userId)
    {
        this.post_location = postLocation;
        this.post_lostfound = post_lostfound;
        this.postGender = postGender;
        this.postSpecies = postSpecies;
        this.postBreed = postBreed;
        this.postDescription = postDescription;
        this.postImagePath = postImagePath;
        this.objectId = objectId;
        this.userEmail = userEmail;
        this.userId = userId;

    }

    public String getPostLocation()
    {
        return post_location;
    }

    public void setPostLocation(String postLocation)
    {
        this.post_location = postLocation;
    }

    public String getPost_lostfound()
    {
        return post_lostfound;
    }

    public void setPost_lostfound(String post_lostfound)
    {
        this.post_lostfound = post_lostfound;
    }

    public String getPostGender()
    {
        return postGender;
    }

    public void setPostGender(String postGender)
    {
        this.postGender = postGender;
    }

    public String getPostSpecies()
    {
        return postSpecies;
    }

    public void setPostSpecies(String postSpecies)
    {
        this.postSpecies = postSpecies;
    }



    public String getPostBreed()
    {
        return postBreed;
    }

    public void setPostBreed(String postBreed)
    {
        this.postBreed = postBreed;
    }

    public String getPostDescription()
    {
        return postDescription;
    }

    public void setPostDescription(String postDescription)
    {
        this.postDescription = postDescription;
    }


    public String getPostImagePath()
    {
        return postImagePath;
    }

    public String getPostId()
    {
        return postId;
    }
    public String getObjectId()
    {
        return objectId;
    }

    public void setObjectId( String objectId )
    {
        this.objectId = objectId;
    }


    public String getPost_location()
    {
        return post_location;
    }

    public void setPost_location(String post_location)
    {
        this.post_location = post_location;
    }

    public void setPostImagePath(String postImagePath)
    {
        this.postImagePath = postImagePath;
    }

    public void setPostId(String postId)
    {
        this.postId = postId;
    }

    public String getUserEmail()
    {
        return userEmail;
    }

    public void setUserEmail(String userEmail)
    {
        this.userEmail = userEmail;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }
}
