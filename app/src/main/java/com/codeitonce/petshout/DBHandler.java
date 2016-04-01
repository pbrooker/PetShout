package com.codeitonce.petshout;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Paul on 4/1/2016.
 */
public class DBHandler extends SQLiteOpenHelper
{

    //Database Version
    private static final int DATABASE_VERSION = 1;

    //Database Name
    private static final String DATABASE_NAME = "petShout";

    //Table Names
    private static final String TABLE_USERS = "users";
    private static final String TABLE_PETS = "pet";
    private static final String TABLE_POSTS = "post";

    //Users table columns
    private static final String USERS_ID = "USER_ID";
    private static final String USERS_LNAME = "USER_LNAME";
    private static final String USERS_FNAME = "USER_FNAME";
    private static final String USERS_PASSWORD = "USER_PASSWORD";
    private static final String USERS_PHONE = "USER_PHONE";
    private static final String USERS_EMAIL = "USER_EMAIL";
    private static final String USERS_CITY = "USER_CITY";
    private static final String USERS_POSTAL_CODE = "USER_POSTAL_CODED";
    private static final String USERS_STATUS = "USER_STATUS";
    private static final String USERS_PET_ID = "PET_ID";
    private static final String USERS_POST_ID = "POST_ID";
    private static final String USERS_DATE_CREATED = "DATE_CREATED";
    private static final String USERS_DATE_EXPIRES = "DATE_EXPIRES";
    private static final String USERS_LAST_UPDATED = "LAST_UPDATED";

    //Pets table columns
    private static final String PETS_ID = "PET_ID";
    private static final String PETS_NAME = "PET_NAME";
    private static final String PETS_SPECIIES = "PET_SPECIES";
    private static final String PETS_IMAGE = "PET_IMAGE";
    private static final String PETS_NEUTERED = "PET_NEUTERED";
    private static final String PETS_GENDER = "PET_GENDER";
    private static final String PETS_BREED = "PET_BREED";
    private static final String PETS_AGE = "PET_AGE";
    private static final String PETS_DESCRIPTION = "PET_DESCRIPTION";
    private static final String PETS_ADDINFO = "PET_ADDINFO";
    private static final String PETS_LAST_UPDATED = "LAST_UPDATED";

    //Post table columns
    private static final String POSTS_ID = "POST_ID";
    private static final String POSTS_DATE = "POST_DATE";
    private static final String POSTS_LOCATION = "POST_LOCATION";
    private static final String POSTS_IMAGE = "POST_IMAGE";
    private static final String POSTS_GENDER = "POST_GENDER";
    private static final String POSTS_SPECIES = "POST_SPECIES";
    private static final String POSTS_EMAIL = "POST_EMAIL";
    private static final String POSTS_BREED = "POST_BREED";
    private static final String POSTS_DESCRIPTION = "POST_DESCRIPTION";
    private static final String POSTS_EXPIRES = "POST_EXPIRES";
    private static final String POSTS_ADDINFO = "POST_ADDINFO";
    private static final String POSTS_LAST_UPDATED = "LAST_UPDATED";


    private String SHAHash;
    public static int NO_OPTIONS=0;


    public DBHandler (Context c)
    {
        super(c, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String createTable = "CREATE TABLE IF NOT EXISTS ";

        db.execSQL(createTable + TABLE_USERS + "(" + USERS_ID + " INTEGER PRIMARY KEY," + USERS_FNAME + " VARCHAR," + USERS_LNAME + " VARCHAR," +
        USERS_PASSWORD + " BINARY," + USERS_PHONE + " INTEGER," + USERS_EMAIL + " VARCHAR," + USERS_CITY + " VARCHAR, " + USERS_POSTAL_CODE + " VARCHAR," +
        USERS_STATUS + " CHAR," + USERS_PET_ID + " INTEGER," + USERS_POST_ID + " INTEGER," + USERS_DATE_CREATED + " TIMESTAMP," + USERS_DATE_EXPIRES +
                " DATE," + USERS_LAST_UPDATED + " TIMESTAMP)");

        db.execSQL(createTable + TABLE_PETS + "(" + PETS_ID + " INTEGER PRIMARY KEY," + PETS_NAME + " VARCHAR," + PETS_AGE + " INTEGER," +
        PETS_GENDER + " CHAR," + PETS_NEUTERED + " BOOLEAN," + PETS_BREED + " VARCHAR," + PETS_IMAGE + " LONGBLOB," + PETS_DESCRIPTION + " VARCHAR," +
        PETS_SPECIIES + " VARCHAR," + PETS_ADDINFO + " VARCHAR," + PETS_LAST_UPDATED + " TIMESTAMP)");

        db.execSQL(createTable + TABLE_POSTS + "(" + POSTS_ID + " INTEGER PRIMARY KEY" + POSTS_DATE + " TIMESTAMP," + POSTS_LOCATION + " VARCHAR," +
                POSTS_IMAGE + " LONGBLOB," + POSTS_GENDER + " CHAR," + POSTS_SPECIES + " VARCHAR," + POSTS_EMAIL + " VARCHAR," + POSTS_BREED + " VARCHAR," +
                POSTS_DESCRIPTION + " VARCHAR," + POSTS_EXPIRES + " DATE," + POSTS_ADDINFO + " VARCHAR," + POSTS_LAST_UPDATED + " TIMESTAMP)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        String drop = "DROP TABLE IF EXISTS ";
        //Drop older table if exists
        db.execSQL(drop + TABLE_USERS);
        db.execSQL(drop + TABLE_PETS);
        db.execSQL(drop + TABLE_POSTS);

        //Create tables again
        onCreate(db);

    }

    public void addUser()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(USERS_LNAME, );
        //values.put(USERS_PASSWORD, )

    }

    public void addPet()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
    }

    public void addPost()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
    }

    public void deletePost()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        //db.delete(TABLE_PETS, POSTS_ID + " = ?", new String[] {String.valueOf(post.getId())} );
    }

    public void deletePet()
    {
        SQLiteDatabase db = this.getWritableDatabase();
       // db.delete(TABLE_PETS, PETS_ID + " = ?", new String[] {String.valueOf(pet.getId())} );

        db.close();
    }

    private static String convertToHex(byte[] data) throws java.io.IOException
    {


        StringBuffer sb = new StringBuffer();
        String hex=null;

        hex= Base64.encodeToString(data, 0, data.length, NO_OPTIONS);

        sb.append(hex);

        return sb.toString();
    }
    public void computeMD5Hash(String password)
    {

        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(password.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuffer MD5Hash = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++)
            {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                MD5Hash.append(h);
            }

            //result.setText("MD5 hash generated is: " + " " + MD5Hash);

        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }


    }




}
