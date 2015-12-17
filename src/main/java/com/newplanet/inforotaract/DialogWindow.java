package com.newplanet.inforotaract;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.newplanet.inforotaract.Models.BloodDonor;
import com.newplanet.inforotaract.Utils.App;

import org.json.JSONObject;

import java.net.URL;

/**
 * Created on 12/17/2015
 * By : $(USER)<suchan211@gmail.com>
 */
public class DialogWindow extends DialogFragment
{
    public DialogWindow(){}
    View view;
    BloodDonor contact;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        view = inflater.inflate(R.layout.dialog_page,null,false);

        TextView contactNameView =(TextView) view.findViewById(R.id.contactName);
        TextView contactEmailView =(TextView) view.findViewById(R.id.contactEmail);
        TextView contactPhoneView =(TextView) view.findViewById(R.id.contactPhone);
        TextView contactCommentView =(TextView) view.findViewById(R.id.tvComment);
        ImageView contactImgView = (ImageView) view.findViewById(R.id.contactImg);

        contactNameView.setText("Name: " + contact.getFirstname() + " " + contact.getLastname() + " [" + contact.getBloodgroup() + "]");
        contactEmailView.setText("Email: " +contact.getEmail());
        contactPhoneView.setText("Contact: " +contact.getPhone());
        contactCommentView.setText(contact.getDescription());
        App.LoadImage(view.getContext(), contactImgView, contact.getImageURL());

        builder.setView(view)
                // Add action buttons
                .setPositiveButton("Call", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(Intent.ACTION_CALL);

                        intent.setData(Uri.parse("tel:" + contact.getPhone()));
                        startActivity(intent);
                    }
                })
                .setNegativeButton("SMS", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        Intent intent = new Intent(Intent.ACTION_SENDTO);
                        intent.setData(Uri.parse("smsto:" + contact.getPhone()));
                        startActivity(intent);
                    }
                });
        return builder.create();

    }
}
