package com.codeitonce.petshout;

/**
 * Created by Paul on 4/4/2016.
 */
public class Post
{
    private String postLocation;
    private String postLostFound;
    private String postGender;
    private String postSpecies;
    private String postEmail;
    private String postPhoneNumber;
    private String postBreed;
    private String postDescription;
    private String postAddInfo;


    public Post(String postLocation, String postLostFound, String postGender, String postSpecies, String postEmail, String postBreed, String postDescription, String postAddInfo, String postPhoneNumber)
    {
        this.postLocation = postLocation;
        this.postLostFound = postLostFound;
        this.postGender = postGender;
        this.postSpecies = postSpecies;
        this.postEmail = postEmail;
        this.postBreed = postBreed;
        this.postDescription = postDescription;
        this.postPhoneNumber = postPhoneNumber;
    }

    public String getPostLocation()
    {
        return postLocation;
    }

    public void setPostLocation(String postLocation)
    {
        this.postLocation = postLocation;
    }

    public String getPostLostFound()
    {
        return postLostFound;
    }

    public void setPostLostFound(String postLostFound)
    {
        this.postLostFound = postLostFound;
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
        return postAddInfo;
    }

    public void setPostAddInfo(String postAddInfo)
    {
        this.postAddInfo = postAddInfo;
    }

    public String getPostPhoneNumber()
    {
        return postPhoneNumber;
    }

    public void setPostPhoneNumber(String postPhoneNumber)
    {
        this.postPhoneNumber = postPhoneNumber;
    }
}
