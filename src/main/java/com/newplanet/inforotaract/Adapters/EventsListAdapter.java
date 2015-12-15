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

import com.newplanet.inforotaract.Models.Event;
import com.newplanet.inforotaract.Models.News;
import com.newplanet.inforotaract.R;
import com.newplanet.inforotaract.Utils.App;

import java.util.List;

/**
 * Created on 12/14/2015
 * By : $(USER)<suchan211@gmail.com>
 */
public class EventsListAdapter<E> extends ArrayAdapter<Event>
{
    private int resourceId = 0;
    private List<Event> items;

    public EventsListAdapter(Context context, List<Event> events, int id)
    {
        super(context, id, events);
        items = events;
        resourceId = id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(resourceId, parent, false);

        Event events = getItem(position);
        TextView lblEventTitle = (TextView) view.findViewById(R.id.lblEventTitle);
        TextView lblEventLikes = (TextView) view.findViewById(R.id.lblEventLikes);
        TextView lblEventDislikes = (TextView) view.findViewById(R.id.lblEventDislikes);
        ImageView ivPic = (ImageView) view.findViewById(R.id.imgEventView);

        lblEventTitle.setText(events.getTitle());
        lblEventLikes.setText(events.getInterested());
        lblEventDislikes.setText(events.getNot_interested());
        App.LoadImage(view.getContext(), ivPic, events.getProf_pic());


        return view;
    }
}
