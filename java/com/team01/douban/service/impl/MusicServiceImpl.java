package com.team01.douban.service.impl;

import com.team01.douban.dao.MusicMapper;
import com.team01.douban.entity.Music;
import com.team01.douban.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicServiceImpl implements MusicService {
    @Autowired
    private MusicMapper mapper;


    @Override
    public List<Music> search(Music music, int page, int limit) {

//        if (music != null && !"".equals(music.getMusicName().trim())){
//            music.setMusicName("%" + music.getMusicName() + "%");
//        }
        if (music !=null && !"".equals(music.getMusicTime().trim())){
            music.setMusicTime(music.getMusicTime() + "%");
        }

        if (music != null && !"".equals(music.getMusicSinger().trim())){
            music.setMusicSinger("%" + music.getMusicSinger() + "%");
        }
        if (page > 0 && limit > 0){
            return mapper.selectByWhere(music,(page-1) * limit,limit);
        }
        return mapper.selectByWhere(music,null,null);

    }

    @Override
    public int searchcount(Music music) {
        return mapper.countSelectByWhere(music);
    }



    @Override
    public List<Music> searchall(Music music,int page, int limit) {

        if (page > 0 && limit > 0){
            return mapper.selectAll((page-1) * limit, limit);
        }
        return mapper.selectAll(null,null);
    }

    @Override
    public int searchallcount() {
        return mapper.selectallcount();
    }

    @Override
    public List<Music> scorecount() {
        return mapper.countByScore();
    }


    @Override
    public List<Music> singerTime(Music music) {

        return mapper.countBySinger(music);
    }

    @Override
    public List<Music> typecount() {
        return mapper.countByType();
    }
}
