package com.newplanet.inforotaract;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 12/14/2015
 * By : $(USER)<suchan211@gmail.com>
 */
public class EventPage extends Fragment
{
    ProgressDialog progress;
    ListView listNewsView;

    public static EventPage newInstance()
    {
        Bundle args = new Bundle();
        EventPage fragment = new EventPage();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final View view = inflater.inflate(R.layout.events_page,container,false);
        listNewsView = (ListView) view.findViewById(R.id.listEventView);
        String mTitle = getArguments().getString("title");

        LoadData();

        //SHOW PROGRESS BAR BEFORE LOADING DATA
        progress = ProgressDialog.show(getActivity(), "Loading",
                "please wait", true);

        return view;
    }

    private void LoadData()
    {
        if(App.IsOnline())
        {
            LoadEventsData();
        }
    }

    private void LoadEventsData()
    {
        Firebase newsRef = new Firebase(GetJson.eventsRefNode);
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

                ListAdapter listAdp = new ListModelAdapter(getActivity(), events, R.layout.event_page_single_view);
                listNewsView.setAdapter(listAdp);
                progress.dismiss();

            }

            @Override
            public void onCancelled(FirebaseError firebaseError)
            {

            }
        });
    }
}
