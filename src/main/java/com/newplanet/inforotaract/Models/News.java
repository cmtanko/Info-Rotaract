package com.newplanet.inforotaract.Models;

import java.io.Serializable;

/**
 * Created on 12/14/2015
 * By : $(USER)<suchan211@gmail.com>
 */
public class News implements Serializable
{
    public String sno;
    public String title;
    public String date;
    public String venue;
    public String time;
    public String description;
    public String comment;
    public String pic_link_01;
    public String pic_link_02;
    public String pic_link_03;
    public String pic_link_04;
    public String prof_pic;

    public String getSno() {
        return sno;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getVenue() {
        return venue;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getComment() {
        return comment;
    }

    public String getPic_link_01() {
        return pic_link_01;
    }

    public String getPic_link_02() {
        return pic_link_02;
    }

    public String getPic_link_03() {
        return pic_link_03;
    }

    public String getPic_link_04() {
        return pic_link_04;
    }

    public String getProf_pic() {
        return prof_pic;
    }



}
