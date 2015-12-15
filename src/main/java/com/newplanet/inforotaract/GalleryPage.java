package com.newplanet.inforotaract;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;


import com.newplanet.inforotaract.Adapters.GalleryAdapter;
import com.newplanet.inforotaract.Adapters.ListModelAdapter;
import com.newplanet.inforotaract.Models.News;
import com.newplanet.inforotaract.Utils.App;
import com.newplanet.inforotaract.Utils.GetJson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 12/14/2015
 * By : $(USER)<suchan211@gmail.com>
 */
public class GalleryPage extends Fragment
{
    ProgressDialog progress;
    GridView gridGalleryView;
    private String[] mThumbInfo;
    private Integer[] mThumbIds;

    public static GalleryPage newInstance()
    {
        Bundle args = new Bundle();
        GalleryPage fragment = new GalleryPage();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final View view = inflater.inflate(R.layout.gallery_page,container,false);
        gridGalleryView  = (GridView) view.findViewById(R.id.gvGallery);
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
        Firebase newsRef = new Firebase(GetJson.rootNode);
        newsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                List<News> news = new ArrayList<News>();
                for(DataSnapshot data:dataSnapshot.getChildren())
                {
                    News n = data.getValue(News.class);
                    news.add(n);
                }

                ListAdapter listAdp = new GalleryAdapter(getActivity(), news, R.layout.gallery_page_single_view);
                gridGalleryView.setAdapter(listAdp);
                progress.dismiss();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError)
            {

            }
        });
    }
}
