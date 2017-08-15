package com.example.shiva.post;

/**
 * Created by shiva on 7/23/2017.
 */

public class MessageDetails {
    String myName,myPost,myArea,time;
    int likesC,dislikesC;

    public MessageDetails() {
    }

    public MessageDetails(int likesC) {
        this.likesC = likesC;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMyName() {
        return myName;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }

    public String getMyPost() {
        return myPost;
    }

    public void setMyPost(String myPost) {
        this.myPost = myPost;
    }

    public String getMyArea() {
        return myArea;
    }

    public void setMyArea(String myArea) {
        this.myArea = myArea;
    }

    public int getLikesC() {
        return likesC;
    }

    public void setLikesC(int likesC) {
        this.likesC = likesC;
    }

    public int getDislikesC() {
        return dislikesC;
    }

    public void setDislikesC(int dislikesC) {
        this.dislikesC = dislikesC;
    }
}
