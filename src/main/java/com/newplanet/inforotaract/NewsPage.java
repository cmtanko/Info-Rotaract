package com.newplanet.inforotaract;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;


import com.newplanet.inforotaract.Adapters.ListModelAdapter;
import com.newplanet.inforotaract.Adapters.ListModelAdapter;
import com.newplanet.inforotaract.Models.IListModel12;
import com.newplanet.inforotaract.Models.News;
import com.newplanet.inforotaract.Utils.App;
import com.newplanet.inforotaract.Utils.GetJson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 12/14/2015
 * By : $(USER)<suchan211@gmail.com>
 */
public class NewsPage extends Fragment
{
    ProgressDialog progress;
    ListView listNewsView;
    View view;

    public static NewsPage newInstance()
    {
        Bundle args = new Bundle();
        NewsPage fragment = new NewsPage();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.news_page,container,false);
        listNewsView = (ListView) view.findViewById(R.id.listNewsView);
        String mTitle = getArguments().getString("title");

        //ON LIST ITEM CLICK EVENT
        listNewsView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        News curItem = (News) (parent.getItemAtPosition(position));
                        Intent i = new Intent(getActivity(), NewsDetailActivity.class);
                        Bundle b = new Bundle();
                        b.putSerializable("curItem", curItem);
                        i.putExtras(b);
                        startActivity(i);
                    }
                }
        );

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
        Firebase newsRef = new Firebase(GetJson.rootNode);
        newsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                List<IListModel12> news = new ArrayList<IListModel12>();
                for(DataSnapshot data:dataSnapshot.getChildren())
                {
                    News n = data.getValue(News.class);
                    news.add(n);
                }

                ListAdapter listAdp = new ListModelAdapter(getActivity(), news, R.layout.news_page_single_view);
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
