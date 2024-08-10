package com.team01.douban.entity;

import lombok.Data;

@Data
public class Music {
    private int id;
    private String musicName;
    private String musicSinger;
    private String musicTime;
    private String musicType;
    private String musicScore;
    private int count;


//    public  Music(String musicName, String musicSinger, String musicTime){
//        this.musicName = musicName;
//        this.musicSinger = musicSinger;
//        this.musicTime = musicTime;
//        this.musicType = musicType;
//        this.musicScore = musicScore;
//    }

}
