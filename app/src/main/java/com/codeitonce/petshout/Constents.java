package com.codeitonce.petshout;

/**
 * Created by Paul on 4/7/2016.
 */
public class Constents
{
    public static final String APP_ID = "11E491EB-2E65-9D2C-FFA6-2E1D6EDBBF00";
    public static final String APP_KEY = "87E6562F-D4A3-9104-FF19-D8706D843F00";
    public static final String APP_VERSION = "v1";


    //Database Version
    public static final int DATABASE_VERSION = 1;

    //Database Name
    public static final String DATABASE_NAME = "petShout";

    //Table Names
    public static final String TABLE_USERS = "Users";
    public static final String TABLE_PETS = "Pets";
    public static final String TABLE_POSTS = "Posts";

    //Users table columns
    public static final String USERS_ID = "USER_ID";
    public static final String USERS_LNAME = "USER_LNAME";
    public static final String USERS_FNAME = "USER_FNAME";
    public static final String USERS_PASSWORD = "password";
    public static final String USERS_PHONE = "USER_PHONE";
    public static final String USERS_EMAIL = "USER_EMAIL";
    public static final String USERS_CITY = "USER_CITY";
    public static final String USERS_STATUS = "USER_STATUS";
    public static final String USERS_PET_ID = "Pets";
    public static final String USERS_POST_ID = "Posts";
    public static final String USERS_OBJECTID = "objectId";
    public static final String USERS_DATE_CREATED = "DATE_CREATED";
    public static final String USERS_DATE_EXPIRES = "DATE_EXPIRES";
    public static final String USERS_LAST_UPDATED = "LAST_UPDATED";

    //Pets table columns
    public static final String PETS_ID = "petId";
    public static final String PETS_NAME = "petName";
    public static final String PETS_SPECIES = "petSpecies";
    public static final String PETS_IMAGEPATH = "imagePath";
    public static final String PETS_NEUTERED = "petNeutered";
    public static final String PETS_GENDER = "petGender";
    public static final String PETS_BREED = "petBreed";
    public static final String PETS_AGE = "petAge";
    public static final String PETS_DESCRIPTION = "petDescription";
    public static final String PETS_ADDINFO = "addInfo";
    public static final String PETS_OBJECTID = "objectId";
    public static final String PETS_USERID = "userId";

    //Post table columns
    public static final String POSTS_ID = "postId";
    public static final String POSTS_LOCATION = "post_location";
    public static final String POSTS_IMAGEPATH = "postImagePath";
    public static final String POSTS_LOST_FOUND = "post_lostfound";
    public static final String POSTS_GENDER = "postGender";
    public static final String POSTS_SPECIES = "postSpecies";
    public static final String POSTS_BREED = "postBreed";
    public static final String POSTS_DESCRIPTION = "postDescription";
    public static final String POSTS_OBJECTID = "objectId";
    public static final String POST_USEREMAIL = "userEmail";
    public static final String POST_USERID = "userId";




    public static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})";
    public static final int SELECT_PHOTO = 0;
    public static final String PREFS_LOGGED_IN = "LoggedInStatusFile";
    public static final String SAVED_CURRENT_USER = "saved_current_user";
    public static final String EXTRA_POST_ID = "com.codeitonce.petshout.post_id";
}
