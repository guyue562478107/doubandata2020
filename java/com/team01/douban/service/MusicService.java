package com.team01.douban.service;

import com.team01.douban.entity.Music;

import java.util.List;


public interface MusicService {
    List<Music> search(Music music, int page, int limit);
    int searchcount(Music music);

    List<Music> searchall(Music music, int page, int limit);
    int searchallcount();


    List<Music> scorecount();
    List<Music> singerTime(Music music);

    List<Music> typecount();
}
