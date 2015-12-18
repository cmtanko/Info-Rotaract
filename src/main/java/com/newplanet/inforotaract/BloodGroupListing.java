package com.newplanet.inforotaract;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Arrays;


public class BloodGroupListing extends AppCompatActivity
{

    private ListView listCityView;
    private ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blood_group_listing);

        listCityView = (ListView) findViewById(R.id.lvBloodGroup);

        //CREATE AND POPULATE A LIST OF CITIES
        String[] cities = {"AB+","AB-","A+","A-","B+","B-","O+","O-"};

        ArrayList<String> cityList = new ArrayList<String>();
        cityList.addAll(Arrays.asList(cities));

        //CREATE ARRAY ADAPTER USING THE CITY ARRAY
        listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, cityList);
        listCityView.setAdapter(listAdapter);
        listCityView.setTextFilterEnabled(true);

        //SETUP THE TOOLBAR
        setupToolbar();

        //ON CITY LIST CLICKED
        listCityView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i = new Intent(BloodGroupListing.this,BloodDonorListingPage.class);
                        i.putExtra("bloodGroup", parent.getItemAtPosition(position).toString());
                        startActivity(i);
                    }
                }
        );
    }

    public boolean onSupportNavigateUp()
    {
        onBackPressed();
        return true;
    }

    private void setupToolbar()
    {
        final Toolbar advToolbar =  (Toolbar) findViewById(R.id.my_awesome_toolbar);
        advToolbar.setTitle("Blood Groups");
        advToolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));

        setSupportActionBar(advToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }



}
