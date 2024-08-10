package com.team01.douban.service;

import com.team01.douban.entity.Drama;
import com.team01.douban.entity.Music;

import java.util.List;


/**
 *Service接口，主要功能：定义业务逻辑的接口（定义职责，标识能力要求）
 * */


public interface DramaService {

    List<Drama> search(Drama drama, int page, int limit);

    int searchCount(Drama drama); //新建的统计条目数的

    Drama findDramaInfo(String id);

    boolean updateDramaInfo(Drama drama);

    List<Drama> scoreCount();

    List<Drama> typeCount();

    List<Drama> languageCount();

    List<Drama> actorTime(Drama drama);

    List<Drama> timeScore();
}
