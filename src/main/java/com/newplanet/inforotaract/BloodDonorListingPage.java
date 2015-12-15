package com.newplanet.inforotaract;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.newplanet.inforotaract.Adapters.BloodDonorListAdapter;
import com.newplanet.inforotaract.Adapters.ListModelAdapter;
import com.newplanet.inforotaract.Models.BloodDonor;
import com.newplanet.inforotaract.Models.News;
import com.newplanet.inforotaract.Utils.GetJson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 12/15/2015
 * By : $(USER)<suchan211@gmail.com>
 */
public class BloodDonorListingPage extends AppCompatActivity
{
    SearchView mSearchView;
    String mSearchString;
    ProgressDialog progress;
    ListView listNewsView;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blood_donar_page);

        //SETUP THE TOOLBAR
        setupToolbar();
    }

    private void setupToolbar()
    {
        final Toolbar advToolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        advToolbar.setTitle("Search Donor");
        advToolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));

        setSupportActionBar(advToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public boolean onSupportNavigateUp()
    {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.advance_search,menu);

        MenuItem searchItem = menu.findItem(R.id.action_searchAdv);

        SearchManager searchManager = (SearchManager)BloodDonorListingPage.this.getSystemService(Context.SEARCH_SERVICE);
        mSearchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_searchAdv));

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(BloodDonorListingPage.this.getComponentName()));
        }

        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener()
        {
            public boolean onQueryTextChange(String newText)
            {
                mSearchString = newText;
                if (TextUtils.isEmpty(mSearchString)) {
                    //listCityView.clearTextFilter();
                } else {
                    //listCityView.setFilterText(mSearchString.toString());
                }
                return true;
            }

            public boolean onQueryTextSubmit(String query)
            {
                mSearchString = query;
                return true;
            }
        };
        mSearchView.setOnQueryTextListener(queryTextListener);
        return super.onCreateOptionsMenu(menu);
    }


    private void LoadDonorData()
    {
        //LOAD BLOOD DONOR DATA
        Firebase newsRef = new Firebase(GetJson.donorRefNode);
        newsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<BloodDonor> donors = new ArrayList<BloodDonor>();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    BloodDonor n = data.getValue(BloodDonor.class);
                    donors.add(n);
                }

               /* ListAdapter listAdp = new BloodDonorListAdapter(BloodDonorListingPage, donors, R.layout.news_page_single_view);
                listNewsView.setAdapter(listAdp);
                progress.dismiss();*/

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
