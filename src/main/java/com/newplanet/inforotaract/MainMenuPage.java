package com.newplanet.inforotaract;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;


public class MainMenuPage extends ActionBarActivity implements View.OnClickListener
{
    ImageButton btnBloodDonor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_page);
        btnBloodDonor = (ImageButton) findViewById(R.id.imageButton4);
        btnBloodDonor.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        Intent intent;
        if(v == findViewById(R.id.imageButton4))
        {
            intent = new Intent(this,BloodDonorListingPage.class);
            startActivity(intent);
        }
        else if (v== findViewById(R.id.imageButton3))
        {
            intent = new Intent(this,BloodDonorListingPage.class);
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
