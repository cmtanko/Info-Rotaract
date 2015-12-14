package com.newplanet.inforotaract.Adapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.newplanet.inforotaract.Models.News;
import com.newplanet.inforotaract.R;
import com.newplanet.inforotaract.Utils.App;

import java.util.List;

/**
 * Created on 12/14/2015
 * By : $(USER)<suchan211@gmail.com>
 */
public class GalleryAdapter extends ArrayAdapter<News>
{
    private int resourceId = 0;
    private List<News> items;

    public GalleryAdapter(Context context, List<News> news, int id)
    {
        super(context, id, news);
        items = news;
        resourceId = id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(resourceId, parent, false);

        News news = getItem(position);
        ImageView ivPic = (ImageView) view.findViewById(R.id.imgGalleryPic);

        App.LoadImage(view.getContext(), ivPic, news.getProf_pic());


        return view;
    }
}
