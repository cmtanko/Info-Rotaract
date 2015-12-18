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

import com.newplanet.inforotaract.Models.IListModel12;
import com.newplanet.inforotaract.R;
import com.newplanet.inforotaract.Utils.App;

import java.util.List;

/**
 * Created on 12/14/2015
 * By : $(USER)<suchan211@gmail.com>
 */
public class ListModelAdapter extends ArrayAdapter<IListModel12>
{
    private int resourceId = 0;
    private List<IListModel12> items;

    public ListModelAdapter(Context context, List<IListModel12> news, int id)
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

        IListModel12 listModel = getItem(position);

        TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        TextView tvDescription = (TextView) view.findViewById(R.id.lblDescription);
        TextView tvDetail = (TextView) view.findViewById(R.id.lblDetail);
        ImageView ivPic = (ImageView) view.findViewById(R.id.imgPic);


        tvTitle.setText(listModel.getTitle());
        tvDescription.setText(listModel.getDescription());
        tvDetail.setText(listModel.getDetail());
        App.LoadImage(view.getContext(), ivPic, listModel.getImageURL());


        return view;
    }
}
