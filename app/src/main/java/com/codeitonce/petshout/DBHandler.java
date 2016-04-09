package com.codeitonce.petshout;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Paul on 4/1/2016.
 */
public class DBHandler extends SQLiteOpenHelper
{


    public DBHandler(Context c)
    {
        super(c, Constents.DATABASE_NAME, null, Constents.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String createTable = "CREATE TABLE IF NOT EXISTS ";

        db.execSQL(createTable + Constents.TABLE_USERS + "(" + Constents.USERS_ID + " VARCHAR," + Constents.USERS_FNAME + " VARCHAR," + Constents.USERS_LNAME + " VARCHAR," +
                Constents.USERS_PASSWORD + " BINARY," + Constents.USERS_PHONE + " INTEGER," + Constents.USERS_EMAIL + " VARCHAR," + Constents.USERS_CITY + " VARCHAR, " +
                Constents.USERS_STATUS + " CHAR," + Constents.USERS_PET_ID + " INTEGER," + Constents.USERS_POST_ID + " INTEGER," + Constents.USERS_DATE_CREATED + " TIMESTAMP," + Constents.USERS_DATE_EXPIRES +
                " DATE," + Constents.USERS_LAST_UPDATED + " TIMESTAMP," + Constents.USERS_POSTAL_CODE + " VARCHAR)");

        db.execSQL(createTable + Constents.TABLE_PETS + "(" + Constents.PETS_ID + " VARCHAR," + Constents.PETS_NAME + " VARCHAR," + Constents.PETS_AGE + " INTEGER," +
                Constents.PETS_GENDER + " CHAR," + Constents.PETS_NEUTERED + " BOOLEAN," + Constents.PETS_BREED + " VARCHAR," + Constents.PETS_IMAGEPATH + " LONGBLOB," + Constents.PETS_DESCRIPTION + " VARCHAR," +
                Constents.PETS_SPECIES + " VARCHAR," + Constents.PETS_ADDINFO + " VARCHAR," + Constents.PETS_LAST_UPDATED + " TIMESTAMP)");

        db.execSQL(createTable + Constents.TABLE_POSTS + "(" + Constents.POSTS_ID + " VARCHAR," + Constents.POSTS_DATE + " TIMESTAMP," + Constents.POSTS_LOCATION + " VARCHAR," +
                Constents.POSTS_IMAGEPATH + " VARCHAR," + Constents.POSTS_GENDER + " CHAR," + Constents.POSTS_SPECIES + " VARCHAR," + Constents.POSTS_LOST_FOUND + " CHAR," + Constents.POSTS_BREED
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

    public void addUser(Users user)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constents.USERS_LNAME, user.getUSER_LNAME());
        values.put(Constents.USERS_FNAME, user.getUSER_FNAME());
        values.put(Constents.USERS_CITY, user.getUSER_CITY());
        values.put(Constents.USERS_EMAIL, user.getEmail());
        values.put(Constents.USERS_POSTAL_CODE, user.getUSER_POSTALCODE());
        values.put(Constents.USERS_PHONE, user.getUSER_PHONE());
        values.put(Constents.USERS_PASSWORD, user.getPassword());

        db.insert(Constents.TABLE_USERS, null, values);

        db.close();

    }

    public void addUserSmall(Users user, Post post)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constents.USERS_POSTAL_CODE, user.getUSER_POSTALCODE());
        values.put(Constents.USERS_PHONE, user.getUSER_PHONE());
        values.put(Constents.USERS_POST_ID, post.getPostId());
        values.put(Constents.USERS_ID, user.getPassword());

        db.insert(Constents.TABLE_USERS, null, values);

