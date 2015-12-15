package com.newplanet.inforotaract.Models;

import java.io.Serializable;

/**
 * Created on 12/15/2015
 * By : $(USER)<suchan211@gmail.com>
 */
public class BloodDonor implements Serializable,IListModel12
{
    private String sno;
    private String firstname;
    private String lastname;
    private String club;
    private String age;
    private String sex;
    private String email;
    private String phone;
    private String bloodgroup;
    private String home;
    private String is_availabe;
    private String note;
    private String prof_pic;



    public String getProf_pic() {
        return prof_pic;
    }

    public String getSno() {
        return sno;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getClub() {
        return club;
    }

    public String getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public String getHome() {
        return home;
    }

    public String getIs_availabe() {
        return is_availabe;
    }

    public String getNote() {
        return note;
    }


    @Override
    public String getTitle() {
        return getFirstname() + " " + getLastname() + " [" + getBloodgroup() + "] ";
    }

    @Override
    public String getImageURL() {
        return getProf_pic();
    }

    @Override
    public String getDescription()
    {
        String available="";
        if(getIs_availabe().equals("1"))
            available = "Yes";
        else
            available = "No";
        return "Blood Group: " + getBloodgroup() + " | Availability:" + available;
    }

    @Override
    public String getDetail() {
        return getClub();
    }
}
