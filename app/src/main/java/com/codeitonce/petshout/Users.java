package com.codeitonce.petshout;

/**
 * Created by Paul on 4/4/2016.
 */
public class Users
{
    private String fName;
    private String lName;
    private String city;
    private String postalCode;
    private String email;
    private String phoneNumber;
    private String password;
    private String userID;
    private String objectId;


    public Users()
    {

    }


    public Users(String email, String phoneNumber, String userID)
    {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userID = userID;
    }


    public Users(String id, String fName, String lName, String city, String postalCode, String email, String phoneNumber, String password)
    {
        this.userID = id;
        this.fName = fName;
        this.lName = lName;
        this.city = city;
        this.postalCode = postalCode;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getfName()
    {
        return fName;
    }

    public void setfName(String fName)
    {
        this.fName = fName;
    }

    public String getlName()
    {
        return lName;
    }

    public void setlName(String lName)
    {
        this.lName = lName;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getPostalCode()
    {
        return postalCode;
    }

    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getUserID()
    {
        return userID;
    }

    public void setUserID(String userID)
    {
        this.userID = userID;
    }
    public String getObjectId()
    {
        return objectId;
    }

    public void setObjectId( String objectId )
    {
        this.objectId = objectId;
    }
}
