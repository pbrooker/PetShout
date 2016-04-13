package com.codeitonce.petshout;

/**
 * Created by Paul on 4/4/2016.
 */
public class Users
{
    private String USER_FNAME;
    private String USER_LNAME;
    private String USER_CITY;
    private String email;
    private String USER_PHONE;
    private String password;
    private String USER_ID;
    private String objectId;


    public Users()
    {

    }


    public Users(String email, String password, String userID)
    {
        this.email = email;
        this.password = password;
        this.USER_ID = userID;
    }

    public Users(String email, String objectId )
    {
        this.email = email;
        this.objectId = objectId;
    }


    public Users(String id, String fName, String lName, String city, String email, String phoneNumber, String password)
    {
        this.USER_ID = id;
        this.USER_FNAME = fName;
        this.USER_LNAME = lName;
        this.USER_CITY = city;
        this.email = email;
        this.USER_PHONE = phoneNumber;
        this.password = password;
    }

    public String getUSER_FNAME()
    {
        return USER_FNAME;
    }

    public void setUSER_FNAME(String USER_FNAME)
    {
        this.USER_FNAME = USER_FNAME;
    }

    public String getUSER_LNAME()
    {
        return USER_LNAME;
    }

    public void setUSER_LNAME(String USER_LNAME)
    {
        this.USER_LNAME = USER_LNAME;
    }

    public String getUSER_CITY()
    {
        return USER_CITY;
    }

    public void setUSER_CITY(String USER_CITY)
    {
        this.USER_CITY = USER_CITY;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getUSER_PHONE()
    {
        return USER_PHONE;
    }

    public void setUSER_PHONE(String USER_PHONE)
    {
        this.USER_PHONE = USER_PHONE;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getUSER_ID()
    {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID)
    {
        this.USER_ID = USER_ID;
    }

    public String getObjectId()
    {
        return objectId;
    }

    public void setObjectId(String objectId)
    {
        this.objectId = objectId;
    }

}
