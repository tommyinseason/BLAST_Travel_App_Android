package com.example.guest.gobal.adapters;

/**
 * Created by Guest on 7/25/16.
 */
public class RowItem {

    private String title;
    private int icon;

    public RowItem(String title, int icon) {
        this.title= title;
        this.icon = icon;

    }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public int getIcon() { return icon; }

    public void setIcon(int icon) { this.icon = icon; }

}

