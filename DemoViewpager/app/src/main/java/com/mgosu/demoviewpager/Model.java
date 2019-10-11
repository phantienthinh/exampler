package com.mgosu.demoviewpager;

public class Model {
    private String title;
    private BlankFragment fragment;

    public Model(String title, BlankFragment fragment) {
        this.title = title;
        this.fragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BlankFragment getFragment() {
        return fragment;
    }

    public void setFragment(BlankFragment fragment) {
        this.fragment = fragment;
    }
}
