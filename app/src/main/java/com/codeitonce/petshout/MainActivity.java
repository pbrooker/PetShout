package com.codeitonce.petshout;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Leave DBHandler commented out until ready to actively use database
        //DBHandler dbHandler = new DBHandler(this);
        populateDatabase();
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

        } else if (id == R.id.nav_steps)
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

        } else if (id == R.id.nav_create_lpp)
        {
            CreateLostPetPostFragment fragment;
            fragment = new CreateLostPetPostFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame, fragment);
            ft.commit();

        }else if (id == R.id.nav_claim_pet)
        {
            ClaimLostPetFragment fragment;
            fragment = new ClaimLostPetFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame, fragment);
            ft.commit();

        }else if (id == R.id.nav_edit_account)
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

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void populateDatabase() //temporary method to populate local database
    {
        DBHandler db = new DBHandler(getApplicationContext());
        db.addUser(new User("Fred", "Jones",
                "Moncton", "E1X4Y5","jones.fred@hotmail.com",
                "506-555-1234", "password"));
        db.addUser(new User("Alex", "Smith",
                "Dieppe", "E1J4H3","Alexasmith@gmail.com",
                "506-444-1234", "password"));
        db.addUser(new User("Janet", "Harrison",
                "Riverview", "E2B3H5", "janetH@gmail.com",
                "506-333-1234", "password"));


    }
}
