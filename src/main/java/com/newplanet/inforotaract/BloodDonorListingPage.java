package com.newplanet.inforotaract;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.newplanet.inforotaract.Adapters.BloodDonorListAdapter;
import com.newplanet.inforotaract.Adapters.ListModelAdapter;
import com.newplanet.inforotaract.Models.BloodDonor;
import com.newplanet.inforotaract.Models.IListModel12;
import com.newplanet.inforotaract.Models.News;
import com.newplanet.inforotaract.Utils.App;
import com.newplanet.inforotaract.Utils.GetJson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created on 12/15/2015
 * By : $(USER)<suchan211@gmail.com>
 */
public class BloodDonorListingPage extends AppCompatActivity
{
    final Context context = this;
    SearchView mSearchView;
    String mSearchString;
    ProgressDialog progress;
    ListView listNewsView;
    View view;
    List<IListModel12> donors = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blood_donar_page);
        listNewsView = (ListView) findViewById(R.id.lvDonors);
        listNewsView.setTextFilterEnabled(true);
        listNewsView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                    {
                        showPopUpWindow((BloodDonor)parent.getItemAtPosition(position));
                    }
                }
        );

        App.Intialize(this);
        App.c = this;


        progress = ProgressDialog.show(BloodDonorListingPage.this, "","Loading...", true);
        progress.setProgressStyle(R.style.ProgressBar);

        //SETUP THE TOOLBAR
        setupToolbar();

        LoadDonorData();


    }

    private void showPopUpWindow(BloodDonor contact)
    {
        FragmentManager fm = getSupportFragmentManager();
        DialogWindow dialogWindow = new DialogWindow();
        dialogWindow.contact = contact;
        dialogWindow.show(fm, "fragment_edit_name");
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
                    listNewsView.clearTextFilter();
                } else {
                    listNewsView.setFilterText(mSearchString.toString());

                    List<IListModel12> donorList = new ArrayList<IListModel12>();
                    String filterString = mSearchString.toString().toLowerCase();
                    for (IListModel12 data : donors)
                    {
                        if(data.getTitle().toLowerCase().contains(filterString.trim())
                                || data.getDetail().toLowerCase().contains(filterString)
                                || data.getDescription ().toLowerCase().contains(filterString))
                            donorList.add(data);
                    }
                    RenderPage(donorList);
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

    private void RenderPage(List<IListModel12> donors)
    {
        //POPULATE THE LIST VIEW
        ListAdapter listAdp = new ListModelAdapter(BloodDonorListingPage.this, donors, R.layout.blood_donor_page_single_view);
        listNewsView.setAdapter(listAdp);
    }

    private void LoadDonorData() {
        //LOAD BLOOD DONOR DATA
        Firebase newsRef = new Firebase(GetJson.donorRefNode);
        newsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                donors = new ArrayList<IListModel12>();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    BloodDonor n = data.getValue(BloodDonor.class);
                    donors.add(n);
                }
                RenderPage(donors);
                progress.dismiss();
            }


            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

}