        db.close();
    }

    public ArrayList<Users> getUsersArray()
    {
        ArrayList<Users> list = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor curUser = db.rawQuery("SELECT * FROM " + Constents.TABLE_USERS, null);
        if (curUser != null && curUser.moveToFirst())
        {
            while (curUser.isAfterLast() == false)
            {
                list = new ArrayList<>();
                Users user = new Users(curUser.getString(curUser.getColumnIndex(Constents.USERS_EMAIL)),
                        curUser.getString(curUser.getColumnIndex(Constents.USERS_PHONE)),
                        curUser.getString(curUser.getColumnIndex(Constents.USERS_ID)));
                user.setUSER_FNAME(curUser.getString((curUser.getColumnIndex(Constents.USERS_FNAME))));
                user.setUSER_LNAME(curUser.getString(curUser.getColumnIndex(Constents.USERS_LNAME)));
                user.setUSER_CITY(curUser.getString(curUser.getColumnIndex(Constents.USERS_CITY)));
                user.setPassword(curUser.getString(curUser.getColumnIndex(Constents.USERS_PASSWORD)));
                user.setUSER_POSTALCODE(curUser.getString(curUser.getColumnIndex(Constents.USERS_POSTAL_CODE)));

                list.add(user);
                curUser.moveToNext();
            }
        }
        curUser.close();
        db.close();
        return list;
    }

    public ArrayList<Post> getPostsArray()
    {
        ArrayList<Post> list = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor curPost = db.rawQuery("SELECT * FROM " + Constents.TABLE_POSTS, null);
        if (curPost != null && curPost.moveToFirst())
        {
            while (curPost.isAfterLast() == false)
            {
                list = new ArrayList<>();

                Post post = new Post(curPost.getString(curPost.getColumnIndex(Constents.POSTS_LOCATION)),
                        curPost.getString(curPost.getColumnIndex(Constents.POSTS_LOST_FOUND)), curPost.getString(curPost.getColumnIndex(Constents.POSTS_GENDER)),
                        curPost.getString(curPost.getColumnIndex(Constents.POSTS_SPECIES)), curPost.getString(curPost.getColumnIndex(Constents.POSTS_BREED)),
                        curPost.getString(curPost.getColumnIndex(Constents.POSTS_DESCRIPTION)), curPost.getString(curPost.getColumnIndex(Constents.POSTS_IMAGEPATH)),
                        curPost.getString(curPost.getColumnIndex(Constents.POSTS_ID)));

                list.add(post);
                curPost.moveToNext();
            }

        }
        curPost.close();
        db.close();
        return list;

    }

    public void addPet(Pets pets)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constents.PETS_NAME, pets.getPetName());
        values.put(Constents.PETS_AGE, pets.getPetAge());
        values.put(Constents.PETS_SPECIES, pets.getPetSpecies());
        values.put(Constents.PETS_BREED, pets.getPetSpecies());
        values.put(Constents.PETS_GENDER, pets.getPetGender());
        values.put(Constents.PETS_DESCRIPTION, pets.getPetDescription());
        values.put(Constents.PETS_ADDINFO, pets.getAddInfo());
        values.put(Constents.PETS_IMAGEPATH, pets.getImagePath());

        db.insert(Constents.TABLE_PETS, null, values);

        db.close();


    }

    public void addPost(Post post)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Constents.POSTS_LOCATION, post.getPostLocation());
        values.put(Constents.POSTS_LOST_FOUND, "F");
        values.put(Constents.POSTS_GENDER, post.getPostGender());
        values.put(Constents.POSTS_SPECIES, post.getPostSpecies());
        values.put(Constents.POSTS_BREED, post.getPostBreed());
        values.put(Constents.POSTS_DESCRIPTION, post.getPostDescription());
        values.put(Constents.POSTS_ID, post.getPostId());

        db.insert(Constents.TABLE_POSTS, null, values);
        db.close();

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

    public ArrayList<Post> getPosts()
    {

        final ArrayList<Post> mPostArray = new ArrayList<>();


        AsyncCallback<BackendlessCollection<Post>> callback = new AsyncCallback<BackendlessCollection<Post>>()
        {
            @Override
            public void handleResponse(BackendlessCollection<Post> posts)
            {

                Iterator<Post> iterator = posts.getCurrentPage().iterator();

                while (iterator.hasNext())
                {
                    Post post = iterator.next();
                    addPost(post);
                    mPostArray.add(post);

                }
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault)
            {
                Log.i("Post handler Fault", "Posts were not proecessed");
            }
        };

        Backendless.Data.of(Post.class).find(callback);


        return mPostArray;
    }


    public ArrayList<Pets> getPets()
    {

        final ArrayList<Pets> mPetsArray = new ArrayList<>();


        AsyncCallback<BackendlessCollection<Pets>> callback = new AsyncCallback<BackendlessCollection<Pets>>()
        {
            @Override
            public void handleResponse(BackendlessCollection<Pets> pets)
            {

                Iterator<Pets> iterator = pets.getCurrentPage().iterator();

                while (iterator.hasNext())
                {
                    Pets pet = iterator.next();
                    addPet(pet);
                    mPetsArray.add(pet);

                }
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault)
            {
                Log.i("Pets handler Fault", "Pets were not proecessed");
            }
        };

        Backendless.Data.of(Pets.class).find(callback);


        return mPetsArray;
    }


    public void getUsers()
    {

        final ArrayList<Users> mUserArray = new ArrayList<>();


        Backendless.Persistence.of(Users.class).find(new AsyncCallback<BackendlessCollection<Users>>()
        {
            @Override
            public void handleResponse(BackendlessCollection<Users> users)
            {
                Iterator<Users> iterator = users.getCurrentPage().iterator();

                while (iterator.hasNext())
                {
                    Users user = iterator.next();
                    addUser(user);

                    mUserArray.add(user);

                    for (int x = 0; x < mUserArray.size(); x++)
                    {
                        Users user1 = mUserArray.get(x);
                        //Log.i("UserInfo", user1.getUSER_ID().toString());
                       // Log.i("UserEmail", user1.getEmail().toString());
                    }
                }
            }

            @Override
            public void handleFault(BackendlessFault fault)
            {
                // an error has occurred, the error code can be retrieved with fault.getCode()
                Log.i("Users handler Fault", "Users were not proecessed");
            }


        });


    }
}
