package com.newplanet.inforotaract;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.newplanet.inforotaract.Adapters.ListModelAdapter;
import com.newplanet.inforotaract.Models.Event;
import com.newplanet.inforotaract.Models.IListModel12;
import com.newplanet.inforotaract.Utils.App;
import com.newplanet.inforotaract.Utils.GetJson;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class EventListingActivity extends AppCompatActivity
{
    ProgressDialog progress;
    ListView listEventsView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events_page);

        listEventsView = (ListView) findViewById(R.id.listEventView);

        //SHOW PROGRESS BAR BEFORE LOADING DATA
        progress = ProgressDialog.show(EventListingActivity.this, "","Loading...", true);

        //SETUP THE TOOLBAR
        setupToolbar();

        try {
            LoadData();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void LoadData() throws ParseException {
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

    private void LoadEventsData() throws ParseException {
        final DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        final String today = new SimpleDateFormat("MM/dd/yyyy").format(new Date());

        Firebase newsRef = new Firebase(GetJson.eventsRefNode);
        newsRef.orderByChild("sno").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<IListModel12> events = new ArrayList<IListModel12>();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Event e = data.getValue(Event.class);

                    String eventDateString = e.date;

                    try {
                        Date eventDate = df.parse(eventDateString);
                        Date currentDate = df.parse(today);

                        if (eventDate.compareTo(currentDate) > 0)
                            events.add(e);
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                }

                ListAdapter listAdp = new ListModelAdapter(EventListingActivity.this, App.sortByDesc(events), R.layout.event_page_single_view);
                listEventsView.setAdapter(listAdp);
                progress.dismiss();

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }



    private void setupToolbar()
    {
        final Toolbar advToolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        advToolbar.setTitle("Upcoming Events");
        advToolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));

        setSupportActionBar(advToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}
