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
    private String postEmail;
    private String postPhoneNumber;
    private String postBreed;
    private String postDescription;
    private String postImagePath;
    private String postId;


    public Post(String postLocation, String post_lostfound, String postGender, String postSpecies, String postEmail, String postBreed, String postDescription, String postImagePath, String postPhoneNumber, String postId)
    {
        this.post_location = postLocation;
        this.post_lostfound = post_lostfound;
        this.postGender = postGender;
        this.postSpecies = postSpecies;
        this.postEmail = postEmail;
        this.postBreed = postBreed;
        this.postDescription = postDescription;
        this.postImagePath = postImagePath;
        this.postPhoneNumber = postPhoneNumber;
        this.postId = postId;
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

    public String getPostEmail()
    {
        return postEmail;
    }

    public void setPostEmail(String postEmail)
    {
        this.postEmail = postEmail;
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

    public String getPostAddInfo()
    {
        return postImagePath;
    }

    public void setPostAddInfo(String postAddInfo)
    {
        this.postImagePath = postAddInfo;
    }

    public String getPostPhoneNumber()
    {
        return postPhoneNumber;
    }

    public void setPostPhoneNumber(String postPhoneNumber)
    {
        this.postPhoneNumber = postPhoneNumber;
    }

    public String getPostImagePath()
    {
        return postImagePath;
    }

    public String getPostId()
    {
        return postId;
    }
}
