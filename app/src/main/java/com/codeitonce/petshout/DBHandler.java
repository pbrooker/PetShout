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

        db.execSQL(createTable + Constents.TABLE_USERS + "("
                + Constents.USERS_ID + " BLOB,"
                + Constents.USERS_FNAME + " BLOB,"
                + Constents.USERS_LNAME + " BLOB,"
                + Constents.USERS_PASSWORD + " BINARY,"
                + Constents.USERS_PHONE + " BLOB,"
                + Constents.USERS_EMAIL + " BLOB,"
                + Constents.USERS_CITY + " BLOB, "
                + Constents.USERS_STATUS + " CHAR,"
                + Constents.USERS_PET_ID + " BLOB,"
                + Constents.USERS_POST_ID + " BLOB,"
                + Constents.USERS_DATE_CREATED + " TIMESTAMP,"
                + Constents.USERS_DATE_EXPIRES + " DATE,"
                + Constents.USERS_LAST_UPDATED + " TIMESTAMP,"
                + Constents.USERS_OBJECTID + " BLOB)");

        db.execSQL(createTable + Constents.TABLE_PETS
                + "(" + Constents.PETS_ID + " BLOB,"
                + Constents.PETS_NAME + " BLOB,"
                + Constents.PETS_AGE + " BLOB,"
                + Constents.PETS_GENDER + " CHAR,"
                + Constents.PETS_NEUTERED + " TEXT,"
                + Constents.PETS_BREED + " BLOB,"
                + Constents.PETS_IMAGEPATH + " BLOB,"
                + Constents.PETS_DESCRIPTION + " BLOB,"
                + Constents.PETS_SPECIES + " BLOB,"
                + Constents.PETS_ADDINFO + " BLOB,"
                + Constents.PETS_USERID + " BLOB,"
                + Constents.PETS_OBJECTID + " BLOB)");

        db.execSQL(createTable + Constents.TABLE_POSTS
                + "(" + Constents.POSTS_ID + " BLOB,"
                + Constents.POSTS_LOCATION + " BLOB,"
                + Constents.POSTS_IMAGEPATH + " BLOB,"
                + Constents.POSTS_GENDER + " CHAR,"
                + Constents.POSTS_SPECIES + " BLOB,"
                + Constents.POSTS_LOST_FOUND + " CHAR,"
                + Constents.POSTS_BREED + " BLOB,"
                + Constents.POSTS_DESCRIPTION + " BLOB,"
                + Constents.POSTS_OBJECTID + " BLOB,"
                + Constents.POST_USERID + " BLOB,"
                + Constents.POST_USEREMAIL + " BLOB)");
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


    public void rebuildTables()
    {
        SQLiteDatabase db = this.getWritableDatabase();
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
        values.put(Constents.USERS_PHONE, user.getUSER_PHONE());
        values.put(Constents.USERS_PASSWORD, user.getPassword());

        db.insert(Constents.TABLE_USERS, null, values);

        db.close();

    }

    public void addUserSmall(Users user, Post post)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constents.USERS_PASSWORD, user.getPassword());
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
        ArrayList<Post>list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor curPost = db.rawQuery("SELECT * FROM " + Constents.TABLE_POSTS, null);

        if (curPost.getCount() > 0)
        {

            while (curPost.moveToNext())
            {
                Post post = new Post(curPost.getString(curPost.getColumnIndex(Constents.POSTS_LOCATION)),
                        curPost.getString(curPost.getColumnIndex(Constents.POSTS_LOST_FOUND)),
                        curPost.getString(curPost.getColumnIndex(Constents.POSTS_GENDER)),
                        curPost.getString(curPost.getColumnIndex(Constents.POSTS_SPECIES)),
                        curPost.getString(curPost.getColumnIndex(Constents.POSTS_BREED)),
                        curPost.getString(curPost.getColumnIndex(Constents.POSTS_DESCRIPTION)),
                        curPost.getString(curPost.getColumnIndex(Constents.POSTS_IMAGEPATH)),
                        curPost.getString(curPost.getColumnIndex(Constents.POSTS_OBJECTID)),
                        curPost.getString(curPost.getColumnIndex(Constents.POST_USEREMAIL)),
                        curPost.getString(curPost.getColumnIndex(Constents.POST_USERID)));

                list.add(post);
                //Log.d("Post Added", "new post added");
            }

        }
                curPost.close();

        //Log.i("getPostArraySize", list.toString());
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
        values.put(Constents.PETS_IMAGEPATH,pets.getImagePath());
        values.put(Constents.PETS_OBJECTID, pets.getObjectId());
        values.put(Constents.PETS_ID, pets.getPetId());
        values.put(Constents.PETS_NEUTERED, pets.isPetNeutered());
        values.put(Constents.PETS_USERID, pets.getUserId());

                db.insert(Constents.TABLE_PETS, null, values);

        db.close();


    }



    public void addPost(Post post)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Constents.POSTS_LOCATION, post.getPostLocation()) ;
        values.put(Constents.POSTS_LOST_FOUND, "F");
        values.put(Constents.POSTS_GENDER, post.getPostGender());
        values.put(Constents.POSTS_SPECIES, post.getPostSpecies());
        values.put(Constents.POSTS_BREED, post.getPostBreed());
        values.put(Constents.POSTS_DESCRIPTION, post.getPostDescription());
        values.put(Constents.POSTS_IMAGEPATH, post.getPostImagePath());
        values.put(Constents.POSTS_OBJECTID,post.getObjectId());
        values.put(Constents.POST_USEREMAIL, post.getUserEmail());
        values.put(Constents.POST_USERID, post.getPostId());
        values.put(Constents.POSTS_ID, post.getPostId());




        db.insert(Constents.TABLE_POSTS, null, values);
        db.close();

    }

    public void deletePost(Post post)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Constents.TABLE_PETS, Constents.POSTS_OBJECTID + " = ?", new String[]{String.valueOf(post.getObjectId())});
    }

    public void deletePet(Pets pet)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Constents.TABLE_PETS, Constents.PETS_OBJECTID + " = ?", new String[]{String.valueOf(pet.getObjectId())});

        db.close();
    }
    public Users getEmailFromPostId(String postID)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor curPost = db.query( Constents.TABLE_USERS , null, Constents.USERS_POST_ID + "=?",
                new String[] { String.valueOf(postID) }, null, null, null, null);
        if (curPost.getCount() == 0)
        {
            return null;
        }
        curPost.moveToFirst();
        Users user = new Users(curPost.getString(curPost.getColumnIndex(Constents.USERS_EMAIL)),
                curPost.getString(curPost.getColumnIndex(Constents.USERS_OBJECTID)));
        // return post

        curPost.close();
        db.close();
        return user;

    }

    public Post getPost(String postID)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        boolean result = checkForRecord(Constents.TABLE_POSTS, Constents.POSTS_ID, new String[]{postID});
        Log.i("post checking for", "post id is " + postID);
        Log.i("is this post here?", "check for record result equals " + result);
        Cursor curPost = db.query( Constents.TABLE_POSTS , null, Constents.POSTS_ID + "=?",
                new String[] { String.valueOf(postID) }, null, null, null, null);

        if(curPost != null)
            curPost.moveToFirst();
            Post post = new Post(curPost.getString(curPost.getColumnIndex(Constents.POSTS_LOCATION)),
                    curPost.getString(curPost.getColumnIndex(Constents.POSTS_LOST_FOUND)),
                    curPost.getString(curPost.getColumnIndex(Constents.POSTS_GENDER)),
                    curPost.getString(curPost.getColumnIndex(Constents.POSTS_SPECIES)),
                    curPost.getString(curPost.getColumnIndex(Constents.POSTS_BREED)),
                    curPost.getString(curPost.getColumnIndex(Constents.POSTS_DESCRIPTION)),
                    curPost.getString(curPost.getColumnIndex(Constents.POSTS_IMAGEPATH)),
                    curPost.getString(curPost.getColumnIndex(Constents.POSTS_OBJECTID)),
                    curPost.getString(curPost.getColumnIndex(Constents.POST_USEREMAIL)),
                    curPost.getString(curPost.getColumnIndex(Constents.POST_USERID)));
            Log.i("getPostEmail", post.getUserEmail().toString());
            // return post

        curPost.close();
        db.close();
        return post;
    }

    public void getPosts()
    {

        AsyncCallback<BackendlessCollection<Post>> callback = new AsyncCallback<BackendlessCollection<Post>>()
        {
            @Override
            public void handleResponse(BackendlessCollection<Post> posts)
            {

                Iterator<Post> iterator = posts.getCurrentPage().iterator();

                while (iterator.hasNext())
                {
                    Post post = iterator.next();
                    if(!(checkForRecord(Constents.TABLE_POSTS, Constents.POSTS_ID, new  String []{ post.getPostId()})))
                    {
                        addPost(post);
                        Log.i("Post ID added", "Post id is "+ post.getPostId().toString());
                       // Log.d("PostAdded", post.toString());
                    }

                }
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault)
            {
                Log.i("Post handler Fault", "Posts were not proecessed");
            }
        };

       Backendless.Data.of(Post.class).find(callback);

    }

    public ArrayList<Post> getUserPosts(String userEmail)
    {
        final ArrayList<Post> mPostArray = new ArrayList<>();


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor curPost = db.query( Constents.TABLE_POSTS , null, Constents.POST_USEREMAIL + "=?",
                new String[] { String.valueOf(userEmail) }, null, null, null, null);
        if (curPost.getCount() == 0)
        {
            return null;
        }

        if (curPost.getCount() > 0)
        {
            curPost.moveToPosition(-1);
            while (curPost.moveToNext())
            {
                Post post = new Post(curPost.getString(curPost.getColumnIndex(Constents.POSTS_LOCATION)),
                        curPost.getString(curPost.getColumnIndex(Constents.POSTS_LOST_FOUND)),
                        curPost.getString(curPost.getColumnIndex(Constents.POSTS_GENDER)),
                        curPost.getString(curPost.getColumnIndex(Constents.POSTS_SPECIES)),
                        curPost.getString(curPost.getColumnIndex(Constents.POSTS_BREED)),
                        curPost.getString(curPost.getColumnIndex(Constents.POSTS_DESCRIPTION)),
                        curPost.getString(curPost.getColumnIndex(Constents.POSTS_IMAGEPATH)),
                        curPost.getString(curPost.getColumnIndex(Constents.POSTS_OBJECTID)),
                        curPost.getString(curPost.getColumnIndex(Constents.POST_USEREMAIL)));
                //Log.i("getPostEmail", post.getUserEmail().toString());

                mPostArray.add(post);
            }

        }


        curPost.close();
        db.close();



        return mPostArray;
    }

    public ArrayList<Pets> getUserPets(String objectID)
    {
        final ArrayList<Pets> mPetArray = new ArrayList<>();


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor curPost = db.query( Constents.TABLE_PETS , null, Constents.PETS_USERID + "=?",
                new String[] { String.valueOf(objectID) }, null, null, null, null);
        if (curPost.getCount() == 0)
        {
            return null;
        }

        if (curPost.getCount() > 0)
        {
            curPost.moveToPosition(-1);
            while (curPost.moveToNext())
            {
                Pets pet = new Pets(curPost.getString(curPost.getColumnIndex(Constents.PETS_NAME)),
                        curPost.getString(curPost.getColumnIndex(Constents.PETS_SPECIES)),
                        curPost.getString(curPost.getColumnIndex(Constents.PETS_NEUTERED)),
                        curPost.getString(curPost.getColumnIndex(Constents.PETS_GENDER)),
                        curPost.getString(curPost.getColumnIndex(Constents.PETS_BREED)),
                        curPost.getString(curPost.getColumnIndex(Constents.PETS_AGE)),
                        curPost.getString(curPost.getColumnIndex(Constents.PETS_DESCRIPTION)),
                        curPost.getString(curPost.getColumnIndex(Constents.PETS_ADDINFO)),
                        curPost.getString(curPost.getColumnIndex(Constents.PETS_IMAGEPATH)),
                        curPost.getString(curPost.getColumnIndex(Constents.PETS_ID)),
                        curPost.getString(curPost.getColumnIndex(Constents.PETS_USERID)));
                //Log.i("getPostEmail", post.getUserEmail().toString());

                mPetArray.add(pet);
            }

        }


        curPost.close();
        db.close();



        return mPetArray;
    }

    public void getPets()
    {

        AsyncCallback<BackendlessCollection<Pets>> callback = new AsyncCallback<BackendlessCollection<Pets>>()
        {
            @Override
            public void handleResponse(BackendlessCollection<Pets> pets)
            {

                Iterator<Pets> iterator = pets.getCurrentPage().iterator();

                while (iterator.hasNext())
                {
                    Pets pet = iterator.next();
                    if(!(checkForRecord(Constents.TABLE_PETS, Constents.PETS_USERID, new  String []{ pet.getUserId()})))
                    {
                        addPet(pet);
                   }

                }
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault)
            {
                Log.i("Pets handler Fault", "Pets were not proecessed");
            }
        };

        Backendless.Data.of(Pets.class).find(callback);


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
                   // if(!(checkForRecord(Constents.TABLE_POSTS, Constents.POSTS_OBJECTID, new  String []{ user.getObjectId()})))
                   //{
                        addUser(user);
                    //}

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

    public void addUserPet(String email, String petId)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String filter = Constents.USERS_EMAIL+"="+ email;
        values.put(Constents.USERS_PET_ID, petId);
       // db.execSQL("UPDATE 'Constents.TABLE_USERS' SET 'Constents.USERS_PET_ID' = 'petId' WHERE 'Constents.USERS_EMAIL'='email' ");

    }

    public boolean checkForRecord(String TableName, String columnName, String[] fieldValue) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TableName, null, columnName  + " = ?", fieldValue, null, null, null );
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }


}
