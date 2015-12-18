package com.newplanet.inforotaract;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;


public class MainMenuPage extends ActionBarActivity implements View.OnClickListener
{
    ImageButton btnAbout, btnEvent,btnNews,btnDonor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_page);
        btnAbout = (ImageButton) findViewById(R.id.btnAbout);
        btnEvent = (ImageButton) findViewById(R.id.btnEvent);
        btnNews = (ImageButton) findViewById(R.id.btnNews);
        btnDonor = (ImageButton) findViewById(R.id.btnDonor);

        btnAbout.setOnClickListener(this);
        btnEvent.setOnClickListener(this);
        btnNews.setOnClickListener(this);
        btnDonor.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        Intent intent;
        if(v == findViewById(R.id.btnAbout))
        {
            intent = new Intent(this,BloodGroupListing.class);
            startActivity(intent);
        }
        else if (v== findViewById(R.id.btnEvent))
        {
            intent = new Intent(this,EventListingActivity.class);
            startActivity(intent);
        }
        else if (v== findViewById(R.id.btnNews))
        {
            intent = new Intent(this,NewsListingActivity.class);
            startActivity(intent);
        }
        else if (v== findViewById(R.id.btnDonor))
        {
            intent = new Intent(this,BloodGroupListing.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


}
