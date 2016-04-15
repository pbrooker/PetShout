package com.codeitonce.petshout;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{

    private boolean isValidUser;
    private BackendlessUser currentbkuser;
    private String userEmail;
    private String userObjectID;
    private ArrayList<Pets> userPets;
    private ArrayList<Post> userPosts;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Backendless.initApp(getApplicationContext(), Constents.APP_ID, Constents.APP_KEY, Constents.APP_VERSION);

        String jsonMyObject;
        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            jsonMyObject = extras.getString("user");
            isValidUser = extras.getBoolean("validUser");
            Users user = new Gson().fromJson(jsonMyObject, Users.class);
            userEmail = user.getEmail();
            userObjectID = user.getObjectId();

            //for testing
            //Log.i("current user", userObjectID.toString());
            //Log.i("user email", userEmail);
            currentbkuser = new BackendlessUser();

        }
        else
        {
            isValidUser = false;
        }


        DBHandler dbHandler = new DBHandler(getApplicationContext());
        //rebuild tables until issue of comparing data in sqlite solved
        //dbHandler.rebuildTables();

        try
        {
            dbHandler.getPosts();
            dbHandler.getPets();
            userPosts = dbHandler.getUserPosts(userEmail);
            userPets = dbHandler.getUserPets(userObjectID);
            //dbHandler.getUsers();

        }catch (Exception e)
        {
            Log.i("Exception message", "Exception Message: " + e.getMessage().toString());
            Toast.makeText(this, "Error loading Databases - please try again", Toast.LENGTH_SHORT).show();
        }
        dbHandler.close();



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        if(isValidUser)
        {
            navigationView.getMenu().setGroupVisible(R.id.logged_in, true);
            navigationView.getMenu().setGroupVisible(R.id.not_logged_in, false);

            if(userPets != null)
            {
                navigationView.getMenu().setGroupVisible(R.id.has_pets, true);

            }
            if(userPosts != null)
            {
                navigationView.getMenu().setGroupVisible(R.id.has_posts, true);
            }

            MainActivityLoggedInFragment fragment;
            fragment = new MainActivityLoggedInFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame, fragment);
            ft.commit();
        }
        else if(!(isValidUser))
        {
            MainActivityNotLoggedInFragment fragment;
            fragment = new MainActivityNotLoggedInFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame, fragment);
            ft.commit();
        }

    }


    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        } else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_home)
        {

            MainActivityNotLoggedInFragment fragment;
            fragment = new MainActivityNotLoggedInFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame, fragment);
            ft.commit();

        }else if(id == R.id.nav_home_logged)
        {
            MainActivityLoggedInFragment fragment;
            fragment = new MainActivityLoggedInFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame, fragment);
            ft.commit();

        }else if (id == R.id.nav_found_pet)
        {
            ReportFoundPetFragment fragment;
            fragment = new ReportFoundPetFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame, fragment);
            ft.commit();

        } else if (id == R.id.nav_browse)
        {
            BrowsePetsFragment fragment;
            fragment = new BrowsePetsFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame, fragment);
            ft.commit();

        } else if (id == R.id.nav_browse1)
        {
            BrowsePetsFragment fragment;
            fragment = new BrowsePetsFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame, fragment);
            ft.commit();

        }else if (id == R.id.nav_steps)
        {
            LostFoundPetFragment fragment;
            fragment = new LostFoundPetFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame, fragment);
            ft.commit();

        } else if (id == R.id.nav_steps1)
        {
            LostFoundPetFragment fragment;
            fragment = new LostFoundPetFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame, fragment);
            ft.commit();

        } else if (id == R.id.nav_edit_pp)
        {
            EditPetProfileFragment fragment;
            fragment = new EditPetProfileFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame, fragment);
            ft.commit();

        } else if (id == R.id.nav_create_i_lost_my_pet)
        {
            ILostMyPetFragment fragment;
            fragment = new ILostMyPetFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame, fragment);
            ft.commit();

        }
//        else if (id == R.id.nav_claim_pet)
//        {
//            ClaimLostPetFragment fragment;
//            fragment = new ClaimLostPetFragment();
//            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            ft.replace(R.id.mainFrame, fragment);
//            ft.commit();
//
//        }
        else if (id == R.id.nav_edit_account)
        {
            EditAccountFragment fragment;
            fragment = new EditAccountFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame, fragment);
            ft.commit();

        }else if (id == R.id.nav_edit_lpp)
        {
            EditLostPetPostFragment fragment;
            fragment = new EditLostPetPostFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame, fragment);
            ft.commit();

        }else if (id == R.id.nav_login)
        {
            LoginFragment fragment;
            fragment = new LoginFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame, fragment);
            ft.commit();

        }else if (id == R.id.nav_register)
        {
            RegistrationFragment fragment;
            fragment = new RegistrationFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame, fragment);
            ft.commit();

        }else if (id == R.id.nav_create_pp)
        {
            CreatePetProfileFragment fragment;
            fragment = new CreatePetProfileFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame, fragment);
            ft.commit();

        }
        else if (id == R.id.nav_logout)
        {
            MainActivityNotLoggedInFragment fragment;
            fragment = new MainActivityNotLoggedInFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame, fragment);
            ft.commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
