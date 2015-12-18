package com.newplanet.inforotaract.Utils;

import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.firebase.client.Firebase;
import com.newplanet.inforotaract.Models.IListModel12;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 12/14/2015
 * By : $(USER)<suchan211@gmail.com>
 */
public class App
{
    public static Context c;

    public static void Intialize(Context ctx) {
        // INITIALIZE THE CONTEXT OBJECT FOR Utils class ::: VERY IMPORTANT
        App.c = ctx;
        Firebase.setAndroidContext(ctx);
        //F.AddTrips(); ONE TIME METHOD
    }

    public static boolean IsOnline() {
        ConnectivityManager connMgr = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        boolean status = networkInfo != null && networkInfo.isConnected();
        if (!status)
            ShowAlert("Please make sure that you are connected to internet.");
        return (status);
    }

    public static void ShowAlert(String msg) {
        AlertDialog.Builder alert = new AlertDialog.Builder(c);
        alert.setMessage(msg);
        alert.show();
    }


    public static void LoadImage(Context ctx, ImageView iv, String imgUrl) {
        Glide.with(ctx)
                .load(imgUrl)
                .centerCrop()
                .into(iv);
    }

    public static List<IListModel12> sortByDesc(List<IListModel12> events)
    {
        List<IListModel12> sortedEvents = new ArrayList<IListModel12>();
        for (int i = events.size() - 1; i >= 0; i--) {
            sortedEvents.add(events.get(i));
        }
        return sortedEvents;
    }
}
