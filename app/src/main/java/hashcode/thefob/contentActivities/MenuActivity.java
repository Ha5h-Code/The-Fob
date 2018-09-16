package hashcode.thefob.contentActivities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import hashcode.thefob.Entities.AccountType;
import hashcode.thefob.R;
import hashcode.thefob.utility.NavigationItemHandler;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        final ArrayList<AccountType> AccountTypes = new ArrayList<AccountType>();
        AccountTypes.add(new AccountType("Donut", 1, R.drawable.ic_social_facebook));
        AccountTypes.add(new AccountType("Eclair", 2, R.drawable.ic_social_facebook));
        AccountTypes.add(new AccountType("Froyo", 2, R.drawable.ic_social_facebook));
        AccountTypes.add(new AccountType("GingerBread", 4, R.drawable.ic_social_facebook));
        AccountTypes.add(new AccountType("Honeycomb", 5, R.drawable.ic_social_facebook));
        AccountTypes.add(new AccountType("Ice Cream Sandwich", 6, R.drawable.ic_social_facebook));
        AccountTypes.add(new AccountType("Jelly Bean", 7, R.drawable.ic_social_facebook));
        AccountTypes.add(new AccountType("KitKat", 8, R.drawable.ic_social_facebook));
        AccountTypes.add(new AccountType("Lollipop", 9, R.drawable.ic_social_facebook));
        AccountTypes.add(new AccountType("Marshmallow", 10, R.drawable.ic_social_facebook));


        AccountTypeAdapter accountTypeAdapter = new AccountTypeAdapter(this, AccountTypes);

        final ListView listView = findViewById(R.id.list_item_view);
        listView.setAdapter(accountTypeAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                AccountType account = (AccountType)parent.getItemAtPosition(position);
                Intent intent = new Intent(MenuActivity.this,AccountActivity.class);
                intent.putExtra("accountId",account.getAccountTypeId());
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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

        NavigationItemHandler handler = new NavigationItemHandler(this);
        if (id == R.id.nav_myFob)
        {
            // Handle the camera action
        } else if (id == R.id.nav_favourites)
        {

        } else if (id == R.id.nav_create)
        {

        } else if (id == R.id.nav_settings)
        {

        } else if (id == R.id.nav_help)
        {
            handler.handleHelp();
        } else if (id == R.id.nav_about)
        {
            handler.handleAboutUs();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
