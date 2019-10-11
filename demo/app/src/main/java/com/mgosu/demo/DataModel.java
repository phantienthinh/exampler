package com.mgosu.demo;

public class DataModel {
    private String linkMp3;
    private boolean isPlay;
    private int posProgress;



    public int getPosProgress() {
        return posProgress;
    }

    public void setPosProgress(int posProgress) {
        this.posProgress = posProgress;
    }


    public DataModel(String linkMp3, boolean isPlay, int posProgress) {
        this.linkMp3 = linkMp3;
        this.isPlay = isPlay;
        this.posProgress = posProgress;
    }

    public DataModel() {
    }

    public String getLinkMp3() {
        return linkMp3;
    }

    public void setLinkMp3(String linkMp3) {
        this.linkMp3 = linkMp3;
    }

    public boolean isPlay() {
        return isPlay;
    }

    public void setPlay(boolean play) {
        isPlay = play;
    }

}
