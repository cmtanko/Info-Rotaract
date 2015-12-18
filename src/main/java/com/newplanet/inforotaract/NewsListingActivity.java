package com.newplanet.inforotaract;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.newplanet.inforotaract.Adapters.ListModelAdapter;
import com.newplanet.inforotaract.Models.Event;
import com.newplanet.inforotaract.Models.IListModel12;
import com.newplanet.inforotaract.Models.News;
import com.newplanet.inforotaract.Utils.App;
import com.newplanet.inforotaract.Utils.GetJson;

import java.util.ArrayList;
import java.util.List;

public class NewsListingActivity extends AppCompatActivity
{
    ProgressDialog progress;
    ListView listNewsView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_page);

        listNewsView = (ListView) findViewById(R.id.listNewsView);

        //ON LIST ITEM CLICK EVENT
        listNewsView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        News curItem = (News) (parent.getItemAtPosition(position));
                        Intent i = new Intent(NewsListingActivity.this, NewsDetailActivity.class);
                        Bundle b = new Bundle();
                        b.putSerializable("curItem", curItem);
                        i.putExtras(b);
                        startActivity(i);
                    }
                }
        );

        //SHOW PROGRESS BAR BEFORE LOADING DATA
        progress = ProgressDialog.show(NewsListingActivity.this, "","Loading...", true);

        //SETUP THE TOOLBAR
        setupToolbar();

        LoadData();
    }

    private void LoadData()
    {
        if(App.IsOnline())
        {
            LoadEventsData();
        }
    }

    public boolean onSupportNavigateUp()
    {
        onBackPressed();
        return true;
    }

    private void LoadEventsData()
    {
        Firebase newsRef = new Firebase(GetJson.rootNode);
        newsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                List<IListModel12> events = new ArrayList<IListModel12>();
                for(DataSnapshot data:dataSnapshot.getChildren())
                {
                    Event e = data.getValue(Event.class);
                    events.add(e);
                }

                ListAdapter listAdp = new ListModelAdapter(NewsListingActivity.this, events, R.layout.news_page_single_view);
                listNewsView.setAdapter(listAdp);
                progress.dismiss();

            }

            @Override
            public void onCancelled(FirebaseError firebaseError)
            {

            }
        });
    }

    private void setupToolbar()
    {
        final Toolbar advToolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        advToolbar.setTitle("Events");
        advToolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));

        setSupportActionBar(advToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}
