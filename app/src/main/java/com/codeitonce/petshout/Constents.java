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
    public static final String TABLE_POSTS = "Post";

    //Users table columns
    public static final String USERS_ID = "USER_ID";
    public static final String USERS_LNAME = "USER_LNAME";
    public static final String USERS_FNAME = "USER_FNAME";
    public static final String USERS_PASSWORD = "USER_PASSWORD";
    public static final String USERS_PHONE = "USER_PHONE";
    public static final String USERS_EMAIL = "USER_EMAIL";
    public static final String USERS_CITY = "USER_CITY";
    public static final String USERS_POSTAL_CODE = "USER_POSTALCODE";
    public static final String USERS_STATUS = "USER_STATUS";
    public static final String USERS_PET_ID = "PET_ID";
    public static final String USERS_POST_ID = "POST_ID";
    public static final String USERS_DATE_CREATED = "DATE_CREATED";
    public static final String USERS_DATE_EXPIRES = "DATE_EXPIRES";
    public static final String USERS_LAST_UPDATED = "LAST_UPDATED";

    //Pets table columns
    public static final String PETS_ID = "PET_ID";
    public static final String PETS_NAME = "PET_NAME";
    public static final String PETS_SPECIES = "PET_SPECIES";
    public static final String PETS_IMAGEPATH = "PET_IMAGEPATH";
    public static final String PETS_NEUTERED = "PET_NEUTERED";
    public static final String PETS_GENDER = "PET_GENDER";
    public static final String PETS_BREED = "PET_BREED";
    public static final String PETS_AGE = "PET_AGE";
    public static final String PETS_DESCRIPTION = "PET_DESCRIPTION";
    public static final String PETS_ADDINFO = "PET_ADDINFO";
    public static final String PETS_LAST_UPDATED = "LAST_UPDATED";

    //Post table columns
    public static final String POSTS_ID = "POST_ID";
    public static final String POSTS_DATE = "POST_DATE";
    public static final String POSTS_LOCATION = "POST_LOCATION";
    public static final String POSTS_IMAGEPATH = "POST_IMAGEPATH";
    public static final String POSTS_LOST_FOUND = "POST_LOSTFOUND";
    public static final String POSTS_GENDER = "POST_GENDER";
    public static final String POSTS_SPECIES = "POST_SPECIES";
    public static final String POSTS_BREED = "POST_BREED";
    public static final String POSTS_DESCRIPTION = "POST_DESCRIPTION";
    public static final String POSTS_EXPIRES = "POST_EXPIRES";
    public static final String POSTS_LAST_UPDATED = "LAST_UPDATED";


    public static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})";
    public static final int SELECT_PHOTO = 0;
}
