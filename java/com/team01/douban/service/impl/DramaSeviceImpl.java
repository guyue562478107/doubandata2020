package com.team01.douban.service.impl;

import com.team01.douban.dao.DramaMapper;
import com.team01.douban.entity.Drama;
import com.team01.douban.service.DramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service实现的主要作用：
 * 1. 具体实现业务逻辑（完成具体工作）
 * 2. 调用mapper层
 */

@Service
public class DramaSeviceImpl implements DramaService {

    // MyBatis具体对哪个类进行实例化ORM框架（Java与数据库关联） + Spring装配
    @Autowired
    private DramaMapper mapper;

    @Override
    public List<Drama> search(Drama drama, int page, int limit) {
        //如果非空而且不是一个空串
        if (drama != null && !"".equals(drama.getTvTitle().trim())){
            drama.setTvTitle("%" + drama.getTvTitle() + "%");
        }

        if (drama != null && !"".equals(drama.getTvActors().trim())){
            drama.setTvActors("%" + drama.getTvActors() + "%");
        }

        if (drama != null && !"".equals(drama.getTvRegion().trim())){
            drama.setTvRegion("%" + drama.getTvRegion() + "%");
        }

        if (page > 0 && limit > 0)
            return mapper.selectByWhere(drama, (page - 1) * limit, limit);

        return mapper.selectByWhere(drama,null,null);
    }

    @Override
    public int searchCount(Drama drama) {
        return mapper.countSelectByWhere(drama);
    }


    @Override
    public Drama findDramaInfo(String id) {
        return mapper.selectById(id);
    }

    @Override
    public boolean updateDramaInfo(Drama drama) {

        if (drama != null && drama.getId() != null ){
            Drama oldDramaInfo = mapper.selectById(drama.getId());
            if (oldDramaInfo != null){
                oldDramaInfo.setTvRegion(drama.getTvRegion());
                oldDramaInfo.setTvActors(drama.getTvActors());
                return mapper.update(oldDramaInfo) == 1 ? true : false;
            }
        }
        return false;
    }

    @Override
    public List<Drama> scoreCount() {
        return mapper.countByScore();
    }

    @Override
    public List<Drama> typeCount() {
        return mapper.countByType();
    }

    @Override
    public List<Drama> languageCount() {
        return mapper.countByLanguage();
    }

    @Override
    public List<Drama> actorTime(Drama drama) {

        if (drama != null && !"".equals(drama.getTvActors().trim())){
            drama.setTvActors("%" + drama.getTvActors() + "%");
        }

        return mapper.countByActor(drama);

    }

    @Override
    public List<Drama>  timeScore() {
        return mapper.timeAndScore();
    }



}
