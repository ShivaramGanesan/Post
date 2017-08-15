package com.example.shiva.post;

import java.util.ArrayList;

/**
 * Created by shiva on 7/8/2017.
 */

public class Model {

    public String actualNews;
    public String user;
    int likes,dislikes;

    public String time;

    public Model()  {

    }

    public Model(String actualNews, String user,String time,int likes,int dislikes) {
        this.actualNews = actualNews;
        this.user = user;
        this.likes=likes;
        this.dislikes=dislikes;
        this.time=time;
    }


    public String getActualNews() {
        return actualNews;
    }

    public String getUser() {
        return user;
    }



    public String getTime() {
        return time;
    }

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }
}
