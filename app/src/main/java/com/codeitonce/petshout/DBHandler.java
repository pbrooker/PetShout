package com.codeitonce.petshout;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Paul on 4/1/2016.
 */
public class DBHandler extends SQLiteOpenHelper
{




    private String SHAHash;
    public static int NO_OPTIONS=0;


    public DBHandler (Context c)
    {
        super(c, Constents.DATABASE_NAME, null, Constents.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String createTable = "CREATE TABLE IF NOT EXISTS ";

        db.execSQL(createTable + Constents.TABLE_USERS + "(" + Constents.USERS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + Constents.USERS_FNAME + " VARCHAR," + Constents.USERS_LNAME + " VARCHAR," +
                Constents.USERS_PASSWORD + " BINARY," + Constents.USERS_PHONE + " INTEGER," + Constents.USERS_EMAIL + " VARCHAR NOT NULL," + Constents.USERS_CITY + " VARCHAR, " + Constents.USERS_POSTAL_CODE + " VARCHAR," +
                Constents.USERS_STATUS + " CHAR," + Constents.USERS_PET_ID + " INTEGER," + Constents.USERS_POST_ID + " INTEGER," + Constents.USERS_DATE_CREATED + " TIMESTAMP," + Constents.USERS_DATE_EXPIRES +
                " DATE," + Constents.USERS_LAST_UPDATED + " TIMESTAMP)");

        db.execSQL(createTable + Constents.TABLE_PETS + "(" + Constents.PETS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + Constents.PETS_NAME + " VARCHAR," + Constents.PETS_AGE + " INTEGER," +
                Constents.PETS_GENDER + " CHAR," + Constents.PETS_NEUTERED + " BOOLEAN," + Constents.PETS_BREED + " VARCHAR," + Constents.PETS_IMAGE + " LONGBLOB," + Constents.PETS_DESCRIPTION + " VARCHAR," +
                Constents.PETS_SPECIES + " VARCHAR," + Constents.PETS_ADDINFO + " VARCHAR," + Constents.PETS_LAST_UPDATED + " TIMESTAMP)");

        db.execSQL(createTable + Constents.TABLE_POSTS + "(" + Constents.POSTS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + Constents.POSTS_DATE + " TIMESTAMP," + Constents.POSTS_LOCATION + " VARCHAR," +
                Constents.POSTS_IMAGE + " LONGBLOB," + Constents.POSTS_GENDER + " CHAR," + Constents.POSTS_SPECIES + " VARCHAR," + Constents.POSTS_LOST_FOUND + " CHAR," + Constents.POSTS_BREED
                + " VARCHAR," + Constents.POSTS_DESCRIPTION + " VARCHAR," + Constents.POSTS_EXPIRES + " DATE," + Constents.POSTS_LAST_UPDATED + " TIMESTAMP)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        String drop = "DROP TABLE IF EXISTS ";
        //Drop older table if exists
        db.execSQL(drop + Constents.TABLE_USERS);
        db.execSQL(drop + Constents.TABLE_PETS);
        db.execSQL(drop + Constents.TABLE_POSTS);

        //Create tables again
        onCreate(db);

    }

    public void addUser(User user)
    {

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(Constents.USERS_LNAME, user.getlName());
            values.put(Constents.USERS_FNAME, user.getfName());
            values.put(Constents.USERS_CITY, user.getCity());
            values.put(Constents.USERS_EMAIL, user.getEmail());
            values.put(Constents.USERS_POSTAL_CODE, user.getPostalCode());
            values.put(Constents.USERS_PHONE, user.getPhoneNumber());
            values.put(Constents.USERS_PASSWORD, user.getPassword());

            db.insert(Constents.TABLE_USERS, null, values);

            db.close();


    }

    public ArrayList<User> getUsersArray()
    {
        ArrayList<User> list = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor curUser = db.rawQuery("SELECT * FROM " + Constents.TABLE_USERS, null);
        if(curUser != null && curUser.moveToFirst())
        {
            while (curUser.isAfterLast() == false)
            {
                list = new ArrayList<>();
                User user = new User(curUser.getString(curUser.getColumnIndex(Constents.USERS_EMAIL)),curUser.getString(curUser.getColumnIndex(Constents.USERS_PHONE)));
                user.setfName(curUser.getString((curUser.getColumnIndex(Constents.USERS_FNAME))));
                user.setlName(curUser.getString(curUser.getColumnIndex(Constents.USERS_LNAME)));
                user.setCity(curUser.getString(curUser.getColumnIndex(Constents.USERS_CITY)));
                user.setPassword(curUser.getString(curUser.getColumnIndex(Constents.USERS_PASSWORD)));
                user.setPostalCode(curUser.getString(curUser.getColumnIndex(Constents.USERS_POSTAL_CODE)));

                list.add(user);
                curUser.moveToNext();
            }
        }
        curUser.close();
        db.close();
        return list;
    }

    public void addPet(Pet pet)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constents.PETS_NAME, pet.getPetName());
        values.put(Constents.PETS_AGE, pet.getPetAge());
        values.put(Constents.PETS_SPECIES, pet.getPetSpecies());
        values.put(Constents.PETS_BREED, pet.getPetSpecies());
        values.put(Constents.PETS_GENDER, pet.getPetGender());
        values.put(Constents.PETS_DESCRIPTION, pet.getPetDescription());
        values.put(Constents.PETS_ADDINFO, pet.getAddInfo());

        db.insert(Constents.TABLE_PETS, null, values);

        db.close();


    }

    public void addPost(Post post)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        ContentValues userValues = new ContentValues();
        values.put(Constents.POSTS_LOCATION, post.getPostLocation());
        values.put(Constents.POSTS_LOST_FOUND, "F" );
        values.put(Constents.POSTS_GENDER, post.getPostGender());
        values.put(Constents.POSTS_SPECIES, post.getPostSpecies());
        values.put(Constents.POSTS_BREED, post.getPostBreed());
        values.put(Constents.POSTS_DESCRIPTION, post.getPostDescription());
        userValues.put(Constents.USERS_EMAIL, post.getPostEmail());
        userValues.put(Constents.USERS_PHONE, post.getPostPhoneNumber());


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


}
