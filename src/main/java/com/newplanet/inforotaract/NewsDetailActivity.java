package com.newplanet.inforotaract;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.newplanet.inforotaract.Models.News;

/**
 * Created on 12/15/2015
 * By : $(USER)<suchan211@gmail.com>
 */
public class NewsDetailActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_detail_view);

        Bundle b = this.getIntent().getExtras();
        News pck = (News)b.getSerializable("curItem");
        Toast.makeText(this, "This page is under contruction", Toast.LENGTH_SHORT).show();
    }
}
